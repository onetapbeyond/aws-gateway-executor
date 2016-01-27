/*
 * Copyright 2016 David Russell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.onetapbeyond.aws.gateway.executor.tasks;

import io.onetapbeyond.aws.gateway.executor.*;
import io.onetapbeyond.aws.gateway.executor.builders.AWSTaskBuilder.Method;
import io.onetapbeyond.aws.gateway.executor.results.AWSResultImpl;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;

public class AWSTaskImpl implements AWSTask {

  private final AWSGateway gateway;
  private final Map<String,String> apiMap;
  private String taskEndpoint;

	public AWSTaskImpl(AWSGateway gateway,
                     Map<String,String> apiMap) {
    this.gateway = gateway;
    this.apiMap = apiMap;
  }

  /*
   * Execute {@link AWSTask} on AWS API Gateway.
   */
  public AWSResult execute() {

    AWSResult aResult = null;
    String method = apiMap.get(METHOD);
    String input = apiMap.get(INPUT);

    try {

      long execstart = System.currentTimeMillis();

      HttpURLConnection conn = call(method, input);

      if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        /*
         * Handle HTTP OK response from AWS API Gateway call.
         */
        String output = ok(conn);
        long timetaken = System.currentTimeMillis() - execstart;
        aResult = new AWSResultImpl(true, input, output,
                                    null, null, timetaken);

      } else {
        /*
         * Handle HTTP !OK response from AWS API Gateway call.
         */
        aResult = fail(conn);
      }

    } catch(Exception ex) {
        /*
         * Handle exception on AWS API Gateway call.
         */
      String msg = "Task execution failed.";
      aResult = new AWSResultImpl(false, input, null, msg, ex, 0L);
    }

    return aResult;
  }

  public String toString() {
    return "API: " + endpoint();
  }

  /*
   * Call AWS API Gateway resource.
   */
  private HttpURLConnection call(String method,
                                 String input)
                            throws AWSException {

    HttpURLConnection conn = null;

    try {

      URL apiCall = new URL(endpoint());
      conn = (HttpURLConnection)apiCall.openConnection();

      conn.setRequestProperty("Content-Type", JSON_TYPE);
      conn.setRequestProperty("Accept", JSON_TYPE);

      if(method.equals(Method.PATCH.toString())) {
        /* 
         * Workaround OpenJDK JDK-7016595.
         */
        conn.setRequestProperty(METHOD_OVERRIDE, Method.PATCH.toString());
        conn.setRequestMethod(Method.POST.toString());
      } else {
        conn.setRequestMethod(method);
      }

      if(gateway.key() != null) {
        conn.setRequestProperty(AWS_X_API_KEY, gateway.key());
      }

      conn.setDoInput(true);

      if(input != null) {
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes(UTF8));
        os.close(); 
      }

    } catch(Exception ex) {
      throw new AWSException(endpoint() + " call failed.", ex);
    }

    return conn;
  }

  /*
   * Handle HTTP OK response from AWS API Gateway resource.
   */
  private String ok(HttpURLConnection conn) throws IOException {

    BufferedReader respBuf = new BufferedReader(
      new InputStreamReader(conn.getInputStream(), UTF8));

    StringBuilder respData = new StringBuilder();
    String jsonPartial = null;

    while ((jsonPartial = respBuf.readLine()) != null) {  
      respData.append(jsonPartial + "\n");  
    }
    respBuf.close();

    return respData.toString();
  }

  /*
   * Handle HTTP !OK response from AWS API Gateway resource.
   */
  private AWSResult fail(HttpURLConnection conn) throws IOException {

    StringBuffer causeMsg =
      new StringBuffer().append("HTTP ")
                        .append(conn.getResponseMessage())
                        .append(", error code ")
                        .append(conn.getResponseCode())
                        .append(".");

    StringBuffer errMsg =
      new StringBuffer(toString()).append(": ")
                                  .append(causeMsg);

    AWSException aEx = new AWSException(causeMsg.toString());

    return new AWSResultImpl(false, apiMap.get(INPUT), null,
                                    errMsg.toString(), aEx, 0L);
  }

  /*
   * Construct AWS API Gateway endpoint for task.
   */
  private String endpoint() {

    /*
     * AWS API Gateway Endpoint Format:
     *
     * https://api-id.execute-api.region-id.amazonaws.com/resource
     */

    if(taskEndpoint == null) {

      StringBuffer sb = new StringBuffer(HTTPS);

      sb.append(gateway.id())
        .append(DOT)
        .append(EXECUTE_API)
        .append(DOT)
        .append(gateway.region())
        .append(DOT)
        .append(AMAZON_AWS);

      if(gateway.stage() != null) {
        sb.append(SLASH)
          .append(gateway.stage());
      }

      String resource = apiMap.get(RESOURCE);

      if(resource == null) // Protect against NPE.
        resource = RESOURCE_UNDEF;

      if(resource.startsWith(SLASH))
        sb.append(resource);
      else
        sb.append(SLASH).append(resource);

        taskEndpoint = sb.toString();
    }

    return taskEndpoint;
  }

  private static final String JSON_TYPE = "application/json";
  private static final String UTF8 = "UTF-8";
  private static final String HTTPS = "https://";
  private static final String METHOD_OVERRIDE = "X-HTTP-Method-Override";
  private static final String SLASH = "/";
  private static final String DOT = ".";
  private static final String EXECUTE_API = "execute-api";
  private static final String AMAZON_AWS = "amazonaws.com";
  private static final String AWS_X_API_KEY = "x-api-key";
  private static final String RESOURCE = "resource";
  private static final String INPUT = "input";
  private static final String METHOD = "method";
  private static final String RESOURCE_UNDEF = "resource-undefined";

}

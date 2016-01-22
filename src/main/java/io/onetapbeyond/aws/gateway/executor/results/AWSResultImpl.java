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
package io.onetapbeyond.aws.gateway.executor.results;

import io.onetapbeyond.aws.gateway.executor.*;
import java.util.Map;
import com.google.gson.*;

/*
 * AWSResultImpl holds the result data of a
 * completed {@link AWSResult}.
 */
public class AWSResultImpl implements AWSResult {

  private boolean success;
  private String input;
  private String output;
  private String error;
  private Exception cause;
  private long timeTaken;

  public AWSResultImpl(boolean success,
                       String input,
                       String output,
                       String error,
                       Exception cause,
                       long timeTaken) {
    this.success = success;
    this.input = input;
    this.output = output;
    this.error = error;
    this.cause = cause;
    this.timeTaken = timeTaken;
  }

  /*
   * Determine if task execution was successful.
   */
  public boolean success() {
    return success;
  }

  /*
   * Return data inputs passed on task execution.
   */
  public Map input() {

    Map inputMap = null;
    /*
     * Covert JSON input string to Map representation.
     */
    try {
      inputMap = gson.fromJson(input, Map.class);
    } catch(Exception gex) {}

    return inputMap;
  }

  /*
   * Return data outputs generated on task execution.
   */
  public Map output() {
    // TODO:DMR
    return null;
  }

  /*
   * Retrieve error message if task execution failed.
   */
  public String error() {
    return error;
  }

  /*
   * Retrieve cause of error if task execution failed.
   */
  public Exception cause() {
    return cause;
  }

  /*
   * Returns approximate time taken (ms) by task execution.
   */
  public long	timeTaken() {
    return timeTaken;
  }

  public String toString() {
    String outcome = success ? "successful" : "failed";
    return "AWSResult [ " + outcome + " ].";
  }

  private static Gson gson = new Gson();

}

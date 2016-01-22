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
package io.onetapbeyond.aws.gateway.executor.builders;

import io.onetapbeyond.aws.gateway.executor.*;
import io.onetapbeyond.aws.gateway.executor.tasks.*;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.*;

/**
 * Builder of {@link AWSTask}. Each {@link AWSTask} represents an
 * API call on an AWS API Gateway. See the documentation on the
 * {@link AWSTask} class for details working with the builder.
 */
public class AWSTaskBuilder {

  private static Gson gson = new Gson();
  private final AWSGateway gateway;
  private String resource;
  private String input;
  private Method method;

  public AWSTaskBuilder(AWSGateway gateway) {
    this.gateway = gateway;
  }

  public AWSTaskBuilder resource(String resource) {
    this.resource = resource;
    return this;
  }

  public AWSTaskBuilder input(String input) {
    this.input = input;
    return this;
  }

  /**
   * Specify API input data on the {@link AWSTask}.
   *
   * @param input a Map representing the JSON data input
   * @return {@link AWSTask} builder instance
   * @throws AWSException if input can not be converted to valid JSON
   */
  public AWSTaskBuilder input(Map input) throws AWSException {

    try {
      this.input = gson.toJson(input);  
    } catch(Exception gex) {
      throw new AWSException("Task input data invalid.", gex);
    }
    return this;
  }

  /**
   * Build an API {@link AWSTask} using HTTP GET.
   * 
   * @return an executable {@link AWSTask}.
   */
  public AWSTask get() {
    this.method = Method.GET;
    return new AWSTaskImpl(gateway, apiMap());
  }

  /**
   * Build an API {@link AWSTask} using HTTP POST.
   * 
   * @return an executable {@link AWSTask}.
   */
  public AWSTask post() {
    this.method = Method.POST;
    return new AWSTaskImpl(gateway, apiMap());
  }

  /**
   * Build an API {@link AWSTask} using HTTP PUT.
   * 
   * @return an executable {@link AWSTask}.
   */
  public AWSTask put() {
    this.method = Method.PUT;
    return new AWSTaskImpl(gateway, apiMap());
  }

  /**
   * Build an API {@link AWSTask} using HTTP PATCH.
   * 
   * @return an executable {@link AWSTask}.
   */
  public AWSTask patch() {
    this.method = Method.PATCH;
    return new AWSTaskImpl(gateway, apiMap());
  }

  /**
   * Build an API {@link AWSTask} using HTTP DELETE.
   * 
   * @return an executable {@link AWSTask}.
   */
  public AWSTask delete() {
    this.method = Method.DELETE;
    return new AWSTaskImpl(gateway, apiMap());
  }

  private Map<String,String> apiMap() {
    Map<String,String> map = new HashMap();
    map.put("resource", resource);
    map.put("input", input);
    map.put("method", method.toString());
    return map;
  }

  /**
   * AWS API Gateway HTTP methods.
   */
  public enum Method {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");

    private final String method;

    Method(String method) {
      this.method = method;
    }

    public String toString() {
      return method;
    }
  }

}

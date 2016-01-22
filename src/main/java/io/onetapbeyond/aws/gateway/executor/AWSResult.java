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
package io.onetapbeyond.aws.gateway.executor;

import java.util.Map;

/**
 * Represents the result of an API call on the AWS API Gateway.
 */
public interface AWSResult extends java.io.Serializable {

  /**
   * Determine if task execution was successful.
   *
   * @return indication of task execution success or failure.
   */
  public boolean success();

  /**
   * Retrieve error message if task execution failed.
   *
   * @return an error message if task execution failed, otherwise null.
   */
  public String error();

  /**
   * Retrieve cause of error if task execution failed.
   *
   * @return an {@link java.lang.Exception} indicating cause of failure.
   */
  public Exception cause();

  /**
   * Returns approximate time taken (ms) by task execution.
   * @return time taken (ms) on task execution success, otherwise zero
   */
  public long timeTaken();

  /**
   * Return data inputs passed on task execution.
   *
   * @return {@link java.util.Map} representation of JSON data
   * inputs passed on task execution.
   */
public Map input();

  /**
   * Return data outputs generated on task execution.
   *
   * @return {@link java.util.Map} representation of JSON data
   * outputs generated on task execution.
   */
  public Map output();

}

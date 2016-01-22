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
package io.onetapbeyond.aws.gateway.executor.api;

import io.onetapbeyond.aws.gateway.executor.AWSGateway;

/*
 * Represents a specific API on the AWS API Gateway.
 */
public class AWSGatewayImpl implements AWSGateway {

  private static final long serialVersionUID = 705042215943644296L;

  private final String id; 
  private String region;
  private String stage;
  private String key; 

  public AWSGatewayImpl(String id, String region, String stage, String key) {
    this.id = id;
    this.region = region;
    this.stage = stage;
    this.key = key;
  }

  /*
   * Return AWS API Gateway task API identifier.
   */
  public String id() {
    return id;
  }

  /*
   * Return AWS API Gateway task API region.
   */
  public String region() {
    return region;
  }

  /*
   * Optional AWS API Gateway task API stage.
   */
  public String stage() {
    return stage;
  }

  /*
   * Optional AWS API Gateway task API key.
   */
  public String key() {
    return key;
  }

}

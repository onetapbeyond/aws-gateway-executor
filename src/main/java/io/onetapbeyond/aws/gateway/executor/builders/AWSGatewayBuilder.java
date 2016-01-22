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

import io.onetapbeyond.aws.gateway.executor.AWSGateway;
import io.onetapbeyond.aws.gateway.executor.api.AWSGatewayImpl;
import io.onetapbeyond.aws.gateway.executor.AWS.Region;


/**
 * Builder of {@link AWSGateway}. Each {@link AWSGateway} represents
 * a specific API on the AWS API Gateway. See the documentation on the
 * {@link AWSGateway} class for details on working with the builder.
 */
public class AWSGatewayBuilder {

  private final String id;
  private Region region;
  private String stage;
  private String key;

  public AWSGatewayBuilder(String id) {
    this.id = id;
  }

  public AWSGatewayBuilder region(Region region) {
    this.region = region;
    return this;
  }

  public AWSGatewayBuilder stage(String stage) {
    this.stage = stage;
    return this;
  }

  public AWSGatewayBuilder key(String key) {
    this.key = key;
    return this;
  }

  public AWSGateway build() {
    return new AWSGatewayImpl(id, region.toString(), stage, key);
  }

}

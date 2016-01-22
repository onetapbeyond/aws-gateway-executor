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

/**
 * Represents a specific API on the AWS API Gateway. A single instance
 * of {@link AWSGateway} is used to configure one or more {@link AWSTask}.
 * <p>
 * To create a basic {@link AWSGateway} use the following builder pattern:
 * <pre> 
 * {@code
 * AWSGateway gateway = AWS.Gateway(api-id).region(api-region).build();
 * }
 * </pre>
 * To create an {@link AWSGateway} for a specific API version (stage)
 * use the following builder pattern:
 * <pre> 
 * {@code
 * AWSGateway gateway =
 *  AWS.Gateway(api-id).region(api-region).stage(api-version).build();
 * }
 * </pre>
 * To create an {@link AWSGateway} for a specific API version (stage)
 * requiring a secure API key use the following builder pattern:
 * <pre> 
 * {@code
 * AWSGateway gateway =
 *  AWS.Gateway(api-id).region(api-region).stage(api-version).key(api-key).build();
 * }
 * </pre>
 */
public interface AWSGateway extends java.io.Serializable {

  /**
   * Return AWS API Gateway task API identifier.
   * @return String AWS API Gateway task API identifier.
   */
  public String id();

  /**
   * Return AWS API Gateway task API region.
   * @return String AWS API Gateway task API region.
   */
  public String region();

  /**
   * Optional AWS API Gateway task API stage.
   * @return String AWS API Gateway task API stage.
   */
  public String stage();

  /**
   * Optional AWS API Gateway task API key.
   * @return String AWS API Gateway task API key.
   */
  public String key();

}

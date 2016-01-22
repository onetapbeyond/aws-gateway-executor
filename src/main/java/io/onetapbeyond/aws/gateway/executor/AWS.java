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

import io.onetapbeyond.aws.gateway.executor.builders.*;
import io.onetapbeyond.aws.gateway.executor.tasks.*;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.*;

/**
 * Builder of {@link AWSGateway} and {@link AWSTask} instances.
 * See the documentation on {@link AWSGateway} and {@link AWSTask}
 * for respective builder details.
 */
public class AWS {
 
  private AWS() {} 
  
  private static Gson gson = new Gson();

  /**
   * Intiailize an {@link AWSGateway} builder. See {@link AWSGateway}
   * class for builder details.
   * @param apiId an AWS API Gateway API identifier.
   * @return AWSGatewayBuilder an {@link AWSGateway} builder.
   */
  public static AWSGatewayBuilder Gateway(String apiId) {
    return new AWSGatewayBuilder(apiId);
  }

  /**
   * Initialize an {@link AWSTask} builder. See {@link AWSTask}
   * class for builder details.
   * @param gateway an {@link AWSGateway} instance.
   * @return AWSTaskBuilder an {@link AWSTask} builder.
   */
  public static AWSTaskBuilder Task(AWSGateway gateway) {
    return new AWSTaskBuilder(gateway);
  }

  /**
   * AWS API Gateway regions.
   */
  public enum Region {

    /**
     * us-east-1
     */
    NVIRGINIA("us-east-1"),
    /**
     * us-west-1
     */
    NCALIFORNIA("us-west-1"),
    /**
     * us-west-2
     */
    OREGON("us-west-2"),
    /**
     * eu-west-1
     */
    IRELAND("eu-west-1"),
    /**
     * eu-central-1
     */
    FRANKFURT("eu-central-1"),
    /**
     * ap-southeast-1
     */
    SINGAPORE("ap-southeast-1"),
    /**
     * ap-southeast-2
     */
    SYDNEY("ap-southeast-2"),
    /**
     * ap-northeast-1
     */
    TOKYO("ap-northeast-1"),
    /**
     * ap-northeast-2
     */
    SEOUL("ap-northeast-2"),
    /**
     * sa-east-1
     */
    SAOPAULO("sa-east-1");

    private final String region;

    Region(String region) {
      this.region = region;
    }

    public String toString() {
      return region;
    }
  }

}

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
 * Represents a call on an API on the AWS API Gateway.
 * <p>
 * To create an {@link AWSTask} representing an API call using the
 * HTTP GET method use the following builder pattern:
 * <pre> 
 * {@code
 * AWSTask aTask = AWS.Task(gateway).resource(api-endpoint).get();
 * }
 * </pre>
 * To create an {@link AWSTask} representing an API call using the
 * HTTP GET method passing JSON input data use the following
 * builder pattern:
 * <pre> 
 * {@code
 * AWSTask aTask = AWS.Task(gateway).resource(api-endpoint).input(json-data).get();
 * }
 * </pre>
 * To create an {@link AWSTask} representing an API call using the
 * HTTP POST method passing JSON input data use the following
 * builder pattern:
 * <pre> 
 * {@code
 * AWSTask aTask = AWS.Task(gateway).resource(api-endpoint).input(json-data).post();
 * }
 * </pre>
 * To create an {@link AWSTask} representing an API call using the
 * HTTP PUT method passing JSON input data use the following
 * builder pattern:
 * <pre> 
 * {@code
 * AWSTask aTask = AWS.Task(gateway).resource(api-endpoint).input(json-data).put();
 * }
 * </pre>
 * To create an {@link AWSTask} representing an API call using the
 * HTTP PATCH method passing JSON input data use the following
 * builder pattern:
 * <pre> 
 * {@code
 * AWSTask aTask = AWS.Task(gateway).resource(api-endpoint).input(json-data).patch();
 * }
 * </pre>
 * To create an {@link AWSTask} representing an API call using the
 * HTTP DELETE method passing JSON input data use the following
 * builder pattern:
 * <pre> 
 * {@code
 * AWSTask aTask = AWS.Task(gateway).resource(api-endpoint).input(json-data).delete();
 * }
 * </pre>
 */
public interface AWSTask extends java.io.Serializable {

	/**
   * Execute AWS API Gateway task.
   * @return AWSResult an instance of {@link AWSResult}
	 */
	public AWSResult execute();

}

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
 * Exception thrown by {@link AWS} builder.
 */
public class AWSException extends Exception {

	public AWSException() { super(); }

	public AWSException(String message) {
		super(message);
	}

	public AWSException(String message, Throwable cause) {
		super(message, cause);
	}
}
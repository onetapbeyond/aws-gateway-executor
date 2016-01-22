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

import io.onetapbeyond.aws.gateway.executor.AWS.Region;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

/*
 * AGE library unit tests.
 */
public class AGETests {

  /*
   * AWS API Gateway Mock API configuration for AGE unit tests.
   */
  static String API_ID = "klgr530w8i";
  static Region REGION = AWS.Region.OREGON;
  static String STAGE = "unit_test";
  static AWSGateway gateway;

  @BeforeClass
  public static void setUpClass() {
    gateway = AWS.Gateway(API_ID).region(REGION).stage(STAGE).build();
  }

  @Test
  public void testMethodGet() throws AWSException {

    assertNotNull(gateway);

    Map data = new HashMap();
    data.put("hello", "world");

    AWSTask aTask = AWS.Task(gateway)
                       .resource("/unit_test/echo")
                       .get();

    assertNotNull(aTask);
    AWSResult aResult = aTask.execute();
    assertNotNull(aResult);
    assertNull(aResult.error());
    assertNull(aResult.cause());
    assertTrue(aResult.success());
  }

  @Test
  public void testMethodPost() throws AWSException {

    assertNotNull(gateway);

    Map data = new HashMap();
    data.put("hello", "world");

    AWSTask aTask = AWS.Task(gateway)
                       .resource("/unit_test/echo")
                       .post();

    assertNotNull(aTask);
    AWSResult aResult = aTask.execute();
    assertNotNull(aResult);
    assertNull(aResult.error());
    assertNull(aResult.cause());
    assertTrue(aResult.success());
  }


  @Test
  public void testMethodPut() throws AWSException {

    assertNotNull(gateway);

    Map data = new HashMap();
    data.put("hello", "world");

    AWSTask aTask = AWS.Task(gateway)
                       .resource("/unit_test/echo")
                       .put();

    assertNotNull(aTask);
    AWSResult aResult = aTask.execute();
    assertNotNull(aResult);
    assertNull(aResult.error());
    assertNull(aResult.cause());
    assertTrue(aResult.success());
  }


  @Test
  public void testMethodPatch() throws AWSException {

    assertNotNull(gateway);

    Map data = new HashMap();
    data.put("hello", "world");

    AWSTask aTask = AWS.Task(gateway)
                       .resource("/unit_test/echo")
                       .patch();

    assertNotNull(aTask);
    AWSResult aResult = aTask.execute();
    assertNotNull(aResult);
    assertNull(aResult.error());
    assertNull(aResult.cause());
    assertTrue(aResult.success());
  }


  @Test
  public void testMethodDelete() throws AWSException {

    assertNotNull(gateway);

    Map data = new HashMap();
    data.put("hello", "world");

    AWSTask aTask = AWS.Task(gateway)
                       .resource("/unit_test/echo")
                       .delete();

    assertNotNull(aTask);
    AWSResult aResult = aTask.execute();
    assertNotNull(aResult);
    assertNull(aResult.error());
    assertNull(aResult.cause());
    assertTrue(aResult.success());
  }

}
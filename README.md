## AWS API Gateway Executor (AGE)

[![Build Status](https://travis-ci.org/onetapbeyond/aws-gateway-executor.svg?branch=master)](https://travis-ci.org/onetapbeyond/aws-gateway-executor)

A lightweight Java client library for calling APIs exposed by the [Amazon Web Service API Gateway](https://aws.amazon.com/api-gateway/).

The API Gateway can route client calls to invoke [AWS Lambda](https://aws.amazon.com/lambda/) functions or any publicly addressable REST service regardless of where it is hosted. This library is ideally suited for integrating services exposed by the API Gateway into a wide range of applications, from mobile to new or existing server, middleware and cluster computing solutions. 


### Gradle Dependency

```
compile 'io.onetapbeyond:aws-gateway-executor:1.2'
```

### Maven Dependency

```
<dependency>
  <groupId>io.onetapbeyond</groupId>
  <artifactId>aws-gateway-executor</artifactId>
  <version>1.2</version>
</dependency>
```

### AWS API Gateway Integration

- Simplified API execution using [AWSTask](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/), no boilerplate [java.net](http://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html) code required.
- Fine grained access control to the API Gateway with support for gateway [API keys](http://docs.aws.amazon.com/apigateway/latest/developerguide/how-to-api-keys.html).
- Distributed cluster environment support through automatic [AWSTask](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/) and [AWSResult](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/)
serialization.

### Usage

When working with this library the basic usage pattern involves two steps:

####Step 1. Identify an API on the AWS API Gateway

An application identifies an API on the AWS API Gateway by creating an instance of [AWSGateway](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/). The 
basic builder pattern for creating instances of `AWSGateway` is as follows:

```
AWSGateway gateway = AWS.Gateway(api-id)
                        .region(api-region)
                        .stage(api-stage)
                        .build();
```

See the documentation on the [AWSGateway](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/) class for builder details. Note, if an application needs to integrate with more than one API on the AWS API Gateway then it should create an `AWSGateway` instance per API.

#### Step 2. Make API calls on the AWS API Gateway

An application can create any number of executable [AWSTask](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/)
where each task represents an API call on the AWS API Gateway.
The basic builder pattern for creating instances of `AWSTask` is as follows:

```
AWSTask aTask = AWS.Task(gateway)
                   .resource(api-endpoint)
                   .get();
```

See the documentation on the [AWSTask](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/) class for builder details. 

### Example Usage

The following code snippet demonstrates the creation of an
[AWSGateway](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/)  targeting the `beta` release of a fictitious `Echo` API.
The API is identified by `echo-api-key`, and maintained in the
`us-west-2` region by the AWS API Gateway:

```
AWSGateway gateway = AWS.Gateway(echo-api-key)
                        .stage("beta")
                        .region(AWS.Region.OREGON)
                        .build();
```

Once your application has an instance of `AWSGateway` 
you can start to create instances of [AWSTask](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/)  in order to make calls on the API. For example, the following code snippet demonstrates how to define a simple HTTP GET call on the API's `/echo` resource endpoint:

```
AWSTask aTask = AWS.Task(gateway)
                   .resource("/echo")
                   .get();
```

To pass JSON input data on an API call specify the data when
building the `AWSTask` instance. For example, here we define a HTTP
POST call passing JSON input data on the API's `/echo/greeting`
resource endpoint:

```
Map data = new HashMap();
data.put("message", "Hello, World!");

AWSTask aTask = AWS.Task(gateway)
                   .resource("/echo/greeting")
                   .input(data)
                   .post();
```

Finally, to execute any `AWSTask` simply call the `execute()` method as follows:

```
AWSResult aResult = aTask.execute();
```

When the task execution completes, the API call status, result data and/or error details are available on the [AWSResult](http://www.javadoc.io/doc/io.onetapbeyond/aws-gateway-executor/) returned by the `aTask.execute()` method.

### License

See the [LICENSE](LICENSE) file for license rights and limitations (Apache License 2.0).
# Quarkus AWS Lambda ABA Routing Number Validation

This project is a demo/POC for validating ABA Routing Numbers using serverless technology.

This project uses the following frameworks and technologies:

- [Quarkus](https://quarkus.io/)
- [Serverless Framework](https://www.serverless.com/)
- [AWS Lambda](https://aws.amazon.com/lambda/)
- [AWS API Gateway](https://aws.amazon.com/api-gateway/)

## Prerequisites

* JDK 11
* Apache Maven {maven-version}
* [Amazon AWS account](https://aws.amazon.com)
* [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html)
* [AWS SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)
  , for local testing
* [Docker](https://www.docker.com/)
* [Serverless Framework](www.serverless.com)

## Build and Deploy

The application can be packaged and deployed to AWS using:

```shell script
mvn clean package -Dquarkus.package.type=uber-jar && mvn package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true && sls deploy
```

It produces an uber jar `aba-routing-number-validation-0.0.1-SNAPSHOT-runner.jar`, and native image file `function.zip`
in the `/target` directory. Also deploys said artifacts using Serverless Framework.

Find out your API endpoints

````shell script
sls info
````

Make some API calls to your newly created endpoints via POSTMAN, curl, etc.

## Delete Serverless Stack

To remove all AWS resources as a result the build and deployment execute the following

```shell script
sls remove
```
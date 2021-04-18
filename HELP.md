# Getting Started

## Creating docker image
Creating docker image can be easily done using spring boot support with maven: ./mvnw spring-boot:build-image
Or simply run createimage.bat to create docker image.
The created image will be stored in the docker repository. Check it with "docker images".

## Deploying to Kubernetes
For quick deploying in development phase, we use the integrated Kubernetes provided by the Docker Desktop. Activate it in the Docker Destop setting.
This integrated Kubernetes have access to the Docker repository to pull the necessary images.
Simply call 'kubectl apply -f deployment.yaml' to deploy the image using the deployment configuration in deployment.yaml.
Simply call 'kubectl delete -f deployment.yaml' to remove those service again.

## Kafka in Kubernetes:
https://strimzi.io/quickstarts/

## Futher documentations
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#service-discovery-eureka-clients)

### Guides
The following guides illustrate how to use some features concretely:

* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)


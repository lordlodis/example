# Getting Started

## Creating docker image
Creating docker image can be easily done using spring boot support with maven: ./mvnw spring-boot:build-image
Or simply run createimage.bat to create docker image.
The created image will be stored in the docker repository. Check it with "docker images".

## Deploying to Kubernetes
For quick deploying in development phase, we use the integrated Kubernetes provided by the Docker Desktop. Activate it in the Docker Destop setting.
This integrated Kubernetes have access to the Docker repository to pull the necessary images.
Simply call 'kubectl apply -f deployment.yaml' to deploy the image using the deployment configuration in deployment.yaml.

## Futher documentations
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-security)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)



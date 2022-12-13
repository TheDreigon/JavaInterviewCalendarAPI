# JavaCalendarAPI
Personal Calendar Java project using Spring Boot and REST endpoints.

### About

This a simple Java application created using Spring Boot. It's a REST endpoint based app focused on a "Calendar" theme. 
With simple HTTP requests you can easily consult, create, edit, and even delete your Calendar entities.
It uses an in-memory database (H2) and, as such, is volatile. 

### Software Requirements

For building, running, and testing the application you need:

- [JDK 19](https://www.oracle.com/java/technologies/downloads/#jdk19-windows)
- [Maven 3](https://maven.apache.org/download.cgi)
- Ideally, an IDE (recommendation: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows))
- An API testing tool such as [Postman](https://www.postman.com/downloads/) 

### Running the application locally

This application is packaged as a jar which already has Tomcat embedded. No Tomcat or JBoss installation is necessary. 

* Clone this repository
* Make sure you are using JDK 19 and Maven 3.x (don't forget the environment variables)
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the application by one of these three methods:

#### Run the Java jar from the Command-line interface (CLI) 

Open the CLI in the jar file folder and simply run the command "java -jar JavaCalendarAPI-1.0.jar" 
(if the port is already being used, you can choose a different port by adding "-port={your_port}" to the above command)

#### Main Method from IDE

You can also execute the `main` method in the `com.github.TheDreigon.JavaCalendarAPI` class from your IDE.

#### Run it as a SpringApplication

Alternatively, as it is a Spring Boot application, you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Once the application runs, you should see something like this:

```
2022-12-03T01:08:46.462Z  INFO 11828 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-12-03T01:08:46.490Z  INFO 11828 --- [           main] c.g.T.J.JavaCalendarApiApplication       : Started JavaCalendarApiApplication in 8.219 seconds (process running for 9.049)
```

### API testing

Postman is an HTTP client that tests HTTP requests, perfect for a REST API. It will serve as a tool to validate this application's responses.

To use it, simply:
* Enter your Postman workspace
* Enter the API Endpoint where it says, “Enter request URL” (you can create a new request by clicking the "+" sign at the top, near "Search Postman"), 
  select the desired method (GET, POST, PUT, DELETE) for that request, and fill any needed parameter and Body payload 
  (type is "raw: JSON" and the Calendar object currently only has two properties - `"name":"String"` and `"description":"String"`)
* Click Send and verify the result

You can even create request collections (left tab) based on the HTTP requests you want to save.

### Closing Remarks

While the application is simple for now, it will suffer maintenance and evolution.

If you have any remarks yourself, feel free to send me a message.

Thank you.

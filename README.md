# JavaInterviewCalendarAPI
Personal Interview Calendar Java project using Spring Boot and REST endpoints.

### About

This a simple Java application created using Spring Boot. It's a REST endpoint based app focused on an "Interview Calendar" theme. 
With simple HTTP requests you can easily consult, create, edit, and even delete your Interview Calendar related entities.
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

Open the CLI in the jar file folder and simply run the command "java -jar JavaInterviewCalendarAPI-2.0.jar" 
(if the port is already being used, you can choose a different port by adding "-port={your_port}" to the above command)

#### Main Method from IDE

You can also execute the `main` method in the `com.github.TheDreigon.JavaInterviewCalendarAPI` class from your IDE.

#### Run it as a SpringApplication

Alternatively, as it is a Spring Boot application, you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Once the application runs, you should see something like this:

```
2022-12-03T01:08:46.462Z  INFO 11828 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-12-03T01:08:46.490Z  INFO 11828 --- [           main] c.g.T.J.JavaInterviewCalendarApiApplication       : Started JavaInterviewCalendarApiApplication in 8.219 seconds (process running for 9.049)
```

### API testing

Postman is an HTTP client that tests HTTP requests, perfect for a REST API. It will serve as a tool to validate this application's responses.

To use it, simply:
* Enter your Postman workspace
* Enter the API Endpoint where it says, “Enter request URL” (you can create a new request by clicking the "+" sign at the top, near "Search Postman"), 
  select the desired method (GET, POST, PUT, DELETE) for that request, and fill any needed parameter and Body payload (type is "raw: JSON")
* An example of a Candidate payload (which is similar to an Interviewer's) would be 
```
{
  "name": "candidate",
  "description": "candidate"
}
```  
* An example of a CandidateAvailability payload (which is similar to an InterviewerAvailability's) would be
```
{
  "dayDate": "30/12/1995",
  "availableHour": "12PM",
  "dayOfWeek": "MONDAY"
}
```
* Click Send and verify the result

You can even create request collections (left sidebar) based on the HTTP requests you want to save.

### Available Endpoints

#### RestIndexController:

{GET [/api/ || /api || / || ]}: showVersion()

#### RestAvailabilityController:

{GET [/api/availabilities/candidates]}: getCandidateAvailabilities()

{GET [/api/availabilities/overlaps/candidate/{cId}/interviewer/{iId} || /api/availabilities/overlaps/interviewer/{iId}/candidate/{cId}]}: getAvailabilityOverlapsForGivenIds(Integer,Integer)

{GET [/api/availabilities/]}: getAllAvailabilities()

{GET [/api/availabilities/interviewers]}: getInterviewerAvailabilities()

{GET [/api/availabilities/overlaps]}: getAllAvailabilityOverlaps()

#### RestCandidateAvailabilityController:

{GET [/api/candidates/{cId}/availabilities/{caId}]}: getCandidateAvailabilityById(Integer,Integer)

{GET [/api/candidates/availabilities/]}: getCandidateAvailabilities()

{GET [/api/candidates/{cId}/availabilities/]}: getCandidateAvailabilities(Integer)

{POST [/api/candidates/{cId}/availabilities/]}: addCandidateAvailability(Integer,CandidateAvailabilityDto,BindingResult,UriComponentsBuilder)

{PUT [/api/candidates/{cId}/availabilities/{caId}]}: editCandidateAvailability(Integer,Integer,CandidateAvailabilityDto,BindingResult)

{DELETE [/api/candidates/{cId}/availabilities/{caId}]}: deleteCandidateAvailability(Integer,Integer)

#### RestCandidateController:

{GET [/api/candidates/]}: getCandidates()

{POST [/api/candidates/]}: addCandidate(CandidateDto,BindingResult,UriComponentsBuilder)

{PUT [/api/candidates/{cId}]}: editCandidate(Integer,CandidateDto,BindingResult)

{DELETE [/api/candidates/{cId}]}: deleteCandidate(Integer)

{GET [/api/candidates/{cId}]}: getCandidateById(Integer)

#### RestInterviewerAvailabilityController:

{GET [/api/interviewers/availabilities/]}: getInterviewerAvailabilities()

{GET [/api/interviewers/{iId}/availabilities/]}: getInterviewerAvailabilities(Integer)

{DELETE [/api/interviewers/{iId}/availabilities/{iaId}]}: deleteInterviewerAvailability(Integer,Integer)

{POST [/api/interviewers/{iId}/availabilities/]}: addInterviewerAvailability(Integer,InterviewerAvailabilityDto,BindingResult,UriComponentsBuilder)

{GET [/api/interviewers/{iId}/availabilities/{iaId}]}: getInterviewerAvailabilityById(Integer,Integer)

{PUT [/api/interviewers/{iId}/availabilities/{iaId}]}: editInterviewer(Integer,Integer,InterviewerAvailabilityDto,BindingResult)

#### RestInterviewerController:

{GET [/api/interviewers/{iId}]}: getInterviewerById(Integer)

{DELETE [/api/interviewers/{iId}]}: deleteInterviewer(Integer)

{POST [/api/interviewers/]}: addInterviewer(InterviewerDto,BindingResult,UriComponentsBuilder)

{PUT [/api/interviewers/{iId}]}: editInterviewer(Integer,InterviewerDto,BindingResult)

{GET [/api/interviewers/]}: getInterviewers()

#### InterviewCalendarErrorController:

{ [/error]}: error()

### Closing Remarks

While the application is simple for now, it will suffer maintenance and evolution. 

Different "pr" branches have different API versions. The most recent version is version 2.

If you have any remarks yourself, feel free to send me a message.

Thank you

### NOTE:

#### The current release (v2) is still in testing. There is still a bug or two present!

#### The next steps, for v2, will be to iron out the bugs, implement the unit tests, and further polish the code.

# Add persons to register

Spring boot application to add and delete persons from a register. Persons are are added and deleted using a REST API. A person is repsesented by email, firstname and lastname. The register is kept in memory with no persistance. The repository is represented by a HashMap for quick add and retrieval.

### Application structure and packages

- Controller: REST controller defining the endpoints
- Model: model class representing a person
- Exception: custom exception handling for indata validation
- Service: service layer to manage application logic
- Tests: tests written in JUnit 5

### Run application

This is a Spring Boot application built with Maven and Java 21.

The application can be run with Maven using:

```
mvn clean install && mvn spring-boot:run
```

The application can also be run with docker compose:

Build application:

```
mvn clean install && mvn clean package
```

Run application with docker compose

```
docker compose up --build

docker compose kill -s SIGINT
```

### Sample requests

Add person:

```json
curl --request POST \
  --url http://localhost:8080/api/person \
  --header 'Content-Type: application/json' \
  --data '{
	"email": "jakob.test@gmail.com",
	"firstname": "Jakob",
	"lastname": "Hamilton"
}'
```

Delete person by email:

```json
curl --request DELETE \
  --url 'http://localhost:8080/api/person?email=jakob.test@gmail.com'
```

Get all persons:

```json
curl --request GET \
  --url http://localhost:8080/api/person
```

### Possible improvements

- Endpoint to get person by email
- More Controller tests and Integration tests
- Data persistance of register

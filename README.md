# Spring Data JPA Criteria Example

Spring Data JPA Criteria example for using h2, Spring Data JPA, Liquibase, hibernate-jpamodelgen,
mapstruct, lombok, swagger, etc.

---
## Justification

Utilizing Spring Data JPA with Criteria Query and Specifications presents a compelling choice for
constructing database queries in your application. This approach offers numerous advantages,
including type safety, which catches query-related errors at compile time. It allows for dynamic
query generation, making it ideal for adapting to runtime conditions or user input. Moreover, the
encapsulation of query logic into reusable predicates promotes code reusability, and it separates
query logic from business logic, enhancing code maintainability. With the ability to easily compose
complex queries, the type-safe and testable nature of Specifications simplifies debugging and unit
testing. Additionally, this approach ensures database-agnostic queries, making it straightforward to
switch between different databases without significant code changes. It also contributes to improved
security by reducing the risk of SQL injection attacks and can optimize query performance. Finally,
its seamless integration with Spring Data JPA repositories provides a unified and developer-friendly
experience. In summary, Spring Data JPA with Criteria Query and Specifications streamlines the
process of constructing dynamic and complex queries, making it a powerful tool for data-driven
applications.

---
## Requirements

* Docker 24.0.6
* Make 3.81
* Java 21
* Maven 3.9.5

---
## Run

```bash
source .env; \
mvn clean install; \
java \
-Dserver.port=${APPLICATION_PORT} \
-jar target/${APPLICATION}.jar
```

or

```bash
mvn clean install; \
make docker.start
```

Once the application is running, you can view the
swagger [here](http://localhost:8080/swagger-ui/index.html#).

---
## Example

### Get all UserLog

```bash
source .env; \
curl  "http://localhost:${APPLICATION_PORT}/v1/userlog?page=0&size=20"
```

### Get all the first UserLog from the second page

```bash
source .env; \
curl "http://localhost:8080/v1/userlog?page=1&size=1"
```

### Get all the UserLog from userId 'EightAugusto'

```bash
source .env; \
curl "http://localhost:${APPLICATION_PORT}/v1/userlog?userIds=EightAugusto&page=0&size=20"
```

### Get all the UserLog from userId 'EightAugusto' in date range '2024-01-01T00:00:00Z' to '2024-01-01T01:00:00Z'

```bash
source .env; \
curl "http://localhost:${APPLICATION_PORT}/v1/userlog?userIds=EightAugusto&date.from=2024-01-01T00:00:00Z&date.to=2024-01-01T01:00:00Z&page=0&size=20"
```

### Post UserLog for userId 'SevenAugusto'

```bash
source .env; \
curl -X "POST" "http://localhost:${APPLICATION_PORT}/v1/userlog" \
  -H 'Content-Type: application/json' \
  -d '{"userId": "SevenAugusto", "timestamp": "2024-01-01T00:00:00Z","detail": "Log created"}'
```

### Get all the UserLog from userId 'SevenAugusto' (Record previously created)

```bash
source .env; \
curl "http://localhost:${APPLICATION_PORT}/v1/userlog?userIds=SevenAugusto&page=0&size=20"
```
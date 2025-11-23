# Intelligent Tutoring System

## Overview
A Spring Boot 3 REST backend that powers an Intelligent Tutoring System (ITS). It manages:

- **Content** – topics, questions, choices.
- **Assessment** – start / submit attempts, calculate scores.
- **User interactions** (future work).

The project uses PostgreSQL + JPA (Hibernate) and exposes a clean OpenAPI specification for the Front-End team.

---

## Tech stack
- Java 21 / Spring Boot 3.2
- Spring Data JPA (Hibernate)
- PostgreSQL (runtime) / H2 (tests)
- Lombok
- Springdoc-OpenAPI 2 (Swagger UI)
- Maven

---

## Prerequisites
1. **Java 21** + **Maven 3.9**
2. **PostgreSQL** running (or update `application.yml` for your DB). Example:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/its
       username: its
       password: its
   ```

---

## Getting started
```bash
# clone and build
mvn clean package -DskipTests

# run
mvn spring-boot:run
# or
java -jar target/Intelligent-Tutoring-System-0.0.1-SNAPSHOT.jar
```

The server starts on **`http://localhost:8080`**.

---

## API Documentation
| Resource | URL |
|----------|-----|
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |
| OpenAPI YAML | `http://localhost:8080/v3/api-docs.yaml` |

### Export static `openapi.yaml`
```bash
mvn clean springdoc-openapi:generate
# file generated: target/openapi/openapi.yaml
```

### Generate Postman collection
```bash
openapi-generator-cli generate \
  -i target/openapi/openapi.yaml \
  -g postman-collection \
  -o postman
```

---

## Key Endpoints

### Content
| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/content/questions` | Create question |
| `GET` | `/api/content/questions/{id}` | Question detail |
| `PUT` | `/api/content/questions/{id}` | Update question |
| `DELETE` | `/api/content/questions/{id}` | Delete question |
| `GET` | `/api/content/questions` | Filter questions (supports `page`/`size` for plain list) |
| `POST` | `/api/content/topics` | Create topic |
| `GET` | `/api/content/topics/{id}` | Topic detail |
| `PUT` | `/api/content/topics/{id}` | Update topic |
| `DELETE` | `/api/content/topics/{id}` | Delete topic |
| `GET` | `/api/content/topics` | List topics (supports `page`/`size`) |
| `POST` | `/api/content/courses` | Create course |
| `GET` | `/api/content/courses/{id}` | Course detail |
| `PUT` | `/api/content/courses/{id}` | Update course |
| `DELETE` | `/api/content/courses/{id}` | Delete course |
| `GET` | `/api/content/courses` | List courses (supports `page`/`size`) |

### Assessments
| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/assessments/quiz` | Create quiz |
| `GET` | `/api/assessments/quiz/{id}` | Quiz detail |
| `PUT` | `/api/assessments/quiz/{id}` | Update quiz |
| `DELETE` | `/api/assessments/quiz/{id}` | Delete quiz |
| `GET` | `/api/assessments/quiz` | List quizzes (supports `page`/`size`) |
| `POST` | `/api/assessments/sessions` | Create or resume session |
| `GET` | `/api/assessments/sessions/{id}` | Session detail |
| `PUT` | `/api/assessments/sessions/{id}` | Update session (completed) |
| `DELETE` | `/api/assessments/sessions/{id}` | Delete session |
| `GET` | `/api/assessments/sessions` | List sessions (supports `page`/`size`) |
| `POST` | `/api/assessments/start` | Start assessment |
| `POST` | `/api/assessments/submit` | Submit assessment |
|
| `GET` | `/api/assessments/attempts/{id}` | Attempt detail |
| `GET` | `/api/assessments/attempts` | List all attempts (supports `page`/`size`) |
| `GET` | `/api/assessments/attempts?studentId=xxx` | Attempts by student |
| `GET` | `/api/assessments/attempts?assessmentId=xxx` | Attempts by assessment |

More endpoints are documented in Swagger.

---

## Running tests
```bash
mvn test
```

Unit tests use Mockito + MockMvc; integration tests spin up the context with H2 in-memory DB.

---

## Roadmap / TODO
- Authentication & authorization (JWT)
- Adaptive question selection algorithm
- Real-time feedback websocket

---

## License
MIT (see `LICENSE` file).
# Inditex Proof - Spring Boot Application

## Overview
This repository contains a Spring Boot application for managing price-related operations within Inditex. The application follows Domain-Driven Design (DDD) principles and provides functionalities for querying applicable prices.

## Guidelines
### Running the Application

1. Clone this repository:
   ```sh
   git clone https://github.com/Azo-dev/inditexProof.git
   cd inditexProof
   ```

2. Build the application using Gradle:
   ```sh
   ./gradlew build
   ```
   or on Windows:
   ```sh
   gradlew.bat build
   ```

3. Run the application:
   ```sh
   ./gradlew bootRun
   ```

4. The application will start on the default port (e.g., `http://localhost:8080`).

### API Usage
Once the application is running, you can query prices using the provided endpoints.
Example request:
```sh
curl -X GET "http://localhost:8080/api/prices?brandId=1&productId=35455&date=2024-02-14T00:00:00"
```

### Source Code Review
The core functionality is implemented in the following classes:

1. **Application entry point:**
   ```java
   @SpringBootApplication
   public class InditexProofApplication {
       public static void main(String[] args) {
           SpringApplication.run(InditexProofApplication.class, args);
       }
   }
   ```

2. **Price entity model:**
   ```java
   @Entity
   public class Price {
       @Id
       private Long id;
       private Long brandId;
       private Long productId;
       private LocalDateTime startDate;
       private LocalDateTime endDate;
       private Double price;
   }
   ```

3. **FindApplicablePriceHandler for querying prices:**
   ```java
   @Service
   public class FindApplicablePriceHandler {
       public ApplicablePriceDto findPrice(Long brandId, Long productId, LocalDateTime date) {
           // Business logic to retrieve applicable price
       }
   }
   ```

## Running Tests
Run the tests using:
```sh
./gradlew test
```

## More Info
This application follows best practices for Spring Boot development and includes a layered architecture for maintainability. Further details and API documentation can be found in the project's source files.

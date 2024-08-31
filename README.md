# Microservices-based Quiz Web Application

This project is a Quiz Web Application built using a microservices architecture, comprising four separate projects that communicate via REST APIs. Below is a detailed description of each microservice and its endpoints:

# 1. Question Microservice

This microservice manages quiz questions and includes the following REST API endpoints:

1. **add**: Creates a new entry in the `questiondb` in the `question` table with fields: `questionTitle`, `category`, `option1`, `option2`, `option3`, `option4`, `rightAnswer`, and `difficultyLevel`.
2. **update**: Updates an existing record in the `question` table using an `Id` and the same fields as above.
3. **delete**: Removes a record from the `question` table by providing the record `Id`.
4. **getAll**: Fetches all records from the `question` table.
5. **getByCategory**: Fetches records filtered by `category`.
6. **generate**: Generates and returns question IDs by consuming `category` and `numOfQuestions` as parameters.
7. **getQuestions**: Returns the questions based on the provided question IDs.
8. **getScore**: Calculates the score based on the responses sent with the question IDs; the correct answers are compared with the responses.

This microservice is registered as a client with Eureka and discovered by the Eureka server.

# 2. Quiz Microservice

This microservice manages quizzes and includes the following REST API endpoints:

1. **create**: Adds a new quiz by fetching questions from the Question Microservice.
2. **getQuestions**: Retrieves questions by providing question IDs.
3. **getScore**: Calculates and returns the score by providing question IDs and answers.

This microservice is also registered as a client with Eureka and discovered by the Eureka server. OpenFeign is used to handle REST APIs, enabling communication between microservices and providing load balancing to efficiently distribute incoming requests among multiple instances of the Question Microservice.

# 3. Service Registry

This project is built using the Eureka server dependency, which acts as a discovery server for the registered clients. The Quiz and Question Microservices are registered as Eureka clients.

# 4. API Gateway

The API Gateway enables seamless communication with microservices without requiring users to know the port numbers. It acts as the single entry point for all client requests, whether from web, mobile, or desktop applications, and routes them to the appropriate microservices.

---

If you have any queries, feel free to contact me at: [zohaib89769@gmail.com](mailto:zohaib89769@gmail.com) / [zohaib.ndc6@gmail.com](mailto:zohaib.ndc6@gmail.com)


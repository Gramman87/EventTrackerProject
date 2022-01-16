# EventTrackerProject

## Backend Overview

The purpose of this solo project is to build a REST back-end API. I first designed my MySQL database using MySQL Workbench. I populated a large amount of test data using Mockaroo.com. Next I built out my entity classes using Spring Data JPA. In this phase I concurrently tested my entities and their JPA mappings using JUnit 5 for my TDD. Once I had all my entities built, mapped, and tested I turned my attention to the repositories, services, and service implementations. Then continued my way up the stack with my REST Controllers. The table below shows my REST API routes which were all tested in Postman before moving on to the frontend.

|Return Type       | HTTP Method | URI                         | Request Body |
|------------------|-------------|-----------------------------|--------------|
| List\<User\>     | GET         | /api/users                  |              |
| User             | GET         | /api/users/{id}             |              |
| User             | Post        | /api/users                  | User         |
| User             | Post        | /api/users/{id}             | User         |
| Void             | Delete      | /api/users/{id}             |              |
| List\<Vehicle\>  | Get         | /api/users/{id}/vehicles    |              |
|------------------|-------------|-----------------------------|--------------|
| List\<Vehicle\>  | GET         | /api/vehicles               |              |
| Vehicle          | GET         | /api/vehicles/{id}          |              |
| Vehicle          | Post        | /api/vehicles               | Vehicle      |
| Vehicle          | Post        | /api/vehicles/{id}          | Vehicle      |
| Void             | Delete      | /api/vehicles/{id}          |              |
| List\<Services\> | Delete      | /api/vehicles/{id}/services |              |
|------------------|-------------|-----------------------------|--------------|
| List\<Services\> | GET         | /api/services               |              |
| Service          | GET         | /api/services/{id}          |              |
| Service          | Post        | /api/services               | Service      |
| Service          | Post        | /api/services/{id}          | Service      |
| Void             | Delete      | /api/services/{id}          |              |
|------------------|-------------|-----------------------------|--------------|

## Frontend Overview

The first iteration of my frontend was put together using JavaScript, XMLHttp, and Html. 

## Lessons Learned

I think the biggest lesson I learned on this solo Full Stack project was scope management. In the very beginning, when assembling my database, I let myself loose track of my "minimum viable project". As a result the project became much bigger and honestly the quality suffered as a result. As helpful as the various frameworks are that we work within there is still so much to do, and expansion later on is always an option.

## Technologies Used
Java, Spring Data JPA, Spring BOOT, Gradle, MySQL, Unix Shell, Git

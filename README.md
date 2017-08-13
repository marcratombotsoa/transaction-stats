# transaction-stats
Rest API written in Spring boot to compute transaction statistics in real time

## Prerequisites
	- Java 8
	- Maven 3.x
	- Internet connection (To download dependencies automatically with maven)
	
## Setup
	- Clone or download the repository
	- Go to the project root folder and run the following maven command to build the application and to run the tests
	$ mvn clean install
	- To run the application, execute the following command:
	$ mvn spring-boot:run
	- The endpoints are located on:
	POST http://localhost:8080/transactions
	GET http://localhost:8080/statistics

## H2 Database console
While the application is running, you can access the H2 in memory database console.
To access the H2 console, navigate to http://localhost:8080/h2 and enter the following value in the URL field: jdbc:h2:mem:transaction_db
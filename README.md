# Restful Booker API Testing Project

## Overview
This project provides automated tests for the [Restful Booker API](https://restful-booker.herokuapp.com), a sample application for testing hotel booking APIs. The test suite is implemented using **Rest Assured** and **JUnit Jupiter** and covers a wide range of API functionalities, including creating, retrieving, updating, and deleting bookings.

The project follows clean coding principles, ensuring reusable and maintainable test code. This repository is an excellent reference for anyone learning or demonstrating API test automation.

---

## Features
- **Comprehensive Test Coverage:** Includes tests for happy paths which refers to the scenario in software testing (or software development) where everything works as expected under ideal conditions., error scenarios, and edge cases.  
- **Reusable Components:** Utility classes for authentication, API requests, and test data management.  
- **Parallel Test Execution:** All tests are designed to run independently for better performance.  
- **Debug-Friendly Logging:** Response bodies and detailed error messages are logged for troubleshooting.  

---

## Prerequisites
To get started with this project, ensure you have the following installed:
- **Java JDK** 8 or higher  
- **Maven**  
- An IDE like **IntelliJ IDEA** or **Eclipse**

---

## Test Coverage

### Basic Operations
1. **Get Booking by ID**  
   Verifies that an existing booking can be retrieved using its unique ID.  

2. **Create New Booking**  
   Ensures a new booking is created successfully with valid data.  

3. **Update Existing Booking**  
   Validates the full update of a booking using the PUT method.  

4. **Partial Update Booking**  
   Confirms partial updates using the PATCH method.  

5. **Delete Booking**  
   Tests the deletion of a booking and verifies the response status and data.  

### Error Scenarios
- Invalid Booking ID (404)  
- Missing Required Fields (400)  
- Invalid Authentication Token (401)  
- Missing Authentication Token (403)  



## How to Run the Tests

### Using Maven
Run the test suite via Maven with the following command:
```bash
mvn clean test
    

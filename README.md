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

## API Endpoints

The following endpoints are tested in the suite:

| Method | Endpoint       | Description                        |
|--------|----------------|------------------------------------|
| POST   | /auth          | Generate an authentication token  |
| GET    | /booking/{id}  | Retrieve a booking by ID          |
| POST   | /booking       | Create a new booking              |
| PUT    | /booking/{id}  | Update an existing booking        |
| PATCH  | /booking/{id}  | Partially update a booking        |
| DELETE | /booking/{id}  | Delete a booking                  |


## How to Run the Tests

### Using Maven
Run the test suite via Maven with the following command:
```bash
mvn clean test

```

## Contributing

We welcome contributions to this project! To get started:

1. **Fork the repository**  
   Create your own copy of the repository by clicking the "Fork" button at the top-right corner of this page.

2. **Create a feature branch**  
   Create a new branch for your feature or bugfix. For example:  
   ```bash
   git checkout -b feature/your-feature-name

     ```
3. **Commit your changes**
   Make your changes and commit them with a clear and descriptive message:
   ```bash
            git commit -m "Add description of your changes"
   ```

4. **Push to the branch**
Push your branch to your forked repository:
   ```bash
   git push origin feature/your-feature-name

      ```
   
5 **Thank you for contributing! **




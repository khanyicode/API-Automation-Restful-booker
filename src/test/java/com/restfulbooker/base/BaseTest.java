/**
 * BaseTest class:
 * - This class provides shared setup and configurations for all API 
 * - It initializes the base URI for REST API .
 * - Acts as a parent class for all test cases in the project.
 */

 package com.restfulbooker.base;

 import io.restassured.RestAssured;
 import io.restassured.builder.RequestSpecBuilder;
 import io.restassured.builder.ResponseSpecBuilder;
 import io.restassured.filter.log.RequestLoggingFilter;
 import io.restassured.filter.log.ResponseLoggingFilter;
 import io.restassured.specification.RequestSpecification;
 import io.restassured.specification.ResponseSpecification;
 import org.junit.jupiter.api.BeforeAll;
 import com.restfulbooker.utils.ConfigManager;  // Added these
 import com.restfulbooker.utils.Endpoints;      // Added  these
 
 public class BaseTest{
     protected static RequestSpecification requestSpec;
     protected static ResponseSpecification responseSpec;
     
     @BeforeAll
     public static void setup() {
         RestAssured.baseURI = "https://restful-booker.herokuapp.com";
         
         requestSpec = new RequestSpecBuilder()
             .setContentType("application/json")
             .addFilter(new RequestLoggingFilter())
             .addFilter(new ResponseLoggingFilter())
             .build();
             
         responseSpec = new ResponseSpecBuilder()
             .expectStatusCode(200)
             .build();
             
         RestAssured.requestSpecification = requestSpec;
         RestAssured.responseSpecification = responseSpec;
     }
     
     protected String getAuthToken() {
         String payload = "{\"username\":\"" + ConfigManager.getUsername() + 
                         "\",\"password\":\"" + ConfigManager.getPassword() + "\"}";
         
         return RestAssured
             .given()
                 .body(payload)
             .when()
                 .post(Endpoints.AUTH)
             .then()
                 .statusCode(200)
                 .extract()
                 .path("token");
     }
 }
package com.example.tests;

import com.example.models.Booking;
import com.example.utils.ApiUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.example.models.BookingResponse; 

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTests {

    // Set up base URI for Rest Assured
    static {
        RestAssured.baseURI = ApiUtils.getBaseUrl();
    }

    @Test
    public void getBookingById() {
        // GET request to retrieve booking by ID
        Response response = given()
                .basePath("/booking/1")
                .when()
                .get();

        // Validate the response status code
        assertEquals(200, response.getStatusCode());

        // Print response body for verification
        System.out.println("Response Body: " + response.asString());
    }
    @Test
    public void createBooking() {
        // JSON body for creating a new booking
        String jsonBody = "{ \"firstname\": \"Jim\", \"lastname\": \"Brown\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2025-01-01\", \"checkout\": \"2025-01-10\" }, \"additionalneeds\": \"Breakfast\" }";
    
        // POST request to create a new booking
        Response response = given()
                .basePath("/booking")
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post();
    
        // Validate the response status code
        assertEquals(200, response.getStatusCode());
    
        // Deserialize the response into BookingResponse object
        BookingResponse bookingResponse = response.as(BookingResponse.class);
        
        // Print details for verification
        System.out.println("Booking ID: " + bookingResponse.bookingid);
        System.out.println("First Name: " + bookingResponse.booking.firstname);
    }
}

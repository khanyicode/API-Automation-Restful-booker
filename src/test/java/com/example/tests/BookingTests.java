package com.example.tests;

import com.example.models.Booking;
import com.example.models.BookingResponse;
import com.example.utils.ApiUtils;
import com.example.utils.AuthUtils;
import com.example.testdata.BookingTestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTests {
    private static String authToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ApiUtils.getBaseUrl();
        authToken = AuthUtils.getAuthToken();
    }

    @Test
    public void getBookingById() {
        Response response = given()
            .basePath("/booking/1")
            .when()
            .get();

        assertEquals(200, response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
    }

    @Test
    public void createBooking() {
        Booking testBooking = BookingTestData.getDefaultBooking();
        
        BookingResponse response = given()
            .contentType(ContentType.JSON)
            .body(testBooking)
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        assertEquals(testBooking.firstname, response.booking.firstname);
        assertEquals(testBooking.lastname, response.booking.lastname);
    }

    @Test
    public void updateBooking() {
        // Create initial booking
        BookingResponse bookingResponse = given()
            .contentType(ContentType.JSON)
            .body(BookingTestData.getDefaultBooking())
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        // Update booking
        Booking updatedBooking = BookingTestData.getUpdatedBooking();
        
        Booking response = given()
            .contentType(ContentType.JSON)
            .header("Cookie", "token=" + authToken)
            .body(updatedBooking)
            .put("/booking/" + bookingResponse.bookingid)
            .then()
            .statusCode(200)
            .extract()
            .as(Booking.class);

        assertEquals(updatedBooking.firstname, response.firstname);
        assertEquals(updatedBooking.totalprice, response.totalprice);
    }

    @Test
    public void partialUpdateBooking() {
        // Create initial booking
        BookingResponse bookingResponse = given()
            .contentType(ContentType.JSON)
            .body(BookingTestData.getDefaultBooking())
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        // Partial update
        Booking response = given()
            .contentType(ContentType.JSON)
            .header("Cookie", "token=" + authToken)
            .body(BookingTestData.getPartialUpdateJson())
            .patch("/booking/" + bookingResponse.bookingid)
            .then()
            .statusCode(200)
            .extract()
            .as(Booking.class);

        assertEquals(150, response.totalprice);
        assertEquals("Dinner", response.additionalneeds);
        assertEquals("John", response.firstname); 
    }

    @Test
    public void deleteBooking() {
        // Create a booking to delete
        BookingResponse bookingResponse = given()
            .contentType(ContentType.JSON)
            .body(BookingTestData.getDefaultBooking())
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        // Delete the booking
        given()
            .contentType(ContentType.JSON)
            .header("Cookie", "token=" + authToken)
            .delete("/booking/" + bookingResponse.bookingid)
            .then()
            .statusCode(201);

        // Verify deletion
        given()
            .get("/booking/" + bookingResponse.bookingid)
            .then()
            .statusCode(404);
    }
}

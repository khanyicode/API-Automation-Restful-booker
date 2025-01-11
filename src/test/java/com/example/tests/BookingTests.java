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

    @Test
    public void getBooking_InvalidId_ShouldReturn404() {
        given()
            .get("/booking/99999") // Non-existent booking ID
            .then()
            .statusCode(404); // Assert 404 not found
    }

    @Test
    public void createBooking_MissingFields_ShouldReturn400() {
        String invalidBookingJson = "{ \"firstname\": \"John\" }"; // Missing required fields like lastname and totalprice

        given()
            .contentType(ContentType.JSON)
            .body(invalidBookingJson)
            .post("/booking")
            .then()
            .statusCode(400); // Assert 400 bad request
    }

    @Test
    public void updateBooking_InvalidToken_ShouldReturn401() {
        BookingResponse bookingResponse = given()
            .contentType(ContentType.JSON)
            .body(BookingTestData.getDefaultBooking())
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        Booking updatedBooking = BookingTestData.getUpdatedBooking();

        given()
            .contentType(ContentType.JSON)
            .header("Cookie", "token=invalidtoken")
            .body(updatedBooking)
            .put("/booking/" + bookingResponse.bookingid)
            .then()
            .statusCode(401); // Assert 401 Unauthorized
    }

    @Test
    public void deleteBooking_MissingToken_ShouldReturn403() {
        BookingResponse bookingResponse = given()
            .contentType(ContentType.JSON)
            .body(BookingTestData.getDefaultBooking())
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        given()
            .contentType(ContentType.JSON)
            .delete("/booking/" + bookingResponse.bookingid)
            .then()
            .statusCode(403); // Assert 403 Forbidden
    }

    @Test
    public void createBooking_LargeTotalPrice_ShouldReturn200() {
        Booking booking = BookingTestData.getDefaultBooking();
        booking.totalprice = Integer.MAX_VALUE;

        BookingResponse response = given()
            .contentType(ContentType.JSON)
            .body(booking)
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        assertEquals(Integer.MAX_VALUE, response.booking.totalprice);
    }

    @Test
    public void createBooking_NegativeTotalPrice_ShouldReturn400() {
        Booking booking = BookingTestData.getDefaultBooking();
        booking.totalprice = -100;

        given()
            .contentType(ContentType.JSON)
            .body(booking)
            .post("/booking")
            .then()
            .statusCode(400); // Assert 400 Bad Request
    }

    @Test
    public void createBooking_LargeStringValues_ShouldReturn200() {
        String largeString = "a".repeat(500);

        Booking booking = BookingTestData.getDefaultBooking();
        booking.firstname = largeString;
        booking.lastname = largeString;

        BookingResponse response = given()
            .contentType(ContentType.JSON)
            .body(booking)
            .post("/booking")
            .then()
            .statusCode(200)
            .extract()
            .as(BookingResponse.class);

        assertEquals(largeString, response.booking.firstname);
        assertEquals(largeString, response.booking.lastname);
    }
}

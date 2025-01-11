package com.example.utils;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class AuthUtils {
    public static String getAuthToken() {
        return given()
            .contentType(ContentType.JSON)
            .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
            .post("/auth")
            .then()
            .statusCode(200)
            .extract()
            .path("token");
    }
}
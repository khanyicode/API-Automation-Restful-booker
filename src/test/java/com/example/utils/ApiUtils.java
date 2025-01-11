package com.example.utils;

import io.restassured.RestAssured;

public class ApiUtils {
    // Base URL for the Restful Booker API
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    // Method to get the base URL
    public static String getBaseUrl() {
        return BASE_URL;
    }
}

/**
 * Endpoints class:
 * - This class defines all API endpoint paths used in the project.
 */

 package com.restfulbooker.utils;

public class Endpoints {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String AUTH = "/auth";
    public static final String BOOKING = "/booking";
    public static final String PING = "/ping";
    
    // Helper method to get specific booking endpoint
    public static String booking(int id) {
        return BOOKING + "/" + id;
    }
}

/**
 * JsonUtils class:
 * - This utility class contains helper methods for JSON serialization and deserialization.
 * - Helps convert Java objects to JSON strings and vice versa using Jackson ObjectMapper.
 * - Simplifies handling of JSON data in test cases.
 * - Improves code reusability by centralizing JSON-related operations.
 */

 package com.restfulbooker.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    
    // These could be loaded from a properties file
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "password123";
    
    public static String getUsername() {
        return USERNAME;
    }
    
    public static String getPassword() {
        return PASSWORD;
    }
}
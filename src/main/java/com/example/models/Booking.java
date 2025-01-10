package com.example.models;

public class Booking {
    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public BookingDates bookingdates;
    public String additionalneeds;

    public static class BookingDates {
        public String checkin;
        public String checkout;
    }
}


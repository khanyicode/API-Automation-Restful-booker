package com.example.testdata;

import com.example.models.Booking;

public class BookingTestData {
    public static Booking getDefaultBooking() {
        Booking booking = new Booking();
        booking.firstname = "Mpho";
        booking.lastname = "Mfokeng";
        booking.totalprice = 100;
        booking.depositpaid = true;
        booking.bookingdates = new Booking.BookingDates();
        booking.bookingdates.checkin = "2025-01-15";
        booking.bookingdates.checkout = "2025-01-20";
        booking.additionalneeds = "Breakfast";
        return booking;
    }

    public static Booking getUpdatedBooking() {
        Booking booking = getDefaultBooking();
        booking.firstname = "Jane";
        booking.totalprice = 200;
        return booking;
    }

    public static String getPartialUpdateJson() {
        return "{ \"totalprice\": 150, \"additionalneeds\": \"Dinner\" }";
    }
}
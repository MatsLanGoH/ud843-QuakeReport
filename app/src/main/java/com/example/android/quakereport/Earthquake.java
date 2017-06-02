package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single Earthquake.
 * Each object has 3 properties: magnitude, location, and date.
 */

class Earthquake {
    // Magnitude of the earthquake
    private double mMagnitude;

    // Location of the earthquake
    private String mLocation;

    // Date of the earthquake
    // TODO: use correct data type instead of placeholder String
    private String mDate;

    /**
     * Create a new Earthquake object.
     *
     * @param location is the location of the earthquake
     * @param magnitude is the magnitude of the earthquake
     * @param date is the date the earthquake has occurred.
     * */

     Earthquake(String location, double magnitude, String date) {
        mLocation = location;
        mMagnitude = magnitude;
        mDate = date;
    }

    /**
     * Get the location of the earthquake
     * */
    String getLocation() {
        return mLocation;
    }

    /**
     * Get the magnitude of the earthquake
     * */
    double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the date of the earthquake
     * */
    String getDate() {
        return mDate;
    }
}

package com.example.android.quakereport;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

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
     * @param magnitude is the magnitude of the earthquake
     * @param location is the location of the earthquake
     * @param date is the date the earthquake has occured.
     * */

    public Earthquake(double magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    /**
     * Get the magnitude of the earthquake
     * */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the location of the earthquake
     * */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the date of the earthquake
     * */
    public String getDate() {
        return mDate;
    }
}

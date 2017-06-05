package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single Earthquake.
 * Each object has 3 properties: magnitude, location, and date.
 */

class Earthquake {
    // Magnitude of the earthquake
    private String mMagnitude;

    // Location of the earthquake
    private String mLocation;

    // Time of the earthquake
    private long mTimeInMilliseconds;

    /**
     * Create a new Earthquake object.
     *
     * @param magnitude is the magnitude of the earthquake
     * @param location is the location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from Epoch)
     *                           when the earthquake happened.
     * */

     Earthquake(String magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    /**
     * Get the magnitude of the earthquake
     * */
    String getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the location of the earthquake
     * */
    String getLocation() {
        return mLocation;
    }

    /**
     * Get the date of the earthquake
     * */
    long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}

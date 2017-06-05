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

    // Time of the earthquake
    private long mTimeInMilliseconds;

    // URL containing the address with details for the earthquake
    private String mUrl;

    /**
     * Create a new Earthquake object.
     *
     * @param magnitude is the magnitude of the earthquake
     * @param location is the location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from Epoch)
     *                          when the earthquake happened.
     * @param url is the address of the website with details for the earthquake.
     * */

     Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    /**
     * Get the magnitude of the earthquake
     * */
    double getMagnitude() {
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

    /**
     * Get the URL of the earthquake
     */
    String getUrl() {
        return mUrl;
    }
}

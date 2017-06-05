package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 */

class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = Earthquake.class.getSimpleName();
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * This is a custom constructor.
     * The context is used to inflate the layout file, and the list is the data
     * to be populated into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A list of Earthquake objects to display in a list.
     */
    EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Initialize the ArrayAdapter's internal storage
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView)
     *
     * @param position    The position in the list of data to be displayed
     * @param convertView The recycled view to be populated
     * @param parent      The parent ViewGroup that is used for inflation
     * @return the View for the position in the AdapterView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing View is being reused, otherwise inflate the View.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list.
        Earthquake currentEarthquake = getItem(position);

        if (currentEarthquake != null) {
            // Find the TextView in the list_item.xml with the ID magnitude.
            TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

            // Get magnitude for current earthquake
            String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());

            // Display the magnitude of the current earthquake in that TextView.
            magnitudeView.setText(formattedMagnitude);

            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude.
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

            // Set the color on the magnitude circle.
            magnitudeCircle.setColor(magnitudeColor);


            // Get the distance and location from the current earthquake.
            String originalLocation = currentEarthquake.getLocation();

            String primaryLocation;
            String locationOffset;

            // Split location string if a location offset is given.
            if (originalLocation.contains(LOCATION_SEPARATOR)) {
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                locationOffset = parts[0] + LOCATION_SEPARATOR;
                primaryLocation = parts[1];
            } else {
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = originalLocation;
            }

            // Find the TextView in the list_item.xml with the ID distance.
            TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
            // Display the distance of the current earthquake in that TextView.
            locationOffsetTextView.setText(locationOffset);

            // Find the TextView in the list_item.xml with the ID location.
            TextView locationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
            // Display the location of the current earthquake in that TextView.
            locationTextView.setText(primaryLocation);


            // Create a new Date object from the time in milliseconds of the earthquake
            Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

            // Find the TextView in the list_item.xml with the ID date.
            TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

            // Format the date string (i.e. "Mar 3, 1984")
            String formattedDate = formatDate(dateObject);

            // Display the date of the current Earthquake in that TextView
            dateTextView.setText(formattedDate);


            // Find the TextView in the list_item.xml with the ID time.
            TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

            // Format the date string (i.e. "3:00 PM")
            String formattedTime = formatTime(dateObject);

            // Display the time of the current Earthquake in that TextView
            timeTextView.setText(formattedTime);
        }
        return listItemView;
    }

    /**
     * Return the correct color value based on the current earthquake's magnitude value.
     */
    private int getMagnitudeColor(double magnitude) {

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            case 10:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string from a Date object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted time string from a Date object
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return dateFormat.format(dateObject);
    }
}

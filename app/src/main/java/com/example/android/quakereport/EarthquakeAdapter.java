package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 */

class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = Earthquake.class.getSimpleName();

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
            TextView nameTextView = (TextView) listItemView.findViewById(R.id.magnitude);

            // Get the magnitude from the current Earthquake object and
            // set this text on the magnitude TextView.
            nameTextView.setText(currentEarthquake.getMagnitude());

            // Find the TextView in the list_item.xml with the ID location.
            TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
            // Get the location from the current Earthquake object and
            // set this text on the location TextView.
            locationTextView.setText(currentEarthquake.getLocation());

            // Find the TextView in the list_item.xml with the ID date.
            TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
            // Get the date from the current Earthquake object and
            // set this text on the date TextView.
            dateTextView.setText(currentEarthquake.getDate());
        }
        return listItemView;
    }
}

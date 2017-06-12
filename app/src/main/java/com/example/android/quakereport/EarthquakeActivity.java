/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /* URL for the top 10 most recent earthquakes from the USGS database */
    public static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Call EarthquakeAsyncTask to download and display Earthquake data.
        new EarthquakeAsyncTask().execute(USGS_REQUEST_URL);

        // Create a fake list of earthquake locations.
        // TODO: Remove.
        final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
    }

    /**
     * Opens a website in the system browser
     * @param url the address of the site to open.
     * */
    private void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * AsyncTask to download Earthquake data in the background
     **/
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            // Perform the HTTP request for earthquake data and process the response.
            // TODO: Use the actual URL
            QueryUtils.fetchEarthquakeData(USGS_REQUEST_URL);

            return QueryUtils.extractEarthquakes();
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            updateUi(earthquakes);
        }
    }


    /**
     * Update the UI with the given earthquake information
     */
    private void updateUi(final ArrayList<Earthquake> earthquakes) {
        // Create an {@link EarthquakeAdapter}, whose data source is a list of
        // {@link Earthquake}s. The adapter knows how to create list item views
        // for each item in the list.
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthquakeAdapter);

        // Set a click listener to open web browser when the list item is clicked on.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the {@link Earthquake} object at the given position the user clicked on
                Earthquake earthquake = earthquakes.get(position);

                // Get the URL from the earthquake object
                String url = earthquake.getUrl();

                // Open the URL in the system browser
                openWebPage(url);

            }
        });
    }

}

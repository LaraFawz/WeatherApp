package com.example.larafawz.weatherapp.utilities;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/forecast?";

    private static final String API_KEY = "3c0803ed5f2ad5c47618ff1b7eb1b175";

    //  "https://api.openweathermap.org/data/2.5/forecast?zip=27701&appid=3c0803ed5f2ad5c47618ff1b7eb1b175"

    public static URL buildUrl(String locationQuery) throws MalformedURLException {

        URL dataURL = new URL(WEATHER_URL
                + "zip=" + locationQuery + "&appid="
                + API_KEY);

        Log.v(TAG, "Built URI " + dataURL);

        return dataURL;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}


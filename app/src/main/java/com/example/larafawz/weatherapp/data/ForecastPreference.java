package com.example.larafawz.weatherapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.example.larafawz.weatherapp.R;

public class ForecastPreference {

//    private static final String DEFAULT_WEATHER_LOCATION = "27701,USA";
//    private static final double[] DEFAULT_WEATHER_COORDINATES = {35.9967, -78.9019};

    /**
     * Returns the location currently set in Preferences. The default location this method
     * will return is "27701,USA", which is Durham, NorthCarolina.
     *
     * @param context Context used to get the SharedPreferences
     * @return Location The current user has set in SharedPreferences.
     *
     * For now, it is static. The user cannot edit this field
     */
    public static String getPreferredWeatherLocation(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String keyForLocation = context.getString(R.string.pref_location_key);
        String defaultLocation = context.getString(R.string.pref_location_default);
        return prefs.getString(keyForLocation, defaultLocation);
    }

    /**
     * Returns true if the user has selected metric temperature display.
     *
     * @param context Context used to get the SharedPreferences
     *
     * @return true If metric display should be used
     */
    public static boolean isMetric(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String keyForUnits = context.getString(R.string.pref_units_key);
        String defaultUnits = context.getString(R.string.pref_units_metric);
        String preferredUnits = prefs.getString(keyForUnits, defaultUnits);
        String metric = context.getString(R.string.pref_units_metric);
        boolean userPrefersMetric;
        if (metric.equals(preferredUnits)) {
            userPrefersMetric = true;
        } else {
            userPrefersMetric = false;
        }
        return userPrefersMetric;
    }

    /**
     * Returns true if the user has selected metric temperature display.
     *
     * @param context Context used to get the SharedPreferences
     *
     * @return true If metric display should be used
     */
    public static boolean isImperial(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String keyForUnits = context.getString(R.string.pref_units_key);
        String defaultUnits = context.getString(R.string.pref_units_imperial);
        String preferredUnits = prefs.getString(keyForUnits, defaultUnits);
        String imperial = context.getString(R.string.pref_units_imperial);
        boolean userPrefersMetric;
        if (imperial.equals(preferredUnits)) {
            userPrefersMetric = true;
        } else {
            userPrefersMetric = false;
        }
        return userPrefersMetric;
    }

}

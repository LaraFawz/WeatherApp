package com.example.larafawz.weatherapp.utilities;


import android.content.Context;
import android.util.Log;

import com.example.larafawz.weatherapp.data.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class OpenWeatherJsonUtils {

    /**
     * This method parses JSON from a web response and returns a list
     * describing the weather over various days from the forecast.
     *
     * @param forecastJsonStr JSON response from server
     * @return List describing weather data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static List<Forecast> getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        /* Weather information. Each day's forecast info is an element of the "list" */
        final String OWM_LIST = "list";


        final String OWM_TEMPERATURE = "temp";


        final String OWM_MAX = "temp_max";
        final String OWM_MIN = "temp_min";
        final String OWN_PRESSURE = "pressure";
        final String OWN_HUMIDITY = "humidity";

        final String OWM_WEATHER = "weather";
        final String OWM_WEATHER_ID = "id";
        final String OWM_MAIN = "main";
        final String OWM_DESCRIPTION = "description";
        final String OWM_ICON = "icon";

        final String OWN_TIME = "dt_txt";

        final String OWM_MESSAGE_CODE = "cod";

        // Create an empty ArrayList that we can start adding forecasts to
        List<Forecast> forecasts = new ArrayList<>();

        try {

            JSONObject forecastJson = new JSONObject(forecastJsonStr);

            /* Is there an error? */
            if (forecastJson.has(OWM_MESSAGE_CODE)) {
                int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);

                switch (errorCode) {
                    case HttpURLConnection.HTTP_OK:
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        /* Location invalid */
                        return null;
                    default:
                        /* Server probably down */
                        return null;
                }
            }

            JSONArray listArray = forecastJson.getJSONArray(OWM_LIST);



            for (int i = 0; i < listArray.length(); i++) {
                String date;
                String highAndLow;

                /* These are the values that will be collected */
                double high;
                double low;
                double temp;
                double pressure;
                double humidity;
                String description;
                //  String icon;
                int weatherId;


                /* Get the JSON object representing the day */
                JSONObject dayForecast = listArray.getJSONObject(i);

                JSONObject weatherObject =
                        dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);

                description = weatherObject.getString(OWM_DESCRIPTION);
                weatherId = weatherObject.getInt(OWM_WEATHER_ID);
                //     icon = weatherObject.getString(OWM_ICON);

                JSONObject mainObject = dayForecast.getJSONObject(OWM_MAIN);
                temp = mainObject.getDouble(OWM_TEMPERATURE);
                low = mainObject.getDouble(OWM_MIN);
                high = mainObject.getDouble(OWM_MAX);
                pressure = mainObject.getDouble(OWN_PRESSURE);
                humidity = mainObject.getDouble(OWN_HUMIDITY);

                String timeObject = dayForecast.getString(OWN_TIME);

                //       System.out.print("result" + time);

                Forecast forecast = new Forecast(temp, low, high, weatherId, pressure, humidity, description, timeObject);
                System.out.print("result" + forecast);
                forecasts.add(forecast);
            }

        } catch (JSONException e) {
            Log.e("JSONUtils", "Problem parsing the forecasts JSON results", e);
        }

        // Return the list of forecasts
        return forecasts;
    }
}
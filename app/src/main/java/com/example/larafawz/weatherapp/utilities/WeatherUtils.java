package com.example.larafawz.weatherapp.utilities;

import android.content.Context;
import android.util.Log;

import com.example.larafawz.weatherapp.R;
import com.example.larafawz.weatherapp.data.ForecastPreference;

/**
 * Contains conversion between Celsius and Fahrenheit,
 *  It also contains the mapping of weather condition
 * codes in OpenWeatherMap to strings.  These strings are contained
 */
public final class WeatherUtils {

    private static final String LOG_TAG = WeatherUtils.class.getSimpleName();


    /**
     * This method will convert a temperature from Kelvin to Celsius.
     *
     * @param temperatureInKelvin Temperature in degrees Kelvin(°K)
     *
     * @return Temperature in degrees Celsius (°C)
     */
    public static double kelvinToCelsius(double temperatureInKelvin){
        double temperatureInCelsius = (temperatureInKelvin -273.15) ;
        return temperatureInCelsius;
    }

    /**
     * This method will convert a temperature from Celsius to Fahrenheit.
     *
     * @param temperatureInCelsius Temperature in degrees Celsius(°C)
     *
     * @return Temperature in degrees Fahrenheit (°F)
     */
    private static double celsiusToFahrenheit(double temperatureInCelsius) {
        double temperatureInFahrenheit = (temperatureInCelsius * 1.8) + 32;
        return temperatureInFahrenheit;
    }

    /**
     * Temperature data is stored in Kelvin by our app. Depending on the user's preference,
     * the app may need to display the temperature in Fahrenheit. This method will perform that
     * temperature conversion if necessary.
     *
     */
    public static String formatTemperature(Context context, double temperature) {
        int temperatureFormatResourceId = R.string.format_temperature_kelvin;

        if (!ForecastPreference.isMetric(context)) {
            temperature = kelvinToFahrenheit(temperature);
            temperatureFormatResourceId = R.string.format_temperature_fahrenheit;
        }

        if (!ForecastPreference.isImperial(context)) {
            temperature = kelvinToCelsius(temperature);
            temperatureFormatResourceId = R.string.format_temperature_celsius;
        }

        return String.format(context.getString(temperatureFormatResourceId), temperature);
    }

    private static double kelvinToFahrenheit(double temperatureInKelvin) {
        double temperatureInFahrenheit = (1.8*(temperatureInKelvin - 273) + 32) ;
        return temperatureInFahrenheit;
    }

//    /**
//     * This method will format the temperatures to be displayed in the
//     * following form: "HIGH°C / LOW°C"
//     *
//     * @param context Android Context to access preferences and resources
//     * @param high    High temperature for a day in user's preferred units
//     * @param low     Low temperature for a day in user's preferred units
//     *
//     * @return String in the form: "HIGH°C / LOW°C"
//     */
//    public static String formatHighLows(Context context, double high, double low) {
//        long roundedHigh = Math.round(high);
//        long roundedLow = Math.round(low);
//
//        String formattedHigh = formatTemperature(context, roundedHigh);
//        String formattedLow = formatTemperature(context, roundedLow);
//
//        String highLowStr = formattedHigh + " / " + formattedLow;
//
//        return highLowStr;
//    }
//
//    public static String formatHighLows(Context context, double average) {
//        long roundedAve = Math.round(average);
//
//
//        String formattedAve = formatTemperature(context, roundedAve);
//
//
//        String highLowStr = formattedAve;
//
//        return highLowStr;
//    }


    /**
     * Helper method to provide the art resource id according to the weather condition id returned
     * by the OpenWeatherMap call.
     *
     * @param weatherId from OpenWeatherMap API response
     *
     */
    public static int getArtResourceForWeatherCondition(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.art_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.art_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.art_clear;
        }
        Log.e(LOG_TAG, "Unknown Weather: " + weatherId);
        return R.drawable.art_storm;
    }
}
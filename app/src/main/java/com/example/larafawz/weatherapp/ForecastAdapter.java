package com.example.larafawz.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.larafawz.weatherapp.data.Forecast;
import com.example.larafawz.weatherapp.utilities.WeatherUtils;

import java.text.NumberFormat;
import java.util.List;

/**
 * {@link ForecastAdapter} exposes a list of weather forecasts to a
 * {@link android.support.v7.widget.RecyclerView}
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    private List<Forecast> mWeatherData;
    public TextView forecastIcon;
    public  TextView forecastDescription;
    public  TextView tempDisplay;
    public  TextView timeTextView;
    public  TextView dateTextView;

    final private ForecastAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface ForecastAdapterOnClickHandler {
        void onClick(Forecast weatherForDay);
    }

    /**
     * Creates a ForecastAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public ForecastAdapter(ForecastAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    /**
     * Cache of the children views for a forecast list item.
     */
    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ForecastAdapterViewHolder(View view) {
            super(view);

            forecastIcon = (TextView) view.findViewById(R.id.forecast_icon);
            forecastDescription = (TextView)view.findViewById(R.id.description);
            tempDisplay = (TextView)view.findViewById(R.id.primary_temp);
            timeTextView = (TextView)view.findViewById(R.id.timeTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);

            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Forecast weatherForDay = mWeatherData.get(adapterPosition);
            mClickHandler.onClick(weatherForDay);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.forecast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ForecastAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param forecastAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder forecastAdapterViewHolder, int position) {
        Forecast weatherForThisDay = mWeatherData.get(position);

        String description = weatherForThisDay.getDescription();
        forecastDescription.setText(description);

        int weatherId = weatherForThisDay.getWeatherId();
        forecastIcon.setBackgroundResource(WeatherUtils.getArtResourceForWeatherCondition(weatherId));

        Double temp = weatherForThisDay.getTemp();
        NumberFormat nm = NumberFormat.getNumberInstance();
        tempDisplay.setText(nm.format(temp));


        String time = weatherForThisDay.getTime();
        String[] splited = time.split("\\s+");

        dateTextView.setText(splited[0]);
        timeTextView.setText(splited[1]);


    }

    /**
     * This method simply returns the number of items to display.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mWeatherData) return 0;
        return mWeatherData.size();
    }

    /**
     * This method is used to set the weather forecast on a ForecastAdapter
     *
     * @param weatherData The new weather data to be displayed.
     */
    public void setWeatherData(List<Forecast> weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }
}
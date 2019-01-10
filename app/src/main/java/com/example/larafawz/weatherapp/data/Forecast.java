package com.example.larafawz.weatherapp.data;

public class Forecast {
    private Double temp;

    private Double low;

    private Double high;

    private Integer weatherId;

    private Double pressure;

    private Double humidity;

    private String description;

    private String time;

    public Forecast(Double temp, Double low, Double high, Integer weatherId, Double pressure, Double humidity, String description, String time){
        this.temp = temp;
        this.low = low;
        this.high = high;
        this.weatherId = weatherId;
        this.pressure = pressure;
        this.humidity= humidity;
        this.description = description;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getLow() {
        return low;
    }

    public Double getHigh() {
        return high;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

}

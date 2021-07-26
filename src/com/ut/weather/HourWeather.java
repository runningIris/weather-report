package com.ut.weather;

public class HourWeather {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String windDirection;
    private String windPower;
    private String weather;
    private String temperature;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String format() {
        return String.format("%s-%s-%s %s:00 %s℃ %s %s\n", year, month, day, hour, temperature, windPower, windDirection);
    }

    @Override
    public String toString() {
        return "HourWeather {\n" +
                "  year='" + year + "', \n" +
                "  month='" + month + "', \n" +
                "  day='" + day + "', \n" +
                "  hour='" + hour + "', \n" +
                "  windDirection='" + windDirection + "', \n" +
                "  windPower='" + windPower + "', \n" +
                "  weather='" + weather + "', \n" +
                "  temperature='" + temperature + "'\n" +
                "}";
    }
}

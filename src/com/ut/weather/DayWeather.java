package com.ut.weather;

public class DayWeather {
    private String year;
    private String month;
    private String day;
    private String dayAirTemperature;
    private String nightAirTemperature;
    private String dayWeather;
    private String nightWeather;
    private String dayWindPower;
    private String nightWindPower;

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

    public String getDayAirTemperature() {
        return dayAirTemperature;
    }

    public void setDayAirTemperature(String dayAirTemperature) {
        this.dayAirTemperature = dayAirTemperature;
    }

    public String getNightAirTemperature() {
        return nightAirTemperature;
    }

    public void setNightAirTemperature(String nightAirTemperature) {
        this.nightAirTemperature = nightAirTemperature;
    }

    public String getDayWeather() {
        return dayWeather;
    }

    public void setDayWeather(String dayWeather) {
        this.dayWeather = dayWeather;
    }

    public String getNightWeather() {
        return nightWeather;
    }

    public void setNightWeather(String nightWeather) {
        this.nightWeather = nightWeather;
    }

    public String getDayWindPower() {
        return dayWindPower;
    }

    public void setDayWindPower(String dayWindPower) {
        this.dayWindPower = dayWindPower;
    }

    public String getNightWindPower() {
        return nightWindPower;
    }

    public void setNightWindPower(String nightWindPower) {
        this.nightWindPower = nightWindPower;
    }

    @Override
    public String toString() {
        return "DayWeather {\n" +
                "  year='" + year + "', \n" +
                "  month='" + month + "', \n" +
                "  day='" + day + "', \n" +
                "  dayAirTemperature='" + dayAirTemperature + "', \n" +
                "  nightAirTemperature='" + nightAirTemperature + "', \n" +
                "  dayWeather='" + dayWeather + "', \n" +
                "  nightWeather='" + nightWeather + "', \n" +
                "  dayWindPower='" + dayWindPower + "', \n" +
                "  nightWindPower='" + nightWindPower + "', \n" +
                "}";
    }
}

package com.ut.weather;

import com.ut.weather.impl.WeatherUtilsImpl;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("check recent weather forecast!");
        System.out.println("type 1, check weather forecast in the next 24 hours.");
        System.out.println("type 2, check weather forecast in the next 3 days.");
        System.out.println("type 3, check weather forecast in the next 7 days.");
        System.out.print("Please type your chose: ");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        System.out.println("Type " + type + " has been chosen.");

        System.out.print("Please type in your city: ");
        String city = scanner.next();

        WeatherUtils weatherUtils = new WeatherUtilsImpl();
        weatherUtils.setAppCode("1d067bd0646f4d6f905599f2d474837e");


        if (type == 1) {
            System.out.println("Weather in the next 24 hours is as following: ");
            List<HourWeather> weathersIn24h = weatherUtils.w24h(city);
            StringBuilder weatherReport = new StringBuilder();
            for (int i = 0; i < weathersIn24h.size(); i++) {
                weatherReport.append(weathersIn24h.get(i).format());
            }
            System.out.println(weatherReport.toString());
            return;
        }

        if (type == 2) {
            System.out.println("Weather in the next 3 days is as following: ");
            List<DayWeather> weathersIn3days = weatherUtils.w3d(city);
            StringBuilder weatherReport = new StringBuilder();
            for (int i = 0; i < weathersIn3days.size(); i++) {
                weatherReport.append(weathersIn3days.get(i).format());
            }
            System.out.println(weatherReport);
            return;
        }

        if (type == 3) {
            System.out.println("Weather in the next 7 days is as following: ");
            List<DayWeather> weathersIn7days = weatherUtils.w7d(city);
            StringBuilder weatherReport = new StringBuilder();
            for (int i = 0; i < weathersIn7days.size(); i++) {
                weatherReport.append(weathersIn7days.get(i).format());
            }
            System.out.println(weatherReport);
            return;
        }

        System.out.println("invalid type");
    }
}

package com.ut.weather.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ut.weather.DayWeather;
import com.ut.weather.HourWeather;
import com.ut.weather.WeatherUtils;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherUtilsImpl implements WeatherUtils {
    private String appCode;

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    private <T> T getValue(Object map, String path, Class<T> clazz) {
        try {
            OgnlContext context = new OgnlContext();
            context.setRoot(map);
            T value = (T) Ognl.getValue(path, context, context.getRoot());
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map fetchData (String appCode, String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .header("Authorization", "APPCODE " + appCode)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            // 获取服务器返回数据
            String resBody = response.body().string();
            Map result = gson.fromJson(resBody, new TypeToken<Map>() {}.getType());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private List<DayWeather> wd (String area, int days) {
        List<DayWeather> resultList = new ArrayList<DayWeather>();
        try {
            String url = "http://ali-weather.showapi.com/area-to-weather?needMoreDay=1&area=" + area;
            Map result = WeatherUtilsImpl.fetchData(appCode, url);

            // 包装为对象集合
            for (int i = 1; i <= days; i++) {
                Map<String, String> day = this.getValue(result, "showapi_res_body.f" + i, Map.class);
                if (day == null) {
                    return new ArrayList<DayWeather>();
                }
                DayWeather dayWeather = new DayWeather();
                dayWeather.setYear(day.get("day").substring(0, 4));
                dayWeather.setMonth(day.get("day").substring(4, 6));
                dayWeather.setDay(day.get("day").substring(6, 8));
                dayWeather.setDayAirTemperature(day.get("day_air_temperature"));
                dayWeather.setNightAirTemperature(day.get("night_air_temperature"));
                dayWeather.setDayWeather(day.get("day_weather"));
                dayWeather.setNightWeather(day.get("night_weather"));
                dayWeather.setDayWindPower(day.get("day_wind_power"));
                dayWeather.setNightWindPower(day.get("night_wind_power"));
                resultList.add(dayWeather);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public List<DayWeather> w3d(String area) {
        return wd(area, 3);
    }

    public List<DayWeather> w7d(String area) {
        return wd(area, 7);
    }

    public List<HourWeather> w24h(String area) {
        List<HourWeather> resultList = new ArrayList<HourWeather>();
        try {
            String url = "http://ali-weather.showapi.com/hour24?area=" + area;
            Map result = WeatherUtilsImpl.fetchData(appCode, url);
            // 包装为对象集合
            List<Map<String, String>> hourList = this.getValue(result, "showapi_res_body.hourList", ArrayList.class);
            // 如果没有找到对应地区天气数据，返回空的List列表
            if (hourList == null || hourList.size() == 0) {
                return new ArrayList<HourWeather>();
            }
            for (Map<String, String> hour: hourList) {
                HourWeather hourWeather = new HourWeather();
                hourWeather.setYear(hour.get("time").substring(0, 4));
                hourWeather.setMonth(hour.get("time").substring(4, 6));
                hourWeather.setDay(hour.get("time").substring(6, 8));
                hourWeather.setHour(hour.get("time").substring(8, 10));
                hourWeather.setWindDirection(hour.get("wind_direction"));
                hourWeather.setWindPower(hour.get("wind_power"));
                hourWeather.setWindPower(hour.get("weather"));
                hourWeather.setTemperature(hour.get("temperature"));
                resultList.add(hourWeather);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public static void main(String[] args) throws IOException, OgnlException {
        WeatherUtils weatherUtils = new WeatherUtilsImpl();
        weatherUtils.setAppCode("1d067bd0646f4d6f905599f2d474837e");
        List<HourWeather> list = weatherUtils.w24h("杭州");
        List<DayWeather> list1 = weatherUtils.w3d("杭州");
        List<DayWeather> list2 = weatherUtils.w7d("杭州");
        System.out.println(list);
        System.out.println(list1);
        System.out.println(list2);
    }
}

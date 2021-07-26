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
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherUtilsImpl implements WeatherUtils {
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

    private static Map fetchData (String appCode, String area) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url("http://ali-weather.showapi.com/hour24?area=" + area)
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

    public List<HourWeather> w24h(String appCode, String area) {
        List<HourWeather> resultList = new ArrayList<HourWeather>();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url("http://ali-weather.showapi.com/hour24?area=" + area)
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

    private List<DayWeather> wd (String appCode, String area, int days) {
        List<DayWeather> resultList = new ArrayList<DayWeather>();

    }

    public List<DayWeather> w3d(String appCode, String area) {

    }

    public List<DayWeather> w7d(String appCode, String area) {

    }
}

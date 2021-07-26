package com.ut.weather;

import java.util.List;

public interface WeatherUtils {
    /**
     * 查询未来24小时天气预报
     * @param appCode 阿里云授权码
     * @param area 查询区域
     * @return HourWeather对象列表，按顺序每小时对应一个HourWeather对象。
     *         如果列表数量=0则代表未查询到对应区域天气数据
     */
    public List<HourWeather> w24h(String appCode, String area);
    /**
     * 查询未来3天天气预报
     * @param appCode 阿里云授权码
     * @param area 查询区域
     * @return DayWeather对象列表，按顺序每天对应一个DayWeather对象
     *         如果列表数量=0则代表未查询到对应区域天气数据
     */
    public List<DayWeather> w3d(String appCode, String area);
    /**
     * 查询未来7天天气预报
     * @param appCode 阿里云授权码
     * @param area 查询区域
     * @return DayWeather对象列表，按顺序每天对应一个DayWeather对象
     *         如果列表数量=0则代表未查询到对应区域天气数据
     */
    public List<DayWeather> w7d(String appCode, String area);
}

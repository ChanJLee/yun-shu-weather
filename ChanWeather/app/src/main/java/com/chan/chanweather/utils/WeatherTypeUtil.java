package com.chan.chanweather.utils;

import com.chan.chanweather.R;

/**
 * Created by chan on 16/3/28.
 */
public class WeatherTypeUtil {

    public static int weatherType2Reference(String type) {

        if ("晴".equals(type)) {
            return R.drawable.weather_sun_day;
        }

        if ("少云".equals(type)) {
            return R.drawable.weather_cloudy_2;
        }

        if("多云".equals(type)) {
            return R.drawable.weather_cloud_day;
        }

        if ("阴".equals(type)) {
            return R.drawable.weather_cloudy;
        }

        if ("晴间多云".equals(type)) {
            return R.drawable.weather_cloud_day;
        }

        if (type.contains("风")) {
            if (type.contains("有") || type.contains("微") ||
                    type.contains("和") || type.contains("清")) {
                return R.drawable.weather_wind_mini;
            } else {
                return R.drawable.weather_wind;
            }
        }

        if ("平静".equals(type)) {
            return R.drawable.weather_wind_mini;
        }

        if (type.contains("雨")) {
            if (type.contains("小") || type.contains("中") ||
                    type.contains("大") || type.contains("阵")) {
                if (type.contains("雷")) {
                    return R.drawable.weather_thunder;
                }
                return R.drawable.weather_rain;
            } else {
                return R.drawable.weather_thunderstorm;
            }
        }

        if (type.contains("雪")) {
            if (type.contains("中") || type.contains("大") ||
                    type.contains("小")) {
                return R.drawable.weather_snow;
            } else {
                return R.drawable.weather_hail;
            }
        }

        if ("雾霾".contains(type)) {
            return R.drawable.weather_fog;
        }

        return R.drawable.unknow;
    }
}

package com.chan.chanweather.mock;

import android.content.Context;

import com.chan.chanweather.R;
import com.chan.chanweather.bean.WeatherResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chan on 16/3/28.
 */
public class MainMock {

    /**
     * 用于测试各种天气
     * @param context
     * @return
     * @throws IOException
     */
    public static WeatherResponse mockWeather(Context context) throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.weather);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String content = null;

        while ((content = bufferedReader.readLine()) != null) {
            stringBuilder.append(content);
        }
        return WeatherResponse.objectFromData(stringBuilder.toString());
    }
}

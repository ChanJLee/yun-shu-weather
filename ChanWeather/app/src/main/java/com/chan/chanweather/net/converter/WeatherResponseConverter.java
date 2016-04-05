package com.chan.chanweather.net.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by chan on 16/3/25.
 */
public class WeatherResponseConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    public WeatherResponseConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override public T convert(ResponseBody value) throws IOException {

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        final StringBuilder stringBuilder = new StringBuilder();

        try {
            inputStreamReader = (InputStreamReader) value.charStream();
            bufferedReader = new BufferedReader(inputStreamReader);

            String content = null;
            while ((content = bufferedReader.readLine()) != null) {
                stringBuilder.append(content);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
        }

        final String json = stringBuilder.toString();

        try {
            return gson.fromJson(json.replace("HeWeather data service 3.0", "decor"), type);
        }catch (Exception e) {
            return null;
        }
    }
}

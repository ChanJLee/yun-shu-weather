package com.chan.chanweather.net.factory;

import com.chan.chanweather.net.converter.WeatherRequestConverter;
import com.chan.chanweather.net.converter.WeatherResponseConverter;
import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by chan on 16/3/25.
 */
public class WeatherConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static WeatherConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static WeatherConverterFactory create(Gson gson) {
        return new WeatherConverterFactory(gson);
    }

    private final Gson gson;

    private WeatherConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new WeatherResponseConverter<>(gson, type);
    }

    @Override public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new WeatherRequestConverter<>(gson, type);
    }
}

package com.chan.chanweather.model.rx;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.net.api.WeatherApi;
import com.chan.chanweather.net.factory.WeatherConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by chan on 16/3/25.
 */
public class RxWeather {
    private WeatherApi m_weatherApi;

    public Observable<WeatherResponse> getWeatherAsync(String key, String cityId) {
        return getWeatherSync(key, cityId).subscribeOn(Schedulers.newThread());
    }

    public Observable<WeatherResponse> getWeatherSync(String key, String cityId) {
        WeatherApi weatherApi = getWeatherApi();
        return weatherApi.query(key, cityId);
    }

    public WeatherApi getWeatherApi() {

        if(m_weatherApi == null) {

            Gson gson = new GsonBuilder().create();
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(WeatherConverterFactory.create(gson))
                    .baseUrl(BuildConfig.BASE_URI)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            m_weatherApi = retrofit.create(WeatherApi.class);
        }

        return m_weatherApi;
    }
}

package com.chan.chanweather.model.rx;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.bean.WeatherTypeResponse;
import com.chan.chanweather.net.api.WeatherTypeApi;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by chan on 16/3/26.
 */
public class RxWeatherType {
    private WeatherTypeApi m_weatherTypeApi;

    public Observable<WeatherTypeResponse> getWeatherAsync(String key, String type) {
        return getWeatherTypeSync(key, type).subscribeOn(Schedulers.newThread());
    }

    public Observable<WeatherTypeResponse> getWeatherTypeSync(String key, String type) {
        WeatherTypeApi weatherTypeApi = getWeatherTypeApi();
        return weatherTypeApi.getWeatherType(key, type);
    }

    private WeatherTypeApi getWeatherTypeApi() {
        if(m_weatherTypeApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URI)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            m_weatherTypeApi = retrofit.create(WeatherTypeApi.class);
        }
        return m_weatherTypeApi;
    }
}

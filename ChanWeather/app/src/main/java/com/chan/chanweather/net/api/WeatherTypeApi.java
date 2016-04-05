package com.chan.chanweather.net.api;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.WeatherTypeResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by chan on 16/3/26.
 */
public interface WeatherTypeApi {

    @GET(BuildConfig.PATH_WEATHER_TYPE)
    Observable<WeatherTypeResponse> getWeatherType(@Query(BuildConfig.QUERY_KEY) String key,
                                                  @Query(BuildConfig.QUERY_SEARCH) String type);
}

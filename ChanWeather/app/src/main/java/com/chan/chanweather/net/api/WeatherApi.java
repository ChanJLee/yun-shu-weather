package com.chan.chanweather.net.api;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.WeatherResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by chan on 16/3/25.
 */
public interface WeatherApi {

    @GET(BuildConfig.PATH_WEATHER)
    Observable<WeatherResponse> query(@Query(BuildConfig.QUERY_KEY) String key, @Query(BuildConfig.QUERY_CITY_ID) String cityId);
}

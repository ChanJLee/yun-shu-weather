package com.chan.chanweather.net.api;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.CityResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by chan on 16/3/26.
 */
public interface CityApi {
    @GET(BuildConfig.PATH_CITY)
    Observable<CityResponse> getCities(@Query(BuildConfig.QUERY_KEY) String key,
                                       @Query(BuildConfig.QUERY_SEARCH) String wide);
}

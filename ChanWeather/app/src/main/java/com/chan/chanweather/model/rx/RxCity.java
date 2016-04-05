package com.chan.chanweather.model.rx;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.CityResponse;
import com.chan.chanweather.net.api.CityApi;
import com.google.gson.Gson;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by chan on 16/3/26.
 */
public class RxCity {
    private CityApi m_cityApi;

    public Observable<CityResponse> getCityAsync(String key, String wide) {
        return getCitySync(key, wide).subscribeOn(Schedulers.newThread());
    }

    public Observable<CityResponse> getCitySync(String key, String wide) {
        CityApi api = getCityApi();
        return api.getCities(key, wide);
    }

    private CityApi getCityApi() {
        if(m_cityApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URI)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            m_cityApi = retrofit.create(CityApi.class);
        }
        return m_cityApi;
    }
}

package com.chan.chanweather.presenter.interfaces;

/**
 * Created by chan on 16/3/29.
 */
public interface IWeatherServicePresenter extends IPresenter {
    void requestWeather(String cityId, String key, String baseUri);
}

package com.chan.chanweather.presenter.interfaces;

/**
 * Created by chan on 16/4/3.
 */
public interface IWeatherForecastPresenter extends IPresenter {
    void initTheme();
    void getWeather();
    void getTemperature();
    void getHumidity();
}

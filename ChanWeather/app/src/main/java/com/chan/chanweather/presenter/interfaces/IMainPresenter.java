package com.chan.chanweather.presenter.interfaces;

import com.chan.chanweather.bean.WeatherResponse;

/**
 * Created by chan on 16/3/25.
 */
public interface IMainPresenter extends IPresenter {
    void fetchWeatherInfo();
    void loadThemeResource();
    void scheduleInflateSetting();
    void refresh();
    void handleResponse(WeatherResponse weatherResponse);
    void feedback();
    void getDevResponse();
}

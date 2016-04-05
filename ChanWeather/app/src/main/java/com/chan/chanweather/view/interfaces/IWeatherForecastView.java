package com.chan.chanweather.view.interfaces;

import android.net.Uri;

import com.chan.chanweather.bean.WeatherHolder;
import com.chan.chanweather.bean.HumidityHolder;
import com.chan.chanweather.bean.TemperatureHolder;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;

import java.util.List;

/**
 * Created by chan on 16/4/3.
 */
public interface IWeatherForecastView extends IView {
    void setBackground(Uri uri);
    String getExtraJson();
    void showWeatherItems(List<WeatherHolder> forecastHolders);
    void showTemperature(BarData barData);
    void setSunsetTheme();
    void setSunriseTheme();
    void showHumidity(LineData lineData);
}

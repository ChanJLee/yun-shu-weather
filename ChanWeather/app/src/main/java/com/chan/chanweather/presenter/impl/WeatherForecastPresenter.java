package com.chan.chanweather.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

import com.chan.chanweather.R;
import com.chan.chanweather.bean.HumidityHolder;
import com.chan.chanweather.bean.WeatherHolder;
import com.chan.chanweather.bean.TemperatureHolder;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.presenter.interfaces.IWeatherForecastPresenter;
import com.chan.chanweather.utils.TimeUtils;
import com.chan.chanweather.utils.WeatherTypeUtil;
import com.chan.chanweather.view.interfaces.IView;
import com.chan.chanweather.view.interfaces.IWeatherForecastView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by chan on 16/4/3.
 */
public class WeatherForecastPresenter implements IWeatherForecastPresenter {

    private Context m_context;
    private IWeatherForecastView m_iWeatherForecastView;
    private WeatherResponse m_weatherResponse;

    @Inject
    public WeatherForecastPresenter(Activity context) {
        m_context = context;
    }

    @Override
    public void attachView(IView iView) {
        m_iWeatherForecastView = (IWeatherForecastView) iView;
        m_weatherResponse = WeatherResponse.objectFromData(
                m_iWeatherForecastView.getExtraJson());
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initTheme() {
        if (TimeUtils.isDay()) {
            m_iWeatherForecastView.setSunriseTheme();
        } else {
            m_iWeatherForecastView.setSunsetTheme();
        }
    }

    @Override
    public void getWeather() {

        WeatherResponse.DecorEntity decorEntity = m_weatherResponse.getDecor().get(0);
        List<WeatherResponse.DecorEntity.DailyForecastEntity> list = decorEntity.getDailyForecast();
        List<WeatherHolder> forecastHolders = new ArrayList<>();

        final boolean isDay = TimeUtils.isDay();

        final int size = list.size();
        for (int i = 0; i < size; ++i) {
            WeatherResponse.DecorEntity.DailyForecastEntity dailyForecastEntity = list.get(i);
            WeatherResponse.DecorEntity.DailyForecastEntity.CondEntity condEntity = dailyForecastEntity.getCond();
            final String description = isDay ? condEntity.getTxtD() : condEntity.getTxtN();
            final int what = WeatherTypeUtil.weatherType2Reference(description);
            forecastHolders.add(new WeatherHolder(Uri.parse("res:///" + what), description));
        }

        m_iWeatherForecastView.showWeatherItems(forecastHolders);
    }

    @Override
    public void getTemperature() {
        marshallTemperature(extraTemperature(m_weatherResponse));
    }

    @Override
    public void getHumidity() {
        marshallHumidity(extraHumidity(m_weatherResponse));
    }

    private static List<TemperatureHolder> extraTemperature(WeatherResponse weatherResponse) {
        WeatherResponse.DecorEntity decorEntity = weatherResponse.getDecor().get(0);
        List<WeatherResponse.DecorEntity.DailyForecastEntity> dailyForecastEntities =
                decorEntity.getDailyForecast();
        List<TemperatureHolder> holders = new ArrayList<>();
        final int size = dailyForecastEntities.size();
        for (int i = 0; i < size; ++i) {
            WeatherResponse.DecorEntity.DailyForecastEntity dailyForecastEntity = dailyForecastEntities.get(i);
            WeatherResponse.DecorEntity.DailyForecastEntity.TmpEntity entity = dailyForecastEntity.getTmp();
            holders.add(new TemperatureHolder(
                    Float.parseFloat(entity.getMax()),
                    Float.parseFloat(entity.getMin()),
                    dailyForecastEntity.getDate().substring(5)));
        }
        return holders;
    }

    private static List<HumidityHolder> extraHumidity(WeatherResponse weatherResponse) {
        WeatherResponse.DecorEntity decorEntity = weatherResponse.getDecor().get(0);
        List<WeatherResponse.DecorEntity.DailyForecastEntity> dailyForecastEntities =
                decorEntity.getDailyForecast();
        List<HumidityHolder> holders = new ArrayList<>();
        final int size = dailyForecastEntities.size();
        for (int i = 0; i < size; ++i) {
            WeatherResponse.DecorEntity.DailyForecastEntity dailyForecastEntity
                    = dailyForecastEntities.get(i);
            holders.add(new HumidityHolder(dailyForecastEntity.getDate().substring(5),
                    Float.parseFloat(dailyForecastEntity.getHum())));
        }
        return holders;
    }

    @SuppressWarnings("deprecation")
    private void marshallTemperature(List<TemperatureHolder> holders) {

        final int count = holders.size();

        List<String> xVals = new ArrayList<String>();
        List<BarEntry> min = new ArrayList<>();
        List<BarEntry> max = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            TemperatureHolder holder = holders.get(i);
            xVals.add(holder.getDate());
            min.add(new BarEntry(holder.getMin(), i));
            max.add(new BarEntry(holder.getMax(), i));
        }

        BarDataSet s = new BarDataSet(max, "最高气温");
        s.setColor(m_context.getResources().getColor(R.color.max));

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(s);

        s = new BarDataSet(min, "最低气温");
        s.setColor(m_context.getResources().getColor(R.color.min));
        dataSets.add(s);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(5f);

       m_iWeatherForecastView.showTemperature(data);
    }

    @SuppressWarnings("deprecation")
    private void marshallHumidity(List<HumidityHolder> humidity) {

        final int count = humidity.size();

        List<String> xVals = new ArrayList<>();
        List<Entry> humidityList = new ArrayList<>();

        for(int i = 0; i < count; ++i) {
            HumidityHolder humidityHolder = humidity.get(i);
            xVals.add(humidityHolder.getDate());
            humidityList.add(new Entry(humidityHolder.getHumidity(), i));
        }

        LineDataSet lineDataSet = new LineDataSet(humidityList, "湿度");
        lineDataSet.setDrawCubic(true);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighLightColor(m_context.getResources().getColor(R.color.max));
        lineDataSet.setColor(Color.WHITE);

        lineDataSet.setFillColor(m_context.getResources().getColor(R.color.fill));
        lineDataSet.setFillAlpha(100);

        LineData data = new LineData(xVals, lineDataSet);
        data.setValueTextSize(5f);
        m_iWeatherForecastView.showHumidity(data);
    }
}

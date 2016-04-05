package com.chan.chanweather.presenter.impl;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.R;
import com.chan.chanweather.adapter.MainAdapter;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.model.sp.CityConfig;
import com.chan.chanweather.presenter.interfaces.IMainPresenter;
import com.chan.chanweather.services.WeatherService;
import com.chan.chanweather.utils.TimeUtils;
import com.chan.chanweather.view.impl.SettingActivity;
import com.chan.chanweather.view.impl.WeatherForecastActivity;
import com.chan.chanweather.view.interfaces.IMainView;
import com.chan.chanweather.view.interfaces.IView;
import com.google.gson.Gson;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by chan on 16/3/25.
 */
public class MainActivityPresenter implements IMainPresenter {
    private Context m_context;
    private IMainView m_iMainView;
    private MainAdapter m_mainAdapter;
    private WeatherResponse m_weatherResponse;
    private CityConfig m_cityConfig;

    @Inject
    public MainActivityPresenter(@ContextLife("activity") Context context, CityConfig cityConfig) {
        m_context = context;
        m_cityConfig = cityConfig;
    }

    @Override
    public void attachView(IView iView) {
        m_iMainView = (IMainView) iView;
        EventBus.getDefault().register(m_context);
    }

    @Override
    public void detachView() {
        EventBus.getDefault().unregister(m_context);
    }

    @Override
    public void fetchWeatherInfo() {
        WeatherService.requestWeather(m_context, m_cityConfig.getCityId(),
                BuildConfig.KEY, BuildConfig.BASE_URI);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void loadThemeResource() {
        if (TimeUtils.isDay()) {
            m_iMainView.setBackground(Uri.parse("res:///" + R.mipmap.sunrise));
            m_iMainView.setHead(Uri.parse("res:///" + R.mipmap.header_back_day));
            m_iMainView.setPrimaryColor(m_context.getResources().getColor(R.color.colorSunrise));
            m_iMainView.showSunriseTheme();
        } else {
            m_iMainView.setBackground(Uri.parse("res:///" + R.mipmap.sunset));
            m_iMainView.setHead(Uri.parse("res:///" + R.mipmap.header_back_night));
            m_iMainView.setPrimaryColor(m_context.getResources().getColor(R.color.colorSunset));
            m_iMainView.showSunsetTheme();
        }
    }

    @Override
    public void scheduleInflateSetting() {
        SettingActivity.invokeSetting(m_context);
    }

    @Override
    public void refresh() {
        fetchWeatherInfo();
    }

    @Override
    public void handleResponse(WeatherResponse weatherResponse) {

        if(weatherResponse == null) {
            m_iMainView.showSimpleMessage("读取天气信息失败");
            return;
        }

        if(m_mainAdapter == null) {
            m_weatherResponse = weatherResponse;
            m_mainAdapter = new MainAdapter(m_context, m_weatherResponse);
            m_mainAdapter.setOnWeatherItemClicked(new MainAdapter.OnWeatherItemClicked() {
                @Override
                public void onWeatherItemClicked(View view) {
                    invokeForecast();
                }
            });
            m_iMainView.showResponse(m_mainAdapter);
        } else {
            m_weatherResponse.setDecor(weatherResponse.getDecor());
            m_iMainView.notifyChanged();
        }

        final String title = weatherResponse.getDecor()
                .get(0).getBasic().getCity();
        m_iMainView.setTitle(title);
        m_iMainView.showSimpleMessage("加载成功");
    }

    @Override
    public void feedback() {
        FeedbackAgent feedbackAgent = new FeedbackAgent(m_context);
        feedbackAgent.openFeedbackPush();
        feedbackAgent.startFeedbackActivity2();
    }

    @Override
    public void getDevResponse() {
        PushAgent.getInstance(m_context).enable();
    }

    public void invokeForecast() {
        if(m_weatherResponse != null) {
            WeatherForecastActivity.invokeForecast(m_context, new Gson().toJson(m_weatherResponse));
        }
    }
}

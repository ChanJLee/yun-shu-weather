package com.chan.chanweather.injector.component;

import android.app.Activity;
import android.content.Context;

import com.chan.chanweather.MainActivity;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.annotation.PerActivity;
import com.chan.chanweather.injector.module.ActivityModule;
import com.chan.chanweather.model.config.NetworkConfig;
import com.chan.chanweather.model.config.PreferencesConfig;
import com.chan.chanweather.model.rx.RxCity;
import com.chan.chanweather.model.rx.RxReadSettingItems;
import com.chan.chanweather.model.rx.RxWeather;
import com.chan.chanweather.model.rx.RxWeatherType;
import com.chan.chanweather.model.sp.CityConfig;
import com.chan.chanweather.view.impl.AboutActivity;
import com.chan.chanweather.view.impl.InitActivity;
import com.chan.chanweather.view.impl.SettingActivity;
import com.chan.chanweather.view.impl.SplashActivity;
import com.chan.chanweather.view.impl.WeatherForecastActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import dagger.Component;

/**
 * Created by chan on 16/2/27.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("application")
    Context getApplicationContext();

    @ContextLife("activity")
    Context getActivityContext();

    Activity getActivity();

    SystemBarTintManager getSystemBarTintManager();

    PreferencesConfig getPreferencesConfig();
    NetworkConfig getNetworkConfig();
    RxCity getRxCity();
    RxWeatherType getRxWeatherType();
    CityConfig getCityConfig();
    RxReadSettingItems getRxReadSettingItems();

    void inject(MainActivity mainActivity);
    void inject(InitActivity initActivity);
    void inject(SplashActivity splashActivity);
    void inject(SettingActivity settingActivity);
    void inject(WeatherForecastActivity weatherForecastActivity);
    void inject(AboutActivity aboutActivity);
}

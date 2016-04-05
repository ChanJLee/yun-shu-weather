package com.chan.chanweather.injector.module;

import android.app.Activity;
import android.content.Context;

import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.annotation.PerActivity;
import com.chan.chanweather.model.rx.RxCity;
import com.chan.chanweather.model.rx.RxReadSettingItems;
import com.chan.chanweather.model.rx.RxWeather;
import com.chan.chanweather.model.rx.RxWeatherType;
import com.chan.chanweather.model.sp.CityConfig;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chan on 16/2/27.
 */
@Module
public class ActivityModule {
    private Activity m_activity;

    public ActivityModule(Activity activity) {
        m_activity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("activity")
    public Context provideContext() {
        return m_activity;
    }

    @PerActivity
    @Provides
    public Activity provideActivity() {
        return m_activity;
    }

    @Provides
    @PerActivity
    public SystemBarTintManager provideSystemBarTintManager() {
        return new SystemBarTintManager(m_activity);
    }

    @PerActivity
    @Provides
    public RxWeatherType provideWeatherType() {
        return new RxWeatherType();
    }

    @Provides
    @PerActivity
    public RxCity provideCity() {
        return new RxCity();
    }

    @PerActivity
    @Provides
    public CityConfig provideCityConfig() {
        return new CityConfig(m_activity.getApplication());
    }

    @Provides
    @PerActivity
    public RxReadSettingItems provideRxReadSettingItems() {
        return new RxReadSettingItems(m_activity);
    }
}

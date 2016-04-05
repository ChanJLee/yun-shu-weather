package com.chan.chanweather.injector.module;

import android.app.Service;
import android.content.Context;

import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.annotation.PerActivity;
import com.chan.chanweather.injector.annotation.PerService;
import com.chan.chanweather.model.rx.RxWeather;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chan on 16/2/27.
 */
@Module
public class ServiceModule {
    private Service m_service;

    public ServiceModule(Service service) {
        m_service = service;
    }

    @PerService
    @Provides
    public Service provideService() {
        return m_service;
    }

    @PerService
    @Provides
    @ContextLife("service")
    public Context provideContext() {
        return m_service;
    }

    @Provides
    @PerService
    public RxWeather provideRxWeather() {
        return new RxWeather();
    }
}

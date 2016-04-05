package com.chan.chanweather.injector.component;


import android.app.Service;
import android.content.Context;


import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.annotation.PerService;
import com.chan.chanweather.injector.module.ServiceModule;
import com.chan.chanweather.model.rx.RxWeather;
import com.chan.chanweather.services.WeatherService;

import dagger.Component;

/**
 * Created by chan on 16/2/27.
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    @ContextLife("application")
    Context getApplicationContext();

    @ContextLife("service")
    Context getServiceContext();

    Service getService();
    RxWeather getRxWeather();

    void inject(WeatherService weatherService);
}

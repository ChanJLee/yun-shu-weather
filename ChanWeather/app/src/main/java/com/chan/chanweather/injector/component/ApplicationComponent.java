package com.chan.chanweather.injector.component;

import android.content.Context;

import com.chan.chanweather.base.BaseApplication;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.module.ApplicationModule;
import com.chan.chanweather.model.config.NetworkConfig;
import com.chan.chanweather.model.config.PreferencesConfig;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chan on 16/2/27.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLife("application")
    Context getContext();

    PreferencesConfig getPreferencesConfig();
    NetworkConfig getNetworkConfig();

    void inject(BaseApplication application);
}

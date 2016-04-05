package com.chan.chanweather.injector.module;

import android.app.Application;
import android.content.Context;

import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.model.config.NetworkConfig;
import com.chan.chanweather.model.config.PreferencesConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chan on 16/2/27.
 */
@Module
public class ApplicationModule {

    private Application m_application;

    public ApplicationModule(Application application) {
        m_application = application;
    }

    @ContextLife("application")
    @Singleton
    @Provides
    public Context provideContext() {
        return m_application;
    }

    @Singleton
    @Provides
    public PreferencesConfig providePreferencesConfig() {
        return new PreferencesConfig(m_application);
    }

    @Singleton
    @Provides
    public NetworkConfig provideNetworkConfig() {
        return new NetworkConfig();
    }
}

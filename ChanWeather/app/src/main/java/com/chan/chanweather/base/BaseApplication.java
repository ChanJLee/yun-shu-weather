package com.chan.chanweather.base;

import android.app.Application;

import com.chan.chanweather.injector.component.ApplicationComponent;
import com.chan.chanweather.injector.component.DaggerApplicationComponent;
import com.chan.chanweather.injector.module.ApplicationModule;
import com.chan.chanweather.model.init.BaseManager;

import javax.inject.Inject;

/**
 * Created by chan on 16/3/21.
 */
public class BaseApplication extends Application {
    private ApplicationComponent m_applicationComponent;

    @Inject
    BaseManager m_baseManager;

    @Override
    public void onCreate() {
        super.onCreate();
        m_applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        m_applicationComponent.inject(this);
        m_baseManager.init();
    }

    public ApplicationComponent getApplicationComponent() {
        return m_applicationComponent;
    }
}

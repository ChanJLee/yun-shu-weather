package com.chan.chanweather.base;

import android.app.IntentService;
import android.content.Intent;

import com.chan.chanweather.injector.component.DaggerServiceComponent;
import com.chan.chanweather.injector.component.ServiceComponent;
import com.chan.chanweather.injector.module.ServiceModule;

/**
 * Created by chan on 16/3/29.
 */
public abstract class BaseIntentService extends IntentService {

    private ServiceComponent m_serviceComponent;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BaseIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        BaseApplication baseApplication = (BaseApplication) getApplicationContext();
        m_serviceComponent = DaggerServiceComponent.builder()
                .applicationComponent(baseApplication.getApplicationComponent())
                .serviceModule(new ServiceModule(this)).build();
        inject(m_serviceComponent);
    }

    protected abstract void inject(ServiceComponent serviceComponent);

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}

package com.chan.chanweather.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.chan.chanweather.base.BaseIntentService;
import com.chan.chanweather.injector.component.ServiceComponent;
import com.chan.chanweather.presenter.impl.WeatherServicePresenter;

import javax.inject.Inject;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class WeatherService extends BaseIntentService {

    @Inject
    WeatherServicePresenter m_weatherServicePresenter;

    public WeatherService() {
        super("WeatherService");
    }

    private static final String EXTRA_CITY_ID = "id";
    private static final String EXTRA_KEY = "key";
    private static final String EXTRA_BASE_URI = "base_uri";
    private static final String ACTION_REQUEST_WEATHER = "com.chan.weather.service";

    @Override
    protected void inject(ServiceComponent serviceComponent) {
        serviceComponent.inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && ACTION_REQUEST_WEATHER.equals(intent.getAction())) {
            m_weatherServicePresenter.requestWeather(intent.getStringExtra(EXTRA_CITY_ID),
                    intent.getStringExtra(EXTRA_KEY), intent.getStringExtra(EXTRA_BASE_URI));
        }
    }

    public static void requestWeather(Context context, String cityId, String key, String baseUri) {
        Intent intent = new Intent(context, WeatherService.class);
        intent.setAction(ACTION_REQUEST_WEATHER);
        intent.putExtra(EXTRA_CITY_ID, cityId);
        intent.putExtra(EXTRA_KEY, key);
        intent.putExtra(EXTRA_BASE_URI, baseUri);
        context.startService(intent);
    }
}

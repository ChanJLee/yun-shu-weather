package com.chan.chanweather.view.interfaces;

import android.net.Uri;

import com.chan.chanweather.adapter.MainAdapter;
import com.chan.chanweather.bean.WeatherResponse;

/**
 * Created by chan on 16/3/25.
 */
public interface IMainView extends IView {
    void showProgressing(int progress);
    void dismissProgressing();
    void showSimpleMessage(String message);
    void showResponse(MainAdapter mainAdapter);
    void setBackground(Uri uri);
    void setPrimaryColor(int color);
    void showSunriseTheme();
    void showSunsetTheme();
    void notifyChanged();
    void setHead(Uri uri);
    void setTitle(String title);
}

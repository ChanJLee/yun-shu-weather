package com.chan.chanweather.presenter.impl;

import android.content.Context;

import com.chan.chanweather.R;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.bus.MainErrorEvent;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.model.rx.RxWeather;
import com.chan.chanweather.presenter.interfaces.IWeatherServicePresenter;
import com.chan.chanweather.view.interfaces.IView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by chan on 16/3/29.
 */
public class WeatherServicePresenter implements IWeatherServicePresenter {

    private RxWeather m_rxWeather;
    private Context m_context;
    private String m_dir = "weather";
    private String m_fileName = "cache.json";
    private boolean m_isRequest = false;

    @Inject
    public WeatherServicePresenter(RxWeather rxWeather, @ContextLife("service") Context context) {
        m_rxWeather = rxWeather;
        m_context = context;
    }

    @Override
    public void attachView(IView iView) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void requestWeather(String cityId, String key, String baseUri) {

        if(m_isRequest) return;
        m_isRequest = true;

        m_rxWeather.getWeatherAsync(key, cityId).subscribe(
                new Subscriber<WeatherResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        m_isRequest = false;
                        EventBus.getDefault().post(new MainErrorEvent());
                        handleResponse(null);
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        m_isRequest = false;
                        handleResponse(weatherResponse);
                    }
                });
    }

    private void handleResponse(WeatherResponse weatherResponse) {

        //读取失败 就从本地加载数据
        if (weatherResponse == null) {
            weatherResponse = readFromDisk();
        } else {

            //从网络读取正常
            List<WeatherResponse.DecorEntity> decorEntityList = weatherResponse.getDecor();

            //但是存在数据异常
            if (decorEntityList == null || decorEntityList.isEmpty() ||
                    !decorEntityList.get(0).getStatus().equals("ok")) {
                weatherResponse = readFromDisk();
            } else {

                //这里开始 数据是正常的 那么我们在本地保留一个副本
                write2Disk(weatherResponse);
            }
        }

        //发送到ui
        EventBus.getDefault().post(weatherResponse);
    }

    private WeatherResponse readFromDisk() {

        Reader reader = null;
        File file = getCache();

        if (file.exists()) {
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(reader == null) {
            reader = new InputStreamReader(m_context.getResources().openRawResource(R.raw.weather));
        }

        return new Gson().fromJson(reader, WeatherResponse.class);
    }

    private void write2Disk(WeatherResponse weatherResponse) {
        Gson gson = new Gson();
        final String json = gson.toJson(weatherResponse);
        BufferedWriter bufferedWriter = null;
        OutputStreamWriter outputStreamWriter = null;

        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(getCache()));
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(json);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File getCache() {
        File dir = m_context.getDir(m_dir, Context.MODE_PRIVATE);
        return new File(dir, m_fileName);
    }
}

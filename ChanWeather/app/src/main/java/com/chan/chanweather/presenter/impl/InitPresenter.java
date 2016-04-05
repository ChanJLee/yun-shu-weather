package com.chan.chanweather.presenter.impl;

import android.content.Context;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.bean.CityResponse;
import com.chan.chanweather.bus.InitErrorEvent;
import com.chan.chanweather.db.core.CityDB;
import com.chan.chanweather.db.dao.CityDao;
import com.chan.chanweather.db.wrapper.CityWrapper;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.model.rx.RxCity;
import com.chan.chanweather.presenter.interfaces.IInitPresenter;
import com.chan.chanweather.view.interfaces.IInitView;
import com.chan.chanweather.view.interfaces.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by chan on 16/3/27.
 */
public class InitPresenter implements IInitPresenter {

    private RxCity m_rxCity;
    private IInitView m_iInitView;
    private Context m_context;
    private int m_count = 0;

    @Inject
    public InitPresenter(@ContextLife("activity") Context context, RxCity rxCity) {
        m_rxCity = rxCity;
        m_context = context;
    }

    @Override
    public void attachView(IView iView) {
        m_iInitView = (IInitView) iView;
        EventBus.getDefault().register(m_context);
    }

    @Override
    public void detachView() {
        EventBus.getDefault().unregister(m_context);
    }

    @Override
    public void loadResources() {
        m_rxCity.getCityAsync(BuildConfig.KEY, BuildConfig.ALL_CITY)
                .subscribe(new Subscriber<CityResponse>() {
                    @Override
                    public void onCompleted() {
                        EventBus.getDefault().post(Integer.valueOf(30));
                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(new InitErrorEvent("读取城市信息失败", e));
                    }

                    @Override
                    public void onNext(CityResponse cityResponse) {
                        if (!"ok".equals(cityResponse.getStatus())) {
                            EventBus.getDefault().post(new InitErrorEvent("读取城市信息失败", null));
                            return;
                        }

                        EventBus.getDefault().post(Integer.valueOf(30));
                        handleCityResponse(cityResponse);
                    }
                });
    }

    @Override
    public void checkIfFinished(int progress) {
        m_count += progress;

        if (m_count >= 100) {
            m_iInitView.handleInitFinishEvent();
        }

        m_iInitView.setInitProgress(m_count);
    }

    private void handleCityResponse(CityResponse cityResponse) {
        CityDao cityDao = CityDB.getCityDao(m_context);

        List<CityResponse.CityInfoEntity> cityInfoEntityList = cityResponse.getCityInfo();
        final int size = cityInfoEntityList.size();
        final int part = size / 5;

        for (int i = 0; i < size; ++i) {

            if(i % part == 0) {
                EventBus.getDefault().post(Integer.valueOf(10));
            }

            CityResponse.CityInfoEntity cityInfoEntity = cityInfoEntityList.get(i);
            CityWrapper cityWrapper = new CityWrapper(
                    cityInfoEntity.getId(),
                    cityInfoEntity.getProv(),
                    cityInfoEntity.getCity());

            try {
                cityDao.insert(cityWrapper);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

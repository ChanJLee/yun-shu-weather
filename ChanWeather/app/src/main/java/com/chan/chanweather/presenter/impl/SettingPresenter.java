package com.chan.chanweather.presenter.impl;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.chan.chanweather.bus.SettingCityEvent;
import com.chan.chanweather.bus.SettingProvinceEvent;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.model.rx.RxReadSettingItems;
import com.chan.chanweather.model.sp.CityConfig;
import com.chan.chanweather.presenter.interfaces.ISettingPresenter;
import com.chan.chanweather.view.interfaces.ISettingView;
import com.chan.chanweather.view.interfaces.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by chan on 16/3/29.
 */
public class SettingPresenter implements ISettingPresenter {

    private ISettingView m_iSettingView;
    private CityConfig m_cityConfig;
    private Context m_context;
    private SpinnerAdapter m_provinceAdapter;
    private SpinnerAdapter m_cityAdapter;
    private List<String> m_provinces = new ArrayList<>();
    private List<String> m_cities = new ArrayList<>();
    private RxReadSettingItems m_rxReadSettingItems;
    private boolean m_isFirst = true;

    @Inject
    public SettingPresenter(CityConfig cityConfig, @ContextLife("activity") Context context,
                            RxReadSettingItems rxReadSettingItems) {
        m_cityConfig = cityConfig;
        m_context = context;
        m_rxReadSettingItems = rxReadSettingItems;
    }

    @Override
    public void attachView(IView iView) {
        m_iSettingView = (ISettingView) iView;
        EventBus.getDefault().register(m_context);
    }

    @Override
    public void detachView() {
        EventBus.getDefault().unregister(m_context);
    }

    @Override
    public void loadData() {

        m_iSettingView.showProgressing("加载数据中");

        m_rxReadSettingItems.readProvinceAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                m_iSettingView.dismissProgressing();
            }

            @Override
            public void onError(Throwable e) {
                m_iSettingView.dismissProgressing();
            }

            @Override
            public void onNext(List<String> list) {
                m_provinces.addAll(list);
                m_provinceAdapter = new ArrayAdapter<String>(m_context,
                        android.R.layout.simple_list_item_1, m_provinces);
                EventBus.getDefault().post(new SettingProvinceEvent(m_provinceAdapter,
                        m_provinces.indexOf(m_cityConfig.getProvince())));
            }
        });
    }

    @Override
    public void onItemSelected(int position) {
        getCities(m_provinces.get(position));
    }

    @Override
    public void saveCity(String province, String city) {
        m_iSettingView.showSimpleMessage("保存设置中");
        m_rxReadSettingItems.queryCityIdAsync(province, city)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                m_iSettingView.showSimpleMessage("保存成功");
                m_iSettingView.finishShow();
            }

            @Override
            public void onError(Throwable e) {
                m_iSettingView.showSimpleMessage("保存失败");
                m_iSettingView.finishShow();
            }

            @Override
            public void onNext(String s) {
                m_cityConfig.setCityId(s);
            }
        });
    }

    @Override
    public void scheduleFinish(String city, String province) {

        final String cityConfig = m_cityConfig.getCity();
        final String provinceConfig = m_cityConfig.getProvince();

        if(!cityConfig.equals(city) ||
                !provinceConfig.equals(province)) {
            m_iSettingView.warningAboutModifyNotSaved();
        } else {
            m_iSettingView.finishShow();
        }
    }

    public void getCities(String province) {

        m_iSettingView.showProgressing("读取城市信息中");
        m_cities.clear();
        m_rxReadSettingItems.readCityAsync(province)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {

            @Override
            public void onCompleted() {
                m_iSettingView.dismissProgressing();
            }

            @Override
            public void onError(Throwable e) {
                m_iSettingView.dismissProgressing();
            }

            @Override
            public void onNext(List<String> list) {

                m_cities.addAll(list);

                m_cityAdapter = new ArrayAdapter<>(m_context,
                        android.R.layout.simple_list_item_1, m_cities);
                EventBus.getDefault().post(new SettingCityEvent(m_cityAdapter,
                        (m_isFirst ? m_cities.indexOf(m_cityConfig.getCity()) : 0)));
                m_isFirst = false;
            }
        });
    }

}

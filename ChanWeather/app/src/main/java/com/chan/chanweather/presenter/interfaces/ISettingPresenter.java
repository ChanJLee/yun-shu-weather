package com.chan.chanweather.presenter.interfaces;

/**
 * Created by chan on 16/3/29.
 */
public interface ISettingPresenter extends IPresenter {
    void loadData();
    void onItemSelected(int position);
    void saveCity(String province, String city);
    void scheduleFinish(String city, String province);
}

package com.chan.chanweather.bus;

import android.widget.SpinnerAdapter;

/**
 * Created by chan on 16/3/31.
 */
public class SettingProvinceEvent {
    private int position;
    private SpinnerAdapter m_spinnerAdapter;

    public SettingProvinceEvent(SpinnerAdapter spinnerAdapter, int position) {
        m_spinnerAdapter = spinnerAdapter;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public SpinnerAdapter getSpinnerAdapter() {
        return m_spinnerAdapter;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

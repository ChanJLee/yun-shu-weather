package com.chan.chanweather.bean;

/**
 * Created by chan on 16/4/3.
 */
public class TemperatureHolder {
    private float m_min;
    private float m_max;
    private String m_date;

    public TemperatureHolder(float max, float min, String date) {
        m_max = max;
        m_min = min;
        m_date = date;
    }

    public float getMin() {
        return m_min;
    }

    public void setMin(float min) {
        m_min = min;
    }

    public float getMax() {
        return m_max;
    }

    public void setMax(float max) {
        m_max = max;
    }

    public String getDate() {
        return m_date;
    }

    public void setDate(String date) {
        m_date = date;
    }
}

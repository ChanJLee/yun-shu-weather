package com.chan.chanweather.bean;

/**
 * Created by chan on 16/4/4.
 */
public class HumidityHolder {

    private String m_date;
    private float humidity;

    public HumidityHolder(String date, float humidity) {
        m_date = date;
        this.humidity = humidity;
    }

    public String getDate() {
        return m_date;
    }

    public void setDate(String date) {
        m_date = date;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}

package com.chan.chanweather.db.wrapper;

/**
 * Created by chan on 16/3/28.
 */
public class CityWrapper {

    private String m_province;
    private String m_city;
    private String m_id;

    public CityWrapper() {
    }

    public CityWrapper(String id, String province, String city) {
        m_id = id;
        m_province = province;
        m_city = city;
    }

    public String getProvince() {
        return m_province;
    }

    public void setProvince(String province) {
        m_province = province;
    }

    public String getCity() {
        return m_city;
    }

    public void setCity(String city) {
        m_city = city;
    }

    public String getId() {
        return m_id;
    }

    public void setId(String id) {
        m_id = id;
    }
}

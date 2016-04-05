package com.chan.chanweather.model.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.chan.chanweather.injector.annotation.ContextLife;

/**
 * Created by chan on 16/3/29.
 */
public class CityConfig {

    private static final String NAME = "cityConfig";
    private SharedPreferences m_sharedPreferences;
    private static final String KEY_CITY_ID = "city_id";
    private static final String KEY_CITY = "city";
    private static final String KEY_PROVINCE = "province";

    public CityConfig(@ContextLife("application") Context context) {
        m_sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public String getCityId() {
        return m_sharedPreferences.getString(KEY_CITY_ID, "CN101190101");
    }

    public void setCityId(String cityId) {
        setStringValue(KEY_CITY_ID, cityId);
    }

    public void setCity(String city) {
        setStringValue(KEY_CITY, city);
    }

    public void setProvince(String province) {
        setStringValue(KEY_PROVINCE, province);
    }

    private void setStringValue(String key, String value) {
        SharedPreferences.Editor editor = m_sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getCity() {
        return m_sharedPreferences.getString(KEY_CITY, "南京");
    }

    public String getProvince() {
        return m_sharedPreferences.getString(KEY_PROVINCE, "江苏");
    }
}

package com.chan.chanweather.bean;

import android.net.Uri;

/**
 * Created by chan on 16/4/3.
 */
public class WeatherHolder {
    private Uri m_imageReference;
    private String m_description;

    public WeatherHolder(Uri imageReference, String description) {
        m_imageReference = imageReference;
        m_description = description;
    }

    public Uri getImageReference() {
        return m_imageReference;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(String description) {
        m_description = description;
    }
}

package com.chan.chanweather.holder.impl;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.R;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.holder.interfaces.IHolder;
import com.chan.chanweather.utils.WeatherTypeUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chan on 16/3/26.
 */
public class CurrentWeatherHolder extends RecyclerView.ViewHolder implements IHolder {

    /**
     * 天气图标
     */
    @Bind(R.id.id_weather_icon)
    SimpleDraweeView m_icon;
    /**
     * 温度
     */
    @Bind(R.id.id_temp_flu)
    TextView m_temp;
    /**
     * pm 2.5
     */
    @Bind(R.id.id_pm)
    TextView m_pm;
    /**
     * 空气质量
     */
    @Bind(R.id.id_quality)
    TextView m_quality;
    /**
     * 更新时间
     */
    @Bind(R.id.id_time)
    TextView m_time;
    /**
     * 具体的天气
     */
    @Bind(R.id.id_detail)
    TextView m_detail;

    public CurrentWeatherHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(WeatherResponse response) {
        WeatherResponse.DecorEntity entity = response.getDecor().get(0);
        WeatherResponse.DecorEntity.NowEntity nowEntity = entity.getNow();
        WeatherResponse.DecorEntity.BasicEntity basicEntity = entity.getBasic();
        WeatherResponse.DecorEntity.AqiEntity aqiEntity = entity.getAqi();

        m_time.setText(basicEntity.getUpdate().getLoc());
        m_detail.setText(nowEntity.getCond().getTxt());
        m_temp.setText(nowEntity.getTmp());
        m_icon.setImageURI(Uri.parse("res:///" + getWeatherIconReference(nowEntity.getCond().getTxt())));

        if(aqiEntity != null) {
            m_pm.setText(aqiEntity.getCity().getPm25());
            m_quality.setText(aqiEntity.getCity().getQlty());
        }
    }

    private int getWeatherIconReference(String weather) {
        return WeatherTypeUtil.weatherType2Reference(weather);
    }

    public void bindClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }
}

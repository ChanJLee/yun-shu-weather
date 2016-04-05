package com.chan.chanweather.holder.impl;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chan.chanweather.R;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.holder.interfaces.IHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chan on 16/3/26.
 */
public class SuggestionHolder extends RecyclerView.ViewHolder implements IHolder {

    /**
     * 穿衣指数
     */
    @Bind(R.id.id_suggestion)
    TextView m_suggestion;
    /**
     * 洗车指数
     */
    @Bind(R.id.id_clean_car)
    TextView m_cleanCar;
    /**
     * 旅行指数
     */
    @Bind(R.id.id_travel)
    TextView m_travel;
    /**
     * 感冒指数
     */
    @Bind(R.id.id_cold)
    TextView m_cold;
    /**
     * 运动指数
     */
    @Bind(R.id.id_sport)
    TextView m_sport;
    /**
     * 紫外线指数
     */
    @Bind(R.id.id_ultraviolet_level)
    TextView m_ultraviolet;

    public SuggestionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(WeatherResponse weatherResponse) {
        WeatherResponse.DecorEntity entity = weatherResponse.getDecor().get(0);
        WeatherResponse.DecorEntity.SuggestionEntity suggestionEntity = entity.getSuggestion();

        if(suggestionEntity == null) return;

        m_suggestion.setText(suggestionEntity.getDrsg().getTxt());
        m_cleanCar.setText(suggestionEntity.getCw().getBrf());
        m_travel.setText(suggestionEntity.getTrav().getBrf());
        m_cold.setText(suggestionEntity.getFlu().getBrf());
        m_sport.setText(suggestionEntity.getSport().getBrf());

        m_ultraviolet.setText(suggestionEntity.getUv().getBrf());
    }
}

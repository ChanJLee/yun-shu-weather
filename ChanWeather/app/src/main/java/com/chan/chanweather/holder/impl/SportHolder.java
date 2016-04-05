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
 * Created by chan on 16/3/28.
 */
public class SportHolder extends RecyclerView.ViewHolder implements IHolder {

    @Bind(R.id.id_suggestion)
    TextView m_suggestion;

    public SportHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(WeatherResponse response) {
        WeatherResponse.DecorEntity decorEntity = response.getDecor().get(0);
        WeatherResponse.DecorEntity.SuggestionEntity suggestionEntity = decorEntity.getSuggestion();

        if(suggestionEntity != null) {
            m_suggestion.setText(suggestionEntity.getSport().getTxt());
        }
    }
}

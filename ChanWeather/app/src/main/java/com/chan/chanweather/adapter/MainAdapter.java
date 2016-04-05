package com.chan.chanweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.chanweather.R;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.holder.impl.CurrentWeatherHolder;
import com.chan.chanweather.holder.impl.FluHolder;
import com.chan.chanweather.holder.impl.SportHolder;
import com.chan.chanweather.holder.impl.SuggestionHolder;
import com.chan.chanweather.holder.impl.TravelHolder;
import com.chan.chanweather.holder.interfaces.IHolder;

/**
 * Created by chan on 16/3/27.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TEMP = 0;
    private static final int TYPE_SUGGESTION = 1;
    private static final int TYPE_TRAVEL = 2;
    private static final int TYPE_FLU = 3;
    private static final int TYPE_SPORT = 4;

    private Context m_context;
    private LayoutInflater m_layoutInflater;
    private WeatherResponse m_weatherResponse;
    private OnWeatherItemClicked m_onWeatherItemClicked;

    public MainAdapter(Context context, WeatherResponse weatherResponse) {
        m_context = context;
        m_weatherResponse = weatherResponse;
        m_layoutInflater = LayoutInflater.from(m_context);
    }

    @Override
    public int getItemViewType(int position) {

        if(position == TYPE_TEMP) {
            return TYPE_TEMP;
        }

        if(position == TYPE_SUGGESTION) {
            return TYPE_SUGGESTION;
        }

        if(position == TYPE_TRAVEL) {
            return TYPE_TRAVEL;
        }

        if(position == TYPE_FLU) {
            return TYPE_FLU;
        }

        return TYPE_SPORT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_TEMP) {
            return new CurrentWeatherHolder(m_layoutInflater.inflate(R.layout.item_temp, parent, false));
        }

        if (viewType == TYPE_SUGGESTION) {
            return new SuggestionHolder(m_layoutInflater.inflate(R.layout.item_suggestion, parent, false));
        }

        if (viewType == TYPE_FLU) {
            return new FluHolder(m_layoutInflater.inflate(R.layout.item_flu, parent, false));
        }

        if (viewType == TYPE_SPORT) {
            return new SportHolder(m_layoutInflater.inflate(R.layout.item_sport, parent, false));
        }

        return new TravelHolder(m_layoutInflater.inflate(R.layout.item_travel, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IHolder iHolder = (IHolder) holder;
        iHolder.bind(m_weatherResponse);

        if(iHolder instanceof CurrentWeatherHolder) {
            ((CurrentWeatherHolder) iHolder).bindClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(m_onWeatherItemClicked != null) {
                        m_onWeatherItemClicked.onWeatherItemClicked(v);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public interface OnWeatherItemClicked {
        void onWeatherItemClicked(View view);
    }

    public void setOnWeatherItemClicked(OnWeatherItemClicked onWeatherItemClicked) {
        m_onWeatherItemClicked = onWeatherItemClicked;
    }
}

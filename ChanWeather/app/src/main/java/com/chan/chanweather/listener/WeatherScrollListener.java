package com.chan.chanweather.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by chan on 16/3/22.
 */
public abstract class WeatherScrollListener extends RecyclerView.OnScrollListener {

    private static final int DELTA_Y_THRESHOLD = 20;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(Math.abs(dy) <= DELTA_Y_THRESHOLD) {
            return;
        }

        if(dy < 0) {
            onScroll2Up(recyclerView, dx, dy);
        } else {
            onScroll2Bottom(recyclerView, dx, dy);
        }
    }

    public abstract void onScroll2Up(RecyclerView recyclerView, int dx, int dy);
    public abstract void onScroll2Bottom(RecyclerView recyclerView, int dx, int dy);
}

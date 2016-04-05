package com.chan.chanweather.view.interfaces;

/**
 * Created by chan on 16/3/27.
 */
public interface IInitView extends IView {
    void handleInitFinishEvent();
    void setInitProgress(int progress);
}

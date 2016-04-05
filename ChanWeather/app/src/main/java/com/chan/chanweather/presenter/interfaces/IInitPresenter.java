package com.chan.chanweather.presenter.interfaces;

/**
 * Created by chan on 16/3/27.
 */
public interface IInitPresenter extends IPresenter {
    void loadResources();
    void checkIfFinished(int progress);
}

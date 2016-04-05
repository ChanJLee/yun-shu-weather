package com.chan.chanweather.presenter.interfaces;

import com.chan.chanweather.view.interfaces.IView;

/**
 * Created by chan on 16/3/25.
 */
public interface IPresenter {
    void attachView(IView iView);
    void detachView();
}

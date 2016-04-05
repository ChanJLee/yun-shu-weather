package com.chan.chanweather.presenter.interfaces;

import android.content.Intent;

/**
 * Created by chan on 16/3/27.
 */
public interface ISplashPresenter extends IPresenter {
    int REQUEST_INIT = 0x0525;
    void preformInflateActivity();

    /**
     * 标记是否初始化成功
     * @param resultCode
     */
    void markInitResult(int resultCode);
}

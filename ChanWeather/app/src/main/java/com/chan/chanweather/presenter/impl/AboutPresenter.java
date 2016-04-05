package com.chan.chanweather.presenter.impl;

import android.app.Activity;

import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.presenter.interfaces.IAboutPresenter;
import com.chan.chanweather.view.interfaces.IAboutView;
import com.chan.chanweather.view.interfaces.IView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

/**
 * Created by chan on 16/4/4.
 */
public class AboutPresenter implements IAboutPresenter {

    private Activity m_activity;
    private IAboutView m_iAboutView;

    @Inject
    public AboutPresenter(Activity activity) {
        m_activity = activity;
    }

    @Override
    public void attachView(IView iView) {
        m_iAboutView = (IAboutView) iView;
    }

    @Override
    public void detachView() {

    }

    @Override
    public SystemBarTintManager getSystemBarTintManager() {
        return ((BaseActivity)m_activity).getActivityComponent().getSystemBarTintManager();
    }
}

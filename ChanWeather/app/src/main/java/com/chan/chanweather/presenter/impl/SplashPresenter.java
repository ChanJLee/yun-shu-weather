package com.chan.chanweather.presenter.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.chan.chanweather.MainActivity;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.model.config.PreferencesConfig;
import com.chan.chanweather.presenter.interfaces.ISplashPresenter;
import com.chan.chanweather.view.impl.InitActivity;
import com.chan.chanweather.view.interfaces.ISplashView;
import com.chan.chanweather.view.interfaces.IView;

import javax.inject.Inject;

/**
 * Created by chan on 16/3/27.
 */
public class SplashPresenter implements ISplashPresenter {

    private ISplashView m_iSplashView;
    private Context m_context;
    private PreferencesConfig m_preferencesConfig;

    @Inject
    public SplashPresenter(@ContextLife("activity") Context context, PreferencesConfig preferencesConfig) {
        m_context = context;
        m_preferencesConfig = preferencesConfig;
    }

    @Override
    public void attachView(IView iView) {
        m_iSplashView = (ISplashView) iView;
    }

    @Override
    public void detachView() {

    }

    /**
     * 选择要加载的第一个类
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void preformInflateActivity() {
        if (m_preferencesConfig.isFirstUse()) {
            Intent intent = InitActivity.newIntent(m_context);
            Activity activity = (Activity) m_context;
            activity.startActivityForResult(intent, REQUEST_INIT);
        } else {
            Intent intent = MainActivity.newIntent(m_context);
//            ActivityOptionsCompat options =
//                    ActivityOptionsCompat.makeCustomAnimation(m_context,
//                            0, 0);
//            ActivityCompat.startActivity((Activity) m_context, intent, options.toBundle());
            m_context.startActivity(intent);
            m_iSplashView.finishShow();
        }
    }

    @Override
    public void markInitResult(int resultCode) {
        final boolean isSuccess = resultCode == Activity.RESULT_OK;
        m_preferencesConfig.setFirstUse(!isSuccess);

        if (isSuccess) {
            preformInflateActivity();
        }
    }
}

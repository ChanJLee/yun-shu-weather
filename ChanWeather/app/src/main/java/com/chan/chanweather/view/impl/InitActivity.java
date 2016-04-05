package com.chan.chanweather.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.chan.chanweather.R;
import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.bus.InitErrorEvent;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.presenter.impl.InitPresenter;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.chan.chanweather.view.interfaces.IInitView;
import com.daimajia.numberprogressbar.NumberProgressBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InitActivity extends BaseActivity implements IInitView {

    @Inject
    InitPresenter m_initPresenter;
    @Bind(R.id.id_progress)
    NumberProgressBar m_numberProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        init();
    }

    @Override
    protected IPresenter attachPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        m_initPresenter.attachView(this);
        return null;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, InitActivity.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleProgressEvent(Integer progress) {
        m_initPresenter.checkIfFinished(progress);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleErrorEvent(InitErrorEvent errorEvent) {
        showSimpleMessage(errorEvent.getMessage());
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void handleInitFinishEvent() {
        setResult(RESULT_OK, null);
        finish();
    }

    @Override
    public void setInitProgress(int progress) {
        m_numberProgressBar.setProgress(progress);
    }

    public void init() {
        ButterKnife.bind(this);
        m_initPresenter.loadResources();
        m_numberProgressBar.setMax(100);
    }

    @Override
    public void showSimpleMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

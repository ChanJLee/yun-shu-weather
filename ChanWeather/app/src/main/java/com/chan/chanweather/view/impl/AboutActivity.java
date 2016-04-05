package com.chan.chanweather.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.chan.chanweather.R;
import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.presenter.impl.AboutPresenter;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.chan.chanweather.view.interfaces.IAboutView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements IAboutView {

    @Bind(R.id.id_toolbar)
    Toolbar m_toolbar;

    @Inject
    AboutPresenter m_aboutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {
        ButterKnife.bind(this);
        m_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager systemBarTintManager = m_aboutPresenter.getSystemBarTintManager();
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.about));
        }
    }

    @Override
    protected IPresenter attachPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        m_aboutPresenter.attachView(this);
        return m_aboutPresenter;
    }

    public static void invokeAbout(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showSimpleMessage(String message) {

    }
}

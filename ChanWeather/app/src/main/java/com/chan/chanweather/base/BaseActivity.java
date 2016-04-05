package com.chan.chanweather.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.chan.chanweather.R;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.injector.component.DaggerActivityComponent;
import com.chan.chanweather.injector.module.ActivityModule;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by chan on 16/3/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponent m_activityComponent;
    private IPresenter m_iPresenter;
    private ThemeState m_themeState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        m_iPresenter = attachPresenter(m_activityComponent);
        ActivityCollections.addActivity(this);
    }

    protected abstract IPresenter attachPresenter(ActivityComponent activityComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollections.removeActivity(this);
        if(m_iPresenter != null) {
            m_iPresenter.detachView();
        }
    }

    private void setupActivityComponent() {
        BaseApplication application = (BaseApplication) getApplication();
        m_activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .activityModule(new ActivityModule(BaseActivity.this)).build();
    }

    public void finishAll() {
        ActivityCollections.finishAll();
    }

    protected void setSunsetTheme() {
        m_themeState = new SunsetThemeState();
        m_themeState.work();
    }

    protected void setSunriseTheme() {
        m_themeState = new SunriseThemeState();
        m_themeState.work();
    }

    private interface ThemeState {
        void work();
    }

    private class SunriseThemeState implements ThemeState {

        @SuppressWarnings("deprecation")
        @Override
        public void work() {
            setPrimaryTheme(getResources().getColor(R.color.colorSunrise));
        }
    }

    private void setPrimaryTheme(int primaryThemeColorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager systemBarTintManager = m_activityComponent.getSystemBarTintManager();
            systemBarTintManager.setStatusBarTintColor(primaryThemeColorId);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setNavigationBarTintEnabled(true);
        }
    }

    private class SunsetThemeState implements ThemeState {

        @SuppressWarnings("deprecation")
        @Override
        public void work() {
            setPrimaryTheme(getResources().getColor(R.color.colorSunset));
        }
    }

    public ActivityComponent getActivityComponent() {
        return m_activityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

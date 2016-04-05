package com.chan.chanweather.view.impl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.chan.chanweather.R;
import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.presenter.impl.SplashPresenter;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.chan.chanweather.view.interfaces.ISplashView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements ISplashView {

    private static final int DURATION = 1500;

    @Inject
    SplashPresenter m_iSplashPresenter;
    @Bind(R.id.id_words)
    TextView m_textView;
    private AnimatorSet m_animatorSet;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindowFlags();
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        setupViews();
        initAnimatorSet();
    }

    private void setupViews() {
        ButterKnife.bind(this);
    }

    private void initAnimatorSet() {
        final String alphaProperty = "alpha";
        ObjectAnimator alpha = ObjectAnimator.ofFloat(m_textView, alphaProperty, 0.f, 1.f);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(displayMetrics);

        final String translationYProperty = "translationY";
        ObjectAnimator translationY = ObjectAnimator.ofFloat(
                m_textView,
                translationYProperty,
                m_textView.getTranslationY() + displayMetrics.heightPixels / 2,
                m_textView.getTranslationY());

        m_animatorSet = new AnimatorSet();
        m_animatorSet.playTogether(alpha, translationY);
        m_animatorSet.setInterpolator(new DecelerateInterpolator());
        m_animatorSet.setDuration(DURATION);

        m_animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                m_iSplashPresenter.preformInflateActivity();
            }
        });
    }

    @Override
    protected IPresenter attachPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        m_iSplashPresenter.attachView(this);
        return m_iSplashPresenter;
    }

    /**
     * 初始化界面的属性
     */
    private void initWindowFlags() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            ActivityComponent activityComponent = getActivityComponent();
            SystemBarTintManager systemBarTintManager = activityComponent.getSystemBarTintManager();
            systemBarTintManager.setNavigationBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintEnabled(true);

            final int color = getResources().getColor(R.color.colorSplash);
            systemBarTintManager.setStatusBarTintColor(color);
            systemBarTintManager.setNavigationBarTintColor(color);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_animatorSet.start();
    }

    @Override
    public void showSimpleMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishShow() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        m_iSplashPresenter.markInitResult(resultCode);
    }
}

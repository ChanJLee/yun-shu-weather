package com.chan.chanweather;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chan.chanweather.adapter.MainAdapter;
import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.bean.WeatherResponse;
import com.chan.chanweather.bus.MainErrorEvent;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.listener.WeatherScrollListener;
import com.chan.chanweather.presenter.impl.MainActivityPresenter;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.chan.chanweather.view.interfaces.IMainView;
import com.chan.circleprogress.CircleProgress;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    private static final int DURATION_ANIMATOR = 800;

    @Bind(R.id.id_toolbar_layout)
    CollapsingToolbarLayout m_collapsingToolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar m_toolbar;
    @Bind(R.id.fab)
    FloatingActionButton m_floatingActionButton;
    @Bind(R.id.drawer_layout)
    DrawerLayout m_drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView m_navigationView;
    @Bind(R.id.id_recyclerView)
    RecyclerView m_recyclerView;
    @Bind(R.id.id_progressCircle)
    CircleProgress m_progressCircle;
    @Bind(R.id.id_image)
    SimpleDraweeView m_simpleDraweeView;
    @Bind(R.id.id_container)
    View m_progressContainer;
    SimpleDraweeView m_head;

    private MainAdapter m_mainAdapter;

    private ObjectAnimator m_buttonShow;
    private ObjectAnimator m_buttonHide;

    @Inject
    MainActivityPresenter m_mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected IPresenter attachPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        m_mainActivityPresenter.attachView(this);
        return m_mainActivityPresenter;
    }

    private void init() {

        setupViews();
        initActionBar();
        initDrawerLayout();
        initNavigationView();
        initRecyclerView();
        initAnimations();
        initFeedback();
    }

    private void initFeedback() {
        m_mainActivityPresenter.getDevResponse();
    }

    private void initHead() {
        m_head = (SimpleDraweeView) m_navigationView.getHeaderView(0).findViewById(R.id.id_head);
    }

    private void setupViews() {
        ButterKnife.bind(this);
        initHead();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initActionBar() {
        setSupportActionBar(m_toolbar);
        setTitle("未知城市");
    }

    @SuppressWarnings("deprecation")
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, m_drawerLayout, m_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        m_drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        m_navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 初始化动画
     */
    private void initAnimations() {

        final float srcY = m_floatingActionButton.getTranslationY();
        final float destY = 400 + srcY;

        final String property = "translationY";

        m_buttonHide = ObjectAnimator.ofFloat(m_floatingActionButton, property, srcY, destY);
        m_buttonShow = ObjectAnimator.ofFloat(m_floatingActionButton, property, destY, srcY);


        m_buttonHide.setDuration(DURATION_ANIMATOR);
        m_buttonShow.setDuration(DURATION_ANIMATOR);
    }

    @SuppressWarnings("deprecation")
    private void initRecyclerView() {
        m_recyclerView.setOnScrollListener(new WeatherScrollListener() {
            @Override
            public void onScroll2Up(RecyclerView recyclerView, int dx, int dy) {
                showButton();
            }

            @Override
            public void onScroll2Bottom(RecyclerView recyclerView, int dx, int dy) {
                hideButton();
            }
        });
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void hideButton() {
        if (m_buttonShow.isRunning()) {
            m_buttonShow.cancel();
        }
        m_buttonHide.start();
    }

    private void showButton() {
        if (m_buttonHide.isRunning()) {
            m_buttonHide.cancel();
        }
        m_buttonShow.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_mainActivityPresenter.loadThemeResource();
        m_mainActivityPresenter.fetchWeatherInfo();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            m_mainActivityPresenter.scheduleInflateSetting();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            m_mainActivityPresenter.feedback();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.fab)
    void onFloatingActionButtonClick() {
        m_mainActivityPresenter.refresh();
    }

    @Override
    public void showProgressing(int progress) {
        if (m_progressCircle.isShown()) {
            m_progressCircle.setVisibility(View.VISIBLE);
        }
        m_progressCircle.setCurrentProgress(progress);
    }

    @Override
    public void dismissProgressing() {
        m_progressContainer.setVisibility(View.GONE);
    }

    @Override
    public void showSimpleMessage(String message) {
        Snackbar.make(m_floatingActionButton, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showResponse(MainAdapter mainAdapter) {
        m_mainAdapter = mainAdapter;
        m_recyclerView.setAdapter(m_mainAdapter);
    }

    @Override
    public void setBackground(Uri uri) {
        m_simpleDraweeView.setImageURI(uri);
    }

    @Override
    public void setPrimaryColor(int color) {
        m_collapsingToolbarLayout.setContentScrimColor(color);
    }

    @Override
    public void showSunriseTheme() {
        setSunriseTheme();
    }

    @Override
    public void showSunsetTheme() {
        setSunsetTheme();
    }

    @Override
    public void notifyChanged() {
        if (m_mainAdapter != null) {
            m_mainAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setHead(Uri uri) {
        m_head.setImageURI(uri);
    }

    @Override
    public void setTitle(String title) {
        m_collapsingToolbarLayout.setTitle(title);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleRequestErrorEvent(MainErrorEvent errorEvent) {
        Toast.makeText(this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleResponseEvent(WeatherResponse weatherResponse) {
        m_mainActivityPresenter.handleResponse(weatherResponse);
    }
}
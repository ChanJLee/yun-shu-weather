package com.chan.chanweather.view.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.chan.chanweather.R;
import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.bus.SettingCityEvent;
import com.chan.chanweather.bus.SettingProvinceEvent;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.presenter.impl.SettingPresenter;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.chan.chanweather.view.interfaces.ISettingView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements ISettingView {

    private ProgressDialog m_progressDialog;
    @Inject
    SettingPresenter m_settingPresenter;

    @Bind(R.id.id_province_spinner)
    AppCompatSpinner m_province;
    @Bind(R.id.id_city_spinner)
    AppCompatSpinner m_city;
    @Bind(R.id.id_container)
    View m_container;
    Toolbar m_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    @Override
    protected IPresenter attachPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        m_settingPresenter.attachView(this);
        return m_settingPresenter;
    }

    private void initProgressDialog() {
        m_progressDialog = new ProgressDialog(this);
        m_progressDialog.setCancelable(false);
        m_progressDialog.setTitle("提示");
    }

    private void init() {
        ButterKnife.bind(this);
        initProgressDialog();
        initSpinner();
        initStatusBar();
        initData();
        initToolbar();
    }

    private void initToolbar() {

        m_toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(m_toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("设置");
            actionBar.setDisplayShowTitleEnabled(true);
        }

        m_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackViewClicked();
            }
        });
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager systemBarTintManager = getActivityComponent().getSystemBarTintManager();
            systemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorSetting));
            systemBarTintManager.setStatusBarTintEnabled(true);
        }
    }

    private void initData() {
        m_settingPresenter.loadData();
    }

    private void initSpinner() {
        m_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                m_settingPresenter.onItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showSimpleMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressing(String message) {
        m_progressDialog.setMessage(message);
        if (!m_progressDialog.isShowing()) {
            m_progressDialog.show();
        }
    }

    @Override
    public void dismissProgressing() {
        if (m_progressDialog.isShowing()) {
            m_progressDialog.dismiss();
        }
    }

    @Override
    public void finishShow() {
        finish();
    }

    @Override
    public void warningAboutModifyNotSaved() {
        Snackbar.make(m_container, "您尚未保存修改，是否保存？", Snackbar.LENGTH_SHORT)
                .setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setCityEvent(SettingCityEvent event) {
        m_city.setAdapter(event.getSpinnerAdapter());
        setCityPosition(event.getPosition());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setProvinceEvent(SettingProvinceEvent event) {
        m_province.setAdapter(event.getSpinnerAdapter());
        setProvincePosition(event.getPosition());
    }

    private void setCityPosition(int position) {
        m_city.setSelection(position);
    }

    private void setProvincePosition(int provincePosition) {
        m_province.setSelection(provincePosition);
    }

    private void onBackViewClicked() {
        onBackEventFire();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setProvinceAdapterEvent(SpinnerAdapter adapter) {
        m_province.setAdapter(adapter);
    }

    public static void invokeSetting(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 预留
     */
    @OnClick(R.id.id_clear_data)
    void clearCache() {
        Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        onBackEventFire();
    }

    void saveSetting() {
        m_settingPresenter.saveCity((String) m_province.getSelectedItem(), (String) m_city.getSelectedItem());
    }

    private void onBackEventFire() {
        m_settingPresenter.scheduleFinish((String) m_city.getSelectedItem(), (String) m_province.getSelectedItem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if(id == R.id.id_save) {
            saveSetting();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.id_about)
    void onAboutClick() {
        AboutActivity.invokeAbout(this);
    }
}

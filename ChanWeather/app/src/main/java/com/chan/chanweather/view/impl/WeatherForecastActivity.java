package com.chan.chanweather.view.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chan.chanweather.R;
import com.chan.chanweather.base.BaseActivity;
import com.chan.chanweather.bean.WeatherHolder;
import com.chan.chanweather.injector.component.ActivityComponent;
import com.chan.chanweather.presenter.impl.WeatherForecastPresenter;
import com.chan.chanweather.presenter.interfaces.IPresenter;
import com.chan.chanweather.view.interfaces.IWeatherForecastView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends BaseActivity implements IWeatherForecastView {
    private static final String EXTRA_JSON = "json";

    @Bind(R.id.id_toolbar)
    Toolbar m_toolbar;
    @Bind(R.id.id_forecastContainer)
    LinearLayout m_linearLayout;
    @Inject
    WeatherForecastPresenter m_weatherForecastPresenter;
    @Bind(R.id.id_background)
    SimpleDraweeView m_simpleDraweeView;
    @Bind(R.id.id_chart)
    BarChart m_barChart;
    @Bind(R.id.id_line_chart)
    LineChart m_lineChart;
    private WeatherForecastTheme m_weatherForecastTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);

        init();
    }

    private void init() {
        ButterKnife.bind(this);

        initActionBar();
        initTheme();
        initChart();
        initData();
    }

    private void initChart() {
        initTemperatureChart();
        initHumidityChart();
    }

    @SuppressWarnings("deprecation")
    private void initHumidityChart() {

        m_lineChart.setDescription("");
        m_lineChart.setBackgroundColor(getResources().getColor(R.color.nothing));

        // enable touch gestures
        m_lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        m_lineChart.setDragEnabled(true);
        m_lineChart.setScaleEnabled(false);
        m_lineChart.setScaleXEnabled(false);
        m_lineChart.setScaleYEnabled(false);

        m_lineChart.setPinchZoom(false);

        m_lineChart.setDrawGridBackground(false);

        XAxis x = m_lineChart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setEnabled(false);
        x.setSpaceBetweenLabels(1);

        m_lineChart.getAxisRight().setEnabled(false);

        Legend l = m_lineChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.LINE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }

    private void initTemperatureChart() {
        m_barChart.setDrawBarShadow(false);
        m_barChart.setDrawValueAboveBar(true);

        m_barChart.setDescription("");
        m_barChart.setDrawBarShadow(false);

        m_barChart.setDrawGridBackground(false);
        m_barChart.setTouchEnabled(false);
        m_barChart.setScaleXEnabled(false);
        m_barChart.setScaleYEnabled(false);

        XAxis xAxis = m_barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(1);

        Legend l = m_barChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }

    private void initData() {
        m_weatherForecastPresenter.getWeather();
        m_weatherForecastPresenter.getTemperature();
        m_weatherForecastPresenter.getHumidity();
    }

    private void initTheme() {
        m_weatherForecastPresenter.initTheme();
    }

    void initActionBar() {
        setSupportActionBar(m_toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        m_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected IPresenter attachPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        m_weatherForecastPresenter.attachView(this);
        return m_weatherForecastPresenter;
    }


    public static void invokeForecast(Context context, String json) {
        Intent intent = new Intent(context, WeatherForecastActivity.class);
        intent.putExtra(EXTRA_JSON, json);
        context.startActivity(intent);
    }

    @Override
    public void setSunsetTheme() {
        m_weatherForecastTheme = new WeatherForecastNightTheme();
        m_weatherForecastTheme.fire();
    }

    @Override
    public void setSunriseTheme() {
        m_weatherForecastTheme = new WeatherForecastDayTheme();
        m_weatherForecastTheme.fire();
    }

    @Override
    public void showHumidity(LineData humidity) {
        m_lineChart.setData(humidity);
    }

    @Override
    public void showSimpleMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBackground(Uri uri) {
        m_simpleDraweeView.setImageURI(uri);
    }

    @Override
    public String getExtraJson() {
        return getIntent().getStringExtra(EXTRA_JSON);
    }

    @Override
    public void showWeatherItems(List<WeatherHolder> forecastHolders) {
        final int count = Math.min(m_linearLayout.getChildCount(), forecastHolders.size());
        for (int i = 0; i < count; ++i) {
            View v = m_linearLayout.getChildAt(i);
            SimpleDraweeView simple = (SimpleDraweeView) v.findViewById(R.id.id_icon);
            WeatherHolder holder = forecastHolders.get(i);
            simple.setImageURI(holder.getImageReference());
            TextView text = (TextView) v.findViewById(R.id.id_details);
            text.setText(holder.getDescription());
        }
    }

    @Override
    public void showTemperature(BarData data) {
        m_barChart.setData(data);
    }

    private interface WeatherForecastTheme {
        void fire();
    }

    private final class WeatherForecastDayTheme implements WeatherForecastTheme {

        @Override
        public void fire() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                ActivityComponent activityComponent = getActivityComponent();
                SystemBarTintManager systemBarTintManager = activityComponent.getSystemBarTintManager();
                systemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorForecastDay));
                systemBarTintManager.setStatusBarTintEnabled(true);
            }

            setBackground(Uri.parse("res:///" + R.mipmap.city_day));
        }
    }

    private final class WeatherForecastNightTheme implements WeatherForecastTheme {

        @Override
        public void fire() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                ActivityComponent activityComponent = getActivityComponent();
                SystemBarTintManager systemBarTintManager = activityComponent.getSystemBarTintManager();
                systemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorForecastNight));
                systemBarTintManager.setStatusBarTintEnabled(true);
            }

            setBackground(Uri.parse("res:///" + R.mipmap.city_night));
        }
    }
}

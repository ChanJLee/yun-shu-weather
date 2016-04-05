package com.chan.chanweather.model.init;

import android.content.Context;

import com.chan.chanweather.BuildConfig;
import com.chan.chanweather.injector.annotation.ContextLife;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

/**
 * Created by chan on 16/3/26.
 */
public class BaseManager {
    private Context m_context;

    @Inject
    public BaseManager(@ContextLife("application") Context context) {
        m_context = context;
    }

    public void init() {
        Fresco.initialize(m_context);
        CrashReport.initCrashReport(m_context, BuildConfig.BUGLY_APPID, BuildConfig.IS_DEBUG);
        PushManager.getInstance().initialize(m_context);
        MobclickAgent.setDebugMode(BuildConfig.IS_DEBUG);
    }
}

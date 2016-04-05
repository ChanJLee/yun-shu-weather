package com.chan.chanweather.injector.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.annotation.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chan on 16/2/27.
 */
@Module
public class FragmentModule {
    private Fragment m_fragment;

    public FragmentModule(Fragment fragment) {
        m_fragment = fragment;
    }

    @PerFragment
    @Provides
    public Fragment provideFragment() {
        return m_fragment;
    }

    @PerFragment
    @Provides
    public Activity provideActivity() {
        return m_fragment.getActivity();
    }

    @PerFragment
    @Provides
    @ContextLife("fragment")
    public Context provideContext() {
        return m_fragment.getActivity();
    }
}

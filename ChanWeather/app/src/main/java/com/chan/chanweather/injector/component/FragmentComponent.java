package com.chan.chanweather.injector.component;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.chan.chanweather.injector.annotation.ContextLife;
import com.chan.chanweather.injector.annotation.PerFragment;
import com.chan.chanweather.injector.module.FragmentModule;

import dagger.Component;

/**
 * Created by chan on 16/2/27.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("application")
    Context getApplicationContext();

    @ContextLife("fragment")
    Context getFragmentContext();

    Fragment getFragment();
    Activity getActivity();
}

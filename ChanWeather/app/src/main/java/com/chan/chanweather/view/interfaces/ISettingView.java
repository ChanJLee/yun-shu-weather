package com.chan.chanweather.view.interfaces;

/**
 * Created by chan on 16/3/29.
 */
public interface ISettingView extends IView {
    void showProgressing(String message);
    void dismissProgressing();
    void finishShow();
    void warningAboutModifyNotSaved();
}

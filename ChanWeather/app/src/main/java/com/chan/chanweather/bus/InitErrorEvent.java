package com.chan.chanweather.bus;

/**
 * Created by chan on 16/3/27.
 */
public class InitErrorEvent {

    private String m_message;
    private Throwable m_throwable;

    public InitErrorEvent(String message, Throwable throwable) {
        m_message = message;
        m_throwable = throwable;
    }

    public String getMessage() {
        return m_message;
    }

    public Throwable getThrowable() {
        return m_throwable;
    }
}

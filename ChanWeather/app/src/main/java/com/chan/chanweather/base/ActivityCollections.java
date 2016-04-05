package com.chan.chanweather.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chan on 16/3/26.
 */
public class ActivityCollections {
    private static List<Activity> s_activityList;

    public static void addActivity(Activity activity) {
        getActivityList().add(activity);
    }

    public static void removeActivity(Activity activity) {
        getActivityList().remove(activity);
    }

    public static void finishAll() {
        List<Activity> activities = getActivityList();
        final int size = activities.size();
        for(int i = 0; i < size; ++i) {
            final Activity activity = activities.get(i);
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    private static List<Activity> getActivityList() {
        if(s_activityList == null) {
            s_activityList = new ArrayList<>();
        }
        return s_activityList;
    }
}

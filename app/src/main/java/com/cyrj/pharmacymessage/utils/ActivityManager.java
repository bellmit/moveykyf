package com.cyrj.pharmacymessage.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by LiJiangLong on 2017/8/7.
 */

public class ActivityManager {

    private static final String TAG = "ActivityManager";
    private static ActivityManager instance;
    private static Stack<Activity> hashSet;

    private ActivityManager(){
    }

    public static ActivityManager getActivityManager(){
        if(instance == null){
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivityclass(Class<?> cls) {
            for (Activity activity : hashSet) {
                if (activity.getClass().equals(cls)) {
                    this.hashSet.remove(activity);
                    finishActivity(activity);
                    break;
                }
            }

    }
    /**
     * 结束指定的Activity
     *
     * @param activity
     */

    public void finishActivity(Activity activity) {

        if (activity != null) {
            this.hashSet.remove(activity);
            activity.finish();
        }
    }
    private boolean isCunzai;
    public boolean haveAativity(Class cls){
            for (Activity activity : hashSet) {
                if (activity.getClass().equals(cls)) {
                    isCunzai=true;
                    break;
                }
        }
        return isCunzai;
    }
    //退出栈中除指定的Activity外所有
    public void popAllActivityExceptOne(Class cls) {
        if (hashSet != null) {
            for (Activity activity : hashSet) {
                if (!activity.getClass().equals(cls)) {
                    this.hashSet.remove(activity);
                    finishActivity(activity);

                }
            }
        }
    }



    /**
     * 每一个Activity 在 onCreate 方法的时候，可以装入当前this
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            if (hashSet==null){
                hashSet = new Stack<Activity>();
            }
            hashSet.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获得当前栈顶Activity
    public static Activity currentActivity(){
        if(hashSet == null||hashSet.empty()){
            return null;
        }
        return hashSet.lastElement();
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = hashSet.size(); i < size; i++) {
            if (null != hashSet.get(i)) {
                hashSet.get(i).finish();
            }
        }
        hashSet.clear();
    }
    /**
     * 应用程序退出
     */
    @SuppressLint("MissingPermission")
    public void exit(Context context) {
        try {
            finishAllActivity();
            @SuppressLint("ServiceCast")
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }

}

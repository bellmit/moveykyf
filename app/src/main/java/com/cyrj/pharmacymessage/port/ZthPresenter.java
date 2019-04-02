package com.cyrj.pharmacymessage.port;

/**
 * activity与ptersenter交互的接口，这个接口用来通知persenter进行数据初始化
 */
public interface ZthPresenter {
    /**
     * 初始化方法
     */
    void onCreate();
    /**
     * 初始化交互接口
     */
    void onView(RxView rxView);
}

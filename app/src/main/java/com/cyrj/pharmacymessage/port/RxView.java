package com.cyrj.pharmacymessage.port;

/**
 * persenter与activity交互接口，用来persenter向activity传递数据，通知页面更新
 */
public interface RxView<T> {
    void success(T dateil);
    void fail(String errMessage);
}

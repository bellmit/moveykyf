package com.cyrj.pharmacymessage.utils;

import com.cyrj.pharmacymessage.MyApplication;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求网络公共类
 */

public class RetrofitUtils {
    private static RetrofitUtils instance;
    private Retrofit retrofit;
    private static HttpLoggingInterceptor log;

    public static Api get() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance.service();
    }

    public Api service() {
        return retrofit.create(Api.class);
    }
    public RetrofitUtils() {
        if (retrofit == null) {
            log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit = new Retrofit.Builder()

                    .baseUrl(MyApplication.baseUrl)
                    .client(genericClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
    }
    public static OkHttpClient genericClient() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(log)
                .build();


        return httpClient;
    }
}

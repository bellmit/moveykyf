package com.cyrj.pharmacymessage.utils;


import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.bean.ChukuHistoryBean;
import com.cyrj.pharmacymessage.pojo.RuKu01;
import com.cyrj.pharmacymessage.bean.RukuDetailsBean;
import com.cyrj.pharmacymessage.bean.RukuHistoryBean;
import com.cyrj.pharmacymessage.bean.RukuWayBean;
import com.cyrj.pharmacymessage.bean.UserBean;
import com.cyrj.pharmacymessage.bean.YpcdBean;
import com.cyrj.pharmacymessage.bean.YpxxBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 网络请求的API接口
 */

public interface Api {
    /**
     * 登录
     * @param map
     * @return
     */
    @POST("login/login")
    Observable<BaseBean<UserBean>> login(@Body Map<String,String> map);

    /**
     * 查询入库历史
     * @param userId 操作工号
     * @param jgid
     * @param yksb 入库识别
     * @return
     */
    @FormUrlEncoded
    @POST("auth/rk_history")
    Observable<BaseBean<List<RukuHistoryBean>>>selectRukuHistory(@Field("czgh") String userId,
                                                                 @Field("jgid") Integer jgid,
                                                                 @Field("yksb") Integer yksb);

    /**
     * 获取入库方式
     * @param jgid
     * @param yksb
     * @return
     */
    @FormUrlEncoded
    @POST("auth/rk_way")
    Observable<BaseBean<List<RukuWayBean>>>getRukuWay(@Field("jgid") Integer jgid,
                                                      @Field("yksb") Integer yksb);

    /**
     * 开始入库，插入入库01，入库02
     * @param
     * @return
     */
    @POST("auth/rk_inser_ruku")
    Observable<BaseBean>ruku(@Body RuKu01 ruKu01);

    /**
     * 获取入库产地
     * @param jgid 机构ID
     * @param ypxh 药品序号
     * @param ypcd 药品产地
     * @return
     */
    @FormUrlEncoded
    @POST("auth/yk_getYpcd")
    Observable<BaseBean<YpcdBean>>getYpcd(@Field("jgid")Integer jgid,
                                      @Field("ypxh")Integer ypxh,
                                      @Field("ypcd")Integer ypcd);

    /**
     * 获取历史详情
     * @param jgid
     * @param yksb
     * @param rkdh
     * @param rkfs
     * @return
     */
    @FormUrlEncoded
    @POST("auth/rk_history_details")
    Observable<BaseBean<List<RukuDetailsBean>>>getRukuHistoryDetails(@Field("jgid")String jgid,
                                                                     @Field("yksb")Integer yksb,
                                                                     @Field("rkdh")String rkdh,
                                                                     @Field("rkfs")String rkfs);
    /**
     * 获取药品信息的集合
     */
    @POST("auth/ypxx_list")
    Observable<BaseBean<YpxxBean>>getYpmlList();

    /**
     * 获取出库历史列表
     */
    @FormUrlEncoded
    @POST("auth/chuku_history")
    Observable<BaseBean<ChukuHistoryBean>> getChukulist(@Field("czgh")String czgh,
                                                              @Field("jgid")Integer jgid,
                                                              @Field("yksb") Integer yksb);

}

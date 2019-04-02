package com.cyrj.pharmacymessage.presenter;

import com.cyrj.pharmacymessage.base.BaseCallBack;
import com.cyrj.pharmacymessage.base.BasePresenter;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.pojo.RuKu01;
import com.cyrj.pharmacymessage.bean.RukuWayBean;
import com.cyrj.pharmacymessage.bean.YpcdBean;
import com.cyrj.pharmacymessage.help.UIUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 入库
 */
public class RukuPresenter extends BasePresenter {

    /**
     * 获取入库方式
     */
    public void getRukuWay(String jgid,Integer yksb){
        api.getRukuWay(Integer.valueOf(jgid),yksb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean<List<RukuWayBean>>>(mActivity) {
                    @Override
                    public void onSuccess(BaseBean<List<RukuWayBean>> data) {
                        if (data.isSuccess()){
                            data.setType(0);
                            rxView.success(data);
                        }else{
                            rxView.fail(data.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        UIUtil.showToast(error);
                    }
                });
    }
    /**
     * 开始入库
     */
    public void ruku(RuKu01 ruKu01){
                api.ruku(ruKu01)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean>(mActivity) {
                    @Override
                    public void onSuccess(BaseBean data) {
                        if (data.isSuccess()){
                            data.setType(1);
                            rxView.success(data);
                        }else{
                            rxView.fail(data.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        UIUtil.showToast(error);
                    }
                });
    }

    /**
     * 获取药品产地
     * @param jgid 机构ID
     * @param ypxh 药品序号
     * @param ypcd 药品产地
     */
    public void getYpcd(int jgid,int ypxh,int ypcd){
        api.getYpcd(jgid, ypxh, ypcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean<YpcdBean>>(mActivity) {
                    @Override
                    public void onSuccess(BaseBean<YpcdBean> data) {
                        if (data.isSuccess()){
                            rxView.success(data.getData());
                        }else{
                            rxView.fail(data.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        rxView.fail(error);
                    }
                });
    }
}

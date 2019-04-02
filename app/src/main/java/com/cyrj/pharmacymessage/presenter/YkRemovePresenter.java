package com.cyrj.pharmacymessage.presenter;

import com.cyrj.pharmacymessage.base.BaseCallBack;
import com.cyrj.pharmacymessage.base.BasePresenter;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.bean.ChukuHistoryBean;
import com.cyrj.pharmacymessage.bean.YpxxBean;
import com.cyrj.pharmacymessage.help.UIUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 出库
 */
public class YkRemovePresenter extends BasePresenter{
    public void getCkhistory(String czgh,Integer jgid,Integer yksb){
        api.getChukulist(czgh, jgid, yksb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean<ChukuHistoryBean>>() {
                    @Override
                    public void onSuccess(BaseBean<ChukuHistoryBean> data) {
                        if (data.isSuccess()) {
                            data.setType(0);
                            rxView.success(data);
                        }else{
                            UIUtil.showToast(data.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        rxView.fail(error);
                    }
                });

    }
    /**
     * 获取药品信息列表
     */
    public void getYpxxList(){
        api.getYpmlList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean<YpxxBean>>() {
                    @Override
                    public void onSuccess(BaseBean<YpxxBean> data) {
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
}

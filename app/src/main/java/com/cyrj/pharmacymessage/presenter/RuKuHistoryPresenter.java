package com.cyrj.pharmacymessage.presenter;

import com.cyrj.pharmacymessage.base.BaseCallBack;
import com.cyrj.pharmacymessage.base.BasePresenter;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.bean.RukuDetailsBean;
import com.cyrj.pharmacymessage.bean.RukuHistoryBean;
import com.cyrj.pharmacymessage.bean.YpxxBean;
import com.cyrj.pharmacymessage.help.UIUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RuKuHistoryPresenter extends BasePresenter{
    /**
     * 查询数据库历史列表Observable ypLits=api.getYpmlList();
     */
    public void selectRukuHistoryList(String idCode,String jgid,Integer yksb){
        api.selectRukuHistory(idCode,Integer.valueOf(jgid),yksb)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseCallBack<BaseBean<List<RukuHistoryBean>>>(mActivity) {
                    @Override
                    public void onSuccess(BaseBean<List<RukuHistoryBean>> data) {
                        if (data.isSuccess()) {
                            data.setType(0);
                            rxView.success(data);
                        }else{
                            UIUtil.showToast(data.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        UIUtil.showToast(error);
                    }
                });
    }
    /**
     * 查询入库历史点击详情
     */
    public void selectRukuHistoryDetails(String rkfs,String rkdh,int yksb,String jgid){
        api.getRukuHistoryDetails(jgid,yksb,rkdh,rkfs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean<List<RukuDetailsBean>>>(mActivity) {
                    @Override
                    public void onSuccess(BaseBean<List<RukuDetailsBean>> data) {
                        if (data.isSuccess()) {
                            rxView.success(data.getData());
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

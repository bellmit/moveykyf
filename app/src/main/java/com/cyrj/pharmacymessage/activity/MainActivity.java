package com.cyrj.pharmacymessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.base.BaseActivity;
import com.cyrj.pharmacymessage.base.BaseFragment;
import com.cyrj.pharmacymessage.bean.UserBean;
import com.cyrj.pharmacymessage.bean.YkTypeBean;
import com.cyrj.pharmacymessage.databinding.ActivityMainBinding;
import com.cyrj.pharmacymessage.fragment.HomeFragment;
import com.cyrj.pharmacymessage.fragment.YFPutawayFragment;
import com.cyrj.pharmacymessage.fragment.YKRemoveFragment;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.presenter.RukuPresenter;
import com.cyrj.pharmacymessage.utils.ActivityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends BaseActivity<RukuPresenter,ActivityMainBinding> {

    private List<BaseFragment> baseFragments;
    private FragmentManager fm;
    private FrameLayout frameLayout;
    private long mExitTime;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout mDlNavButtom;
    private UserBean userBean;
    private Set<String> testSet;
    private List<YkTypeBean> tklmList;
    private View headNv;
    private TextView userName;
    private TextView tv_nav_txt;
    private int tagId=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initLisener() {
        mBindingView.nvButtom.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case 1:
                        if (tagId!=id){
                            //点击药库入库
                            loagFragment(currentFragment,baseFragments.get(0));
                        }
                        break;
                    case 2:
                        //点击药库出库
                        if (tagId!=id){
                            //点击药库入库
                            loagFragment(currentFragment,baseFragments.get(1));
                        }
                        break;
                    case 3:
                        //药房上架
                        if (tagId!=id){
                            //点击药库入库
                            loagFragment(currentFragment,baseFragments.get(2));
                        }
                        break;
                    case 4:
                        //药房发药
                        break;
                    case 5:
                        //物流运输
                        break;
                }
                tagId=id;
                mDlNavButtom.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
    }
    @Override
    public void initView() {
        hideTitleBar();
        mDlNavButtom = mBindingView.dlNavButtom;
        Intent intent=getIntent();
        userBean = intent.getParcelableExtra("qxkzList");
        headNv = LayoutInflater.from(this).inflate(R.layout.layout_nav_header,mBindingView.nvButtom,false);
        mBindingView.nvButtom.addHeaderView(headNv);
        userName = headNv.findViewById(R.id.tv_user_name);
        tv_nav_txt = headNv.findViewById(R.id.tv_nav_txt);
        initData();
    }
    private void initData(){
        testSet = new HashSet<String>();
        tklmList = new ArrayList<>();
        baseFragments=new ArrayList<>();
        baseFragments.add(new HomeFragment(tklmList));
        baseFragments.add(new YKRemoveFragment(tklmList));
        baseFragments.add(new YFPutawayFragment());
        loagFragment(null,baseFragments.get(0));

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,mDlNavButtom,null,0,0);
        mDlNavButtom.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //权限列表
        Menu menu= mBindingView.nvButtom.getMenu();
        for (int i=0;i<userBean.getQxkzList().size();i++){
            testSet.add(userBean.getQxkzList().get(i).getYWLB());
            if (userBean.getQxkzList().get(i).getYWLB().equals("药库")){
                for (YkTypeBean ykTypeBean:userBean.getYkTypeBeanList()){
                    if (userBean.getQxkzList().get(i).getKSDM()==ykTypeBean.getYKSB()){
                        tklmList.add(ykTypeBean);
                    }

                }
            }
        }
        userName.setText(userBean.getUserName());
        tv_nav_txt.setText(userBean.getUserId());

            if (testSet.contains("药库")){
                menu.add(1,1,1,getResources().getString(R.string.in_storage));
                menu.add(2,2,2,getResources().getString(R.string.out_storage));
            }
            if (testSet.contains("药房")){
                menu.add(3,3,3,"药房上架");
                menu.add(4,4,4,"药房发药");
            }
            if (testSet.contains("病区")){
                menu.add(5,5,5,"物流运输");
            }

    }


    private BaseFragment currentFragment;
    //点击按钮切换fragment
    private void loagFragment(BaseFragment from, BaseFragment to) {
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (from == null) {
            //第一次进入时添加默认的fragment
            ft.add(R.id.main_framelayout, to).commit();
            currentFragment = to;
        } else {

            if (from != to) {
                //如果不相等的时候说明是切换fragment
                if (to.isAdded()) {
                    //没有添加过了就将点击处的fragment替换之前的
                    ft.hide(from).show(to).commit();
                } else {
                    ft.hide(from).add(R.id.main_framelayout, to).commit();
                }
                currentFragment = to;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                UIUtil.showToast("再按一次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                ActivityManager.getActivityManager().exit(getApplicationContext());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

package com.cyrj.pharmacymessage.fragment;


import android.support.v4.app.Fragment;

import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.base.BaseFragment;
import com.cyrj.pharmacymessage.databinding.FragmentYfputawayBinding;
import com.cyrj.pharmacymessage.presenter.YFPutawayPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class YFPutawayFragment extends BaseFragment<YFPutawayPresenter,FragmentYfputawayBinding> {


    public YFPutawayFragment() {
        // Required empty public constructor
    }


    @Override
    public int setContent() {
        return R.layout.fragment_yfputaway;
    }

    @Override
    protected void initView() {
        hideBackImg();
        showMenu();
    }

    @Override
    protected void initPresenter() {

    }

}

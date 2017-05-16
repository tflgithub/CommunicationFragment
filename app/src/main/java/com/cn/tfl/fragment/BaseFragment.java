package com.cn.tfl.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.cn.tfl.BaseActivity;
import com.cn.tfl.utils.Functions;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected BaseActivity mBaseActivity;

    /**
     * 函数的集合
     */
    protected Functions mFunctions;

    /**
     * activity调用此方法进行设置Functions
     *
     * @param functions
     */
    public void setFunctions(Functions functions) {
        this.mFunctions = functions;
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
            mBaseActivity.setFunctionsForFragment(getId());
        }
        super.onAttach(context);
    }
}

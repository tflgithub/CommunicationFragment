package com.cn.tfl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BaseActivity extends AppCompatActivity {


    /**
     * 为fragment设置functions，具体实现子类来做
     * @param fragmentId
     */
    public void setFunctionsForFragment(int fragmentId){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

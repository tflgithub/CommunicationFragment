package com.cn.tfl;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.cn.tfl.fragment.BaseFragment;
import com.cn.tfl.fragment.BlankFragment;
import com.cn.tfl.utils.Functions;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setFunctionsForFragment(int fragmentId) {
        super.setFunctionsForFragment(fragmentId);
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) fm.findFragmentById(fragmentId);
        fragment.setFunctions(
                new Functions().addFunction(new Functions.FunctionNoParamAndResult(BlankFragment.FUNCTION_NO_PARAM_NO_RESULT) {
                    @Override
                    public void function() {
                        Toast.makeText(MainActivity.this, "成功调用无参无返回值方法", Toast.LENGTH_LONG).show();
                    }
                }).addFunction(new Functions.FunctionWithParamNoResult<String>(BlankFragment.FUNCTION_WITH_PARAM_NO_RESULT) {
                    @Override
                    public void function(String s) {
                        Toast.makeText(MainActivity.this, "成功调用有参无返回值方法,参数：" + s, Toast.LENGTH_LONG).show();
                    }
                }).addFunction(new Functions.FunctionNoParamWithResult<String>(BlankFragment.FUNCTION_NO_PARAM_WITH_RESULT) {
                    @Override
                    public String function() {
                        return "我是返回值";
                    }
                }).addFunction(new Functions.FunctionWithParamAndResult<String, String>(BlankFragment.FUNCTION_WITH_PARAM_AND_RESULT) {

                    @Override
                    public String function(String s) {
                        Toast.makeText(MainActivity.this, "成功调用有参有返回值方法,参数：" + s, Toast.LENGTH_LONG).show();
                        return "我是返回值";
                    }
                }).addFunction(new Functions.FunctionWithParamNoResult<Functions.FunctionParams>(BlankFragment.FUNCTION_HAS_MORE_PARAM_Bundle) {
                    @Override
                    public void function(Functions.FunctionParams functionParams) {
                        String name = functionParams.getString();
                        int age = functionParams.getInt();
                        boolean jige = functionParams.getBoolean();
                        Toast.makeText(MainActivity.this, "成功调用有多个参数方法" + "姓名：" + name + "年龄：" + age + "是否及格：" + jige, Toast.LENGTH_LONG).show();
                    }
                })
        );
    }
}

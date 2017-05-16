package com.cn.tfl.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cn.tfl.R;
import com.cn.tfl.utils.FunctionException;
import com.cn.tfl.utils.Functions;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {

    /**
     * 没有参数没有返回值的函数
     */
    public static final String FUNCTION_NO_PARAM_NO_RESULT = BlankFragment.class.getSimpleName() + "FUNCTION_NO_PARAM_NO_RESULT";

    /**
     * 有参数没有返回值的函数
     */
    public static final String FUNCTION_WITH_PARAM_NO_RESULT = BlankFragment.class.getSimpleName() + "FUNCTION_WITH_PARAM_NO_RESULT";
    /**
     * 没有参数有返回值的函数
     */
    public static final String FUNCTION_NO_PARAM_WITH_RESULT = BlankFragment.class.getSimpleName() + "FUNCTION_NO_PARAM_WITH_RESULT";
    /**
     * 有参数有返回值的函数
     */
    public static final String FUNCTION_WITH_PARAM_AND_RESULT = BlankFragment.class.getSimpleName() + "FUNCTION_WITH_PARAM_AND_RESULT";

    /**
     * 具有多个参数的函数
     */
    public static final String FUNCTION_HAS_MORE_PARAM_Bundle = BlankFragment.class.getSimpleName() + "FUNCTION_HAS_MORE_PARAM_Bundle";

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_no_param_and_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mFunctions.invokeFunc(FUNCTION_NO_PARAM_NO_RESULT);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        view.findViewById(R.id.button_with_param_no_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mFunctions.invokeFunc(FUNCTION_WITH_PARAM_NO_RESULT, "我是参数");
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        view.findViewById(R.id.button_no_param_with_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String str = mFunctions.invokeFunc(FUNCTION_NO_PARAM_WITH_RESULT, String.class);
                    Toast.makeText(getActivity(), "成功调用无参有返回值方法,返回值：" + str, Toast.LENGTH_LONG).show();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        view.findViewById(R.id.button_with_param_and_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String str = mFunctions.invokeFunc(FUNCTION_WITH_PARAM_AND_RESULT, String.class, "我是参数");
                    Toast.makeText(getActivity(), "成功调用有参有返回值方法,返回值：" + str, Toast.LENGTH_LONG).show();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        view.findViewById(R.id.button_with_more_param).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mFunctions.invokeFunc(FUNCTION_HAS_MORE_PARAM_Bundle, new Functions.FunctionParams.FunctionParamsBuilder().putString("张三")
                            .putInt(12).putBoolean(true).create());
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

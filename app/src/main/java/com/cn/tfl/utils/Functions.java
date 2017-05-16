package com.cn.tfl.utils;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Happiness on 2017/5/16.
 */

public class Functions {

    private HashMap<String, FunctionNoParamAndResult> mFunctionNoParamAndResult;
    private HashMap<String, FunctionWithParamNoResult> mFunctionWithParamNoResult;
    private HashMap<String, FunctionNoParamWithResult> mFunctionNoParamWithResult;
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;

    public static abstract class Function {
        public String mFunctionName;

        public Function(String functionName) {
            this.mFunctionName = functionName;
        }
    }

    //无参无返回
    public static abstract class FunctionNoParamAndResult extends Function {
        public FunctionNoParamAndResult(String functionName) {
            super(functionName);
        }

        public abstract void function();
    }

    //添加无参无返回方法
    public Functions addFunction(FunctionNoParamAndResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionNoParamAndResult == null) {
            mFunctionNoParamAndResult = new HashMap<>();
        }
        mFunctionNoParamAndResult.put(function.mFunctionName, function);
        return this;
    }

    //根据函数名，回调无参无返回值的函数
    public void invokeFunc(String funcName) throws FunctionException {
        FunctionNoParamAndResult f = null;
        if (mFunctionNoParamAndResult != null) {
            f = mFunctionNoParamAndResult.get(funcName);
            if (f != null) {
                f.function();
            }
        }

        if (f == null) {
            throw new FunctionException("没有此函数");
        }
    }


    //有参无返回
    public static abstract class FunctionWithParamNoResult<Param> extends Function {
        public FunctionWithParamNoResult(String functionName) {
            super(functionName);
        }

        public abstract void function(Param param);
    }

    //添加有参无返回方法
    public Functions addFunction(FunctionWithParamNoResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithParamNoResult == null) {
            mFunctionWithParamNoResult = new HashMap<>();
        }
        mFunctionWithParamNoResult.put(function.mFunctionName, function);
        return this;
    }

    //根据函数名，回调有参无返回值的函数
    public <Param> void invokeFunc(String funcName, Param param) throws FunctionException {
        FunctionWithParamNoResult f = null;
        if (mFunctionWithParamNoResult != null) {
            f = mFunctionWithParamNoResult.get(funcName);
            if (f != null) {
                f.function(param);
            }
        }
        if (f == null) {
            throw new FunctionException("没有此函数");
        }
    }

    //无参有返回
    public static abstract class FunctionNoParamWithResult<Result> extends Function {
        public FunctionNoParamWithResult(String functionName) {
            super(functionName);
        }

        public abstract Result function();
    }

    //添加无参有返回方法
    public Functions addFunction(FunctionNoParamWithResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionNoParamWithResult == null) {
            mFunctionNoParamWithResult = new HashMap<>();
        }
        mFunctionNoParamWithResult.put(function.mFunctionName, function);
        return this;
    }

    //根据函数名，回调无参有返回值的函数
    public <Result> Result invokeFunc(String funcName, Class<Result> c) throws FunctionException {
        FunctionNoParamWithResult f = null;
        if (mFunctionNoParamWithResult != null) {
            f = mFunctionNoParamWithResult.get(funcName);
            if (f != null) {
                if (c != null) {
                    return c.cast(f.function());
                } else {
                    return (Result) f.function();
                }
            }
        }
        if (f == null) {
            throw new FunctionException("没有此函数");
        }
        return null;
    }

    //有参有返回
    public static abstract class FunctionWithParamAndResult<Result, Param> extends Function {
        public FunctionWithParamAndResult(String functionName) {
            super(functionName);
        }

        public abstract Result function(Param param);
    }

    //添加有参有返回方法
    public Functions addFunction(FunctionWithParamAndResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithParamAndResult == null) {
            mFunctionWithParamAndResult = new HashMap<>();
        }
        mFunctionWithParamAndResult.put(function.mFunctionName, function);
        return this;
    }

    //根据函数名，回调有参有返回值的函数
    public <Result, Param> Result invokeFunc(String funcName, Class<Result> c, Param param) throws FunctionException {
        FunctionWithParamAndResult f = null;
        if (mFunctionWithParamAndResult != null) {
            f = mFunctionWithParamAndResult.get(funcName);
            if (f != null) {
                if (c != null) {
                    return c.cast(f.function(param));
                } else {
                    return (Result) f.function(param);
                }
            }
        }
        if (f == null) {
            throw new FunctionException("没有此函数");
        }
        return null;
    }


    /**
     * 函数的参数，当函数的参数涉及到多个值时，可以用此类，
     * 此类使用规则：存参数与取参数的顺序必须一致，否则报错
     */
    public static class FunctionParams {

        private Bundle mParams = new Bundle(1);
        private int mIndex = -1;
        private Map mObjectParams = new HashMap(1);

        FunctionParams(Bundle mParams, Map mObjectParams) {
            this.mParams = mParams;
            this.mObjectParams = mObjectParams;
        }

        public <Param> Param getObject(Class<Param> p) {
            if (mObjectParams == null) {
                return null;
            }
            return p.cast(mObjectParams.get((mIndex++) + ""));
        }

        /**
         * 获取int值
         *
         * @return
         */
        public int getInt() {
            if (mParams != null) {
                return mParams.getInt((mIndex++) + "");
            }
            return 0;
        }

        /**
         * 获取int值
         *
         * @param defalut
         * @return
         */
        public int getInt(int defalut) {
            if (mParams != null) {
                return mParams.getInt((mIndex++) + "");
            }
            return defalut;
        }

        /**
         * 获取字符串
         *
         * @param defalut
         * @return
         */
        public String getString(String defalut) {
            if (mParams != null) {
                return mParams.getString((mIndex++) + "");
            }
            return defalut;
        }

        /**
         * 获取字符串
         *
         * @return
         */
        public String getString() {
            if (mParams != null) {
                return mParams.getString((mIndex++) + "");
            }
            return null;
        }


        /**
         * 获取Boolean值
         *
         * @return 默认返回false
         */
        public boolean getBoolean() {
            if (mParams != null) {
                return mParams.getBoolean((mIndex++) + "");
            }
            return false;
        }

        /**
         * 该类用来创建函数参数
         */
        public static class FunctionParamsBuilder {
            private Bundle mParams;
            private int mIndex = -1;
            private Map mObjectParams = new HashMap(1);

            public FunctionParamsBuilder() {

            }

            public FunctionParamsBuilder putInt(int value) {
                if (mParams == null) {
                    mParams = new Bundle(2);
                }
                mParams.putInt((mIndex++) + "", value);
                return this;
            }

            public FunctionParamsBuilder putString(String value) {
                if (mParams == null) {
                    mParams = new Bundle(2);
                }
                mParams.putString((mIndex++) + "", value);
                return this;
            }

            public FunctionParamsBuilder putBoolean(boolean value) {
                if (mParams == null) {
                    mParams = new Bundle(2);
                }
                mParams.putBoolean((mIndex++) + "", value);
                return this;
            }

            public FunctionParamsBuilder putObject(Object value) {

                if (mObjectParams == null) {
                    mObjectParams = new HashMap(1);
                }
                mObjectParams.put((mIndex++) + "", value);
                return this;
            }

            public FunctionParams create() {
                FunctionParams instance = new FunctionParams(mParams, mObjectParams);
                return instance;
            }
        }
    }
}




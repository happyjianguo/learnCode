package com.test;

import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
  
public class Func {  
    private IFunction _obj;  
    private Method _method;  
    private Object[] _args;  //������Ҫ�Ĳ���������������(1��Ĭ�ϵĲ������������ͣ�2�����ݿ������еĲ�������������)  
    private int _preArgsNum;  
  
    public Func(IFunction obj, Method method, int preArgsNum, String... args) {  
        this._obj = obj;  
        this._method = method;  
        this._preArgsNum = preArgsNum;  
        this._args = new Object[args.length + preArgsNum];  
        System.arraycopy(args, 0, this._args, preArgsNum, args.length);  //�������ݿ������õĲ�������������  
    }  
  
    public Object call(Object... args) throws IllegalArgumentException, IllegalAccessException,  
            InvocationTargetException {  
  
        if (args.length != this._preArgsNum)  
            throw new IllegalArgumentException("Illegal number of the arguments, need " + this._preArgsNum + " but "  
                    + args.length + ".");  
        System.arraycopy(args, 0, this._args, 0, args.length);  //����Ĭ�ϵĲ�������������  
        return this._method.invoke(this._obj, this._args);  //���ò����������еĺ���  
  
    }  
  
}  

package com.test;

import java.lang.reflect.InvocationTargetException;  
import java.util.ArrayList;  
import java.util.List;  
  
public class ReflectMethod {  
      
  
    public static void main(String[] args) {  
        ConvertFunction convert = new ConvertFunction();  
        List<Func> dst = new ArrayList<Func>();  
        String funcStr = "convert_if_exist(\"field1\",\"field2\");convert_if_exist(\"field1\",\"field2\",\"field3\");";//��������ݿ��ж���������  
        Utils.initFunctions(convert, dst, funcStr);  
        for(int i = 0;i<dst.size();i++){  
            try {  
                dst.get(i).call("defaultKey","defaultValue");//���ã�Ĭ�ϵ�����������ʱ���룬�����ݿ������е�field1,field2�޹أ���ҵ�����  
            } catch (IllegalArgumentException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (InvocationTargetException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
}  

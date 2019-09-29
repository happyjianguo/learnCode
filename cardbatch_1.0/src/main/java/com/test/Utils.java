package com.test;

import java.lang.reflect.Method;  
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;  
  
import org.apache.commons.lang.StringUtils;  
  
public class Utils {  
    public static boolean initFunctions(IFunction obj,List<Func> dst,String funcStr){  
          
        if (StringUtils.isEmpty(funcStr) || StringUtils.isBlank(funcStr))  
            return true;  
        int PRE_ARGS_NUM = -1;  
        Class<?>[] PRE_ARGS_TYPE = null;  
        try {  
            PRE_ARGS_NUM = obj.getClass().getDeclaredField("PRE_ARGS_NUM").getInt(obj);  
            PRE_ARGS_TYPE = (Class<?>[]) obj.getClass().getDeclaredField("PRE_ARGS_TYPE").get(obj);  
        } catch (IllegalArgumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (NoSuchFieldException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        List<String> funcs = getFunctions(funcStr);  
        for (String func : funcs) {  
            String funcName = getFuncName(func);  
            String[] funcParams = getFuncParams(func);  
  
            Class<?>[] paramsType = new Class[funcParams.length + PRE_ARGS_NUM];  
            Arrays.fill(paramsType, PRE_ARGS_NUM, paramsType.length, String.class);  
            System.arraycopy(PRE_ARGS_TYPE, 0, paramsType, 0, PRE_ARGS_NUM);  
  
            try {  
                Method method = obj.getClass().getMethod(funcName, paramsType);  //���ݺ����� && �������ͣ��ҵ���Ӧ�ĺ���  
                  
                dst.add(new Func(obj, method, PRE_ARGS_NUM, funcParams));  
            } catch (SecurityException e) {  
                // TODO Auto-generated catch block  
                //LOG.error("Error when parse method " + funcName, e);  
                return false;  
            } catch (NoSuchMethodException e) {  
                // TODO Auto-generated catch block  
                //LOG.error("Error when parse method " + funcName, e);  
                return false;  
            } catch (IllegalArgumentException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
  
        return dst.size() > 0;  
    }  
    public static List<String> getFunctions(String funcStr){  
        List<String> ret = new ArrayList<String>();  
          
        if (StringUtils.isEmpty(funcStr) || StringUtils.isBlank(funcStr))  
            return ret;  
          
        int preIndex = 0; // ��ȡ����ָ��  
        boolean in = false; // ������չ��ȡ�����еĲ���  
        for (int i = 0; i <= funcStr.length();) {  
            if (!in && (i == funcStr.length() || funcStr.charAt(i) == ';') && preIndex != i) {  
                String func = funcStr.substring(preIndex, i).trim();  
                if (StringUtils.isNotEmpty(func) && StringUtils.isNotBlank(func)) {  
                    ret.add(func);  
                }  
  
                i = i + 1;  
                preIndex = i;  
                continue;  
            }  
  
            if (i < funcStr.length() && funcStr.charAt(i) == '\"' && (i - 1 < 0 || funcStr.charAt(i - 1) != '\\'))  
                in ^= true;  
  
            i = i + 1;  
        }  
  
        return ret;  
    }  
    public static String[] getFuncParams(String func) {  
        int idx = func.indexOf('(');  
        if (idx != -1) {  
            String params = func.substring(idx + 1, func.length() - 1);  
            int count = getParamsCount(params);  
            String[] args = new String[count];  
            for (int i = 0, j = 0; i < count && j < params.length();) {  
                boolean in = false;  
                for (int k = j; k <= params.length(); k++) {  
                    if (!in && (k == params.length() || params.charAt(k) == ',')) {  
                        args[i] = params.substring(j, k).trim();  
                        if (args[i].startsWith("\""))  
                            args[i] = args[i].substring(1);  
                        if (args[i].endsWith("\""))  
                            args[i] = args[i].substring(0, args[i].length() - 1);  
  
                        args[i] = args[i].replaceAll("\\\\\"", "\"");  
  
                        i = i + 1;  
                        j = k + 1;  
                        break;  
                    }  
  
                    if (params.charAt(k) == '\"' && (k - 1 < 0 || params.charAt(k - 1) != '\\'))  
                        in ^= true;  
                }  
            }  
  
            return args;  
        }  
        return new String[0];  
    }  
    public static String getFuncName(String func) {  
        int idx = func.indexOf('(');  
        if (idx != -1)  
            return func.substring(0, idx).toLowerCase();  
        return null;  
    }  
    public static int getParamsCount(String params) {  
        if (StringUtils.isEmpty(params) || StringUtils.isBlank(params))  
            return 0;  
  
        int cnt = 0;  
        boolean in = false;  
        for (int i = 0; i < params.length(); i++) {  
            if (params.charAt(i) == '\"' && (i - 1 < 0 || params.charAt(i - 1) != '\\'))  
                in ^= true;  
            if (!in && params.charAt(i) == ',')  
                cnt++;  
        }  
  
        return cnt + 1;  
    }  
  
}  

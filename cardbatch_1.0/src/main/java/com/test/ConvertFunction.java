package com.test;

public class ConvertFunction implements IFunction {
	public final int PRE_ARGS_NUM = 2;   //Ĭ�ϲ���������������Ҫ�����޸�  
    public final Class<?>[] PRE_ARGS_TYPE = new Class<?>[] {String.class,String.class};   //Ĭ�ϵĲ��������ͣ�������Ҫ�����޸�  
      
      
    public int convert_if_exist(String name,String value,String field1,String field2){ //���޸ĳ��Ը���ҵ���߼�  
        System.out.println("name = " + name);  
        System.out.println("value = " + value);  
        System.out.println(field1 + " " + field2);  
        return 0;  
    }  
      
    public int convert_if_exist(String name,String value,String field1,String field2,String field3){//���޸ĳ��Լ���ҵ���߼�  
        System.out.println("name = " + name);  
        System.out.println("value = " + value);  
        System.out.println(field1 + " " + field2 + " " + field3);  
        return 0;  
    }//Ҫ��Ӻ���������
}

package cn.yufu.posp.common.common.util;



public class HelpHandUtil
{
    /**
     *зЊТы
     */
    public static String encodeStrng(String str)
    {
    	String reStr = "";
    	try{
    		if(!"".equals(str))
    			reStr = new String(str.getBytes("ISO8859_1"), "GB2312");
	    }catch(Exception e){
	   		
			e.printStackTrace();
			
	   	}
	    return reStr;
    }


   
	public static void main(String[] args)
	{
		
	}
	
}

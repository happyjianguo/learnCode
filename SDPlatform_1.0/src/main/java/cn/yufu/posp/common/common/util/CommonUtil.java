package cn.yufu.posp.common.common.util;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.LabelValueBean;

import cn.yufu.posp.common.common.exception.OAException;

public  class CommonUtil
{
	public static double formateDouble(double newNumber){
		//将截取数字小数点后两位		
		try{
			DecimalFormat df=new DecimalFormat();
			double result=df.parse(String.valueOf(newNumber*100)).intValue()/100.00;
			return result;
		}
		catch(Exception e){
			return newNumber;
		}		
	}
	
	public static List getDepartmentValueBean(List deptId,List deptName)
	{
		List list = new ArrayList();
		
		Iterator it = deptId.iterator();
		int index = 0;
		while(it.hasNext())
		{
			LabelValueBean vb = new LabelValueBean();
			String value = (String)it.next();
			
			vb.setValue(value);
			
			String label="";
			try
			{
				label = (String)deptName.get(index);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			vb.setLabel(label);
			
			list.add(vb);
			
			index++;
		}	
		
		return list;
	}
	public static String encode(String s)
    {
        if(s == null)
            return "";
        StringBuffer stringbuffer = new StringBuffer();
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c = s.charAt(j);
            if(c == '&')
                stringbuffer.append("&amp;");
            else
            if(c == '<')
                stringbuffer.append("&lt;");
            else
            if(c == '>')
                stringbuffer.append("&gt;");
            else
                stringbuffer.append(c);
        }

        String s1 = stringbuffer.toString();
        return s1;
    }

    public static String decode(String s)
    {
        if(s == null)
            return "";
        StringBuffer stringbuffer = new StringBuffer();
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c = s.charAt(j);
            if(c == '&')
            {
                if(s.charAt(j + 1) == 'a' && s.charAt(j + 2) == 'm' && s.charAt(j + 3) == 'p' && s.charAt(j + 4) == ';')
                {
                    stringbuffer.append('&');
                    j += 4;
                } else
                if(s.charAt(j + 1) == 'l' && s.charAt(j + 2) == 't' && s.charAt(j + 3) == ';')
                {
                    stringbuffer.append('<');
                    j += 3;
                } else
                if(s.charAt(j + 1) == 'g' && s.charAt(j + 2) == 't' && s.charAt(j + 3) == ';')
                {
                    stringbuffer.append('>');
                    j += 3;
                } else
                {
                    stringbuffer.append(c);
                }
            } else
            {
                stringbuffer.append(c);
            }
        }

        String s1 = stringbuffer.toString();
        return s1;
    }
    
    
    public static void downloadFile(String fileName,byte[] content,HttpServletResponse response) throws OAException
    {
    	try
    	{
    		if(fileName.endsWith(".doc"))
    		{
    			response.setContentType("application/msword");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".pdf"))
    		{
    			response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".xls"))
    		{
    			response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".ppt"))
    		{
    			response.setContentType("application/vnd.ms-powerpoint");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".rtf"))
    		{
    			response.setContentType("application/rtf");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".jpg"))
    		{
    			response.setContentType("image/jpeg");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".gif"))
    		{
    			response.setContentType("image/gif");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".bmp"))
    		{
    			response.setContentType("image/bmp");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".htm")||fileName.endsWith(".html"))
    		{
    			response.setContentType("text/html");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".txt"))
    		{
    			response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else if(fileName.endsWith(".tif")||fileName.endsWith(".tiff"))
    		{
    			response.setContentType("image/tiff");
                response.setHeader("Content-Disposition", "inline;"
                        + " filename="
                        + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		else
    		{
    			response.setContentType("application/x-msdownload");
    			response.setHeader("Content-Disposition", "attachment;" + " filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
    		}
    		
    		
    		OutputStream os = response.getOutputStream();

			os.write(content);
			os.flush();
    		
    	}catch(Exception e)
    	{
    		throw new OAException("设置Response的下载类型Head信息出错。");
    	}
    	
    }
    
    public static boolean isNotEmptyString(String str)
    {
    	if(str==null)
    		return false;
    	if("".equals(str))
    		return false;
    	
    	return true;
    }
}

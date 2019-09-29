package cn.yufu.posp.common.common.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

public class MD5
{
    public static final String ENCODE = "GBK"; //UTF-8
    public static final String aKey = "RACDESDSDSDSDS"; //UTF-8
    public static String hmacSign(String aValue)
    {
//        byte k_ipad[] = new byte[64];
//        byte k_opad[] = new byte[64];
//        byte keyb[];
//        byte value[];
//        try
//        {
//            keyb = aKey.getBytes(ENCODE);
//            value = aValue.getBytes(ENCODE);
//        }
//        catch(UnsupportedEncodingException e)
//        {
//            keyb = aKey.getBytes();
//            value = aValue.getBytes();
//        }
//        Arrays.fill(k_ipad, keyb.length, 64, (byte)54);
//        Arrays.fill(k_opad, keyb.length, 64, (byte)92);
//        for(int i = 0; i < keyb.length; i++)
//        {
//            k_ipad[i] = (byte)(keyb[i] ^ 0x36);
//            k_opad[i] = (byte)(keyb[i] ^ 0x5c);
//        }
//
//        MessageDigest md = null;
//        try
//        {
//            md = MessageDigest.getInstance("MD5");
//        }
//        catch(NoSuchAlgorithmException e)
//        {
//        e.printStackTrace();
//            return null;
//        }
//        md.update(k_ipad);
//        md.update(value);
//        byte dg[] = md.digest();
//        md.reset();
//        md.update(k_opad);
//        md.update(dg, 0, 16);
//        dg = md.digest();
//        return MD5.toHex(dg);
    	
    	MessageDigest md = null;
        byte[] pwd = null;
        
    	try
        {
    	  md = MessageDigest.getInstance("MD5");
    	 pwd = md.digest(aValue.getBytes());
    	
        }
        catch (NoSuchAlgorithmException ex)
        {
           ex.printStackTrace();
        }
        
        return MD5.toHex(pwd);
    	
    }

    public static String digest(String aValue)
    {
        aValue = aValue.trim();
        byte value[];
        try
        {
            value = aValue.getBytes(ENCODE);
        }
        catch(UnsupportedEncodingException e)
        {
            value = aValue.getBytes();
        }
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA");
        }
        catch(NoSuchAlgorithmException e)
        {
        e.printStackTrace();
            return null;
        }
        return MD5.toHex(md.digest(value));
    }
    
    public static String toHex(byte[] b)
    {
//        if(input == null)
//            return null;
//        StringBuffer output = new StringBuffer(input.length * 2);
//        for(int i = 0; i < input.length; i++)
//        {
//            int current = input[i] & 0xff;
//            if(current < 16)
//                output.append("0");
//            output.append(Integer.toString(current, 16));
//        }
//
//        return output.toString();
//        
        
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++)
        {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
        
    }
    
    public static void main( String[] args )
    {
    String inputStr = "111111";
    String md5edStr = MD5.hmacSign(inputStr);
    System.out.println( md5edStr );
    }
    
}


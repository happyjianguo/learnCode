package com.pay.util;

public class Pack {
	
	//”“≤π¡„
    public static String rZero(String sRStr, int iLen) {
        if (sRStr == null)
            sRStr = "";

        String sRtn = "";
        char b = 0;

        for (int i = 0; i < (iLen - sRStr.length()); i++)
            sRtn += b;
        return sRStr + sRtn;
    }
}

package com.pay.batch.bflowlog.tool;

import com.pay.util.SystemConfig;

public class KabbinConfig {
   private String [] kabin;
   
	//获得卡bin数据
	public String[] getKabins()
	{
		String str=SystemConfig.getValue("kabinconfig");
		if(null!=str&&!"".equals(str.trim())&&!"null".equals(str.trim())){
			 kabin=str.split(",");
		}
		if(kabin==null) {
			kabin=new String[1];
			kabin[0]="";
		}
		return kabin;
	}
	//判断是否含有传送的卡bin 1 含有 0 没有
	public String ischeckKabin(String kabininfo){
		String res="0";
		if(kabininfo ==null ||"".equals(kabininfo)) return "0";
		String [] kabin=getKabins();
		for(int i=0;i<kabin.length;i++){
			if(kabininfo.equals(kabin[i])){
				res="1";
				break;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String t1="1234,111";
		String s2[]=t1.split(",");
		System.out.println(s2.length);

	}

}

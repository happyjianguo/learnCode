package com.pay.batch.bflowlog.tool;

import com.pay.util.SystemConfig;

public class KabbinConfig {
   private String [] kabin;
   
	//��ÿ�bin����
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
	//�ж��Ƿ��д��͵Ŀ�bin 1 ���� 0 û��
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

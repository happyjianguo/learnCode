package com.pay.batch.bflowlog.tool;

public class DepreSingle {
   private static DepreSingle single;
   private boolean isEnd;//true Ϊִ�����   falseδִ�����
   private String stats;
   private DepreSingle(){
	   
   }
   public static DepreSingle getInstance()
   {
	   if(single==null) 
		   single=new DepreSingle();
	   return single;
   }
   public void init()
   {
	   isEnd=false;
	   stats="���ݸ����У������ĵȴ�";
   }
   public boolean isEnd() {
	  return isEnd;
   }
   public void setEnd(boolean isEnd) {
	  this.isEnd = isEnd;
   }
   public String getStats() {
	  return stats;
  }
  public void setStats(String stats) {
     this.stats = stats;
  }
   
   
   
}

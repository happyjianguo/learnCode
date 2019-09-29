package com.pay.batch.bflowlog.tool;

public class DepreSingle {
   private static DepreSingle single;
   private boolean isEnd;//true 为执行完毕   false未执行完成
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
	   stats="数据更新中，请耐心等待";
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

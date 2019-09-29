package com.pay.batch.bflowlog.tool;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.proxy.dwr.Util;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bflowlog.bean.SixBFlowLogBean;
import com.pay.batch.bflowlog.dao.BFlowLogDao;
import com.pay.batch.bflowlog.dao.SixBFlowLogDao;

public class ShellServiceOfHousekeep {
	
	private static final String END = "end";
    
	//通过反向ajax 通过单例模式 获取 更新提交的状态 
	public void submitstats() throws InterruptedException {		
			WebContext wctx = WebContextFactory.get(); // 这里是获取WebContext上下文
			String currentPage = wctx.getCurrentPage(); // 从上下文中获取当前页面,这些是DWRReverse
			ScriptSession scriptSession = wctx.getScriptSession();// Ajax 要求的必须方式			
			Util util = new Util(scriptSession); // Util 是DWR 在Server端模拟Brower端// dwr.util.js的类, Engine也是
		
			try{
				  // Date  date=new Date();
				  DepreSingle depreS=DepreSingle.getInstance();
				  do
				  {	  
					  Thread.sleep(100);	      
					  util.setValue("msginfo", depreS.getStats());
					  // i++;
					  // System.out.println("=============="+depreS.getStats()+"=======================");
				  }while(!depreS.isEnd());		         
					
				}catch(Exception e){			
					e.printStackTrace();			
				}finally{
					
				}
			
		}
		//通过反向ajax 通过单例模式 获取 更新提交的状态 
		public void readstats(String panflagno, String batchflag) throws InterruptedException {		
			WebContext wctx = WebContextFactory.get(); // 这里是获取WebContext上下文
			String currentPage = wctx.getCurrentPage(); // 从上下文中获取当前页面,这些是DWRReverse
			ScriptSession scriptSession = wctx.getScriptSession();// Ajax 要求的必须方式			
			Util util = new Util(scriptSession); // Util 是DWR 在Server端模拟Brower端// dwr.util.js的类, Engine也是
			BFlowLogDao dao=new BFlowLogDao();
			int result=-1;
			int count=-6;//初始值
			try{
				 BFlowLogBean flowlogBean=new BFlowLogBean();
				  do
				  {	  
					  Thread.sleep(5);	  
				       result= dao.getBFlowLogInfoStatus(panflagno, batchflag,flowlogBean);
				       if(result == -6){
	//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","脚本正在执行请耐心等待"));
	//							    	   t.start();
				    	   Thread.sleep(5);
				    	   util.setValue("msginfo","脚本正在执行请耐心等待<br/>受理号："+panflagno);	
				       }else  if(result==0){
	//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","脚本执行成功,请查看流水记录"));
	//							    	   t.start();
				    	   Thread.sleep(5);
					     util.setValue("msginfo", "脚本执行成功,请查看流水记录<br/>受理号："+panflagno);								      
				       }else  if(result==2){
	//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","脚本执行成功,请查看流水记录"));
	//							    	   t.start();
				    	   Thread.sleep(5);
					     util.setValue("msginfo", "受理号："+panflagno+"<br/>脚本已初始化正在执行请耐心等待");								      
				       }else  if(result==-1){//查询数据库 执行结果为失败 
				    	   String errinfo=flowlogBean.getExecinfo();
				    	   if(errinfo!=null){
				    	     errinfo=errinfo.replaceAll(",", "<br>");
				    	     util.setValue("msginfo",errinfo);
	//							    	     Thread t=new Thread(new ThreadUtilSet(util,"msginfo",errinfo));
	//								    	   t.start();
					    	  Thread.sleep(5);
				    	   }else{
				    		   errinfo="脚本执行失败，请联系管理员<br/>受理号："+panflagno;
	//							    		   Thread t=new Thread(new ThreadUtilSet(util,"msginfo",errinfo));
	//								    	   t.start();
					    	   //Thread.sleep(5);
				    		   util.setValue("msginfo",errinfo);
				    		   
				    		   break;
				    	   }
				       }else  if(result==-2){
	//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","记录为空脚本正在执行请耐心等待"));
	//							    	   t.start();
	//							    	   Thread.sleep(5);
				    	  util.setValue("msginfo", "记录为空脚本正在执行请耐心等待<br/>受理号："+panflagno); 
				       }							      
				      count++;
					   if(count>30)
					   {
						   result=-1;
						   util.setValue("msginfo", "脚本执行失败请联系管理员<br/>受理号："+panflagno);	
						   break;
					   }
					  Thread.sleep(1000);
				  }while(result!=0);		         
					
				}catch(Exception e){			
					e.printStackTrace();			
				}finally{
					
				}
		
	}
				
	public void readstatsOfNC(String panflagno, String batchflag) throws InterruptedException {		
		WebContext wctx = WebContextFactory.get(); // 这里是获取WebContext上下文
		String currentPage = wctx.getCurrentPage(); // 从上下文中获取当前页面,这些是DWRReverse
		ScriptSession scriptSession = wctx.getScriptSession();// Ajax 要求的必须方式			
		Util util = new Util(scriptSession); // Util 是DWR 在Server端模拟Brower端// dwr.util.js的类, Engine也是
		BFlowLogDao dao=new BFlowLogDao();
		int result=-1;
		int count=-6;//初始值
		try{
			 BFlowLogBean flowlogBean=new BFlowLogBean();
			  do
			  {	  
				  Thread.sleep(5);	  
			       result= dao.getBFlowLogInfoStatusOfNC(panflagno, batchflag,flowlogBean);
			       if(result == -6){
//						    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","脚本正在执行请耐心等待"));
//						    	   t.start();
			    	   Thread.sleep(5);
			    	   util.setValue("msginfo","脚本正在执行请耐心等待<br/>受理号："+panflagno);	
			       }else  if(result==0){
//						    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","脚本执行成功,请查看流水记录"));
//						    	   t.start();
			    	   Thread.sleep(5);
				     util.setValue("msginfo", "脚本执行成功,请查看流水记录<br/>受理号："+panflagno);								      
			       }else  if(result==2){
//						    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","脚本执行成功,请查看流水记录"));
//						    	   t.start();
			    	   Thread.sleep(5);
				     util.setValue("msginfo", "受理号："+panflagno+"<br/>脚本已初始化正在执行请耐心等待");								      
			       }else  if(result==-1){//查询数据库 执行结果为失败 
			    	   String errinfo=flowlogBean.getExecinfo();
			    	   if(errinfo!=null){
			    	     errinfo=errinfo.replaceAll(",", "<br>");
			    	     util.setValue("msginfo",errinfo);
//						    	     Thread t=new Thread(new ThreadUtilSet(util,"msginfo",errinfo));
//							    	   t.start();
				    	  Thread.sleep(5);
			    	   }else{
			    		   errinfo="脚本执行失败，请联系管理员<br/>受理号："+panflagno;
//						    		   Thread t=new Thread(new ThreadUtilSet(util,"msginfo",errinfo));
//							    	   t.start();
				    	   //Thread.sleep(5);
			    		   util.setValue("msginfo",errinfo);
			    		   
			    		   break;
			    	   }
			       }else  if(result==-2){
//						    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","记录为空脚本正在执行请耐心等待"));
//						    	   t.start();
//						    	   Thread.sleep(5);
			    	  util.setValue("msginfo", "记录为空脚本正在执行请耐心等待<br/>受理号："+panflagno); 
			       }							      
			      count++;
				   if(count>30)
				   {
					   result=-1;
					   util.setValue("msginfo", "脚本执行失败请联系管理员<br/>受理号："+panflagno);	
					   break;
				   }
				  Thread.sleep(1000);
			  }while(result!=0);		         
				
			}catch(Exception e){			
				e.printStackTrace();			
			}finally{
				
			}
		
	}	
	
	public void readstatsOfMakeCard(String panflagno)
			throws InterruptedException {
		WebContext wctx = WebContextFactory.get(); // 这里是获取WebContext上下文
		String currentPage = wctx.getCurrentPage(); // 从上下文中获取当前页面,这些是DWRReverse
		ScriptSession scriptSession = wctx.getScriptSession();// Ajax 要求的必须方式
		Util util = new Util(scriptSession); // Util 是DWR 在Server端模拟Brower端//
		// dwr.util.js的类, Engine也是
		BFlowLogDao dao = new BFlowLogDao();
		int result = -1;
		int count = -6;// 初始值
		try {
			BFlowLogBean flowlogBean = new BFlowLogBean();
			do {
				Thread.sleep(5);
				result = dao.getMakeCardStat(panflagno, flowlogBean);
				if (result == -6) {
					Thread.sleep(5);
					util
							.setValue("msginfo", "脚本正在执行请耐心等待<br/>批次号1111111："
									+ panflagno);
				} else if (result == 0) {
					Thread.sleep(5);
					util.setValue("msginfo", "脚本执行成功,请查看流水记录<br/>批次号："
							+ panflagno);
				} else if (result == 2) {
					Thread.sleep(5);
					util.setValue("msginfo", "批次号：" + panflagno
							+ "<br/>脚本已初始化正在执行请耐心等待");
				} else if (result == -1) {// 查询数据库 执行结果为失败
					String errinfo = flowlogBean.getExecinfo();
					if (errinfo != null) {
						errinfo = errinfo.replaceAll(",", "<br>");
						util.setValue("msginfo", errinfo);
						Thread.sleep(5);
					} else {
						errinfo = "脚本执行失败，请联系管理员<br/>批次号：" + panflagno;
						util.setValue("msginfo", errinfo);

						break;
					}
				} else if (result == -2) {
					util.setValue("msginfo", "记录为空脚本正在执行请耐心等待<br/>批次号："
							+ panflagno);
				}
				count++;
				if (count > 30) {
					result = -1;
					util.setValue("msginfo", "脚本执行失败请联系管理员<br/>批次号："
							+ panflagno);
					break;
				}
				Thread.sleep(1000);
			} while (result != 0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	//校验kabin
	public String checkkabin(String kabininfo)
	{
		String res="0";
		KabbinConfig  kabinc=new KabbinConfig();
		res=kabinc.ischeckKabin(kabininfo);
		return res;
	}

	// 通过反向ajax 通过单例模式 获取 更新提交的状态
	public void readstatsSix(String panflagno, String batchflag)
			throws InterruptedException {
		WebContext wctx = WebContextFactory.get(); // 这里是获取WebContext上下文
		String currentPage = wctx.getCurrentPage(); // 从上下文中获取当前页面,这些是DWRReverse
		ScriptSession scriptSession = wctx.getScriptSession();// Ajax 要求的必须方式
		Util util = new Util(scriptSession); // Util 是DWR 在Server端模拟Brower端//
		// dwr.util.js的类, Engine也是
		SixBFlowLogDao dao = new SixBFlowLogDao();
		int result = -1;
		int count = -6;// 初始值
		try {
			SixBFlowLogBean sixflowlogBean = new SixBFlowLogBean();
			do {
				Thread.sleep(5);
				result = dao.getSixBFlowLogInfoStatus(panflagno, batchflag,
						sixflowlogBean);
				if (result == -6) {
					// Thread t=new Thread(new
					// ThreadUtilSet(util,"msginfo","脚本正在执行请耐心等待"));
					// t.start();
					Thread.sleep(5);
					util
							.setValue("msginfo", "脚本正在执行请耐心等待<br/>受理号："
									+ panflagno);
				} else if (result == 0) {
					// Thread t=new Thread(new
					// ThreadUtilSet(util,"msginfo","脚本执行成功,请查看流水记录"));
					// t.start();
					Thread.sleep(5);
					util.setValue("msginfo", "脚本执行成功,请查看流水记录<br/>受理号："
							+ panflagno);
				} else if (result == 2) {
					// Thread t=new Thread(new
					// ThreadUtilSet(util,"msginfo","脚本执行成功,请查看流水记录"));
					// t.start();
					Thread.sleep(5);
					util.setValue("msginfo", "受理号：" + panflagno
							+ "<br/>脚本已初始化正在执行请耐心等待");
				} else if (result == -1) {// 查询数据库 执行结果为失败
					String errinfo = sixflowlogBean.getExecinfo();
					if (errinfo != null) {
						errinfo = errinfo.replaceAll(",", "<br>");
						util.setValue("msginfo", errinfo);
						// Thread t=new Thread(new
						// ThreadUtilSet(util,"msginfo",errinfo));
						// t.start();
						Thread.sleep(5);
					} else {
						errinfo = "脚本执行失败，请联系管理员<br/>受理号：" + panflagno;
						// Thread t=new Thread(new
						// ThreadUtilSet(util,"msginfo",errinfo));
						// t.start();
						// Thread.sleep(5);
						util.setValue("msginfo", errinfo);

						break;
					}
				} else if (result == -2) {
					// Thread t=new Thread(new
					// ThreadUtilSet(util,"msginfo","记录为空脚本正在执行请耐心等待"));
					// t.start();
					// Thread.sleep(5);
					util.setValue("msginfo", "记录为空脚本正在执行请耐心等待<br/>受理号："
							+ panflagno);
				}
				count++;
				if (count > 30) {
					result = -1;
					util.setValue("msginfo", "脚本执行失败请联系管理员<br/>受理号："
							+ panflagno);
					break;
				}
				Thread.sleep(1000);
			} while (result != 0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	
}
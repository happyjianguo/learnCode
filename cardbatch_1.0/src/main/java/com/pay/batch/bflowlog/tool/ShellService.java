package com.pay.batch.bflowlog.tool;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.proxy.dwr.Util;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bflowlog.dao.BFlowLogDao;



public class ShellService {
	
	private static final String END = "end";
    
	//ͨ������ajax ͨ������ģʽ ��ȡ �����ύ��״̬ 
		public void submitstats() throws InterruptedException {		
				WebContext wctx = WebContextFactory.get(); // �����ǻ�ȡWebContext������
				String currentPage = wctx.getCurrentPage(); // ���������л�ȡ��ǰҳ��,��Щ��DWRReverse
				ScriptSession scriptSession = wctx.getScriptSession();// Ajax Ҫ��ı��뷽ʽ			
				Util util = new Util(scriptSession); // Util ��DWR ��Server��ģ��Brower��// dwr.util.js����, EngineҲ��
			
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
		//ͨ������ajax ͨ������ģʽ ��ȡ �����ύ��״̬ 
				public void readstats(String panflagno, String batchflag) throws InterruptedException {		
						WebContext wctx = WebContextFactory.get(); // �����ǻ�ȡWebContext������
						String currentPage = wctx.getCurrentPage(); // ���������л�ȡ��ǰҳ��,��Щ��DWRReverse
						ScriptSession scriptSession = wctx.getScriptSession();// Ajax Ҫ��ı��뷽ʽ			
						Util util = new Util(scriptSession); // Util ��DWR ��Server��ģ��Brower��// dwr.util.js����, EngineҲ��
						BFlowLogDao dao=new BFlowLogDao();
						int result=-1;
						int count=-6;//��ʼֵ
						try{
							 BFlowLogBean flowlogBean=new BFlowLogBean();
							  do
							  {	  
								  Thread.sleep(5);	  
							       result= dao.getBFlowLogInfoStatus(panflagno, batchflag,flowlogBean);
							       if(result == -6){
//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","�ű�����ִ�������ĵȴ�"));
//							    	   t.start();
							    	   Thread.sleep(5);
							    	   util.setValue("msginfo","�ű�����ִ�������ĵȴ�");	
							       }else  if(result==0){
//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","�ű�ִ�гɹ�,��鿴��ˮ��¼"));
//							    	   t.start();
							    	   Thread.sleep(5);
								     util.setValue("msginfo", "�ű�ִ�гɹ�,��鿴��ˮ��¼");								      
							       }else  if(result==2){
//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","�ű�ִ�гɹ�,��鿴��ˮ��¼"));
//							    	   t.start();
							    	   Thread.sleep(5);
								     util.setValue("msginfo", "�ű��ѳ�ʼ������ִ�������ĵȴ�"+count);								      
							       }else  if(result==-1){//��ѯ���ݿ� ִ�н��Ϊʧ�� 
							    	   String errinfo=flowlogBean.getExecinfo();
							    	   if(errinfo!=null){
							    	     errinfo=errinfo.replaceAll(",", "<br>");
							    	     util.setValue("msginfo",errinfo);
//							    	     Thread t=new Thread(new ThreadUtilSet(util,"msginfo",errinfo));
//								    	   t.start();
								    	   Thread.sleep(5);
							    	   }else{
							    		   errinfo="�ű�ִ��ʧ�ܣ�����ϵ����Ա";
//							    		   Thread t=new Thread(new ThreadUtilSet(util,"msginfo",errinfo));
//								    	   t.start();
								    	   //Thread.sleep(5);
							    		   util.setValue("msginfo",errinfo);
							    		   
							    		   break;
							    	   }
							       }else  if(result==-2){
//							    	   Thread t=new Thread(new ThreadUtilSet(util,"msginfo","��¼Ϊ�սű�����ִ�������ĵȴ�"));
//							    	   t.start();
//							    	   Thread.sleep(5);
							    	  util.setValue("msginfo", "��¼Ϊ�սű�����ִ�������ĵȴ�"); 
							       }							      
							      count++;
								   if(count>10)
								   {
									   result=-1;
									   util.setValue("msginfo", "�ű�ִ��ʧ������ϵ����Ա");	
									   break;
								   }
								  Thread.sleep(1000);
							  }while(result!=0);		         
								
							}catch(Exception e){			
								e.printStackTrace();			
							}finally{
								
							}
						
					}

}
package cn.yufu.posp.common.web.action;

import java.util.Date;

public class testThread implements Runnable {

	/**
	 * @param args
	 * @throws Exception 
	 */
	private int num;
	public testThread(int count){
		num=count;
	}
	public static void main(String[] args) throws Exception { 
//		// TODO Auto-generated method stub
//		for(int i=0;i<6;i++){
//			Thread test1=new Thread(new Runnable(){
//				public void run() {
//					// TODO Auto-generated method stub
//					while(true){
//					try {
//						System.out.println("I am working in room"+9+" at"+new Date(System.currentTimeMillis()));
//						Thread.sleep(1000*(9+1));
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					}
//				}
//			});
//			test1.start();
//		}
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
			System.out.println("I am working in room"+num+" at"+new Date(System.currentTimeMillis()));
			Thread.sleep(1000*(num+1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	

}

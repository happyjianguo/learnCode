package com.pay.batch.bflowlog.tool;

import org.directwebremoting.proxy.dwr.Util;

public class ThreadUtilSet implements Runnable {
    private Util util;
    private String txtinfo;
    private String msginfo;
	public ThreadUtilSet(Util util,String info,String msginfo) {
		super();
		this.util = util;
		this.txtinfo=info;
		this.msginfo=msginfo;
	}

	public void run() {
		util.setValue(msginfo,txtinfo);	

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

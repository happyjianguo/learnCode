package com.pay.batch.bflowlog.tool;


public class CallShellThread implements Runnable {
	private String command;
	private String logfile;

	public CallShellThread(String command, String logfile) {
		this.command = command;
		this.logfile = logfile;

	}

	public void run() {
		CallShell callshell = new CallShell();
		String mes = "";
		int ret = 0;
		try {
			ret = callshell.execShell(command, logfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用单例程序，记录返回值状态
		
		if (ret == 0) {
			mes = "脚本执行成功";
		} else if (ret == -2) {
			mes = "脚本执行失败";
		}else if (ret == -3) {
			mes = "获取脚本信息失败";
		}else if (ret == -4) {
			mes = "等待失败 进程被中断";
		} else {
			mes = "脚本执行失败";
		}
		DepreSingle depreS = DepreSingle.getInstance();
		depreS.setStats(mes);
		depreS.setEnd(true);
	}

}

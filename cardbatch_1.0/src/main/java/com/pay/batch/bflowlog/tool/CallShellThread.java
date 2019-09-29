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
		//���õ������򣬼�¼����ֵ״̬
		
		if (ret == 0) {
			mes = "�ű�ִ�гɹ�";
		} else if (ret == -2) {
			mes = "�ű�ִ��ʧ��";
		}else if (ret == -3) {
			mes = "��ȡ�ű���Ϣʧ��";
		}else if (ret == -4) {
			mes = "�ȴ�ʧ�� ���̱��ж�";
		} else {
			mes = "�ű�ִ��ʧ��";
		}
		DepreSingle depreS = DepreSingle.getInstance();
		depreS.setStats(mes);
		depreS.setEnd(true);
	}

}

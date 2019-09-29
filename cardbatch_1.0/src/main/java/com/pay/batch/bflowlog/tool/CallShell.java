package com.pay.batch.bflowlog.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CallShell {

	/**
	 * @param args
	 * @throws IOException
	 */
	public int execShell(String command, String logfile) throws IOException {
		Runtime rt = Runtime.getRuntime();
		PrintWriter outWriter = null;
		BufferedReader br = null;
		Process pcs = null;
		int ret =-5;
		try {
			pcs = rt.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
			ret=-2;//执行失败
			return ret;
		}
		try {
			
			
			outWriter = new PrintWriter(new File(logfile));
			br = new BufferedReader(new InputStreamReader(pcs.getInputStream()));
			String line = new String();
			while ((line = br.readLine()) != null) {
				outWriter.write(line);
			}
			try {
				pcs.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
				ret=-4;//等待失败 进程被中断
				System.err.println("processes was interrupted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret=-3;//获取输出信息执行失败
		} finally {
			br.close();
			outWriter.flush();
			outWriter.close();
		}
		if(ret!=-5){//如果原值被修改，则返回被修改的值
		      ret = pcs.exitValue();
		}		
		return ret;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

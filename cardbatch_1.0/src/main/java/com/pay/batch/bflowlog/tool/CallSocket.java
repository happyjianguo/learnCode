package com.pay.batch.bflowlog.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CallSocket {

	public int execShell(String ip, int port, String sendbuf,
			StringBuilder recvbuf) {
		PrintWriter os = null;
		BufferedReader is = null;
		Socket socket = null;
		try {
			// 发出客户请求
			socket = new Socket(ip, port);
			// 由系统标准输入设备构造BufferedReader对象
			os = new PrintWriter(socket.getOutputStream());

			// 由Socket对象得到输出流，并构造PrintWriter对象
			is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象

			int sendlen = sendbuf.length();
			// String lenhexstr = Integer.toHexString(sendlen);
			// int lenhex = lenhexstr.length();
			// for (int i = 4; i > lenhex; i--) {
			// lenhexstr = "0" + lenhexstr;
			// }
			// byte lenhexb[] = ASCII_To_BCD(lenhexstr.getBytes(), 4);
			// sendbuf=new String(lenhexb)+sendbuf;
			os.print(sendbuf);
			// 刷新输出流，使Server马上收到该字符串
			os.flush();
			char[] cbuf = new char[128];
			// 从Server读入一字符串
			int nbyte = is.read(cbuf);
			System.out.println(cbuf);
			// if (nbyte != 2 || !"00".equals(String.valueOf(cbuf, 0, nbyte))) {
			// return -2;
			// }

			recvbuf.append(cbuf);

			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				is.close(); // 关闭Socket输入流
			if (socket != null)
				socket.close(); // 关闭Socket
			os = null;
			is = null;
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket输入流
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket
			os = null;
			is = null;
			socket = null;

		}
		return 0;

	}

	public int execShell(String ip, int port, String sendbuf) {
		PrintWriter os = null;
		BufferedReader is = null;
		Socket socket = null;
		try {
			// 发出客户请求
			socket = new Socket(ip, port);
			// 由系统标准输入设备构造BufferedReader对象
			os = new PrintWriter(socket.getOutputStream());

			// 由Socket对象得到输出流，并构造PrintWriter对象
			is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象

			int sendlen = sendbuf.length();
			// String lenhexstr = Integer.toHexString(sendlen);
			// int lenhex = lenhexstr.length();
			// for (int i = 4; i > lenhex; i--) {
			// lenhexstr = "0" + lenhexstr;
			// }
			// byte lenhexb[] = ASCII_To_BCD(lenhexstr.getBytes(), 4);
			// sendbuf=new String(lenhexb)+sendbuf;
			os.print(sendbuf);
			// 刷新输出流，使Server马上收到该字符串
			os.flush();
			char[] cbuf = new char[128];
			// 从Server读入一字符串
			int nbyte = is.read(cbuf);
			System.out.println(cbuf);
			if (nbyte != 2 || !"00".equals(String.valueOf(cbuf, 0, nbyte))) {
				return -2;
			}

			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				is.close(); // 关闭Socket输入流
			if (socket != null)
				socket.close(); // 关闭Socket
			os = null;
			is = null;
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket输入流
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket
			os = null;
			is = null;
			socket = null;

		}
		return 0;

	}

	private byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	private byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}
	
	public int execMyShell(String ip, int port, String sendbuf,
			StringBuilder recvbuf) {
		PrintWriter os = null;
		BufferedReader is = null;
		Socket socket = null;
		try {
			// 发出客户请求
			socket = new Socket(ip, port);
			// 由系统标准输入设备构造BufferedReader对象
			//os = new PrintWriter(socket.getOutputStream());
			os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"gb2312"),true);

			// 由Socket对象得到输出流，并构造PrintWriter对象
			//is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			is = new BufferedReader(new InputStreamReader(socket.getInputStream(),"gb2312"));

			// 由Socket对象得到输入流，并构造相应的BufferedReader对象

			int sendlen = sendbuf.length();
			// String lenhexstr = Integer.toHexString(sendlen);
			// int lenhex = lenhexstr.length();
			// for (int i = 4; i > lenhex; i--) {
			// lenhexstr = "0" + lenhexstr;
			// }
			// byte lenhexb[] = ASCII_To_BCD(lenhexstr.getBytes(), 4);
			// sendbuf=new String(lenhexb)+sendbuf;
			os.print(sendbuf);
			// 刷新输出流，使Server马上收到该字符串
			os.flush();
			char[] cbuf = new char[128];
			// 从Server读入一字符串
			int nbyte = is.read(cbuf);
			System.out.println(cbuf);
			// if (nbyte != 2 || !"00".equals(String.valueOf(cbuf, 0, nbyte))) {
			// return -2;
			// }

			recvbuf.append(cbuf);

			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				is.close(); // 关闭Socket输入流
			if (socket != null)
				socket.close(); // 关闭Socket
			os = null;
			is = null;
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket输入流
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket
			os = null;
			is = null;
			socket = null;

		}
		return 0;

	}

}

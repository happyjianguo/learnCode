package com.pay.batch.bflowlog.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CallSocketOfHousekeep {
	
	public int execShell(String ip, int port, String sendbuf) throws IOException {
		PrintWriter os=null;
		BufferedReader is=null;
		Socket socket =null;
		try {
			// �����ͻ�����
			socket = new Socket(ip, port);
			// ��ϵͳ��׼�����豸����BufferedReader����
			os = new PrintWriter(socket.getOutputStream());

			// ��Socket����õ��������������PrintWriter����
			is = new BufferedReader(new InputStreamReader(	socket.getInputStream()));
			// ��Socket����õ�����������������Ӧ��BufferedReader����
			
			int sendlen=sendbuf.length();
			String lenhexstr=Integer.toHexString(sendlen);
			int lenhex=lenhexstr.length();
			for(int i=4;i>lenhex;i--){
				lenhexstr="0"+lenhexstr;
			}			
			byte lenhexb[]=ASCII_To_BCD(lenhexstr.getBytes(),4);
			sendbuf=new String(lenhexb)+sendbuf;
			os.print(sendbuf);			
			// ˢ���������ʹServer�����յ����ַ���
			os.flush();
			char [] cbuf=new char[128];
			// ��Server����һ�ַ���
			int nbyte= is.read(cbuf);
			if(nbyte!=2 || !"00".equals(String.valueOf(cbuf,0,nbyte))){
				return -2;
			}			
			if(os!=null)
			   os.close();     // �ر�Socket�����
			if(is !=null)
			   is.close();     // �ر�Socket������
			if(socket!=null)
			  socket.close(); // �ر�Socket
           os=null;
           is=null;
           socket=null;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			if(os!=null)
			   os.close();     // �ر�Socket�����
			if(is !=null)
			    is.close();     // �ر�Socket������
			if(socket!=null)
			    socket.close(); // �ر�Socket
           os=null;
           is=null;
           socket=null;
			
		}
		return 0;

	}
	private  byte asc_to_bcd(byte asc) {  
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
	private  byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
        byte[] bcd = new byte[asc_len / 2];  
        int j = 0;  
        for (int i = 0; i < (asc_len + 1) / 2; i++) {  
            bcd[i] = asc_to_bcd(ascii[j++]);  
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
        }  
        return bcd;  
    }  
	
	public static void main(String[] argc) throws IOException, InterruptedException{
		//String str="0011 1405231785 888888 500";
		String str="0011 20170122184853 216888111 1 1 20850101";
//		int n=str.length();
//		n=1;
//		String n2=Integer.toHexString(n);
//		int len=n2.length();
//		for(int i=4;i>len;i--){
//			n2="0"+n2;
//		}
//		System.out.println(n2);
		CallSocketOfHousekeep s=new CallSocketOfHousekeep();
//		int nm=s.execShell("192.168.6.222", 32733, str);
		int nm=s.execShell("120.133.24.21", 32733, str);
		System.out.println(nm);
		//s.test1(); 
	}
}

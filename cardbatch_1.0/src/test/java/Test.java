import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.pay.batch.tlog.struts.form.SinopecSweepCardUploadForm;

/**
 *����:
 *����:
 */
/**
 * Test.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��8��10��
 * ����:test
 */
public class Test {

	public static void main(String[] args) throws Exception{
		
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		Boolean b = true;
//		String content = "ok";
//		map.put("flag", b);
//		map.put("content", content);
//		if((boolean) map.get("flag")){
////			System.out.println(map.get("flag"));
//		}
//		System.out.println(isNumeric("",8));
		System.out.println(isValidDate("20180217"));
		if(compareNum("20180101","20180201")>0){
			System.out.println(compareNum("20180101","20180201"));
		}
		SinopecSweepCardUploadForm f = new SinopecSweepCardUploadForm();
//		 �žų˷���(); 
//		ƽ���ı�����();  
//		����������(); 
//		����������2(); 
//		����������();
//		tSubString();
//		String sl = "41a6ba10-9735-43c0-b8d6-36b45725f906";
//		String sl = "41A6ba10-9735-43c0-b8D6-36b45725f906";
//		System.out.println(sl.toLowerCase());
//		String ssl ="";
//		System.out.println(ssl.toLowerCase());
//		String ff = "";
//		File f = new File(ff);
//		System.out.println(isImage(f));
	}
	
//	public static boolean compareNum(String str1,String str2) {
//		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//		String a="20140401";
//		String b = "20140225";
//		Long c = sf.parse(a).getTime()-sf.parse(b).getTime();
//		long d = c/1000/60/60/24;//��
//		System.out.println(d+"��");
//	}
	public static Long compareNum(String starttime,String endtime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		long d = 0;
		Long c;
		try {
			c = sf.parse(endtime).getTime()-sf.parse(starttime).getTime();
			d = c/1000/60/60/24;//��
		} catch (ParseException e) {
			System.out.println(d+"��");
		}
		return d;
	}
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
	//����һ����JAVA�Դ��ĺ���
	public static boolean isNumeric(String str,int pos){
	   for (int i = str.length();--i>=0;){  
	       if (!Character.isDigit(str.charAt(i))){
	           return false;
	       }
	   }
	   if(str.length() != pos){
		   return false;
	   }
	   return true;
	}

	public static void �žų˷���() {
		//��ѭ����������  
        for(int i=1;i<10;i++)   
        {   
            //��ѭ������ÿ�б��ʽ����  
            for(int j=1; j<=i; j++)   
            {   
                System.out.print(" "+i+"*"+j+"="+(i*j));   
            }   
            //һ�н�������  
            System.out.println();  
        }
	}

	public static void ƽ���ı�����() {
		int i,j,k,n;  
        Scanner input=new Scanner(System.in);  
        System.out.print("������ƽ���ı��β�����");  
        n=input.nextInt();//���ѭ�����Ʋ���  
        for(i=1;i<=n;i++)  
        {  
            for(k=1;k<=i-1;k++)  
                System.out.print(" ");  
            for(j=1;j<=2*n;j++)  
                System.out.printf("*");  
            System.out.printf("\n");  
        }
	}

	public static void ����������() {
		int i,j,k,n;  
        Scanner input=new Scanner(System.in);  
        System.out.print("���������������ǲ�����");  
        n=input.nextInt();//���ѭ�����Ʋ���  
        for(i=1;i<=n;i++)  
        {  
            for(j=i;j<=n;j++)  
                System.out.print(" ");  
            for(k=1;k<=i;k++)  
                System.out.printf("* ");  
            System.out.printf("\n");  
        }  
        for(i=2;i<=n;i++)  
        {  
            for(k=1;k<=i;k++)  
                System.out.print(" ");  
            for(j=i;j<=n;j++)  
                System.out.printf("* ");  
            System.out.printf("\n");  
        }
	}

	public static void ����������2() {
		int i,j,k,n;  
        Scanner input=new Scanner(System.in);  
        System.out.print("�����������������");  
        n=input.nextInt();//���ѭ�����Ʋ���  
        for(i=1;i<=n;i++)  
        {  
              
            for(j=i;j<=n;j++)  
                System.out.print(" ");//��������кţ�����ǺŸ���  
            for(k=1;k<=i;k++)  
                System.out.printf("* ");//һ�н���������  
            System.out.printf("\n");  
        }
	}

	public static void ����������() {
		int i,j,k,n;  
        Scanner input=new Scanner(System.in);  
        System.out.print("�����������������");  
        n=input.nextInt();//���ѭ�����Ʋ���  
        for(i=1;i<=n;i++)  
        {  
            //��������кţ�����Ǻ���߿ո�  
            for(j=1;j<=n-i;j++)  
                System.out.print(" ");//��������кţ�����ǺŸ���  
            for(k=1;k<=2*i-1;k++)  
                System.out.printf("*");//һ�н���������  
            System.out.printf("\n");  
        }
	}

	public static boolean isImage(File imageFile) {  
	    if (!imageFile.exists()) {  
	        return true;  
	    } else{
	    	 Image img = null;  
	 	    try {  
	 	        img = ImageIO.read(imageFile);  
	 	        if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {  
	 	            return false;  
	 	        }  
	 	        return true;  
	 	    } catch (Exception e) {  
	 	        return false;  
	 	    } finally {  
	 	        img = null;  
	 	    }  
	    }
	}  
	
	public static void tSubString() {
		String time = "20170810111500";
		System.out.println(time.substring(0, 8));
		System.out.println(time.substring( 8));
	}
}

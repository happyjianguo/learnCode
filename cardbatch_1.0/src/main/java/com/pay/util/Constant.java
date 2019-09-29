package com.pay.util;


public class Constant {
    public  int PageSize = 10;
	
	/***************
	 * �������
	 */
	public  int MoniSize = 25;
	private Constant(){
		try{
		String pagesize=SystemConfig.getValue("PageSize");
		String monisize=SystemConfig.getValue("MoniSize");
		if(pagesize==null||pagesize.trim().equals("")||pagesize.equals("NULL")) pagesize="10";
		if(monisize==null||monisize.trim().equals("")||monisize.equals("NULL")) pagesize="25";
		PageSize = Integer.parseInt(pagesize);
		MoniSize = Integer.parseInt(monisize );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Constant getInstance(){
		
		return new Constant();
	}
	/************
	 * ���׿�����
	 */
	public static String CLOSE = "0";
	public static String OPEN = "1";
	
	public static String NO = "0";
	public static String YES = "1";

	/***************
	 * �����Ա��
	 */
	public static String NOT_ACTIVE = "0";
	public static String ACTIVE = "1";

	public static String NOT_ACTIVE_STRING = "δ����";
	public static String ACTIVE_STRING = "����";
	
	/***************
	 * ���δ�����
	 */
	
	/***************
	 * ��ҳ��
	 */

	/***************
	 * ��ѯ�����
	 */
	public static String TODAY = "day_00_";
	public static String HISTORY = "his_00";

	/***************
	 * �����
	 */
	public static String QUERY = "query";
	public static String CHECK = "check";

	/***************
	 * ��ز���
	 */
	public static String ALLMONI = "0";
	public static String BIGAMOUNTMONI = "1";
	public static String IMPORTANTMONI = "2";
	
	
	
}

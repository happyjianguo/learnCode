package cn.yufu.core.common.util;

/**
 * TODO ����ѹ�����ݺ�ϵͳ�Զ�������ݶ�д����� �ⲿ�ִ���Ӧ�ý��з��룬�Ժ�ͳһ����
 * 
 * @author other
 */
public class CommonDomain {

	/* spring bean name �������� */
	public static final String BEAN_NAME_COMMON_CLIENT = "crmCommons.commonClient";

	public static final ConstManager CONST_MANAGER = ConstManagerFactory.getConstManagerImpl();

	public static final Integer INT_0 = new Integer(0);
	public static final Integer INT_1 = new Integer(1);
	public static final Integer INT_2 = new Integer(2);
	public static final Integer INT_3 = new Integer(3);
	public static final Integer INT_4 = new Integer(4);
	public static final Integer INT_5 = new Integer(5);
	public static final Integer INT_6 = new Integer(6);
	public static final Integer INT_7 = new Integer(7);
	public static final Integer INT_8 = new Integer(8);
	public static final Integer INT_9 = new Integer(9);
	public static final Integer INT_10 = new Integer(10);
	// #yes ����������֤
	public static final String CONST_CHECK_KOULING = CONST_MANAGER.getString("CONST_CHECK_KOULING");
	public static final String CONST_CHECK_URL = CONST_MANAGER.getString("CONST_CHECK_URL");
	public static final String CONST_CHECK_OPERATION = CONST_MANAGER.getString("CONST_CHECK_OPERATION");
	public static final String CONST_CHECK_SOAPACTION = CONST_MANAGER.getString("CONST_CHECK_SOAPACTION");
	public static final String CONST_CHECK_INPARAM = CONST_MANAGER.getString("CONST_CHECK_INPARAM");
	// #���ö�̬����������Կ���
	public static final String CONST_GET_HSM_KEY_PARAM = CONST_MANAGER.getString("CONST_GET_HSM_KEY_PARAM");
	// #�̻���ʼ��Ȩ��
	public static final String CONST_MERCHANT_GROUP_ID = CONST_MANAGER.getString("CONST_MERCHANT_GROUP_ID");
	// #ͬ�����ݿ���
	public static final String CONST_CHECK_SYNCDATA = CONST_MANAGER.getString("CONST_CHECK_SYNCDATA");

	public static final String CONST_SYNC_OLD_FK_HOST = CONST_MANAGER.getString("CONST_SYNC_OLD_FK_HOST");
	public static final int CONST_SYNC_OLD_FK_PORT = CONST_MANAGER.getInt("CONST_SYNC_OLD_FK_PORT");
	public static final String CONST_SYNC_OLD_FK_USER = CONST_MANAGER.getString("CONST_SYNC_OLD_FK_USER");
	public static final String CONST_SYNC_OLD_FK_PWD = CONST_MANAGER.getString("CONST_SYNC_OLD_FK_PWD");
	public static final String CONST_SYNC_OLD_FK_SETTLE_COMMAND = CONST_MANAGER.getString("CONST_SYNC_OLD_FK_SETTLE_COMMAND");
	public static final String CONST_SYNC_OLD_FK_LOGIN_COMMAND = CONST_MANAGER.getString("CONST_SYNC_OLD_FK_LOGIN_COMMAND");

}

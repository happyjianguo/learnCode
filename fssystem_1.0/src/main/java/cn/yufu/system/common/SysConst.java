package cn.yufu.system.common;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.yufu.system.common.utils.ConstManager;
import cn.yufu.system.common.utils.ConstManagerFactory;






/**
 * 系统自定义的数据
 * 
 * @author mengfp
 * @date 2014年9月12日
 */
public class SysConst {
	public static final ConstManager CONST_MANAGER = ConstManagerFactory.getConstManagerImpl();
	/**
	 * 保存在session中的商户号的key
	 */
	public static final String SESSION_USER_INFO_KEY = "userInfo";

	public static final String RESULT = "result";
	public static final String RESULT_MSG = "resultMsg";
	public static final String RESULT_SUCCESS = "0";
	public static final String RESULT_FAIL = "-1";
	/**
	 * 模块选择
	 */
	public static final String MODEL = "currentModule";
	public static final int MENU_FOCUS_0 = 0;// 代理商管理
	public static final int MENU_FOCUS_1 = 1;// 商户管理
	public static final int MENU_FOCUS_2 = 2;// 终端管理
	public static final int MENU_FOCUS_3 = 3;// 交易管理
	public static final int MENU_FOCUS_4 = 4;// 结算管理
	public static final String SUB_MODEL = "sub_currentModule";
	public static final int SUB_MENU_FOCUS_0_1 = 01;// 代理商信息
	public static final int SUB_MENU_FOCUS_0_2 = 02;// 修改密码
	public static final int SUB_MENU_FOCUS_1_1 = 11;// 商户信息
	public static final int SUB_MENU_FOCUS_1_2 = 12;// 修改密码
	public static final int SUB_MENU_FOCUS_2_1 = 21;// 终端列表
	public static final int SUB_MENU_FOCUS_3_1 = 31;// 消费列表
	public static final int SUB_MENU_FOCUS_3_2 = 32;// 撤销列表
	public static final int SUB_MENU_FOCUS_3_3 = 33;// 应用类列表
	public static final int SUB_MENU_FOCUS_4_1 = 42;// 日结算报表

	/**
	 * 用户类型 1：商户用户
	 */
	public static final String USER_TYPE_MERC = "1";

	/**
	 * 用户类型 2：代理商用户
	 */
	public static final String USER_TYPE_AGENT = "2";

	public static final String CONST_MSTCARD_ACCOUNT_HOST = CONST_MANAGER.getString("CONST_MSTCARD_ACCOUNT_HOST");
	public static final int CONST_MSTCARD_ACCOUNT_PORT = CONST_MANAGER.getInt("CONST_MSTCARD_ACCOUNT_PORT");
	public static final String CONST_MSTCARD_ACCOUNT_USER = CONST_MANAGER.getString("CONST_MSTCARD_ACCOUNT_USER");
	public static final String CONST_MSTCARD_ACCOUNT_PWD = CONST_MANAGER.getString("CONST_MSTCARD_ACCOUNT_PWD");
	public static final String CONST_MSTCARD_ACCOUNT_COMMAND = CONST_MANAGER.getString("CONST_MSTCARD_ACCOUNT_COMMAND");

	public static final String CONST_SRC_FTP_HOST = CONST_MANAGER.getString("CONST_SRC_FTP_HOST");
	public static final int CONST_SRC_FTP_PORT = CONST_MANAGER.getInt("CONST_SRC_FTP_PORT");
	public static final String CONST_SRC_FTP_USERNAME = CONST_MANAGER.getString("CONST_SRC_FTP_USERNAME");
	public static final String CONST_SRC_FTP_PASSWORD = CONST_MANAGER.getString("CONST_SRC_FTP_PASSWORD");
	public static final String CONST_DST_FTP_HOST = CONST_MANAGER.getString("CONST_DST_FTP_HOST");
	public static final int CONST_DST_FTP_PORT = CONST_MANAGER.getInt("CONST_DST_FTP_PORT");
	public static final String CONST_DST_FTP_USERNAME = CONST_MANAGER.getString("CONST_DST_FTP_USERNAME");
	public static final String CONST_DST_FTP_PASSWORD = CONST_MANAGER.getString("CONST_DST_FTP_PASSWORD");
	public static final String CONST_DST_FTP_DIR = CONST_MANAGER.getString("CONST_DST_FTP_DIR");
	public static final String CONST_MSTCARD_LOCAL_DIR = CONST_MANAGER.getString("CONST_MSTCARD_LOCAL_DIR");

	/**
	 * X
	 */
	public static final String CONST_X_TAXCODE=CONST_MANAGER.getString("CONST_X_TAXCODE");
	public static final String CONST_X_CURRCODE=CONST_MANAGER.getString("CONST_X_CURRCODE");
	public static final String CONST_X_INST_ID=CONST_MANAGER.getString("CONST_X_INST_ID");
	public static final String CONST_X_OFFICIAL=CONST_MANAGER.getString("CONST_X_OFFICIAL");
	public static final String CONST_X_BRNCODE=CONST_MANAGER.getString("CONST_X_BRNCODE");
	public static final String CONST_X_ITYPEID=CONST_MANAGER.getString("CONST_X_ITYPEID");
	
	
	/**
	 * 
	 */
	public static TreeMap<String, List<Map<String, Object>>> ATTR_MAP = new TreeMap<String, List<Map<String, Object>>>();

}

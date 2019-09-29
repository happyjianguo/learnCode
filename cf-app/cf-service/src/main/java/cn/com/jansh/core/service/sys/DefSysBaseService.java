package cn.com.jansh.core.service.sys;

/**
 * 系统参数服务接口
 *
 * @author Mr.wong
 *
 */
public interface DefSysBaseService {

	/**
	 * 通过系统参数名获取参数实体
	 */
//	public PubsSysBase querySysBaseAceBaseId(String baseId);

	/**
	 * 通过系统参数名获取参数值
	 */
	public String querySysBaseValue(String baseId);

	/**
	 * 获取重置密码
	 */
	public String querySysBaseInitPwd();

	/**
	 * 获取微信请求url
	 * 
	 * @return
	 */
//	public String querySysBaseWxMsgRequrl();

	/**
	 * 获取全部的系统参数
	 */
//	public List<PubsSysBase> queryAllSysBase(String sysBaseId);

	/**
	 * 更新系统参数
	 */
//	public void updateSysBase(PubsSysBase pubsSysBase);
}

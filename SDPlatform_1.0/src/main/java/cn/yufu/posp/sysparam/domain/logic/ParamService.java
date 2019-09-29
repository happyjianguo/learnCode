package cn.yufu.posp.sysparam.domain.logic;

public interface ParamService {

	/**
	 * 同步卡类基本信息
	 * 
	 * @param userId
	 * @return
	 */
	public abstract String syncCardType(String jsonString);
}

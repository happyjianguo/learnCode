package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxdResMessage;

public interface WxdResMessageMapper {
	/**
	 * 关注，根据RESTYPE和PLATFORMID取出对象
	 * @param resmsg
	 * @return
	 */
	public WxdResMessage selectByResType(WxdResMessage resmsg);
	/**
	 * 根据RESTYPE，RESMSGKEY和PLATFORMID取出对象
	 * @param resmsg
	 * @return
	 */
	public WxdResMessage getResContent(WxdResMessage resmsg);

}

package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxdErrMsg;

public interface WxdErrMsgMapper {
	/**
	 * 根据ERRORCODE获取WxdErrMsg信息
	 * @param errorCode
	 * @return
	 */
	public WxdErrMsg  selectByErrorCode(String errorCode);
}

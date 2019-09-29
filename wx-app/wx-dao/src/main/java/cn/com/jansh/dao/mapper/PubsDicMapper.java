package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.PubsDic;

public interface PubsDicMapper {
	/**
	 * 根据NOTETAG和NOTETYPE获取PubsDic对象
	 * @param pubsDic
	 * @return
	 */
	public PubsDic getUrl(PubsDic pubsDic);
}

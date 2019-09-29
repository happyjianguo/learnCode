package cn.yufu.cortex.service;

import java.util.List;

import cn.yufu.cortex.entity.NumDescr;

public interface NumDescrService {

	/**
	 * X
	 * @param descrtype
	 * @param lang
	 * @return
	 */
	public List<NumDescr> queryList(String descrtype, String lang);

}

package cn.com.jansh.dao.mapper;

import java.util.List;
import java.util.Map;

import cn.com.jansh.dao.entity.PubTasSign;

public interface PubTasSignMapper {
	/**
	 * 通过DOCID获取PubTasSign对象
	 * @param docId
	 * @return
	 */
	public PubTasSign selectByDocId(String docId);

	/**
	 * 获取未发送的动帐提醒
	 * @return
	 */
	public List<PubTasSign> selectPubTassgin();
	
	/**
	 * 更新状态
	 * @param map
	 * @return
	 */
	public int updateByWSQM(Map<String,String> map);
}

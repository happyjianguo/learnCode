package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.wsfdn.CfAuditEntity;

public interface CfAuditMapper {

	/**
	 * 查询集合根据对象
	 * 
	 * @param cfAuditEntity
	 * @return
	 */
	public List<CfAuditEntity> queryAll(CfAuditEntity cfAuditEntity,String userid);
	/**
	 * 查询集合根据对象
	 * 待审
	 * 
	 * @param cfAuditEntity
	 * @return
	 */
	public List<CfAuditEntity> queryAllShow(Map<String,String> map);

	/**
	 * 查询集合根据对象
	 * 
	 * @param cfAuditEntity
	 * @return
	 */
	public List<CfAuditEntity> queryResultAll(CfAuditEntity cfAuditEntity,String userid);
	
	/**
	 * 查询通过id
	 * 
	 * @return
	 */
	public CfAuditEntity query(String id);

	/**
	 * 通过id及状态查询审批数据
	 * @return
	 */
	public CfAuditEntity queryAudit(@Param("id")String id,@Param("status")String status);
	
	/**
	 * 更新
	 * 
	 * @param cfAuditEntity
	 */
	public int update(CfAuditEntity cfAuditEntity,String status);

	/**
	 * 插入Audit
	 * 
	 * @param cfAuditEntity
	 */
	public void insertAudit(CfAuditEntity cfAuditEntity);

}

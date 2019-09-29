package cn.com.jansh.mapper.wsfdn;

import cn.com.jansh.entity.wsfdn.CfAuditdetailEntity;

public interface CfAuditDetailMapper {

	public CfAuditdetailEntity query(String id);
	
	public void insert(CfAuditdetailEntity auditdetailEntity);
}

package cn.com.jansh.service.audit;

import cn.com.jansh.core.exception.AppException;

/**
 * 审批接口
 * @author duanmuyn
 *
 */
public interface AuditService {
	
	/**
	 * 审批新增
	 */
	public void auditinsert(Object obj)throws AppException;
	
	/**
	 * 审批修改
	 */
	public void auditupdate(Object obj)throws AppException;
	
	/**
	 * 审批删除
	 */
	public void auditdelete(Object obj)throws AppException;
}

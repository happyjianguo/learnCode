package cn.com.jansh.service.wsfdn;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfAuditEntity;
import cn.com.jansh.service.audit.AuditService;

/**
 * 审批管理
 * @author duanmuyn
 *
 */
public interface CfAuditService {

	/**
	 * 初始化审批数据
	 * 
	 * @return
	 */
	public List<CfAuditEntity> init(CfAuditEntity cfAuditEntity,String userid);
	/**
	 * 初始化审批数据只显示待审
	 * 
	 * @return
	 */
	public List<CfAuditEntity> initshow(String status,String userid);

	/**
	 * 初始化审批查询数据
	 * 
	 * @return
	 */
	public List<CfAuditEntity> resultinit(CfAuditEntity cfAuditEntity,String userid);
	/**
	 * 回显审批数据
	 * 
	 * @return
	 */
	public Map<String,Object> audit(String id);

	/**
	 * 存储审批信息
	 * 
	 * @param transName
	 * @param operation
	 * @param model
	 * @param AuditService
	 * @throws JsonProcessingException 
	 * @throws AppException 
	 */
	public void saveAudit(String transName, Operation operation, String detailkey, Object model,
			AuditService AuditService) throws JsonProcessingException, AppException;
	
	/**
	 * 审批数据
	 * @param cfAuditEntity
	 * @throws AppException 
	 */
	public void audit(CfAuditEntity cfAuditEntity) throws AppException;
}

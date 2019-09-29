package cn.com.jansh.service.audit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.mapper.wsfdn.CfBatchinfoMapper;
import cn.com.jansh.service.audit.AuditService;
/**
 * 批量充值审批
 * @author duanmuyn
 *
 */
@Service
public class AuditBatchrechargeServiceImpl implements AuditService {

	@Autowired
	private CfBatchinfoMapper cfBatchinfoMapper;
	
	@Override
	public void auditinsert(Object obj) throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void auditupdate(Object obj) throws AppException {
		CfBatchinfoEntity cfBatchinfoEntity = new CfBatchinfoEntity();
		cfBatchinfoEntity = (CfBatchinfoEntity)obj;
		cfBatchinfoMapper.updataCfBatchinfo(cfBatchinfoEntity);
	}

	@Override
	public void auditdelete(Object obj) throws AppException {
		// TODO Auto-generated method stub

	}

}

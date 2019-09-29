package cn.com.jansh.service.audit.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.mapper.wsfdn.CfAccesspriceMapper;
import cn.com.jansh.model.AccesspriceModel;
import cn.com.jansh.service.audit.AuditService;

/**
 * 接入者报价审批
 * @author duanmuyn
 *
 */
@Service
public class AuditAccesspriceServiceImpl implements AuditService{

	private static final Logger logger = LogManager.getLogger(AuditAccesspriceServiceImpl.class);
	
	@Autowired
	private CfAccesspriceMapper cfAccesspriceMapper;
	
	@Override
	public void auditinsert(Object obj) throws AppException {
		
		AccesspriceModel accesspriceModel = new AccesspriceModel();
		accesspriceModel = (AccesspriceModel)obj;
		CfAccesspriceEntity cfAccesspriceEntity = modelToEntity(accesspriceModel);
		logger.info("新增接入者报价{}"+cfAccesspriceEntity);
		String apid = cfAccesspriceEntity.getApid();
		String price = cfAccesspriceEntity.getPrice();
		cfAccesspriceEntity.setApid("");
		cfAccesspriceEntity.setPrice("");
		List<CfAccesspriceEntity> li = cfAccesspriceMapper.query(cfAccesspriceEntity);
		if(CollectionUtils.isNotEmpty(li)){
			throw new AppException(AppErrorCode.E260001);
		}else{
			cfAccesspriceEntity.setApid(apid);
			cfAccesspriceEntity.setPrice(price);
			cfAccesspriceMapper.insert(cfAccesspriceEntity);
		}
	}

	@Override
	public void auditupdate(Object obj) {
		AccesspriceModel accesspriceModel = new AccesspriceModel();
		accesspriceModel = (AccesspriceModel)obj;
		cfAccesspriceMapper.updateAccessprice(modelToEntity(accesspriceModel));
	}

	@Override
	public void auditdelete(Object obj) {
		AccesspriceModel accesspriceModel = new AccesspriceModel();
		accesspriceModel = (AccesspriceModel)obj;
		cfAccesspriceMapper.deleteAccessprice(accesspriceModel.getApid());
	}

	/**
	 * Model转Entity(新增)
	 * @param SupplierpriceModel
	 * @return
	 */
	private CfAccesspriceEntity modelToEntity(AccesspriceModel accesspriceModel){
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setApid(accesspriceModel.getApid());
		cfAccesspriceEntity.setAcid(accesspriceModel.getAcid());
		cfAccesspriceEntity.setFacevalue(accesspriceModel.getFacevalue());
		cfAccesspriceEntity.setIpstype(accesspriceModel.getIpstype());
		cfAccesspriceEntity.setIspno(accesspriceModel.getIspno());
		cfAccesspriceEntity.setPrice(accesspriceModel.getPrice());
		cfAccesspriceEntity.setProvince(accesspriceModel.getProvince());
		cfAccesspriceEntity.setStatus(accesspriceModel.getStatus());
		return cfAccesspriceEntity;
	}

}

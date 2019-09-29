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
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.mapper.wsfdn.CfAccesspriceMapper;
import cn.com.jansh.mapper.wsfdn.CfSupplierpriceMapper;
import cn.com.jansh.model.SupplierpriceModel;
import cn.com.jansh.service.audit.AuditService;
/**
 * 供应商报价审批
 * @author duanmuyn
 *
 */
@Service
public class AuditSupplierpriceServiceImpl implements AuditService {

	private final static Logger logger = LogManager.getLogger(AuditSupplierpriceServiceImpl.class);
	
	@Autowired
	private CfSupplierpriceMapper cfSupplierpriceMapper;
	
	@Autowired
	private CfAccesspriceMapper cfAccesspriceMapper;
	
	@Override
	public void auditinsert(Object obj) throws AppException {
		SupplierpriceModel supplierpriceModel = new SupplierpriceModel();
		supplierpriceModel = (SupplierpriceModel)obj;
		CfSupplierpriceEntity Supplierprice = cfSupplierpriceMapper.querySupplierpriceByName(supplierpriceModel.getPname());
		if(null != Supplierprice){
			throw new AppException(AppErrorCode.E250001);
		}else{
			cfSupplierpriceMapper.addCFSupplierprice(modelToEntity(supplierpriceModel));
		}
	}

	@Override
	public void auditupdate(Object obj) {
		SupplierpriceModel supplierpriceModel = new SupplierpriceModel();
		supplierpriceModel = (SupplierpriceModel)obj;
		logger.info("修改供应商报价{}",supplierpriceModel);
		cfSupplierpriceMapper.updateSupplierprice(modelToEntity(supplierpriceModel));
	}

	@Override
	public void auditdelete(Object obj) throws AppException {
		SupplierpriceModel supplierpriceModel = new SupplierpriceModel();
		supplierpriceModel = (SupplierpriceModel)obj;
		
		logger.info("删除供应商报价{}",supplierpriceModel);
		CfAccesspriceEntity accessprice = new CfAccesspriceEntity();
		accessprice.setIspno(supplierpriceModel.getIspno());
		accessprice.setIpstype(supplierpriceModel.getIpstype());
		accessprice.setFacevalue(supplierpriceModel.getSize());
		accessprice.setProvince(supplierpriceModel.getPno());
		List<CfAccesspriceEntity> cfAccesspriceEntity = cfAccesspriceMapper.queryAccesspriceByApprice(accessprice);
		if(CollectionUtils.isNotEmpty(cfAccesspriceEntity)){
			throw new AppException(AppErrorCode.E250002);
		}else{
			cfSupplierpriceMapper.delSupplierprice(supplierpriceModel.getId());
		}

	}

	
	/**
	 * Model转Entity(新增)
	 * @param SupplierpriceModel
	 * @param type("add"时id自动生成，"edit"时用原有id)
	 * @return
	 */
	private CfSupplierpriceEntity modelToEntity(SupplierpriceModel supplierpriceModel){
		CfSupplierpriceEntity cfSupplierpriceEntity = new CfSupplierpriceEntity();
		cfSupplierpriceEntity.setId(supplierpriceModel.getId());
		cfSupplierpriceEntity.setPname(supplierpriceModel.getPname());
		cfSupplierpriceEntity.setIspno(supplierpriceModel.getIspno());
		cfSupplierpriceEntity.setIpstype(supplierpriceModel.getIpstype());
		cfSupplierpriceEntity.setPno(supplierpriceModel.getPno());
		cfSupplierpriceEntity.setSize(supplierpriceModel.getSize());
		cfSupplierpriceEntity.setSid(supplierpriceModel.getSid());
		cfSupplierpriceEntity.setPrice(supplierpriceModel.getPrice());
		cfSupplierpriceEntity.setBegintime(supplierpriceModel.getBegintime());
		cfSupplierpriceEntity.setEndtime(supplierpriceModel.getEndtime());
		cfSupplierpriceEntity.setStatus(supplierpriceModel.getStatus());
		cfSupplierpriceEntity.setCpordernos(supplierpriceModel.getCpordernos());
		return cfSupplierpriceEntity;
	}
}

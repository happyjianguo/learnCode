package cn.com.jansh.service.wsfdn.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.exception.SysErrorCode;
import cn.com.jansh.core.service.sys.EmailService;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.wsfdn.CfAuditEntity;
import cn.com.jansh.entity.wsfdn.CfAuditdetailEntity;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.wsfdn.CfAuditDetailMapper;
import cn.com.jansh.mapper.wsfdn.CfAuditMapper;
import cn.com.jansh.model.AccesspriceModel;
import cn.com.jansh.model.BatchinfoModel;
import cn.com.jansh.model.SupplierpriceModel;
import cn.com.jansh.service.audit.AuditService;
import cn.com.jansh.service.audit.impl.AuditAccesspriceServiceImpl;
import cn.com.jansh.service.audit.impl.AuditBatchrechargeServiceImpl;
import cn.com.jansh.service.audit.impl.AuditSupplierpriceServiceImpl;
import cn.com.jansh.service.wsfdn.CfAuditService;

@Service
public class CfAuditServiceImpl implements CfAuditService{

	private final static Logger logger = LogManager.getLogger(CfAuditServiceImpl.class);
	
	@Autowired
	private CfAuditMapper cfAuditMapper;
	
	@Autowired
	private CfAuditDetailMapper cfAuditDetailMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private IMUserMapper iMUserMapper;
	
	private AuditService auditService;
	
	@Override
	public List<CfAuditEntity> init(CfAuditEntity cfAuditEntity,String userid) {
		logger.info("审批初始化service");
		return cfAuditMapper.queryAll(cfAuditEntity,userid);
	}
	@Override
	public List<CfAuditEntity> initshow(String status, String userid) {
		logger.info("审批初始化待审service");
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", status);
		map.put("userid", userid);
		List<CfAuditEntity> list =  cfAuditMapper.queryAllShow(map);
		return list;
	}

	@Override
	public List<CfAuditEntity> resultinit(CfAuditEntity cfAuditEntity, String userid) {
		logger.info("审批查询初始化service");
		List<CfAuditEntity> list = cfAuditMapper.queryResultAll(cfAuditEntity,userid);
		return list;
	}
	@Override
	public Map<String,Object> audit(String id) {
		CfAuditEntity cfAuditEntity = cfAuditMapper.query(id);
		CfAuditdetailEntity cfAuditdetailEntity = cfAuditDetailMapper.query(id);
		Map<String,Object> mapobj = new HashMap<String,Object>();
		try {
			mapobj = JsonUtil.readMapObject(cfAuditdetailEntity.getDetail());
			logger.info("解析json:{}",mapobj.toString());
			if(mapobj != null && mapobj.size()>0){
				@SuppressWarnings("unchecked")
				Map<String,String> map = (Map<String, String>) mapobj.get("data");
				String classname = (String)mapobj.get("class");
				Class<?> classType = Class.forName(classname);  
		        Object obj = classType.newInstance();  
		        obj = JsonUtil.readObject(JsonUtil.obj2json(map), classType);
		        mapobj.clear();
		        mapobj.put("class", classname);
		        mapobj.put("data", obj);
		        mapobj.put("audit", cfAuditEntity);
		        return mapobj;
			}
		} catch (Exception e) {
			logger.error("解析JSON错误{}",cfAuditdetailEntity.getDetail());
		}
		return null;
	}
	
	@Override
	public void saveAudit(String transName, Operation operation, String detailkey, Object model, AuditService AuditService) throws JsonProcessingException, AppException {
		if(operation.equals(Operation.UPDATE)||operation.equals(Operation.DELETE)){
			CfAuditEntity cfAuditEntity = cfAuditMapper.queryAudit(detailkey, AppCommonsCode.AUDIT_WAIT.value());
			if(cfAuditEntity != null){
				throw new AppException(SysErrorCode.E110007);
			}
		}
		CfAuditEntity auditEntity = new CfAuditEntity();
		auditEntity.setAutype(getOperationVal(operation));
		auditEntity.setAuname(transName);
		String dataTime = DateUtil.getDateTime();
		auditEntity.setCreatetime(dataTime);
		auditEntity.setUpdatetime("");
		auditEntity.setDetailkey(detailkey);
		auditEntity.setDraftman(AppUtil.getUserDetail().getUserid());
		String auid = IDUtils.getTimeRandon();
		auditEntity.setId(auid);
		String className = AuditService.getClass().getSimpleName().replaceAll("\\$\\$.*$", "");
		String serviceName = (new StringBuilder()).append(Character.toLowerCase(className.charAt(0))).append(className.substring(1)).toString();
		auditEntity.setServicename(serviceName);
		auditEntity.setStatus(AppCommonsCode.AUDIT_WAIT.value());
		cfAuditMapper.insertAudit(auditEntity);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("class", model.getClass().getName());
		jsonMap.put("data", model);
		
		CfAuditdetailEntity auditdetailEntity = new CfAuditdetailEntity();
		auditdetailEntity.setId(auid);
		auditdetailEntity.setDetail(JsonUtil.obj2json(jsonMap));
		cfAuditDetailMapper.insert(auditdetailEntity);
	}
	
	private String getOperationVal(Operation operation) {
		switch (operation) {
		case CREATE:
			return "create";
		case DELETE:
			return "delete";
		case UPDATE:
			return "update";
		case RETRIEVE:
			return "retrieve";
		case DEFULT:
			return "defult";
		default:
			return "defult";
		}
	}

	/**
	 * 审批数据
	 * @throws AppException 
	 */
	@Override
	public void audit(CfAuditEntity cfAuditEntity) throws AppException {
		int i = cfAuditMapper.update(cfAuditEntity,AppCommonsCode.AUDIT_WAIT.value());
		if(i<=0){
			logger.error("其他管理员正在审核此条记录:{}",cfAuditEntity);
			throw new AppException(SysErrorCode.E110007);
		}
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		cfAuditEntity = cfAuditMapper.query(cfAuditEntity.getId());
		IMUser iMUser = iMUserMapper.selectByUserid(cfAuditEntity.getAuditer());
		
		
		//如果审批通过，则对原业务进行处理
		if(cfAuditEntity.getStatus().equals(AppCommonsCode.AUDIT_OK.value())){
			CfAuditdetailEntity cfAuditdetailEntity = cfAuditDetailMapper.query(cfAuditEntity.getId());
			Map<String,Object> mapobj = new HashMap<String,Object>();
				try {
					mapobj = JsonUtil.readMapObject(cfAuditdetailEntity.getDetail());
				} catch (Exception e) {
					logger.error("解析json错误:{}",e);
					throw new AppException(SysErrorCode.E100001);
				}
				logger.info("解析json:{}",mapobj.toString());
				if(mapobj != null && mapobj.size()>0){
					@SuppressWarnings("unchecked")
					Map<String,String> map = (Map<String, String>) mapobj.get("data");
					String classname = (String)mapobj.get("class");
					Object obj = null;
					try{
						Class<?> classType = Class.forName(classname);
						obj = classType.newInstance();  
				        obj = JsonUtil.readObject(JsonUtil.obj2json(map), classType);
					}catch(Exception e){
						throw new AppException(SysErrorCode.E100001);
					}
			        //接入者报价
			        if(obj instanceof AccesspriceModel){
			        	auditService =  (AuditAccesspriceServiceImpl) wac.getBean(cfAuditEntity.getServicename());
						if(cfAuditEntity.getAutype().equals("create")){
							auditService.auditinsert(obj);
						}else if(cfAuditEntity.getAutype().equals("update")){
							auditService.auditupdate(obj);
						}else if(cfAuditEntity.getAutype().equals("delete")){
							auditService.auditdelete(obj);
						}
					//供应商报价
					}else if(obj instanceof SupplierpriceModel){
						auditService =  (AuditSupplierpriceServiceImpl) wac.getBean(cfAuditEntity.getServicename());
						if(cfAuditEntity.getAutype().equals("create")){
							auditService.auditinsert(obj);
						}else if(cfAuditEntity.getAutype().equals("update")){
							auditService.auditupdate(obj);
						}else if(cfAuditEntity.getAutype().equals("delete")){
							auditService.auditdelete(obj);
						}
					//批量充值
					}else if(obj instanceof BatchinfoModel){
						auditService = (AuditBatchrechargeServiceImpl) wac.getBean(cfAuditEntity.getServicename());
						BatchinfoModel BatchinfoModel = (BatchinfoModel)obj;
			        	CfBatchinfoEntity cfBatchinfoEntity = new CfBatchinfoEntity();
			        	cfBatchinfoEntity.setBatchid(BatchinfoModel.getBatchid());
			        	cfBatchinfoEntity.setStatus(AppCommonsCode.RECHARGE_22.value());
			        	auditService.auditupdate(cfBatchinfoEntity);
					}
				}
		}else if(cfAuditEntity.getStatus().equals(AppCommonsCode.AUDIT_ERROR.value())){
			CfAuditdetailEntity cfAuditdetailEntity = cfAuditDetailMapper.query(cfAuditEntity.getId());
			Map<String,Object> mapobj = new HashMap<String,Object>();
			try {
				mapobj = JsonUtil.readMapObject(cfAuditdetailEntity.getDetail());
			
				logger.info("解析json:{}",mapobj.toString());
				if(mapobj != null && mapobj.size()>0){
					@SuppressWarnings("unchecked")
					Map<String,String> map = (Map<String, String>) mapobj.get("data");
					String classname = (String)mapobj.get("class");
					Class<?> classType = Class.forName(classname);  
			        Object obj = classType.newInstance();  
			        obj = JsonUtil.readObject(JsonUtil.obj2json(map), classType);
			        
			        if(obj instanceof BatchinfoModel){
			        	auditService = (AuditBatchrechargeServiceImpl) wac.getBean(cfAuditEntity.getServicename());
			        	BatchinfoModel BatchinfoModel = (BatchinfoModel)obj;
			        	CfBatchinfoEntity cfBatchinfoEntity = new CfBatchinfoEntity();
			        	cfBatchinfoEntity.setBatchid(BatchinfoModel.getBatchid());
			        	cfBatchinfoEntity.setStatus(AppCommonsCode.RECHARGE_23.value());
			        	auditService.auditupdate(cfBatchinfoEntity);
					}
				}
			} catch (Exception e1) {
				logger.error("解析JSON错误{}",cfAuditdetailEntity.getDetail());
			}
		}
		if(iMUser != null){
			try {
				String str ="审批人:"+iMUser.getCname();
				str = str+"\n审批名称:"+cfAuditEntity.getAuname();
				if(cfAuditEntity.getStatus().equals(AppCommonsCode.AUDIT_ERROR.value())){
					str = str+"\n审批结果:不同意";
				}else if(cfAuditEntity.getStatus().equals(AppCommonsCode.AUDIT_OK.value())){
					str = str+"\n审批结果:同意";
				}
				if(StringUtils.isEmpty(cfAuditEntity.getOpinion())){
					str = str+"\n意见:无";
				}else {
					str = str+"\n意见:"+cfAuditEntity.getOpinion();
				}
				
				emailService.sendmail(iMUser.getEmail(), "话费流量分发平台审批结果", str);
			} catch (EmailException e) {
				logger.error("发送邮件错误：{}", e);
			}
		}
	}
}

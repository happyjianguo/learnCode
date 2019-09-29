package cn.com.jansh.service.wsfdn.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.exception.SysErrorCode;
import cn.com.jansh.core.util.ExcelUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.mapper.wsfdn.CfBatchinfoMapper;
import cn.com.jansh.mapper.wsfdn.CfBatchrechargeMapper;
import cn.com.jansh.service.system.GlobalPropertiesService;
import cn.com.jansh.service.wsfdn.CfBatchrechargeService;

@Service
public class CfBatchrechargeServiceImpl implements CfBatchrechargeService {

	private final static Logger logger = LogManager.getLogger(CfBatchrechargeServiceImpl.class);
	
	@Autowired
	private GlobalPropertiesService globalPropertiesService;
	
	@Autowired
	private CfBatchinfoMapper cfBatchinfoMapper;
	
	@Autowired
	private CfBatchrechargeMapper cfBatchrechargeMapper;
	
	@Override
	public void uploadExcel(MultipartFile myfile,CfBatchinfoEntity cfBatchinfoEntity) throws AppException {
		logger.info("批量上传入库");
		String id = IDUtils.getTimeRandon();
		//判断目录是否存在
		String path = globalPropertiesService.getUploadBatchchargePath();
		File file=new File(path);
		if(!file.exists()&&!file.isDirectory()){
			file.mkdir();
		}
		try {
			FileUtils.copyInputStreamToFile(myfile.getInputStream(),new File(path,id+".xlsx"));
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("批量充值保存文件错误{}",e1);
			throw new AppException(SysErrorCode.E110005);
		}
		
		try {
			List<List<String>> list = ExcelUtil.readExcel(myfile);
			List<CfBatchrechargeEntity> cfBatchList = new ArrayList<CfBatchrechargeEntity>();
			int i = 0 ;
			for (List<String> list2 : list) {
				logger.info("第{}行{}",i++,list2.toString());
				if(i==1){
					continue;
				}
				CfBatchrechargeEntity cfBatch = new CfBatchrechargeEntity();
				cfBatch.setId(IDUtils.getTimeRandon());
				cfBatch.setIspno(list2.get(0).toString());//运营商
				cfBatch.setIsptype(list2.get(1).toString());//充值类型
				cfBatch.setFacevalue(list2.get(2).toString());//面值
				cfBatch.setPhone(list2.get(3).toString());//手机号
				cfBatch.setBatchid(cfBatchinfoEntity.getBatchid());//充值编号
				cfBatch.setStatus(AppCommonsCode.RECHARGE_21.value());//发送状态
				cfBatchList.add(cfBatch);
			}
			
			cfBatchinfoMapper.insertCfBatchinfo(cfBatchinfoEntity);
			cfBatchrechargeMapper.insertCfBatchrecharge(cfBatchList);
		} catch (IOException e) {
			throw new AppException(SysErrorCode.E110005);
		}
	}

	@Override
	public List<CfBatchrechargeEntity> queryBatchrecharge(CfBatchrechargeEntity cfBatchrechargeEntity) {
		logger.info("查询批量充值信息{}",cfBatchrechargeEntity);
		return cfBatchrechargeMapper.queryBatchrechargeli(cfBatchrechargeEntity);
	}

	@Override
	public void uploadOk(CfBatchinfoEntity cfBatchinfoEntity) {
		cfBatchinfoMapper.updataCfBatchinfo(cfBatchinfoEntity);
		cfBatchrechargeMapper.updataBatchrecharge(cfBatchinfoEntity);
	}

	@Override
	public void rollback(CfBatchinfoEntity cfBatchinfoEntity) {
		cfBatchinfoMapper.deleteCfBatchinfo(cfBatchinfoEntity.getBatchid());
		cfBatchrechargeMapper.rollback(cfBatchinfoEntity);
	}

	@Override
	public List<CfBatchrechargeEntity> queryStatusByBatchid(String batchid) {
		return cfBatchrechargeMapper.queryStatusByBatchid(batchid);
	}

	@Override
	public CfBatchinfoEntity queryBatchinfo(String id) {
		return cfBatchinfoMapper.queryBatchinfo(id);
	}

}

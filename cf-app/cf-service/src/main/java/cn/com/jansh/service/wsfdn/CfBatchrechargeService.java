package cn.com.jansh.service.wsfdn;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;

public interface CfBatchrechargeService {
	/**
	 * 上传Excel并解析
	 */
	public void uploadExcel(MultipartFile myfile,CfBatchinfoEntity cfBatchinfoEntity) throws AppException;
	
	/**
	 *  确认上传
	 * @param batchid
	 * @return
	 */
	public void uploadOk(CfBatchinfoEntity cfBatchinfoEntity);
	/**
	 * 取消上上传
	 * @param cfBatchinfoEntity
	 */
	public void rollback(CfBatchinfoEntity cfBatchinfoEntity);
	
	/**
	 * 通过id查询批量充值信息
	 * @param id
	 * @return
	 */
	public CfBatchinfoEntity queryBatchinfo(String id) ;
	
	/**
	 * 查询批量充值信息
	 * @return
	 */
	public List<CfBatchrechargeEntity> queryBatchrecharge(CfBatchrechargeEntity cfBatchrechargeEntity);
	
	/**
	 * 通过batchid获取
	 * @param batchid
	 * @return
	 */
	public List<CfBatchrechargeEntity> queryStatusByBatchid(String batchid);
	
	
}

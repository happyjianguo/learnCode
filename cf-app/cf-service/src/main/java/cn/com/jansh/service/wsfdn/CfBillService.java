package cn.com.jansh.service.wsfdn;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfOfbillEntity;
import cn.com.jansh.entity.wsfdn.CfWsbillEntity;

public interface CfBillService {

	/**
	 * 根据时间和状态查询
	 * @param cfOfbillEntity
	 * @return
	 */
	public List<CfOfbillEntity> query(CfOfbillEntity cfOfbillEntity);

	public List<CfWsbillEntity> querywsBill(CfWsbillEntity cfWsbillEntity);
	
	/**
	 * 上传Excel并解析
	 */
	public void uploadExcel(MultipartFile myfile,CfWsbillEntity cfWsbillEntity) throws AppException;
	/**
	 *  确认上传
	 * @param batchid
	 * @return
	 */
	public void uploadOk(CfWsbillEntity cfWsbillEntity);	
	/**
	 * 取消上上传
	 * @param cfBatchinfoEntity
	 */
	public void rollback(CfWsbillEntity cfWsbillEntity);		
}

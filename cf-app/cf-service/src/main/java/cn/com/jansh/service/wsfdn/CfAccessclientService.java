package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;

/**
 * 接入者service
 * @author gll
 *
 */
public interface CfAccessclientService {

	/**
	 * 初始化接入者列表
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclient();
	
	/**
	 * 查询接入者列表
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclientBy(CfAccessclientEntity cfAccessclientEntity);	
	/**
	 * 通过id查询接入者
	 * @param id
	 * @return
	 */
	public CfAccessclientEntity queryAccessclientById(String id);
	
	/**
	 * 通过客户id查询接入者
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclientByCustormerId(String id);
	/**
	 * 通过客户id查询接入者
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessclientByCustormerIdAndSta(String id,String status);	
	
	/**
	 * 新增接入者
	 * @param cfAccessclientEntity
	 * @throws AppException 
	 */
	public void insertAccessclient(CfAccessclientEntity cfAccessclientEntity) throws AppException;
	
	/**
	 * 修改接入者
	 * @param cfAccessclientEntity
	 */
	public void updateAccessclient(CfAccessclientEntity cfAccessclientEntity);
	
	
	/**
	 * 通过id删除接入者
	 * @param id
	 * @throws AppException 
	 */
	public void deleteAccessclient(String id) throws AppException;

	/**
	 * 通过acname查询接入者
	 * @param acname
	 * @return
	 */
	public List<CfAccessclientEntity> queryAccessClientByAcName(String acname);



}

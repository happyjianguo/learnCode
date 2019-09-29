/**
 * CloudplatformorginationService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月19日
 */
package cn.com.jansh.service.system;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.system.CloudplatformorginationModel;



/**
 * 机构service
 * @author gll
 * @version 1.0
 */
public interface CloudplatformorginationService {

	/**
	 * 初始化查询条件
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryParentOrgination();

	/**
	 * 查询数据
	 * @param cloudplatformorginationModel
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryOrgination(
			CloudplatformorginationModel cloudplatformorginationModel);

	/**
	 * 新增数据
	 * @param cloudplatformorginationModel
	 */
	public void addOrgination(CloudplatformorginationModel cloudplatformorginationModel);

	/**
	 * 通过机构名查询机构
	 * @param orgname
	 * @return
	 */
	public CloudplatformorginationEntity selectOrginationByorgName(String orgname);

	/**
	 * 通过机构ID查询机构
	 * @param id
	 * @return
	 */
	public CloudplatformorginationModel selectOrginationById(String id);
	
	/**
	 * 通过当前机构ID查询此机构是否为其他机构的父机构
	 * @param id
	 * @return
	 */
	public List<CloudplatformorginationEntity> selectOrginationParentId(String id);

	/**
	 * 修改机构数据
	 * @param cloudplatformorginationModel
	 */
	public void editOrgination(CloudplatformorginationModel cloudplatformorginationModel);

	/**
	 * 获取所有机构名称和code
	 */
	public List<CloudplatformorginationEntity> queryAllDate();

	/**
	 * 删除机构数据
	 * @param id
	 */
	public void delOrginationById(String id);

	/**
	 * 通过状态，查询总行机构
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryParentOrginationByStatus();

	/**
	 * 通过id和状态查询机构
	 * @param queryparentorgid
	 * @return
	 */
	public List<CloudplatformorginationEntity> selectOrginationParentIdAndStatus(String queryparentorgid);

	/**
	 * 通过id查询机构
	 * @param id
	 * @return
	 */
	public CloudplatformorginationEntity selectOrgById(String id);

	/**
	 * 通过机构id查询用户
	 * @param id
	 * @return
	 */
	public List<FsmUserEntity> selectFsmUserbyOrgid(String id);

	/**
	 * 通过机构id查询平台币
	 * @param id
	 * @return
	 */
	public CloudaccountsurplusEntity selectAccountsurplusbyOrgid(String id);

	/**
	 * 查询状态为0时此机构的父机构的状态
	 * @param id
	 * @return
	 */
	public CloudplatformorginationEntity sOrginationParentIdAndStatus(String id);

	/**
	 * 查询状态为0时此机构的父机构的状态
	 * @param id
	 * @return
	 */
	public List<CloudplatformorginationEntity> sOrginationChildIdAndStatus(String id);

	/**
	 * 通过机构名和删除标志判断机构信息是否存在
	 * @param map
	 * @return
	 */
	public CloudplatformorginationEntity selectOrgByorgNameAnddelFlag(CloudplatformorginationModel cloudplatformorginationModel);

	/**
	 * 通过手机号码查询用户
	 * @param phoneno
	 * @return
	 */
	List<FsmUserEntity> selectFsmUserbyPhoneno(String phoneno);

}

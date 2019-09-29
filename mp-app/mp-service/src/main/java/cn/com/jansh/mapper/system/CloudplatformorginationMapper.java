/**
 * CloudplatformorginationMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月19日
 */
package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;

/**
 * 机构Mapper
 * @author gll
 * @version 1.0
 */
public interface CloudplatformorginationMapper {

	/**
	 * 机构管理页面初始化查询
	 * @param map
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryParentOrgination(Map<String, String> map);

	/**
	 * 获取所有机构的名称和CODE
	 */
	public List<CloudplatformorginationEntity> queryAllDate();

	
	/**
	 * 查询数据
	 * @param orgination
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryOrgination(CloudplatformorginationEntity orgination);

	/**
	 * 插入数据
	 * @param orgination
	 */
	public void insertOrgination(CloudplatformorginationEntity orgination);

	/**
	 * 通过机构名查询机构
	 * @param map
	 * @return
	 */
	public CloudplatformorginationEntity selectOrginationByorgName(Map<String, String> map);

	/**
	 * 通过机构ID查询机构
	 * @param map
	 * @return
	 */
	public CloudplatformorginationEntity selectOrginationById(Map<String, String> map);

	/**
	 * 更新数据
	 * @param orgination
	 */
	public void editOrgination(CloudplatformorginationEntity orgination);

	/**
	 * 通过当前机构ID查询此机构是否为其他机构的父机构
	 * @param map
	 * @return
	 */
	public List<CloudplatformorginationEntity> selectOrginationParentId(Map<String, String> map);

	/**
	 * 删除数据
	 * @param map
	 */
	public void delOrginationById(Map<String, String> map);

	/**
	 * 通过状态，查询总行机构
	 * @param map
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryParentOrginationByStatus(Map<String, String> map);

	/**
	 * 通过机构id查询用户
	 * @param map
	 * @return
	 */
	public List<FsmUserEntity> selectFsmUserbyOrgid(Map<String, String> map);

	/**
	 * 通过机构id查询平台币 
	 * @param map
	 * @return
	 */
	public CloudaccountsurplusEntity selectAccountsurplusbyOrgid(Map<String, String> map);

	/**
	 * 查询状态为0时此机构的父机构的状态
	 * @param map
	 * @return
	 */
	public CloudplatformorginationEntity sOrginationParentIdAndStatus(Map<String, String> map);

	/**
	 * 查询状态为0时此机构的子机构的状态
	 * @param map
	 * @return
	 */
	public List<CloudplatformorginationEntity> sOrginationChildIdAndStatus(Map<String, String> map);

	/**
	 * 获取所有机构的名称和CODE2
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryAllDate2();

	/**
	 * 通过机构名和删除标志判断机构信息是否存在
	 * @param map
	 * @return
	 */
	public CloudplatformorginationEntity selectOrgByorgNameAnddelFlag(Map<String, String> map);

	/**
	 * 通过手机号码查询用户
	 * @param map
	 * @return
	 */
	public List<FsmUserEntity> selectFsmUserbyPhoneno(Map<String, String> map);

}

/**
 * CloudplatformorginationServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月19日
 */
package cn.com.jansh.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.ContextCode;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.mapper.system.CloudplatformorginationMapper;
import cn.com.jansh.model.system.CloudplatformorginationModel;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 机构Service实现
 * @author gll
 * @version 1.0
 */
@Service
public class CloudplatformorginationServiceImpl implements CloudplatformorginationService {
	
	private static final Logger logger = LogManager.getLogger(CloudplatformorginationServiceImpl.class);

	@Autowired
	private CloudplatformorginationMapper cloudplatformorginationMapper;
	
	@Autowired
	private CloudplatformorginationMapper cplatforginationMapper;

	/**
	 * 初始化查询条件
	 */
	@Override
	public List<CloudplatformorginationEntity> queryParentOrgination() {
		logger.info("机构管理service，初始化查询条件");
		Map<String,String> map=new HashMap<String,String>();  
		map.put("parentorgid","00000");  
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());
		return cloudplatformorginationMapper.queryParentOrgination(map);
	}
	/**
	 * 查询数据
	 * @param cloudplatformorginationModel
	 * @return
	 */
	@Override
	public List<CloudplatformorginationEntity> queryOrgination(
			CloudplatformorginationModel cloudplatformorginationModel) {
		logger.info("机构管理service，初始化查询数据:{}",cloudplatformorginationModel);
		String queryIsparentorgid = cloudplatformorginationModel.getQueryIsparentorgid();
		CloudplatformorginationEntity orgination = new CloudplatformorginationEntity();
		orgination.setOrgname(cloudplatformorginationModel.getQueryorgname());
		orgination.setOrgstatus(cloudplatformorginationModel.getQueryorgstatus());
	    /* !queryIsparentorgid null(不是总行)  true*/
		if(!StringUtils.isNotBlank(queryIsparentorgid)){
	    	//不是总行,赋值父id
			/*判断查询条件中的分行机构是否为空*/
			if(cloudplatformorginationModel.getBranchid()==null || cloudplatformorginationModel.getBranchid().length()<=0){
				orgination.setParentorgid(cloudplatformorginationModel.getQueryparentorgid());		
			}else{
				orgination.setParentorgid(cloudplatformorginationModel.getBranchid());
			}
	    }else{
	    	//是总行,最高級机构父id
	    	orgination.setParentorgid(ContextCode.ORGINATION_PARENT_ORGID.value());
	    }
		orgination.setDelflag(ContextCode.ORGINATION_DELFLAG_1.value());
		/*通过id查询查询下级机构*/
		List<CloudplatformorginationEntity> list = cloudplatformorginationMapper.queryOrgination(orgination);
		//List<CloudplatformorginationEntity> list1 = null;
		if(null != list && list.size() !=0 ){
			for(int i=0; i<list.size(); i++){
				//判断父机构名称是否为空,如果为空将父机构名设为“当前机构名”
				if(list.get(i).getParentorgname() == null || list.get(i).getParentorgname().length() <= 0){
					list.get(i).setParentorgname(list.get(i).getOrgname());
				}
				//将数据库中的时间格式转换成 yyyy-MM-dd HH:mm:ss用于显示
				list.get(i).setCreatetime(DateUtil.formatDateTimestamp(list.get(i).getCreatetime()));
			}
		}
		  
		return list;
	}
	/**
	 * 新增数据
	 * @param cloudplatformorginationModel
	 */
	@Override
	public void addOrgination(CloudplatformorginationModel cloudplatformorginationModel) {
		logger.info("机构管理service，新增数据:{}",cloudplatformorginationModel);
		CloudplatformorginationEntity orgination = new CloudplatformorginationEntity();
		orgination.setId(IDUtils.getTimeRandon());
		orgination.setNote(cloudplatformorginationModel.getNote());
		orgination.setOrgname(cloudplatformorginationModel.getOrgname());
		orgination.setOrgstatus(cloudplatformorginationModel.getOrgstatus());
		//新增时，删除标志位置为1
		orgination.setDelflag(ContextCode.ORGINATION_DELFLAG_1.value());
		//判断Model中的总行机构是否为空
		boolean bParent = cloudplatformorginationModel.getParentorgid()==null || cloudplatformorginationModel.getParentorgid().length()<=0;
		//判断Model中的分行机构是否为空
		boolean bbranch = cloudplatformorginationModel.getBranchid()==null || cloudplatformorginationModel.getBranchid().length()<=0;
		//判断总行机构id是否为空
		if(!bParent){
			//判断分行机构id是否为空
			if(!bbranch){
				//总行机构id、分行机构id都不为空，即添加网点机构
				orgination.setParentorgid(cloudplatformorginationModel.getBranchid());
			}else{
				//总行机构id不为空，分行机构id为空，即添加分行机构
				orgination.setParentorgid(cloudplatformorginationModel.getParentorgid());
			}
		}else{
			//总行机构id、分行机构id都为空，即添加总行机构
			orgination.setParentorgid(ContextCode.ORGINATION_PARENT_ORGID.value());
		}
		orgination.setUpdatetime(DateUtil.getDateTimestamp());
		orgination.setCreatetime(DateUtil.getDateTimestamp());
		cloudplatformorginationMapper.insertOrgination(orgination);
	}
	/**
	 * 修改数据
	 * @param cloudplatformorginationModel
	 */
	@Override
	public void editOrgination(CloudplatformorginationModel cloudplatformorginationModel) {
		logger.info("机构管理service，修改数据:{}",cloudplatformorginationModel);
		CloudplatformorginationEntity orgination = new CloudplatformorginationEntity();
		orgination.setId(cloudplatformorginationModel.getId());
		orgination.setNote(cloudplatformorginationModel.getNote());
		orgination.setOrgname(cloudplatformorginationModel.getOrgname());
		orgination.setOrgstatus(cloudplatformorginationModel.getOrgstatus());
		orgination.setUpdatetime(DateUtil.getDateTimestamp());
		cloudplatformorginationMapper.editOrgination(orgination);
	}

	/**
	 *通过机构名查询机构
	 * @param cloudplatformorginationModel
	 */
	@Override
	public CloudplatformorginationEntity selectOrginationByorgName(String orgname) {
		logger.info("机构管理service，通过机构名查询机构:{}",orgname);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("orgname", orgname);
		return cloudplatformorginationMapper.selectOrginationByorgName(map);
	}
	/**
	 *通过机构ID查询机构
	 * @param cloudplatformorginationModel
	 */
	@Override
	public CloudplatformorginationModel selectOrginationById(String id) {
		logger.info("机构管理service，通过机构名查询机构:{}",id);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("id", id);
		CloudplatformorginationEntity orgination = cloudplatformorginationMapper.selectOrginationById(map);
		//判断父机构名称是否为空,如果为空将父机构名设为“当前机构名”
		if(orgination.getParentorgname() == null || orgination.getParentorgname().length() <= 0){
			orgination.setParentorgname(orgination.getOrgname());
		}
		return entityToModel(orgination);
	}
	/**
	 * 获取机构表中所有的机构
	 */
	@Override
	public List<CloudplatformorginationEntity> queryAllDate() {
		return cplatforginationMapper.queryAllDate();
	}
	/**
	 * 通过当前机构ID查询此机构是否为其他机构的父机构
	 */
	@Override
	public List<CloudplatformorginationEntity> selectOrginationParentId(String id) {
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("parentorgid", id);
		//机构删除状态为“1（未删除）”
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());
		return cloudplatformorginationMapper.selectOrginationParentId(map);
	}
	/**
	 * 删除机构通过ID
	 */
	@Override
	public void delOrginationById(String id) {
		Map<String,String> map=new HashMap<String,String>(); 
		//删除时将删除标志位置为未生效
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_0.value());
		//删除时将机构状态置为未生效
		map.put("orgstatus", ContextCode.ORGINATION_STATUS_GUOQI.value());
		map.put("id", id);
		cloudplatformorginationMapper.delOrginationById(map);
	}
	/**
	 * Entity转Model
	 * @param orginationEntity
	 * @return
	 */
	private CloudplatformorginationModel entityToModel(CloudplatformorginationEntity orginationEntity) {
		CloudplatformorginationModel orginationModel = new CloudplatformorginationModel();
		orginationModel.setId(orginationEntity.getId());
		orginationModel.setNote(orginationEntity.getNote());
		orginationModel.setOrgname(orginationEntity.getOrgname());
		orginationModel.setOrgstatus(orginationEntity.getOrgstatus());
		orginationModel.setParentorgid(orginationEntity.getParentorgid());
		orginationModel.setParentorgname(orginationEntity.getParentorgname());
		orginationModel.setCreatetime(orginationEntity.getCreatetime());
		orginationModel.setUpdatetime(orginationEntity.getUpdatetime());
		return orginationModel;
	}
	/**
	 * 通过状态，查询总行机构
	 * @param orginationEntity
	 * @return
	 */
	@Override
	public List<CloudplatformorginationEntity> queryParentOrginationByStatus() {
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("parentorgid", "00000");
		//设置机构状态为生效 
//		map.put("orgstatus", ContextCode.ORGINATION_STATUS_SHENGXIAO.value());
		//机构删除状态为“1（未删除）”
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());
		return cloudplatformorginationMapper.queryParentOrginationByStatus(map);
	}
	/**
	 * 通过状态，总行id，查询总分行机构
	 * @param orginationEntity
	 * @return
	 */
	@Override
	public List<CloudplatformorginationEntity> selectOrginationParentIdAndStatus(String queryparentorgid) {
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("parentorgid", queryparentorgid);
//		map.put("orgstatus", ContextCode.ORGINATION_STATUS_SHENGXIAO.value());
		//机构删除状态为“1（未删除）”
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());
		return cloudplatformorginationMapper.queryParentOrginationByStatus(map);
	}
	/**
	 * 通过id，查询机构
	 * @param orginationEntity
	 * @return
	 */
	@Override
	public CloudplatformorginationEntity selectOrgById(String id) {
		logger.info("机构管理service，通过机构id查询机构:{}",id);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("id", id);
		return  cloudplatformorginationMapper.selectOrginationById(map);
	}
	/**
	 * 通过机构id查询用户
	 */
	@Override
	public List<FsmUserEntity> selectFsmUserbyOrgid(String id) {
		logger.info("机构管理service，通过机构id查询用户:{}",id);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("orgid", id);
		List<FsmUserEntity> li = cloudplatformorginationMapper.selectFsmUserbyOrgid(map);
		return li;
	}
	/**
	 * 通过手机号码查询用户
	 */
	@Override
	public List<FsmUserEntity> selectFsmUserbyPhoneno(String phoneno) {
		logger.info("通过手机号码查询用户:{}",phoneno);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("phoneno", phoneno);
		List<FsmUserEntity> li = cloudplatformorginationMapper.selectFsmUserbyPhoneno(map);
		return li;
	}
	/**
	 * 通过机构id查询平台币
	 */
	@Override
	public CloudaccountsurplusEntity selectAccountsurplusbyOrgid(String id) {
		logger.info("机构管理service，通过机构id查询平台币:{}",id);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("orgid", id);
		return cloudplatformorginationMapper.selectAccountsurplusbyOrgid(map);
	}
	/**
	 * 查询此机构的父机构的状态
	 */
	@Override
	public CloudplatformorginationEntity sOrginationParentIdAndStatus(String id) {
		logger.info("机构管理service，查询此机构的父机构的状态:{}",id);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("id", id);
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());
		return cloudplatformorginationMapper.sOrginationParentIdAndStatus(map);
	}
	/**
	 * 查询此机构的子机构的状态
	 */
	@Override
	public List<CloudplatformorginationEntity> sOrginationChildIdAndStatus(String parentorgid) {
		logger.info("机构管理service，查询此机构的子机构的状态:{}",parentorgid);
		Map<String,String> map=new HashMap<String,String>(); 
		map.put("parentorgid", parentorgid);
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());
		map.put("orgstatus", ContextCode.ORGINATION_STATUS_GUOQI.value());
		return cloudplatformorginationMapper.sOrginationChildIdAndStatus(map);
	}
	/**
	 * 通过机构名和删除标志判断机构信息是否存在
	 */
	@Override
	public CloudplatformorginationEntity selectOrgByorgNameAnddelFlag(CloudplatformorginationModel cloudplatformorginationModel) {
		logger.info("机构管理service，通过机构名和删除标志位查询机构:{}",cloudplatformorginationModel);
		Map<String,String> map = new HashMap<String,String>();
		map.put("delflag", ContextCode.ORGINATION_DELFLAG_1.value());	//未删除
		map.put("orgname",cloudplatformorginationModel.getOrgname());	//机构名称
		return cloudplatformorginationMapper.selectOrgByorgNameAnddelFlag(map);
	}
}

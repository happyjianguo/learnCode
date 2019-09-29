/**
 * AccountSurplusrevServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月19日
 */
package cn.com.jansh.service.recharge.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.ContextCode;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.entity.recharge.CloudaccountsurplusrevEntity;
import cn.com.jansh.entity.recharge.CloudrechargerecordEntity;
import cn.com.jansh.mapper.recharge.IAccountSurplusrevMapper;
import cn.com.jansh.mapper.recharge.ICloudrechargerecordMapper;
import cn.com.jansh.model.recharge.CloudaccountsurplusrevModel;
import cn.com.jansh.service.recharge.AccountSurplusrevService;
import cn.com.jansh.service.recharge.CloudaccountsurplusService;

/**
 * 人工充值实现类
 * @author xieliangliang
 * @version 1.0
 */
@Service
public class AccountSurplusrevServiceImpl implements AccountSurplusrevService {

	private static final Logger logger = LogManager.getLogger(AccountSurplusrevServiceImpl.class);
	
	@Autowired
	private IAccountSurplusrevMapper accountSurplusrevMapper;
	
	@Autowired
	private CloudaccountsurplusService claccsurpService; //账户余额接口
	
	@Autowired
	private ICloudrechargerecordMapper clodrechService; //充值流水记录
	
	/**
	 * 插入充值审核表
	 */
	@Override
	public void insertAmt(CloudaccountsurplusrevEntity entity) {
		logger.info("人工充值记录：{}",entity);
		accountSurplusrevMapper.insert(entity);
	}

	/**
	 * 查询所有充值审核数据
	 */
	@Override
	public List<CloudaccountsurplusrevEntity> queryAll(CloudaccountsurplusrevEntity modelToEntity) {
		logger.info("获取所有需要审核的充值记录");
		List<CloudaccountsurplusrevEntity> list = accountSurplusrevMapper.queryAll(modelToEntity);
		
		for(int i=0; i<list.size(); i++){
			
			//将数据库中的时间格式转换成 yyyy-MM-dd HH:mm:ss用于显示
			list.get(i).setCreatetime(DateUtil.formatDateTimestamp(list.get(i).getCreatetime()));
			
			if(StringUtils.isNotBlank(list.get(i).getUpdatetime())) {
				list.get(i).setUpdatetime(DateUtil.formatDateTimestamp(list.get(i).getUpdatetime()));
			}
		}
		
		return list;
	}

	/**
	 * 根据ID查询审核数据
	 */
	@Override
	public CloudaccountsurplusrevEntity queryById(String id) {
		logger.info("根据ID{}查询审核数据，显示到审核页面",id);
		return accountSurplusrevMapper.queryById(id);
	}

	/**
	 * 根据机构ID更新充值审核状态信息
	 */
	@Override
	public void updateByOrgid(CloudaccountsurplusrevEntity entity) {
		logger.info("更新充值订单状态信息{}",entity);
		accountSurplusrevMapper.updateById(entity);
	}

	/**
	 * 实现充值
	 */
	@Override
	public void recharge(CloudaccountsurplusrevModel cloudaccsupMolde) {
		logger.info("人工充值审核通过业务处理开始");
		BigDecimal prepareAmount = cloudaccsupMolde.getAmount(); //审核表金额-待充值金额
		
		//审核通过的处理-更新审核记录表，在进行充值
		accountSurplusrevMapper.updateById(modelToEntity(cloudaccsupMolde));
		//先查询余额，然后将充值金额+余额，更新数据库
		CloudaccountsurplusEntity cloudAccPlusDate = claccsurpService.queryByOrgid(cloudaccsupMolde.getOrgid());
		//判断余额表中是否有此记录
		if(null == cloudAccPlusDate) {
			logger.info("人工充值审核，机构第一次充值，直接新增数据");
			//余额表若无此机构数据，则进行新增表数据操作
			CloudaccountsurplusEntity casEntity = new CloudaccountsurplusEntity();
			casEntity.setId(IDUtils.getTimeRandon());
			casEntity.setOrgid(cloudaccsupMolde.getOrgid());
			casEntity.setCurrentmoney(prepareAmount);
			casEntity.setTotalmoney(prepareAmount);
			casEntity.setCreatetime(DateUtil.getDateTimestamp());
			casEntity.setUpdatetime(DateUtil.getDateTimestamp());
			
			claccsurpService.insertNewAmount(casEntity);
			
		}else {
			logger.info("人工充值审核，非第一次充值，进行余额表金额计算处理");
			//余额表中存在记录，则进行金额累加计算，然后更新数据库
			BigDecimal origAmount = cloudAccPlusDate.getCurrentmoney(); //平台币余额
			BigDecimal accumuAmount = cloudAccPlusDate.getTotalmoney(); //平台币累计金额
			
			BigDecimal chargeAmount = origAmount.add(prepareAmount); //计算充值金额
			BigDecimal newAccumuAmount = accumuAmount.add(prepareAmount); //计算累计充值金额
			
			CloudaccountsurplusEntity casEntity = new CloudaccountsurplusEntity();
			casEntity.setOrgid(cloudaccsupMolde.getOrgid());
			casEntity.setCurrentmoney(chargeAmount);
			casEntity.setTotalmoney(newAccumuAmount);
			casEntity.setUpdatetime(DateUtil.getDateTimestamp());
			
			claccsurpService.updateAccByOrgid(casEntity);
		}
		
		//记录流水表
		logger.info("人工充值审核，记录充值流水");
		CloudrechargerecordEntity clodrechEntity = new CloudrechargerecordEntity();
		clodrechEntity.setOrderid(IDUtils.getTimeRandon());
		clodrechEntity.setUserid(ContextCode.RECHARG_USERID_NAME.value());
		clodrechEntity.setOrgid(cloudaccsupMolde.getOrgid());
		clodrechEntity.setCurrentmoney(prepareAmount); //平台币数量
		clodrechEntity.setPaytype(ContextCode.RECHARG_PAYTYPE_SUCCES.value()); //支付方式
		clodrechEntity.setStatus(ContextCode.RECHARG_ERECORD_SUCCES.value()); //充值状态成功
//		clodrechEntity.setTxntime(DateUtil.getDateTimestamp());//交易时间
		clodrechEntity.setUpdatetime(DateUtil.getDateTimestamp());//更新时间
		
		clodrechService.saveRecordData(clodrechEntity);
	}
	
	/**
	 * model转Entity
	 */
	private CloudaccountsurplusrevEntity modelToEntity(CloudaccountsurplusrevModel accountsurplusrevModel){
		CloudaccountsurplusrevEntity accountsurpEntity = new CloudaccountsurplusrevEntity();
		accountsurpEntity.setId(accountsurplusrevModel.getId());
		accountsurpEntity.setOrgid(accountsurplusrevModel.getOrgid());
		accountsurpEntity.setRstatus(accountsurplusrevModel.getRstatus());
		accountsurpEntity.setAudresult(accountsurplusrevModel.getAudresult());
		accountsurpEntity.setAuditor(accountsurplusrevModel.getAuditor());
		accountsurpEntity.setUpdatetime(accountsurplusrevModel.getUpdatetime());
		return accountsurpEntity;
	}
}

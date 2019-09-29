/**
 * AccountsurplusrevControl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月19日
 */
package cn.com.jansh.controller.recharge;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.recharge.CloudaccountsurplusrevEntity;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.recharge.CloudaccountsurplusrevModel;
import cn.com.jansh.service.recharge.AccountSurplusrevService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 人工充值
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/accountsurplusrev")
public class AccountSurplusrevController {
	
	private static final Logger logger = LogManager.getLogger(AccountSurplusrevController.class);	
	
	@Autowired
	private AccountSurplusrevService accSurService; //审核表接口
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	/**
	 * 充值列表页面初始化
	 */
	@RequestMapping(value = "/init")
	public String rechargeinit(CloudaccountsurplusrevModel cloudaccsupMolde) {
		logger.info("人工充值查询始化页面");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		
		List<CloudaccountsurplusrevEntity> accountpList = accSurService.queryAll(modelToEntity(cloudaccsupMolde)); //获取所有审核记录数据
		
		cloudaccsupMolde.setCloudpforgList(orgList); //机构下拉列表
		
		cloudaccsupMolde.setAccountsurpList(accountpList); //充值审核记录
		
		return "/recharge/accountsurplusrevlist";
	}
	
	/**
	 * 充值页面初始化
	 */
	@RequestMapping(value = "/addinit")
	public String recharge(CloudaccountsurplusrevModel cloudaccsupMolde) {
		logger.info("人工充值始化页面");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		
		cloudaccsupMolde.setCloudpforgList(orgList);
		
		return "/recharge/accountsurplusrev";
	}
	
	/**
	 * 人工充值页面
	 */
	@RequestMapping("/add")
	@ExceptionHandle("/accountsurplusrev/init")
	@OperationResult
	@OperationLog
	public String add(String porgid,String pamount,String phonenocssid,String orgcodecssid,CloudaccountsurplusrevModel cloudaccsupMolde) throws AppException{
		logger.info("人工充值处理，充值机构：{},充值金额：{}",porgid,pamount);
		
		BigDecimal bd = new BigDecimal(pamount);
		BigDecimal num1 = new BigDecimal("1000");
		BigDecimal result = bd.multiply(num1); //将金额转换为平台币
		CloudaccountsurplusrevEntity entity = new CloudaccountsurplusrevEntity();
		/*如果porgid为空*/
		if(StringUtils.isEmpty(porgid)){
			/*判断机构code是否为空*/
			if(StringUtils.isEmpty(orgcodecssid)){
				/*判断手机号码是否为空*/
				if(!StringUtils.isEmpty(phonenocssid)){
					//在会员表中通过手机号查询会员,如果存在拿出会员的机构code
					List<FsmUserEntity> li = cloudpfgService.selectFsmUserbyPhoneno(phonenocssid);
					if(null == li || li.size() ==0 ){
						logger.error("手机号码不存在");
						throw new AppException(AppErrorCode.E260001);
					}else{
						entity.setOrgid(li.get(0).getOrgid()); //机构
					}
				}
			}else{
				//在机构表中通过机构code查询机构，如果存在
				List<FsmUserEntity> li = cloudpfgService.selectFsmUserbyOrgid(orgcodecssid);
				if(null == li || li.size() ==0 ){
					//抛出异常提示错误信息
					logger.error("机构code不存在");
					throw new AppException(AppErrorCode.E260002);
				}else{
					entity.setOrgid(orgcodecssid); //机构
				}
			}
		}else{
			entity.setOrgid(porgid); //机构
		}
		entity.setAmount(result); //充值金额
		entity.setOperator(AppUtil.getUserDetail().getUsername()); //操作人
		entity.setCreatetime(DateUtil.getDateTimestamp()); //创建时间
		entity.setRstatus(ContextCode.RECHARGE_RSTATUS_AUDIT.value()); //待审核状态
		entity.setId(IDUtils.getTimeRandon()); //机构ID随机数
		
		accSurService.insertAmt(entity);
		
		return rechargeinit(cloudaccsupMolde);
	}
	
	/**
	 * model转Entity
	 */
	private CloudaccountsurplusrevEntity modelToEntity(CloudaccountsurplusrevModel accountsurplusrevModel){
		CloudaccountsurplusrevEntity accountsurpEntity = new CloudaccountsurplusrevEntity();
		accountsurpEntity.setOrgid(accountsurplusrevModel.getOrgid());
		accountsurpEntity.setRstatus(accountsurplusrevModel.getRstatus());
		return accountsurpEntity;
	}
}

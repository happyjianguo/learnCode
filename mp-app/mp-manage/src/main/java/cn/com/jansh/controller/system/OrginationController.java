/**
 * OrginationController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月19日
 */
package cn.com.jansh.controller.system;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.system.CloudplatformorginationModel;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 机构Controller
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/orgination")
public class OrginationController {

	private static final Logger logger = LogManager.getLogger(OrginationController.class);
	
	@Autowired
	private CloudplatformorginationService cloudplatformorginationService;	//机构service
	
	/**
	 * 机构页面初始化
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(CloudplatformorginationModel cloudplatformorginationModel){
		logger.info("跳入初始化机构管理页面");
		/** 页面初始化加入父机构信息*/
		cloudplatformorginationModel.setCloudplatformorginationList(cloudplatformorginationService.queryParentOrgination());
		return "sysmanage/orgination/orgination";
	}
	
	/**
	 * 机构管理页面初始化查询数据
	 * @param cloudplatformorginationModel
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ajax/getdata")
	@ResponseBody
	public ViewObject getdata(CloudplatformorginationModel cloudplatformorginationModel){
		logger.info("机构管理页面初始化数据:{}",cloudplatformorginationModel);
		return new ViewObject(cloudplatformorginationService.queryOrgination(cloudplatformorginationModel));
	}
	/**
	 * 查询下拉列表初始化
	 * @param queryparentorgid
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/ajax/initbranchid")
	@ResponseBody
	public ViewObject initbranch(String queryparentorgid){
		logger.info("分行机构初始化数据{}",queryparentorgid);
		return new ViewObject(cloudplatformorginationService.selectOrginationParentId(queryparentorgid));
	}
	/**
	 * 新增下拉列表初始化
	 * @param queryparentorgid
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ajax/initbranchidbystatus")
	@ResponseBody
	public ViewObject initbranchidbystatus(String queryparentorgid){
		logger.info("分行机构初始化数据{}",queryparentorgid);
		return new ViewObject(cloudplatformorginationService.selectOrginationParentIdAndStatus(queryparentorgid));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="ajax/queryPstatusById")
	@ResponseBody
	public ViewObject queryPstatusById(String queryparentorgid){
		logger.info("查询所选机构状态",queryparentorgid);
		return new ViewObject(cloudplatformorginationService.selectOrginationById(queryparentorgid));
	}
	/**
	 * 新增机构页面初始化
	 * @param cloudplatformorginationModel
	 * @return
	 */
	@RequestMapping(value ="addinit")
	public String addinit(CloudplatformorginationModel cloudplatformorginationModel){
		logger.info("新增机构页面初始化",cloudplatformorginationModel);
		/** 页面初始化加入父机构信息*/
		cloudplatformorginationModel.setCloudplatformorginationList(cloudplatformorginationService.queryParentOrginationByStatus());
		return "sysmanage/orgination/orginationadd";
	}
	
	/**
	 * 新增机构数据
	 * @param cloudplatformorginationModel
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value ="adddata")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@ExceptionHandle("/orgination/addinit")
	public String adddata(CloudplatformorginationModel cloudplatformorginationModel) throws AppException{
		logger.info("新增机构数据",cloudplatformorginationModel);
		//新增机构时使用机构名和删除标志判断数据库中是否存在
		if(null != cloudplatformorginationService.selectOrgByorgNameAnddelFlag(cloudplatformorginationModel)){
			throw new AppException(AppErrorCode.E220001);
		}
		//插入数据
		cloudplatformorginationService.addOrgination(cloudplatformorginationModel);
		return init(cloudplatformorginationModel);
		
	}
	/**
	 * 跳转到修改机构页面
	 * @param cloudplatformorginationModel
	 * @return
	 */
	@RequestMapping(value="/editinit")
	public String editinit(CloudplatformorginationModel cloudplatformorginationModel,Model model){
		logger.info("修改机构页面初始化",cloudplatformorginationModel);
		cloudplatformorginationModel = cloudplatformorginationService.selectOrginationById(cloudplatformorginationModel.getId());
		/** 页面初始化加入父机构信息*/
		cloudplatformorginationModel.setCloudplatformorginationList(cloudplatformorginationService.queryParentOrgination());
		model.addAttribute(cloudplatformorginationModel);
		return "sysmanage/orgination/orginationedit";
	}
	/**
	 * 修改机构数据
	 * @param cloudplatformorginationModel
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value="/editdata")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	@ExceptionHandle("/orgination/editinit")
	public String editdata(CloudplatformorginationModel cloudplatformorginationModel) throws AppException{
		logger.info("修改机构数据",cloudplatformorginationModel);
		
		/**
		 * 一、
		 * 1、当前机构状态为0
		 * 2、判断父机构状态是否为0，子机构状态是否为0
		 * 		都为0		 			允许修改
		 * 		都为1		 			不允许修改
		 * 		父机构为0，子机构不为0 	不允许修改
		 * 		父机构为1，子机构为0		允许修改
		 * 		父机构为null，子机构为0	允许修改
		 * 		父机构为null，子机构为1	不允许修改
		 * 
		 * 二、
		 * 3、当前机构状态为1
		 * 4、判断父机构状态是否为0，子机构状态是否为0
		 * 		都为1					允许修改
		 * 		父机构为1，子机构为0		允许修改
		 * 		父机构为0，子机构不为0		不允许修改
		 * 		都为0					不允许修改
		 * 		父机构为null，子机构为0	允许修改
		 * 		父机构为null，子机构为1	允许修改
		 * 
		 * 
		 * 父机构为null
		 * 			1、说明此机构是最高级机构
		 * 				
		 * 
		 * 父机构为0 
		 * 			用id查出机构实体，判断父机构状态是否为0
		 * 子机构为0
		 * 			1、子机构状态为0或者不为0查询出的机构将是一个list
		 * 			2、查询数据库时，使用父机构id的状态不为0这两个条件查询 机构表
		 * 			3、查询出的结果的个数不为0，则，则机构下面有生效的子机构
		 * 
		 * 			4、查询出的结果的个数为0，则，则机构下面没有生效的子机构
		 * 
		 */
		//查询当前机构
		CloudplatformorginationModel model = cloudplatformorginationService.selectOrginationById(cloudplatformorginationModel.getId());
		//查询父机构
		CloudplatformorginationEntity orginationP = cloudplatformorginationService.sOrginationParentIdAndStatus(model.getParentorgid());
		//查询子机构
		List<CloudplatformorginationEntity> orginationC = cloudplatformorginationService.sOrginationChildIdAndStatus(model.getId());
		//当前机构的状态
		String status = cloudplatformorginationModel.getOrgstatus();
		//判断状态是否为空
		if(StringUtils.isNotEmpty(status)){
			//判断状态是否为0（未生效）
			if(status.equals(ContextCode.ORGINATION_STATUS_GUOQI.value())){
				//父机构为null
				if(null == orginationP){
					//子list.size() 为0
					if(null == orginationC || orginationC.size() ==0 ){
						//修改数据
						cloudplatformorginationService.editOrgination(cloudplatformorginationModel);
						//子list.size() 不为0
					}else{
						//父机构为null，此机构下有子机构为生效状态，不能修改
						throw new AppException(AppErrorCode.E220007);
					}
					//父机构不为null
				}else{
					//判断父机构status是否为空
					if(StringUtils.isNotEmpty(orginationP.getOrgstatus())){
						//父status=0
						if(orginationP.getOrgstatus().equals(ContextCode.ORGINATION_STATUS_GUOQI.value())){
							//子list.size() 为0
							if(null == orginationC || orginationC.size() ==0 ){
								//修改数据
								cloudplatformorginationService.editOrgination(cloudplatformorginationModel);
								//子list.size() 不为0
							}else{
								//父机构为0，子机构为1		不允许修改
								//此机构的父机构为未生效状态且此机构下有子机构为生效状态，不能改为未生效
								throw new AppException(AppErrorCode.E220007);
							}
							//父status=1
						}else{
							//子list.size() 为0
							if(null == orginationC || orginationC.size() ==0 ){
								//父机构为null，子机构为0	允许修改
								//父机构为null，子机构为1	允许修改
								//修改数据
								cloudplatformorginationService.editOrgination(cloudplatformorginationModel);
								//子list.size() 不为0
							}else{
								//都为1		 			不允许修改
								//此机构的父机构为生效状态且此机构下有子机构为生效状态，不能改为未生效
								throw new AppException(AppErrorCode.E220007);
							}
						}
					}
				}
				
			//判断状态是否为1（生效）
			}else if(status.equals(ContextCode.ORGINATION_STATUS_SHENGXIAO.value())){
				//判断父机构是否为空
				if(null == orginationP){
					//都为1					允许修改
					//父机构为1，子机构为0		允许修改
					//修改数据
					cloudplatformorginationService.editOrgination(cloudplatformorginationModel);
					//父机构不为空
				}else{
					//判断父机构status是否为空
					if(StringUtils.isNotEmpty(orginationP.getOrgstatus())){
						//父status=0
						if(orginationP.getOrgstatus().equals(ContextCode.ORGINATION_STATUS_GUOQI.value())){
							//父机构为0，子机构不为0		不允许修改
							//都为0					不允许修改
							//此机构的父机构为未生效状态，不能改为生效
							throw new AppException(AppErrorCode.E220008);
							//父status=1
						}else{
							//都为1					允许修改
							//父机构为1，子机构为0		允许修改
							//修改数据
							cloudplatformorginationService.editOrgination(cloudplatformorginationModel);
						}
					}
				}
			}
		}
		return init(cloudplatformorginationModel);
	}
	/**
	 * 跳转入删除页面
	 * @return
	 */
	@RequestMapping(value="/delinit")
	public String delinit(CloudplatformorginationModel cloudplatformorginationModel,Model model){
		logger.info("删除机构页面初始化",cloudplatformorginationModel);
		cloudplatformorginationModel = cloudplatformorginationService.selectOrginationById(cloudplatformorginationModel.getId());
		/** 页面初始化加入父机构信息*/
		cloudplatformorginationModel.setCloudplatformorginationList(cloudplatformorginationService.queryParentOrgination());
		model.addAttribute(cloudplatformorginationModel);
		return "sysmanage/orgination/orginationdel";
	}
	/**
	 * 删除机构数据
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value="/deldata")
	@OperationResult(value = Operation.DELETE)
	@OperationLog(value = Operation.DELETE)
	@ExceptionHandle("/orgination/delinit")
	public String deldata(CloudplatformorginationModel cloudplatformorginationModel) throws AppException{
		logger.info("删除机构数据",cloudplatformorginationModel);
		String id = cloudplatformorginationModel.getId();
		//查看机构账户上面的余额
		CloudaccountsurplusEntity accountsurplus = cloudplatformorginationService.selectAccountsurplusbyOrgid(id);
		if(null != accountsurplus){
			if(!BigDecimal.ZERO.equals(accountsurplus.getCurrentmoney())){
				throw new AppException(AppErrorCode.E220006);
			}
		}
		//查看机构下面有没有用户
		List <FsmUserEntity> li = cloudplatformorginationService.selectFsmUserbyOrgid(id);
		if(null!=li && li.size()!=0){
			//此机构下面有用户未删除
			throw new AppException(AppErrorCode.E220005);
		}
		/*通过当前机构ID查询此机构是否为其他机构的父机构*/
		List<CloudplatformorginationEntity> list = cloudplatformorginationService.selectOrginationParentId(id);
		if(list != null && !list.isEmpty()){
			// 此机构下面还有子机构未删除
			throw new AppException(AppErrorCode.E220004);
		}
			//进行删除
			cloudplatformorginationService.delOrginationById(id);
		return init(cloudplatformorginationModel);
	}
}

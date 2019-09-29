/**
 * CVirOrderServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.service.component.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.ExcelWrite;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.JsonUtil;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.component.CloudATSurplusEntitiy;
import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.component.CloudorderloserEntity;
import cn.com.jansh.entity.component.CloudorgvirgoodEntity;
import cn.com.jansh.entity.component.CloudwinningrecordEntity;
import cn.com.jansh.entity.component.bo.CloudorgvirgoodBO;
import cn.com.jansh.entity.component.bo.ShowOrderBO;
import cn.com.jansh.janshpay.util.Sign;
import cn.com.jansh.mapper.component.CloudgameparamMapper;
import cn.com.jansh.mapper.component.CloudorderloserMapper;
import cn.com.jansh.mapper.component.CloudorgvirgoodMapper;
import cn.com.jansh.mapper.component.CloudwinningrecordMapper;
import cn.com.jansh.model.component.CloudVirOrderModel;
import cn.com.jansh.model.component.OrderManageModel;
import cn.com.jansh.model.component.ShowOrderModel;
import cn.com.jansh.service.component.CvOrderService;
import cn.com.jansh.service.component.CyInterfaceService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 话费流量订单接口服务实现
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class CvOrderServiceImpl implements CvOrderService {

	private static final Logger logger = LogManager.getLogger(CvOrderServiceImpl.class);
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private CloudgameparamMapper gameparamMapper;
	@Autowired
	private CloudwinningrecordMapper winnerrecordMapper;
	@Autowired
	private CloudorderloserMapper orderLoserMapper;
	@Autowired
	private CloudorgvirgoodMapper orgVrMapper;
	@Autowired
	private CyInterfaceService cyService;
	
	/**
	 * 
	 * 保存订单信息
	 *
	 */
	@Override
	public JsonVO saveOrder(String cloudVirOrderModelJson, String sign) {
		logger.info("开始对传入信息进行校验:"+sign);
		JsonVO jsonVO = new JsonVO();
		
		CloudVirOrderModel cloudVirOrderModel = null;
		try {
			cloudVirOrderModel = JsonUtil.readObject(cloudVirOrderModelJson, CloudVirOrderModel.class);
			/*校验传入信息是否符合接口规范*/
			String awardid = cloudVirOrderModel.getAwardid();
			if(StringUtils.isBlank(awardid)||awardid.length()>20){
				logger.error("数据格式非法！领奖流水记录id为"+cloudVirOrderModel.getAwardid());
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！领奖流水记录id不符合规范");
				return jsonVO;
			}
			String gameid = cloudVirOrderModel.getGameid();
			if(StringUtils.isBlank(gameid)||gameid.length()>20){
				logger.error("数据格式非法！游戏活动id为"+gameid);
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！游戏活动id不符合规范");
				return jsonVO;
			}
			CloudgameparamEntity game = gameparamMapper.selectByGameid(gameid);
			if(game == null){
				logger.error("活动id不存在！");
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法，活动id不存在！");
				return jsonVO;
			}
			String userid = cloudVirOrderModel.getUserid();
			if(StringUtils.isBlank(userid)||userid.length()>20){
				logger.error("数据格式非法！用户id为"+userid);
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！用户id不符合规范");
				return jsonVO;
			}
			String mobile = cloudVirOrderModel.getMobile();
			if(StringUtils.isBlank(mobile)||mobile.length()!=11||!mobile.matches(AppCommonsCode.MOBIL_PATTEN.value())){
				logger.error("数据格式非法！手机号为"+mobile);
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！手机号不符合规范");
				return jsonVO;
			}
			String prizeid = cloudVirOrderModel.getPrizeid();
			if(StringUtils.isBlank(prizeid)||prizeid.length()>20){
				logger.error("数据格式非法！奖品id为"+prizeid);
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！奖品id不符合规范");
				return jsonVO;
			}
			//商品不存在不增加这条记录，目前未启用该校验
//			CloudvirgoodEntity virgood = virGoodMapper.selectStatusOkByapid(prizeid,null);
//			if(virgood == null){
//				logger.error("数据格式非法！奖品id为"+prizeid);
//				jsonVO.setResult(0);
//				jsonVO.setSuccess(false);
//				jsonVO.setMsg("数据格式非法！奖品id不存在");
//				return jsonVO;
//			}
			String prizetype = cloudVirOrderModel.getPrizetype();
			if(StringUtils.isBlank(prizetype)||prizetype.length()!=2||!(prizetype.equals(AppCommonsCode.IPSTYPE_Fictitious.value())||prizetype.equals(AppCommonsCode.IPSTYPE_Material.value()))){
				logger.error("数据格式非法！奖品类型为"+prizetype);
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！奖品类型不符合规范");
				return jsonVO;
			}
			String prizename = cloudVirOrderModel.getPrizename();
			if(StringUtils.isNotBlank(prizename)){
				byte[] nameBytes = prizename.getBytes("UTF-8");
				if(nameBytes.length>50){
					logger.error("数据格式非法！奖品名称为"+prizename);
					jsonVO.setResult(0);
					jsonVO.setSuccess(false);
					jsonVO.setMsg("数据格式非法！奖品名称不符合规范");
					return jsonVO;
				}
			}else{
				logger.error("数据格式非法！奖品名称为"+prizename);
				jsonVO.setResult(0);
				jsonVO.setSuccess(false);
				jsonVO.setMsg("数据格式非法！奖品名称不符合规范");
				return jsonVO;
			}
		} catch (Exception e) {
			logger.error("数据格式非法！",e);
			jsonVO.setResult(0);
			jsonVO.setSuccess(false);
			jsonVO.setMsg("数据格式非法！");
			return jsonVO;
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("gameid", cloudVirOrderModel.getGameid());
		String paramSign = Sign.getSign(map, globalProperties.getTokenSecret());
		if(!paramSign.equals(sign)){
			jsonVO.setResult(0);
			jsonVO.setSuccess(false);
			jsonVO.setMsg("订单接口验签失败，非法请求！");
			return jsonVO;
		}
		
		CloudgameparamEntity gameparam = gameparamMapper.selectByGameid(cloudVirOrderModel.getGameid());
		if(gameparam!=null){
			/*为订单实体赋值,为机构套餐赋值*/
			CloudwinningrecordEntity winningRecordEntity = valueRecordEntity(cloudVirOrderModel,gameparam.getOrgid());
			CloudorgvirgoodEntity orgVirgoodEntity = valueVrEntity(cloudVirOrderModel, gameparam.getOrgid());
			/*将订单保存到数据库*/
			winnerrecordMapper.insert(winningRecordEntity);
			if(!AppCommonsCode.IPSTYPE_Material_defaultId.value().equals(cloudVirOrderModel.getPrizeid())){
				Map<String, String> orgMap = new HashMap<>();
				orgMap.put("orgid", orgVirgoodEntity.getOrgid());
				orgMap.put("prizestyle", orgVirgoodEntity.getPrizestyle());
				List<CloudorgvirgoodBO> check = orgVrMapper.select(orgMap);
				if(CollectionUtils.isEmpty(check)){
					orgVrMapper.insert(orgVirgoodEntity);
				}
			}
			jsonVO.setResult(1);
			jsonVO.setSuccess(true);
			jsonVO.setMsg("订单接口信息保存成功！");
		}else{
			jsonVO.setResult(0);
			jsonVO.setSuccess(false);
			jsonVO.setMsg("订单接口获取活动信息失败，参数不正确！");
		}
		return jsonVO;
	}
	/**
	 * 为机构套餐赋值
	 */
	private CloudorgvirgoodEntity valueVrEntity(CloudVirOrderModel cloudVirOrderModel,String orgid){
		CloudorgvirgoodEntity cloudorgvirgoodEntity = new CloudorgvirgoodEntity();
		
		String id = IDUtils.getTimeRandon();
		String createtime = com.jansh.comm.util.DateUtil.getDateTimestamp();
		
		cloudorgvirgoodEntity.setId(id);
		cloudorgvirgoodEntity.setOrgid(orgid);
		cloudorgvirgoodEntity.setGameid(cloudVirOrderModel.getGameid());
		cloudorgvirgoodEntity.setPrizestyle(cloudVirOrderModel.getPrizeid());
		cloudorgvirgoodEntity.setCreatetime(createtime);
		return cloudorgvirgoodEntity;
	}
	/**
	 * 为订单实体赋值
	 */
	private CloudwinningrecordEntity valueRecordEntity(CloudVirOrderModel cloudVirOrderModel,String orgid){
		/*生成基本信息*/
		String orderid = IDUtils.getTimeRandon();
		String createtime = com.jansh.comm.util.DateUtil.getDateTimestamp();
		/*保存订单信息*/
		CloudwinningrecordEntity entity = new CloudwinningrecordEntity();
		entity.setCreatetime(createtime);
		entity.setGameid(cloudVirOrderModel.getGameid());
		entity.setId(orderid);
		entity.setAwardid(cloudVirOrderModel.getAwardid());
		entity.setOpenid(cloudVirOrderModel.getUserid());
		entity.setOrgid(orgid);
		/*
		 * 如果是实物则设置审核状态为已经通过，发送状态为无需发送
		 * 否则，是未发送，初审未通过 
		 */
		if(AppCommonsCode.IPSTYPE_Material.value().equals(cloudVirOrderModel.getPrizetype())){
			entity.setSendstatus(AppCommonsCode.RECHARGEINTER_STATUS_8.value());
			entity.setPreliminarystatus(AppCommonsCode.RECHARGE_1.value());
		}else{
			entity.setSendstatus(AppCommonsCode.RECHARGEINTER_STATUS_0.value());
			entity.setPreliminarystatus(AppCommonsCode.RECHARGE_0.value());
		}
		entity.setPrizestyle(cloudVirOrderModel.getPrizeid());
		entity.setUpdatetime(createtime);
		entity.setWinnerphone(cloudVirOrderModel.getMobile());
		entity.setPrizetype(cloudVirOrderModel.getPrizetype());
		entity.setPrizename(cloudVirOrderModel.getPrizename());
		return entity;
	}
	/**
	 * 展示所有的订单
	 * @return 
	 * 
	 */
	@Override
	public OrderManageModel showAllData(OrderManageModel manageModel) {
		logger.info("获取登录用户信息");
		String userId = AppUtil.getUserDetail().getUserid();
		manageModel.setUserId(userId);
		/** 获取查询订单状态集合 **/
		List<String> sendstatus = getStatusList(manageModel);
		/** 获取统计消费的订单状态集合 **/
		List<String> sumstatus = getSumStatus();
		logger.info("开始获取所有数据");
		if(StringUtils.isNotBlank(manageModel.getBeginTime())&&
				StringUtils.isNotBlank(manageModel.getEndTime())){
			String begin =  DateUtil.getDateTime(DateUtil.parseDate(manageModel.getBeginTime()));
			String end = DateUtil.getDateTime(DateUtil.parseDate(DateUtil.addDate(manageModel.getEndTime(), 1)));
			manageModel.setBeginTime(com.jansh.comm.util.DateUtil.formatDateTime(begin));
			manageModel.setEndTime(com.jansh.comm.util.DateUtil.formatDateTime(end));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("beginTime", manageModel.getBeginTime());
		map.put("endTime", manageModel.getEndTime());
		map.put("sendstatus",sendstatus);
		map.put("prizetype", manageModel.getPrizetype());
		map.put("start", manageModel.getStart());
		map.put("vrid", manageModel.getVrid());
		map.put("gpid", manageModel.getGpid());
		map.put("phone", manageModel.getPhone());
		map.put("length", manageModel.getLength());
		List<ShowOrderBO> records = winnerrecordMapper.selectRecords(map);
		int recordsCount = winnerrecordMapper.selectRecordsCount(map);
		map.put("sendstatus",sumstatus);
		String sumPrice = winnerrecordMapper.sumPrice(map);
		List<ShowOrderModel> showOrderModels = new ArrayList<>();
		for (ShowOrderBO rechargeBO : records) {
			ShowOrderModel orderModel = new ShowOrderModel();
			if(StringUtils.isBlank(sumPrice)){
				orderModel.setSumPrice("0");
			}else{
				orderModel.setSumPrice(sumPrice);
			}
			
			try {
				BeanUtils.copyProperties(orderModel, rechargeBO);
				showOrderModels.add(orderModel);
			} catch (Exception e) {
				logger.error("属性拷贝异常", e);
			}
		}
		manageModel.setCount(recordsCount);
		manageModel.setShowOrderModels(showOrderModels);
		
		
		return manageModel;
	}
	/**
	 * 展示所有的订单
	 * @return 
	 * 
	 */
	public OrderManageModel showAllflows(OrderManageModel manageModel) {
		logger.info("获取登录用户信息");
		String userId = AppUtil.getUserDetail().getUserid();
		manageModel.setUserId(userId);
		/** 获取查询订单状态集合 **/
		List<String> sendstatus = getStatusList(manageModel);
		/** 获取统计消费的订单状态集合 **/
		List<String> sumstatus = getSumStatus();
		logger.info("开始获取所有数据");
		Map<String, Object> map = new HashMap<>();
		map.put("beginTime", manageModel.getBeginTime());
		map.put("endTime", manageModel.getEndTime());
		map.put("sendstatus",sendstatus);
		map.put("prizetype", manageModel.getPrizetype());
		map.put("start", manageModel.getStart());
		map.put("vrid", manageModel.getVrid());
		map.put("gpid", manageModel.getGpid());
		map.put("phone", manageModel.getPhone());
		map.put("length", manageModel.getLength());
		List<ShowOrderBO> records = winnerrecordMapper.selectRecords(map);
		int recordsCount = winnerrecordMapper.selectRecordsCount(map);
		map.put("sendstatus",sumstatus);
		String sumPrice = winnerrecordMapper.sumPrice(map);
		List<ShowOrderModel> showOrderModels = new ArrayList<>();
		for (ShowOrderBO rechargeBO : records) {
			ShowOrderModel orderModel = new ShowOrderModel();
			if(StringUtils.isBlank(sumPrice)){
				orderModel.setSumPrice("0");
			}else{
				orderModel.setSumPrice(sumPrice);
			}
			
			try {
				BeanUtils.copyProperties(orderModel, rechargeBO);
				showOrderModels.add(orderModel);
			} catch (Exception e) {
				logger.error("属性拷贝异常", e);
			}
		}
		manageModel.setCount(recordsCount);
		manageModel.setShowOrderModels(showOrderModels);
		
		
		return manageModel;
	}
	/**
	 * 获取要统计消费的状态
	 * @return
	 */
	public List<String> getSumStatus(){
		List<String> sumStatus = new ArrayList<>();
		sumStatus.add(AppCommonsCode.RECHARGEINTER_STATUS_1.value());
		sumStatus.add(AppCommonsCode.RECHARGEINTER_STATUS_7.value());
		return sumStatus;
	}
	/** 
	 * 获取查询订单状态集合
	 */
	private List<String> getStatusList(OrderManageModel manageModel){
		List<String> sendstatus = new ArrayList<>();
		if(AppCommonsCode.RECHARGEINTER_STATUS_0.value().equals(manageModel.getStatus())){
			if(StringUtils.isBlank(manageModel.getSendstatus())){
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_2.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_5.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_6.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_9.value());
			}else{
				sendstatus.add(manageModel.getSendstatus());
			}
			
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_1.value() .equals(manageModel.getStatus())){
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_1.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_8.value());
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_4.value() .equals(manageModel.getStatus())){
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_0.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_3.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_4.value());
				sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_7.value());
		}else{
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_0.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_1.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_2.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_3.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_4.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_5.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_6.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_7.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_8.value());
			sendstatus.add(AppCommonsCode.RECHARGEINTER_STATUS_9.value());
		}
		return sendstatus;
	}
	
	
	/**
	 * 导出报表
	 * @return 
	 * 
	 */
	public ResponseEntity<byte[]> exportData(OrderManageModel orderManageModel) throws Exception{
		/**生成表单输出流 */
		if(StringUtils.isNotBlank(orderManageModel.getBeginTime())&&
				StringUtils.isNotBlank(orderManageModel.getEndTime())){
			String begin = DateUtil.getDateTime(DateUtil.parseDate(orderManageModel.getBeginTime()));
			String end = DateUtil.getDateTime(DateUtil.parseDate(DateUtil.addDate(orderManageModel.getEndTime(), 1)));
			orderManageModel.setBeginTime(com.jansh.comm.util.DateUtil.formatDateTime(begin));
			orderManageModel.setEndTime(com.jansh.comm.util.DateUtil.formatDateTime(end));
		}
		ExcelWrite datas = createExcelWrite(orderManageModel);
		/**写入文件 */
		String name = getFilePath();
        File file = new File(name);
		datas.write(name);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=dowmloadls.xlsx");
		byte[] datafiles = FileUtils.readFileToByteArray(file);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(datafiles, header, HttpStatus.CREATED);
		return responseEntity;
	}
	
	/**
	 * 
	 * 生成excel表格输出流
	 * 
	 */
	private ExcelWrite createExcelWrite(OrderManageModel orderManageModel){
		ExcelWrite datas = new ExcelWrite();
		String[] headers = {"活动名称","用户编号","流水号", "中奖手机号", "奖品类型","奖品名称","派奖状态", "创建时间"};
		List<String> titles = Arrays.asList(headers);
		orderManageModel.setStart(orderManageModel.getStart());
		orderManageModel.setLength(Integer.parseInt(globalProperties.getPageRecordCount()));
		/** 查询日期必须成对出现 **/
		int j = 0;
		while(true){
			List<List<String>> lstDatas = new ArrayList<List<String>>();
			if(j == 0){
				lstDatas.add(titles);
				/**添加标题 */
			}
			int baseData = 500;
			int sStart = j * baseData;
			orderManageModel.setStart(sStart);
			logger.info("分页查询流水信息，分页起始条数：{}",sStart);
			OrderManageModel showAllData = showAllflows(orderManageModel);
			List<ShowOrderModel> dataset = showAllData.getShowOrderModels();
			/**将数据源数据追加到目的集合中 */
			convertProperties(lstDatas,dataset);
			/**分页追加数据，每页500条数据 */
			datas.append("话费流量充值订单流水"+j,lstDatas);
			if(dataset.size() < baseData){
				logger.info("追加账户余额及消费信息");
				List<List<String>> lstDatas1 = appendSurplus(dataset);
				datas.append("话费流量充值消费信息",lstDatas1);
				logger.info("分页查询流水信息，查询结束：{}",sStart);
				break;
			}
			j++;
		}
		
		return datas;
	}
	
	/**
	 * 
	 * 生成excel文件路径 
	 * 
	 */
	private String getFilePath() throws IOException{
		/**写入文件 */
		String name = globalProperties.getDownOrderRecordsPath()+"/ls"+ com.jansh.comm.util.DateUtil.getDateTimestamp()+IDUtils.getTimeRandon()+".xlsx";
		/**读文件 */
		File dir = new File(globalProperties.getDownOrderRecordsPath());
		if(!dir.exists()){
			dir.mkdirs();
		}
		return name;
	}
	/**
	 * 
	 * 将账户信息消费信息追加到目的集合中 
	 * 
	 */
	private List<List<String>> appendSurplus(List<ShowOrderModel> dataset){
		String[] headers1 = {"账户余额","已消费余额"};
		List<String> titles1 = Arrays.asList(headers1);
		List<List<String>> lstDatas1 = new ArrayList<List<String>>();
		List<String> list1 = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(dataset)){
			CloudATSurplusEntitiy accountSurplus = cyService.getAccountSurplus();
			if(accountSurplus !=null){
				list1.add(accountSurplus.getCurrentMoney().toString());
			}else{
				list1.add("0");
			}
			list1.add(dataset.get(0).getSumPrice());
		}
		lstDatas1.add(titles1);
		lstDatas1.add(list1);
		return lstDatas1;
		
	}
	
	/**
	 * 
	 * 将数据源数据追加到目的集合中 
	 * 
	 */
	private void  convertProperties(List<List<String>> dest , List<ShowOrderModel> org ){
		if(org!=null&&org.size()>0){
			int size = org.size();
			for(int m = 0; m < size; m++){
				List<String> datas1 = new ArrayList<String>();
				datas1.add(org.get(m).getGamename());
				datas1.add(org.get(m).getOpenid());
				datas1.add(org.get(m).getId());
				datas1.add(org.get(m).getWinnerphone());
				datas1.add(getPrizeType(org.get(m).getPrizetype()));
				String prizetype = org.get(m).getPrizetype();
				/*如果是虚拟奖品则奖品名是套餐类型，如果是实物则是用户自己填写的奖品名称*/
				if(AppCommonsCode.IPSTYPE_Fictitious.value().equals(prizetype)){
					datas1.add(org.get(m).getPrizestylename());
				}else if(AppCommonsCode.IPSTYPE_Material.value().equals(prizetype)){
					datas1.add(org.get(m).getPrizename());
				}
				datas1.add(getSendStatus(org.get(m).getSendstatus()));
				datas1.add(org.get(m).getCreatetime());
				/**添加数据 */
				dest.add(datas1);
			}
		}
	}
	
	/**
	 * 
	 * 获取订单的发送状态
	 * 
	 */
	private String getSendStatus(String sendStatus){
		if(AppCommonsCode.RECHARGEINTER_STATUS_0.value().equals(sendStatus)){
			return "未发送";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_1.value().equals(sendStatus)){
			return "充值成功";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_2.value().equals(sendStatus)){
			return "充值失败";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_3.value().equals(sendStatus)){
			return "无此订单";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_4.value().equals(sendStatus)){
			return "已受理";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_5.value().equals(sendStatus)){
			return "套餐不存在";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_6.value().equals(sendStatus)){
			return "游戏预算不足";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_7.value().equals(sendStatus)){
			return "已发送";
		}else if(AppCommonsCode.RECHARGEINTER_STATUS_8.value().equals(sendStatus)){
			return "无需发送";
		}else{
			return "状态未知";
		}
	}
	/**
	 * 
	 * 获取奖品类型
	 * 
	 */
	private String getPrizeType(String prizeType){
		if(AppCommonsCode.IPSTYPE_Fictitious.value().equals(prizeType)){
			return "虚拟奖品"; 
		}else if(AppCommonsCode.IPSTYPE_Material.value().equals(prizeType)){
			return "实物奖品"; 
		}else{
			return "无此类型";
		}
	}
	@Override
	public OrderManageModel batchRecharge(OrderManageModel manageModel) throws Exception {
		String userId = AppUtil.getUserDetail().getUserid();
		manageModel.setUserId(userId);
		
		/** 查询日期必须成对出现 **/
		if(StringUtils.isNotBlank(manageModel.getBeginTime())&&
				StringUtils.isNotBlank(manageModel.getEndTime())){
			String begin = DateUtil.getDateTime(DateUtil.parseDate(manageModel.getBeginTime()));
			String end = DateUtil.getDateTime(DateUtil.parseDate(DateUtil.addDate(manageModel.getEndTime(), 1)));
			manageModel.setBeginTime(com.jansh.comm.util.DateUtil.formatDateTime(begin));
			manageModel.setEndTime(com.jansh.comm.util.DateUtil.formatDateTime(end));
		}
		/** 获取查询订单状态集合 **/
		List<String> sendstatus = getStatusList(manageModel);
		logger.info("开始获取所有数据");
		Map<String, Object> map = new HashMap<>();
		map.put("beginTime", manageModel.getBeginTime());
		map.put("endTime", manageModel.getEndTime());
		map.put("sendstatus",sendstatus);
		map.put("prizetype", manageModel.getPrizetype());
		map.put("vrid", manageModel.getVrid());
		map.put("gpid", manageModel.getGpid());
		map.put("phone", manageModel.getPhone());
		List<ShowOrderBO> records = winnerrecordMapper.selectRecords(map);
		if(CollectionUtils.isNotEmpty(records)){
			manageModel.setQueryCount(records.size());
			//获取需要更新状态的集合
			List<String> list = getUpdateList(records);
			if(CollectionUtils.isNotEmpty(list)){
				manageModel.setChargeCount(list.size());
				int updateNum = winnerrecordMapper.batchUpdate(list);
				manageModel.setValidateCount(updateNum);
			}
		}
		return manageModel;
	}
	/**
	 * 获取需要更新状态的集合
	 * @param records
	 * @return
	 * @throws Exception
	 */
	private List<String> getUpdateList(List<ShowOrderBO> records) throws Exception{
		List<String> list = new ArrayList<>();
		for (ShowOrderBO rechargeBO : records) {
			// 活动预算不足和平台币不足的订单直接加入更新集合
			if(AppCommonsCode.RECHARGEINTER_STATUS_6.value().equals(rechargeBO.getSendstatus())||
					AppCommonsCode.RECHARGEINTER_STATUS_9.value().equals(rechargeBO.getSendstatus())){
				list.add(rechargeBO.getId());
			}
			// 充值失败失败的订单，重新更换id 将订单关系中间表保存后 再加入集合
			if(AppCommonsCode.RECHARGEINTER_STATUS_2.value().equals(rechargeBO.getSendstatus())){
				CloudwinningrecordEntity cloudwinningrecord = new CloudwinningrecordEntity();
				cloudwinningrecord.setId(rechargeBO.getId());
				List<CloudwinningrecordEntity> selects = winnerrecordMapper.select(cloudwinningrecord);
				if(CollectionUtils.isNotEmpty(selects)&&selects.size()==1){
					CloudwinningrecordEntity record = selects.get(0);
					//插入新的中奖就记录
					CloudwinningrecordEntity newRecord = new CloudwinningrecordEntity();
					BeanUtils.copyProperties(newRecord, record);
					newRecord.setId(IDUtils.getTimeRandon());
					newRecord.setCreatetime(com.jansh.comm.util.DateUtil.getDateTimestamp());
					winnerrecordMapper.insert(newRecord);
					//将旧的中奖记录状态更改为无效订单
					record.setSendstatus(AppCommonsCode.RECHARGEINTER_STATUS_10.value());
					winnerrecordMapper.update(record);
					//插入新旧订单中间表 供对账使用
					CloudorderloserEntity cloudorderloserEntity = new CloudorderloserEntity();
					cloudorderloserEntity.setId(newRecord.getId());
					cloudorderloserEntity.setParentid(record.getId());
					cloudorderloserEntity.setCreatetime(com.jansh.comm.util.DateUtil.getDateTimestamp());
					orderLoserMapper.insert(cloudorderloserEntity);
					list.add(newRecord.getId());
				}
			}
		}
		return list;
	}
	
	@Override
	public AjaxObj recharge(String id) throws Exception {
		logger.info("开始单个补充");
		AjaxObj ajaxObj = new AjaxObj();
		Map<String, Object> map = new HashMap<>();
		map.put("id",id);
		List<ShowOrderBO> records = winnerrecordMapper.selectRecords(map);
		if(CollectionUtils.isNotEmpty(records)){
			List<String> list = getUpdateList(records);
			if(CollectionUtils.isNotEmpty(list)){
				int updateNum = winnerrecordMapper.batchUpdate(list);
				if(updateNum>0){
					ajaxObj.setResult(1);
					ajaxObj.setSuccess(true);
					ajaxObj.setMsg("订单补充成功！");
				}else{
					ajaxObj.setResult(0);
					ajaxObj.setSuccess(false);
					ajaxObj.setMsg("此订单补充失败！");
				}
			}else{
				ajaxObj.setResult(0);
				ajaxObj.setSuccess(false);
				ajaxObj.setMsg("没有此订单，补充失败！");
			}
		}else{
			ajaxObj.setResult(0);
			ajaxObj.setSuccess(false);
			ajaxObj.setMsg("没有此订单，补充失败！");
		}
		
		return ajaxObj;
	}
}

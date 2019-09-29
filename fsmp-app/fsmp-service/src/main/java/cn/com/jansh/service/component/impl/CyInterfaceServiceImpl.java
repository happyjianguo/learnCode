/**
 * CyInterfaceServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月26日
 */
package cn.com.jansh.service.component.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.JsonUtil;
import com.jansh.core.entity.sys.PubsSysBase;
import com.jansh.core.service.component.HttpClientComponent;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppConstant;
import cn.com.jansh.constant.AppPayOrderCode;
import cn.com.jansh.entity.component.CfolTradeQueryEntity;
import cn.com.jansh.entity.component.CloudATSurplusEntitiy;
import cn.com.jansh.entity.component.bo.ShowRechargeBO;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.janshpay.util.Sign;
import cn.com.jansh.mapper.component.CloudATSurplusMapper;
import cn.com.jansh.mapper.component.CloudcurrencyMapper;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;
import cn.com.jansh.model.component.RechargeManageModel;
import cn.com.jansh.model.component.ShowRechargeModel;
import cn.com.jansh.service.component.CyInterfaceService;
import cn.com.jansh.utils.RandomNumUtil;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 平台币购买实现类
 * 
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class CyInterfaceServiceImpl implements CyInterfaceService {

	private final static Logger logger = LogManager.getLogger(CyInterfaceServiceImpl.class);

	@Autowired
	private PubsSysBaseMapper systemBaseMapper;
	@Autowired
	private IMUserMapper imusermapper;
	@Autowired
	private CloudcurrencyMapper currencymapper;
	@Autowired
	private CloudATSurplusMapper surplusmapper;
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private HttpClientComponent httpClientComponent;

	/**
	 * 支付接口
	 */
	@Override
	public JsonVO pay(String payno) {
		JsonVO jsonVO = new JsonVO();
		logger.info("开始支付");
		/** 获取机构id 银联支付秘钥 **/
		PubsSysBase orgidBase = systemBaseMapper.selectOneByBaseId(AppConstant.SYSBASE_ORGID_KEY);
		PubsSysBase secretBase = systemBaseMapper.selectOneByBaseId(AppConstant.SYSBASE_SECRET_KEY);
		/** 获取用户信息 **/
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN userNew = imusermapper.selectNewByUserid(userid);
		String timetamp = DateUtil.getDateTimestamp();
		String randomNum = RandomNumUtil.getRandomNum(6);
		/** 订单号id **/
		StringBuilder builder = new StringBuilder();
		builder.append(orgidBase.getSysBaseValue());
		builder.append(timetamp);
		builder.append(randomNum);
		String orderid = builder.toString();
		logger.info("支付订单号为: " + orderid);
		/** 购买的平台币数量及支付的人民币金额 **/
		String money = payno.replaceAll("\\,", "");
		BigDecimal coinToCentRate = new BigDecimal(Integer.parseInt(globalProperties.getIconExchageRate()));
		BigDecimal centToInterRate = new BigDecimal(AppConstant.CENTTOINTERRATE);
		int inteTocent = new BigDecimal(money).multiply(centToInterRate).intValue();
		BigDecimal coinbuy = (new BigDecimal(inteTocent).multiply(coinToCentRate));
		BigDecimal moneypay = (new BigDecimal(inteTocent).divide(centToInterRate, 2, 4));

		/** 生成实体 **/
		CfolTradeQueryEntity queryEntity = new CfolTradeQueryEntity();
		queryEntity.setOrderid(orderid);
		queryEntity.setUserid(userid);
		queryEntity.setOrgid(userNew.getOrgid());
		queryEntity.setTxntime(timetamp);
		queryEntity.setCode(orgidBase.getSysBaseValue());
		queryEntity.setPrice(moneypay);
		queryEntity.setCurrentmoney(coinbuy);
		queryEntity.setSign(secretBase.getSysBaseValue());
		ObjectMapper mapper = new ObjectMapper();
		/** 支付参数 **/
		Map<String, String> currency_pay_param = new HashMap<>();
		currency_pay_param.put("code", orgidBase.getSysBaseValue());
		currency_pay_param.put("txnTime", timetamp);
		currency_pay_param.put("orderId", orderid);
		//globalProperties.getFrontURL() 删掉前台回调
		
		HttpServletRequest httprequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		currency_pay_param.put("frontUrl", globalProperties.getAuthRedirectURI()+httprequest.getContextPath()+"/curcb/init");
		currency_pay_param.put("backUrl", globalProperties.getBackURL());
		currency_pay_param.put("txnAmt", String.valueOf(inteTocent));
		currency_pay_param.put("code", orgidBase.getSysBaseValue());
		/** 校验串 **/
		Map<String, String> signMap = new HashMap<String, String>();
		String paramSign = Sign.getSign(currency_pay_param, secretBase.getSysBaseValue());
		signMap.put("sign", paramSign);
		signMap.put("code", orgidBase.getSysBaseValue()); // 若此处存放了key为code的值，则同步交易返回数据会带有验签串，需要验签
		/** 发起支付网络请求 **/
		HttpClientRequest request = new HttpClientRequest();
		request.setHeaders(signMap);
		try {
			request.setBody(mapper.writeValueAsString(currency_pay_param));
			request.setSocketTimeout(999999999);

			String result = HttpClientUtil.httpPost(globalProperties.getCurrency_pay_url(), request);
			Map<String, Object> res = JsonUtil.readMapObject(result);

			@SuppressWarnings("unchecked")
			Map<String, Object> resultResult = (Map<String, Object>) res.get("result");
			/** 得到result结果集 进行分类处理 **/
			if (StringUtils.isEmpty((String) res.get("sign"))) {
				/** 无签名直接处理 **/
				String errorCode = (String) resultResult.get("errorCode");
				/** 结果集中的errorCode **/
				if (AppPayOrderCode.RESSUCCEED.value().equals(errorCode)) {
					/** 正常处理 **/
					logger.info("无签名，处理正常：" + result);
					String data = (String) resultResult.get("data");
					jsonVO.setData(data);
					jsonVO.setSuccess(true);
					return jsonVO;
				} else {
					/** 处理失败 **/
					logger.error("无签名，处理失败：" + result);
				}
				jsonVO.setData(resultResult);
				jsonVO.setMsg("无签名，处理失败");
				jsonVO.setSuccess(false);
				return jsonVO;
			} else {
				/** 有签名，先验证签名是否正常 **/
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", JsonUtil.obj2json(res.get("result")));
				if (Sign.getSign(map, secretBase.getSysBaseValue()).equals((String) res.get("sign"))) {
					/** 签名校验成功 **/
					String errorCode = (String) resultResult.get("errorCode");
					if (AppPayOrderCode.RESSUCCEED.value().equals(errorCode)) {
						/** 返回正确应答状态正常处理 **/
						logger.info("有签名，签名校验通过，处理正常：" + result);
						String data = (String) resultResult.get("data");
						queryEntity.setStatus(AppPayOrderCode.RESSUCCEED.value());
						currencymapper.insert(queryEntity);
						jsonVO.setData(data);
						jsonVO.setSuccess(true);
						return jsonVO;
					} else {
						/** 返回异常应答状态处理失败 **/
						logger.error("有签名，签名校验通过，处理失败：" + result);
					}
					jsonVO.setData(resultResult);
					jsonVO.setMsg("有签名，签名校验通过，处理失败");
					jsonVO.setSuccess(false);
					return jsonVO;
				} else {
					/** 签名校验失败处理 **/
					logger.error("有签名，签名校验失败：" + result);
					jsonVO.setData(resultResult);
					jsonVO.setMsg("支付平台返回信息验签失败");
					jsonVO.setSuccess(false);
					return jsonVO;
				}
			}
		} catch (Exception e) {
			logger.error("支付订单异常{}", e);
		}
		Map<String, String> map = new HashMap<>();
		map.put("errorCode", AppPayOrderCode.YLRESBUSY.value());
		map.put("errorMsg", "银联系统异常 ");
		jsonVO.setData(map);
		jsonVO.setMsg("支付订单异常");
		jsonVO.setSuccess(false);
		return jsonVO;
	}

	/**
	 * 支付回调接口
	 */
	@Override
	public void payBack(String reqBody) throws Exception {
		Map<String, Object> res = JsonUtil.readMapObject(reqBody);
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) res.get("result");
		if (StringUtils.isEmpty((String) res.get("sign"))) {// 无签名直接处理
			String errorCode = (String) result.get("errorCode");
			if (AppPayOrderCode.RESSUCCEED.value().equals(errorCode)) {
				/** 正常处理 **/
				logger.info("无签名，处理正常：{}", result);
				@SuppressWarnings("unchecked")
				Map<String, Object> data = (Map<String, Object>) result.get("data");
				CfolTradeQueryEntity cfolTradeQueryEntity = new CfolTradeQueryEntity();

				cfolTradeQueryEntity.setOrigrespcode(data.get("respCode").toString());
				cfolTradeQueryEntity.setTraceno(data.get("traceNo").toString());// 跟踪号
				cfolTradeQueryEntity.setQueryid(data.get("queryId").toString());// 交易号
				cfolTradeQueryEntity.setOrderid(data.get("orderId").toString());// 订单号
				cfolTradeQueryEntity.setUpdatetime(DateUtil.getDateTimestamp());
				cfolTradeQueryEntity.setStatus1(AppPayOrderCode.NOPAYMENT.value());
				if (AppPayOrderCode.YLSUCCESS.value().equals(data.get("respCode").toString())) {
					/** 交易成功 **/
					cfolTradeQueryEntity.setStatus(AppPayOrderCode.OLSUCCEED.value());
				} else if (AppPayOrderCode.YLRESPFAIl.value().equals(data.get("respCode").toString())) {
					/** 处理失败 */
					cfolTradeQueryEntity.setStatus(AppPayOrderCode.OLFAILED.value());
				} else {
					/** 其他情况已受理 */
					cfolTradeQueryEntity.setStatus(AppPayOrderCode.BEENACCEPTED.value());
				}
				currencymapper.update(cfolTradeQueryEntity);
			} else {
				/** 处理失败 **/
				logger.info("无签名，处理失败：{}", result);
			}
		} else {
			/** 有签名，先验证签名是否正常 **/
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", JsonUtil.obj2json(res.get("result")));
			/** guid为支付平台下发的秘钥 **/
			PubsSysBase secretBase = systemBaseMapper.selectOneByBaseId(AppConstant.SYSBASE_SECRET_KEY);
			String guid = secretBase.getSysBaseValue();
			if (Sign.getSign(map, guid).equals((String) res.get("sign"))) {// 签名校验成功
				String errorCode = (String) result.get("errorCode");
				if (AppPayOrderCode.RESSUCCEED.value().equals(errorCode)) {
					/**
					 * 此处返回000000说明银联支付回调验签成功，不代表交易成功，需判断交易码 正常处理
					 **/
					logger.info("有签名，签名校验通过，处理正常：{}" + result);
					@SuppressWarnings("unchecked")
					Map<String, Object> data = (Map<String, Object>) result.get("data");
					CfolTradeQueryEntity cfolTradeQueryEntity = currencymapper
							.selectByOrderid(data.get("orderId").toString());
					if (AppPayOrderCode.YLSUCCESS.value().equals(data.get("respCode").toString())
							|| AppPayOrderCode.YLA6.value().equals(data.get("respCode").toString())) {
						/**
						 * 支付成功的处理逻辑，其中00为交易完全成功，A6为有缺陷的成功，所有返回码参考文档：银联平台接入接口规范-第5部分-附录V1.8.pdf
						 * 交易成功 账户增加平台币的逻辑是
						 * 先看订单是否是已经交易成功，如果未成功，但是回调结果是成功的说明是第一次回调，将订单金额与账户余额相加
						 * 如果订单已经成功处理说明这不是首次回调，不进行平台币处理
						 **/
						if (!cfolTradeQueryEntity.getStatus().equals(AppPayOrderCode.OLSUCCEED.value())) {
							CloudATSurplusEntitiy surplusEntitiy = surplusmapper
									.selectByOrgid(cfolTradeQueryEntity.getOrgid());
							String updatetime = DateUtil.getDateTimestamp();
							BigDecimal coinbuy = cfolTradeQueryEntity.getCurrentmoney();
							if (surplusEntitiy == null) {
								/** 为空说明没有账户余额，插入数据 **/
								surplusEntitiy = new CloudATSurplusEntitiy();
								String timeRandon = IDUtils.getTimeRandon();
								surplusEntitiy.setId(timeRandon);
								surplusEntitiy.setOrgid(cfolTradeQueryEntity.getOrgid());
								surplusEntitiy.setCurrentMoney(coinbuy);
								surplusEntitiy.setTotalMoney(coinbuy);
								surplusEntitiy.setUpdateTime(updatetime);
								surplusEntitiy.setCreateTime(updatetime);
								surplusmapper.insert(surplusEntitiy);
							} else {
								/** 不为空说明有账户余额，更新数据 **/
								Map<String, Object> account = new HashMap<>();
								account.put("orgid", surplusEntitiy.getOrgid());
								account.put("coinBuy", coinbuy);
								account.put("updateTime", updatetime);
								surplusmapper.updateBalance(account);
							}
						}
						cfolTradeQueryEntity.setStatus(AppPayOrderCode.OLSUCCEED.value());
						cfolTradeQueryEntity.setStatus1(AppPayOrderCode.OLSUCCEED.value());
					} else if (AppPayOrderCode.YLRESPFAIl.value().equals(data.get("respCode").toString())) {
						/** 处理失败 **/
						cfolTradeQueryEntity.setStatus(AppPayOrderCode.OLFAILED.value());
						cfolTradeQueryEntity.setStatus1(AppPayOrderCode.OLFAILED.value());

					} else {
						/** 其他情况已受理 **/
						cfolTradeQueryEntity.setStatus(AppPayOrderCode.BEENACCEPTED.value());
						cfolTradeQueryEntity.setStatus1(AppPayOrderCode.BEENACCEPTED.value());
					}
					/** 更新订单 **/
					cfolTradeQueryEntity.setOrigrespcode(data.get("respCode").toString());
					cfolTradeQueryEntity.setTraceno(data.get("traceNo").toString());// 跟踪号
					cfolTradeQueryEntity.setQueryid(data.get("queryId").toString());// 交易号
					cfolTradeQueryEntity.setOrderid(data.get("orderId").toString());// 订单号
					cfolTradeQueryEntity.setUpdatetime(DateUtil.getDateTimestamp());
					cfolTradeQueryEntity.setPaytype(AppPayOrderCode.UNIONOPTION.value());
					currencymapper.update(cfolTradeQueryEntity);

				} else {
					/** 处理失败 **/
					logger.info("有签名，签名校验通过，处理失败：{}", result);
				}
			} else {
				/** 签名校验失败 **/
				logger.info("有签名，签名校验失败：{}", result);
			}
		}
	}

	/**
	 * 银联支付交易查询
	 */
	@Override
	public void tradeCheck() {
		logger.info("开始查询订单状态");
		/** 生成订单查询条件 **/
		Map<String, Object> map = new HashMap<>();
		map.put("status", AppPayOrderCode.RESSUCCEED.value());
		map.put("statust", AppPayOrderCode.BEENACCEPTED.value());
		List<CfolTradeQueryEntity> tradeQuerys = currencymapper.select(map);
		/** 判断集合是否为空，否则遍历集合挨个查询充值结果 **/
		if (CollectionUtils.isNotEmpty(tradeQuerys)) {
			logger.info("需要查询的订单数量是：" + tradeQuerys.size());
			for (CfolTradeQueryEntity queryEntity : tradeQuerys) {
				int querynum;
				// 查看订单的查询次数，超过6次直接标记为失败，不再查询
				if (queryEntity.getQuerynum() != null) {
					querynum = Integer.valueOf(queryEntity.getQuerynum()) + 1;
				} else {
					querynum = Integer.valueOf(AppPayOrderCode.QUERYNUM_0.value());
				}
				int queryMaxNum = Integer.valueOf(AppPayOrderCode.QUERYNUM_6.value());
				queryEntity.setQuerynum(String.valueOf(querynum));
				if (querynum >= queryMaxNum) {
					logger.error("银联交易查询次数已超限，无此订单交易！");
					queryEntity.setUpdatetime(DateUtil.getDateTimestamp());
					queryEntity.setStatus(AppPayOrderCode.OLFAILED.value());
					queryEntity.setStatus1(AppPayOrderCode.OLFAILED.value());
					currencymapper.update(queryEntity);
				} else {
					String queryResult = null;
					try {
						queryResult = unionTradequery(queryEntity.getCode(), queryEntity.getOrderid(),
								queryEntity.getTxntime());
					} catch (Exception e) {
						logger.error("查询交易结果异常{}", e);
						continue;
					}
					/** 此处加入事务管理，如果出现异常则立即回滚事务 **/
					DefaultTransactionDefinition def = new DefaultTransactionDefinition();
					def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
					TransactionStatus status = transactionManager.getTransaction(def);
					try {
						if (StringUtils.isNotBlank(queryResult)) {
							checkQueryResult(queryResult, queryEntity);
							transactionManager.commit(status);
						}
					} catch (Exception e) {
						logger.error("处理交易结果异常{}", e);
						transactionManager.rollback(status);
					}
				}
			}
		}
	}

	public void checkQueryResult(String reqBody,CfolTradeQueryEntity cfolTradeQueryEntity) throws Exception {
		Map<String, Object> res = JsonUtil.readMapObject(reqBody);
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) res.get("result");
		/** 有签名，先验证签名是否正常 **/
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", JsonUtil.obj2json(res.get("result")));
		/** guid为支付平台下发的秘钥 **/
		PubsSysBase secretBase = systemBaseMapper.selectOneByBaseId(AppConstant.SYSBASE_SECRET_KEY);
		String guid = secretBase.getSysBaseValue();
		if (Sign.getSign(map, guid).equals((String) res.get("sign"))) {// 签名校验成功
			String errorCode = (String) result.get("errorCode");
			if (AppPayOrderCode.RESSUCCEED.value().equals(errorCode)) {
				/**
				 * 此处返回000000说明银联支付回调验签成功，不代表交易成功，需判断交易码 正常处理
				 **/
				logger.info("有签名，签名校验通过，处理正常：{}" + result);
				@SuppressWarnings("unchecked")
				Map<String, Object> data = (Map<String, Object>) result.get("data");
				if (AppPayOrderCode.YLSUCCESS.value().equals(data.get("origRespCode").toString())
						|| AppPayOrderCode.YLA6.value().equals(data.get("origRespCode").toString())) {
					/**
					 * 支付成功的处理逻辑，其中00为交易完全成功，A6为有缺陷的成功，所有返回码参考文档：银联平台接入接口规范-第5部分-附录V1.8.pdf
					 * 交易成功 账户增加平台币的逻辑是
					 * 先看订单是否是已经交易成功，如果未成功，但是回调结果是成功的说明是第一次回调，将订单金额与账户余额相加
					 * 如果订单已经成功处理说明这不是首次回调，不进行平台币处理
					 **/
					if (!cfolTradeQueryEntity.getStatus().equals(AppPayOrderCode.OLSUCCEED.value())) {
						CloudATSurplusEntitiy surplusEntitiy = surplusmapper
								.selectByOrgid(cfolTradeQueryEntity.getOrgid());
						String updatetime = DateUtil.getDateTimestamp();
						BigDecimal coinbuy = cfolTradeQueryEntity.getCurrentmoney();
						if (surplusEntitiy == null) {
							/** 为空说明没有账户余额，插入数据 **/
							surplusEntitiy = new CloudATSurplusEntitiy();
							String timeRandon = IDUtils.getTimeRandon();
							surplusEntitiy.setId(timeRandon);
							surplusEntitiy.setOrgid(cfolTradeQueryEntity.getOrgid());
							surplusEntitiy.setCurrentMoney(coinbuy);
							surplusEntitiy.setTotalMoney(coinbuy);
							surplusEntitiy.setUpdateTime(updatetime);
							surplusEntitiy.setCreateTime(updatetime);
							surplusmapper.insert(surplusEntitiy);
						} else {
							/** 不为空说明有账户余额，更新数据 **/
							Map<String, Object> account = new HashMap<>();
							account.put("orgid", surplusEntitiy.getOrgid());
							account.put("coinBuy", coinbuy);
							account.put("updateTime", updatetime);
							surplusmapper.updateBalance(account);
						}
					}
					cfolTradeQueryEntity.setStatus(AppPayOrderCode.OLSUCCEED.value());
					cfolTradeQueryEntity.setStatus1(AppPayOrderCode.OLSUCCEED.value());
				} else if (AppPayOrderCode.YLRESPFAIl.value().equals(data.get("respCode").toString())) {
					/** 处理失败 **/
					cfolTradeQueryEntity.setStatus(AppPayOrderCode.OLFAILED.value());
					cfolTradeQueryEntity.setStatus1(AppPayOrderCode.OLFAILED.value());

				} else {
					/** 其他情况已受理 **/
					cfolTradeQueryEntity.setStatus(AppPayOrderCode.BEENACCEPTED.value());
					cfolTradeQueryEntity.setStatus1(AppPayOrderCode.BEENACCEPTED.value());
				}
				/** 更新订单 **/
				cfolTradeQueryEntity.setOrigrespcode(data.get("origRespCode").toString());
				cfolTradeQueryEntity.setTraceno(data.get("traceNo").toString());// 跟踪号
				cfolTradeQueryEntity.setUpdatetime(DateUtil.getDateTimestamp());
				cfolTradeQueryEntity.setPaytype(AppPayOrderCode.UNIONOPTION.value());
				currencymapper.update(cfolTradeQueryEntity);

			}
			currencymapper.update(cfolTradeQueryEntity);
		}
	}

	/**
	 * 银联交易状态查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unionTradequery(String code, String orderId, String txnTime) throws Exception {
		// 获取银联支付秘钥
		PubsSysBase secretBase = systemBaseMapper.selectOneByBaseId(AppConstant.SYSBASE_SECRET_KEY);

		// 前台页面传过来的
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("code", code);
		paramMap.put("txnTime", txnTime);
		paramMap.put("orderId", orderId);

		String json = JsonUtil.obj2json(paramMap);

		HttpClientRequest request = new HttpClientRequest();

		Map<String, String> signMap = new HashMap<String, String>();
		String paramSign = Sign.getSign(paramMap, secretBase.getSysBaseValue());
		signMap.put("code", code);
		signMap.put("sign", paramSign);
		request.setHeaders(signMap);
		request.setBody(json);
		request.setSocketTimeout(999999999);
		return httpClientComponent.httpPost(globalProperties.getCurrency_query_url(), request);
	}

	/**
	 * 获取机构账户信息
	 * 
	 * @throws Exception
	 */
	public CloudATSurplusEntitiy getAccountSurplus() {
		logger.info("获取机构账户信息,开始获取机构基础信息");
		/** 获取用户信息 **/
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN userNew = imusermapper.selectNewByUserid(userid);
		logger.info("机构基础信息" + userNew.getOrgid());
		/** 获取机构账号信息 **/
		CloudATSurplusEntitiy surplus = surplusmapper.selectByOrgid(userNew.getOrgid());
		return surplus;
	}

	/**
	 * 获取机构账户充值记录
	 * 
	 * @throws Exception
	 */
	public RechargeManageModel searchRecharge(RechargeManageModel rechargeManageModel) {
		logger.info("开始获取用户信息");
		String userId = AppUtil.getUserDetail().getUserid();
		IMUserN user = imusermapper.selectNewByUserid(userId);
		rechargeManageModel.setOrgid(user.getOrgid());
		/** 查询日期必须成对出现 **/
		if (StringUtils.isNotBlank(rechargeManageModel.getBeginTime())
				&& StringUtils.isNotBlank(rechargeManageModel.getEndTime())) {
			Date begin = cn.com.jansh.utils.DateUtil.parseDate(rechargeManageModel.getBeginTime());
			Date end = cn.com.jansh.utils.DateUtil.parseDate(cn.com.jansh.utils.DateUtil.addDate(rechargeManageModel.getEndTime(), 1));
			rechargeManageModel.setBeginTime(com.jansh.comm.util.DateUtil.formatDateTime(cn.com.jansh.utils.DateUtil.getDateTime(begin)));
			rechargeManageModel.setEndTime(com.jansh.comm.util.DateUtil.formatDateTime(cn.com.jansh.utils.DateUtil.getDateTime(end)));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("userId", rechargeManageModel.getUserId());
		map.put("orgid", rechargeManageModel.getOrgid());
		map.put("beginTime", rechargeManageModel.getBeginTime());
		map.put("endTime", rechargeManageModel.getEndTime());
		map.put("status", rechargeManageModel.getStatus());
		map.put("start", rechargeManageModel.getStart());
		map.put("length", rechargeManageModel.getLength());
		List<ShowRechargeBO> searchRecharge = currencymapper.searchRecharge(map);
		List<ShowRechargeModel> showRechargeModels = new ArrayList<>();
		for (ShowRechargeBO rechargeBO : searchRecharge) {
			ShowRechargeModel rechargeModel = new ShowRechargeModel();
			try {
				BeanUtils.copyProperties(rechargeModel, rechargeBO);
				showRechargeModels.add(rechargeModel);
			} catch (Exception e) {
				logger.error("属性拷贝异常", e);
			}
		}
		RechargeManageModel rechargeModel = new RechargeManageModel();
		rechargeModel.setShowRechargeModels(showRechargeModels);
		rechargeModel.setCount(currencymapper.searchRechargeCount(map));
		return rechargeModel;
	}

	/**
	 * 获取机构账户充值记录
	 * 
	 * @throws Exception
	 */
	@Override
	public AjaxObj getRechargeRecord() {
		logger.info("获取机构账户充值记录,开始获取机构基础信息");
		AjaxObj ajaxObj = new AjaxObj();
		/** 获取用户信息 **/
		String userid = AppUtil.getUserDetail().getUserid();
		IMUserN userNew = imusermapper.selectNewByUserid(userid);
		logger.info("机构基础信息" + userNew.getOrgid());
		List<CfolTradeQueryEntity> list = currencymapper.selectByOrgid(userNew.getOrgid());
		if (list != null && list.size() > 0) {
			logger.info("查询记录成功：记录条数为 " + list.size());
			ajaxObj.setObj(list);
			ajaxObj.setResult(1);
		}
		return ajaxObj;
	}

}

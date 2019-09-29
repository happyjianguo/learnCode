/**
 * CfOrderServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.service.scheduler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.JsonUtil;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.component.CloudATSurplusEntitiy;
import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.entity.component.CloudvirgoodEntity;
import cn.com.jansh.entity.component.CloudwinningrecordEntity;
import cn.com.jansh.mapper.component.CloudATSurplusMapper;
import cn.com.jansh.mapper.component.CloudgameparamMapper;
import cn.com.jansh.mapper.component.CloudvirgoodMapper;
import cn.com.jansh.mapper.component.CloudwinningrecordMapper;
import cn.com.jansh.service.component.CfInterfaceService;
import cn.com.jansh.service.scheduler.CfOrderService;

/**
 * 定时任务实现类
 * @author duanmuyn
 * @version 1.0
 */
@Service
public class CfOrderServiceImpl implements CfOrderService {

	private static final Logger logger = LogManager.getLogger(CfOrderServiceImpl.class);
	
	@Autowired
	private CfInterfaceService cfInterfaceService;
	
	@Autowired
	private CloudwinningrecordMapper cloudwinningrecordMapper;
	
	@Autowired
	private CloudvirgoodMapper cloudvirgoodMapper;
	
	@Autowired
	private CloudgameparamMapper gameparamMapper;
	
	@Autowired
	private CloudATSurplusMapper surplusmapper;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public void createOrder() {
		logger.info("定时任务-直冲接口");
		CloudwinningrecordEntity cloudwinningrecord = new CloudwinningrecordEntity();
		cloudwinningrecord.setSendstatus(AppCommonsCode.RECHARGEINTER_STATUS_0.value());//订单发送状态为0(未发送)
		cloudwinningrecord.setPreliminarystatus(AppCommonsCode.RECHARGE_1.value());//复审状态为（复审状态）
		//0、查询待充值记录，审批状态通过，并且状态为未发送
		List<CloudwinningrecordEntity> list = cloudwinningrecordMapper.select(cloudwinningrecord);
		
		if(!list.isEmpty()){
			for(CloudwinningrecordEntity entity : list){
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				TransactionStatus status = transactionManager.getTransaction(def);
				
				//判断奖品类型是否为虚拟商品
				if(entity.getPrizetype().equals(AppCommonsCode.IPSTYPE_Fictitious.value())){
					//1、检查套餐状态
					CloudvirgoodEntity cloudvirgoodEntity = cloudvirgoodMapper.selectStatusOkByapid(entity.getPrizestyle(),AppCommonsCode.RECHARGE_PACKAGE_STATUS_kt.value());
					
					if(cloudvirgoodEntity == null){
						//1-1套餐不存在更新发送状态为套餐不存在
						cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_5.value(),entity);
					}else{
						//1-2、查询游戏预算
						CloudgameparamEntity cloudgameparamEntity = gameparamMapper.selectByGameid(entity.getGameid());
						//查询平台币余额
						CloudATSurplusEntitiy catsEntitiy = surplusmapper.selectByOrgid(entity.getOrgid());
						
						//机构或游戏不存在，标记为非法订单
						if(cloudgameparamEntity==null || catsEntitiy==null){
							cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_10.value(),entity);
							transactionManager.commit(status);
							continue;
						}
						
						BigDecimal catsm = catsEntitiy.getCurrentMoney();//机构下平台币金额
						
						BigDecimal aprice =new BigDecimal(cloudgameparamEntity.getAmount());//预算金额
						BigDecimal uaprice =new BigDecimal(cloudgameparamEntity.getUamount());//已用预算
						BigDecimal Prizeprice =new BigDecimal(cloudvirgoodEntity.getPrizeprice());//套餐价格
						
						BigDecimal uprice =uaprice.add(Prizeprice);//预计累加后使用预算
						Integer price = uprice.intValue();
						
						if(uprice.compareTo(aprice)>0){//已用预算+套餐价格>游戏预算金额 
							//修改订单状态为超出游戏预算
							cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_6.value(),entity);
						}else if(Prizeprice.compareTo(catsm)>0){//套餐价格>当前机构下平台币
							//修改订单状态为平台币不足
							cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_9.value(),entity);
						}else{
							logger.info("修改订单发送状态:{}",entity.getId());
							//1-2-1、机构减少平台币、修改订单状态为已发送，增加已用预算
							catsEntitiyUpdate(catsm.subtract(Prizeprice),catsEntitiy);//机构当前平台币=当前机构平台币-套餐价格
							gameparamUpdate(price,cloudgameparamEntity);//增加游戏预算
							cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_7.value(),entity);//定单状态修改为已发送
							
							//1-2-2、发送订单
							String resultmsg = cfInterfaceService.order(entity.getId(), entity.getWinnerphone(), cloudvirgoodEntity);
							logger.info("直冲返回结果:{}",resultmsg);
							
							try {
								Map<String, String> res = JsonUtil.readMapString(resultmsg);
								String errorCode = (String)res.get("errorCode");
								
								//受理不成功预算恢复原使用预算
								Integer mprice = uaprice.intValue();
								
								if(errorCode.equals(AppCommonsCode.RECHARGEINTER_ERROR_REQUEST.value())){//充值接口，非法请求
									//1-2-3、非法请求则恢复已使用预算
									gameparamUpdate(mprice,cloudgameparamEntity);
									//恢复原机构平台币
									catsEntitiyUpdate(catsm,catsEntitiy);
									//更新订单发送状态为非法请求
									cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_ERROR_REQUEST.value(),entity);
								}else if(errorCode.equals(AppCommonsCode.RECHARGEINTER_ERROR_PARSE.value())){//参数异常/充值流量无单位（M,G）/订单号重复
									//1-2-4、订单异常则恢复已使用预算
									gameparamUpdate(mprice,cloudgameparamEntity);
									//恢复原机构平台币
									catsEntitiyUpdate(catsm,catsEntitiy);
									//更新订单发送状态为参数异常
									cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_ERROR_PARSE.value(),entity);
								}else if(errorCode.equals(AppCommonsCode.RECHARGEINTER_OK.value())){//返回000000则更新受理成功
									//1-2-5、受理成功不处理金额
									//修改订单发送状态为已发送
									cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_4.value(),entity);
								}else{
									//1-2-3、异常请求则恢复已使用预算
									gameparamUpdate(mprice,cloudgameparamEntity);
									//恢复原机构平台币
									catsEntitiyUpdate(catsm,catsEntitiy);
									//更新订单发送状态为充值失败
									cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_2.value(),entity);
								}
							} catch (Exception e) {
								logger.error(e);
								transactionManager.rollback(status);//回滚事物
								continue;
							}
						}
					}
				}else{
					//修改订单状态为实物订单，不需发送
					cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_8.value(),entity);
				}
				//提交事务
				transactionManager.commit(status);
			}
		}
	}

	@Override
	public void queryOrder() {
		
		List<String> li = new ArrayList<String>();
		li.add(AppCommonsCode.RECHARGEINTER_STATUS_4.value());//已受理
		li.add(AppCommonsCode.RECHARGEINTER_STATUS_7.value());//已发送
		
		//0、查询已充值记录，状态为已受理、已发送
		List<CloudwinningrecordEntity> list = cloudwinningrecordMapper.selectByStatus(li);
		
		if(!list.isEmpty()){
			for(CloudwinningrecordEntity entity : list){
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				TransactionStatus transtatus = transactionManager.getTransaction(def);
				try {
					
					//1-1、话费流量充值平台订单信息查询
					String resultmsg = cfInterfaceService.queryorder(entity.getId());
					
					//1-2、查询游戏预算
					CloudgameparamEntity cloudgameparamEntity = gameparamMapper.selectByGameid(entity.getGameid());
					Integer price = cloudgameparamEntity.getUamount();//已用预算
					//查询平台币余额
					CloudATSurplusEntitiy catsEntitiy = surplusmapper.selectByOrgid(entity.getOrgid());
					BigDecimal catsm = catsEntitiy.getCurrentMoney();//机构下平台币金额
					
					//机构或游戏不存在，标记为非法订单
					if(cloudgameparamEntity==null || catsEntitiy==null){
						cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_10.value(),entity);
						continue;
					}
					
					//1-3、查询充值套餐信息
					CloudvirgoodEntity cloudvirgoodEntity = cloudvirgoodMapper.selectStatusOkByapid(entity.getPrizestyle(),null);
					//套餐不存在
					if(cloudvirgoodEntity==null){
						cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_5.value(),entity);
						continue;
					}
					
					logger.info("{}订单充值状态:{}",entity.getId(),resultmsg);
				
					//2-1、对接口返回信息解析
					Map<String, Object> res = JsonUtil.readMapObject(resultmsg);
					String errorCode = (String)res.get("errorCode");
					
					//充值不成功减去已用预算
					BigDecimal Uaprice =new BigDecimal(price);//游戏已用预算
					BigDecimal Prizeprice =new BigDecimal(cloudvirgoodEntity.getPrizeprice());//套餐报价
					Integer mprice = Uaprice.subtract(Prizeprice).intValue();//游戏已用预算-套餐报价
					//充值不成功恢复机构下平台币
					BigDecimal catsmN =catsm.add(Prizeprice);//当前机构平台币+套餐价格
							
					if(errorCode.equals(AppCommonsCode.RECHARGEINTER_OK.value())){//返回000000则更新受理成功
						@SuppressWarnings("unchecked")
						Map<String, String>  data = (Map<String, String>)res.get("data");
						String status = data.get("status");
						
						if(StringUtils.isNotEmpty(status)){
							if(status.equals(AppCommonsCode.RECHARGEINTER_STATUS_1.value())){//充值成功
								//2-4-1、订单状态修改为充值成功
								cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_1.value(),entity);
							}else if(status.equals(AppCommonsCode.RECHARGEINTER_STATUS_2.value())){//充值失败
								//2-4-2、充值失败则减少预算，订单状态修改为充值失败
								gameparamUpdate(mprice,cloudgameparamEntity);
								//订单失败退回平台币
								catsEntitiyUpdate(catsmN,catsEntitiy);
								//修改订单状态为订单失败
								cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_2.value(),entity);
							}else if(status.equals(AppCommonsCode.RECHARGEINTER_STATUS_4.value())){//已受理
								//2-4-3、已受理不更新状态
								if(entity.getSendstatus().equals(AppCommonsCode.RECHARGEINTER_STATUS_7.value())){
									//修改已发送订单状态为已受理
									cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_4.value(),entity);
								}
							}else if(status.equals(AppCommonsCode.RECHARGEINTER_STATUS_3.value())){//无此订单
								//2-4-4、无此订单则减少预算，订单状态修改为未发送
								gameparamUpdate(mprice,cloudgameparamEntity);
								//订单失败退回平台币
								catsEntitiyUpdate(catsmN,catsEntitiy);
								//无此订单状态更新为未发送，从新发送
								cloudwinningrecordUpdate(AppCommonsCode.RECHARGEINTER_STATUS_0.value(),entity);
							}
						}
					}	
				} catch (Exception e) {
					logger.error(e);
					transactionManager.rollback(transtatus);//回滚事物
					continue;
				}
				transactionManager.commit(transtatus);
			}
		}
	}
	
	/**
	 * 修改游戏已使用预算
	 * @param price
	 * @param entity
	 */
	private void gameparamUpdate(Integer price,CloudgameparamEntity entity){
		entity.setUamount(price);
		entity.setUpdatetime(DateUtil.getDateTimestamp());
		gameparamMapper.update(entity);
	}
	
	/**
	 * 修改订单状态
	 * @param sendstatus
	 * @param entity
	 */
	private void cloudwinningrecordUpdate(String sendstatus,CloudwinningrecordEntity entity){
		entity.setSendstatus(sendstatus);
		entity.setUpdatetime(DateUtil.getDateTimestamp());
		cloudwinningrecordMapper.update(entity);
	}
	
	/**
	 * 修改机构下平台币金额
	 * @param price
	 * @param entity
	 */
	private void catsEntitiyUpdate(BigDecimal price,CloudATSurplusEntitiy entity){
		entity.setCurrentMoney(price);
		entity.setUpdateTime(DateUtil.getDateTimestamp());
		surplusmapper.update(entity);
	}
}

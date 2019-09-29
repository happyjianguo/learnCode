/**
 * CfInterfaceService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.service.component;

import java.util.List;

import cn.com.jansh.entity.component.CloudvirgoodEntity;
import cn.com.jansh.model.component.CloudorgvirgoodModel;


/**
 * 话费流量充值平台接口SDk
 * @author duanmuyn
 * @version 1.0
 */
public interface CfInterfaceService {

	/**
	 * 查询接入者套餐信息
	 * @return
	 */
	public List<CloudvirgoodEntity> queryPack();
	
	/**
	 * 查询机构话费流量套餐
	 * @return
	 */
	public List<CloudorgvirgoodModel> queryVrGoods(String gameid);
	
	/**
	 * 直冲
	 * @param cporder
	 * @param phone
	 * @param apid
	 * @return
	 */
	public String order(String cporder , String phone, CloudvirgoodEntity entity);
	
	/**
	 * 订单查询
	 * @param cporder
	 * @return
	 */
	public String queryorder(String cporder);
}

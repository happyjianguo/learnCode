/**
 *包名:cn.yufu.posp.dao
 *描述:package cn.yufu.posp.dao;
 */
package cn.yufu.posp.dao;

import org.springframework.stereotype.Repository;

import cn.yufu.posp.entity.EdcTerminalX;

/**
 * EdcTerminalXMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月1日
 * 描述:终端同步扩展表
 */
@Repository("posp.EdcTerminalXDao")
public interface EdcTerminalXMapper {

	public void insertSelective(EdcTerminalX edcTerminalX);

	public void delete(EdcTerminalX edcTerminalX);

}

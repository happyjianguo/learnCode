package cn.com.jansh.mapper.wsfdn;

import cn.com.jansh.entity.wsfdn.CfPhoneNoInfoEntity;

/**
 * 手机归属地Mapper
 * 
 * @author gll
 *
 */
public interface CfPhoneNoInfoMapper {

	/**
	 * 将查询出的归属地记录保存
	 * 
	 * @param cfHomeownershipEntity
	 */
	public void save(CfPhoneNoInfoEntity phone);

	/**
	 * 通过手机号码在数据库中查询归属地
	 * 
	 * @param phone
	 * @return
	 */
	public CfPhoneNoInfoEntity queryByPhone(String phone);
}

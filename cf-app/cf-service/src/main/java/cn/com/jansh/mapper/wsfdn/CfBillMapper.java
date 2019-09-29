package cn.com.jansh.mapper.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfOfbillEntity;
/**
 * 对账Mapper
 * @author gll
 *
 */
public interface CfBillMapper {

	/**
	 * 欧飞对账查询
	 * @param cfOfbillEntity
	 * @return
	 */
	public List<CfOfbillEntity> query(CfOfbillEntity cfOfbillEntity);
}

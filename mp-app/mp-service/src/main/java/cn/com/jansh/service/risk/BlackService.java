package cn.com.jansh.service.risk;

import java.text.ParseException;
import java.util.List;

import cn.com.jansh.entity.risk.BlacklistEntity;

/**
 * 黑名单service
 * @author gll
 *
 */
public interface BlackService {

	/**
	 * 根据查询条件查询黑名单
	 * @param blacklistEntity
	 * @return
	 */
	public List<BlacklistEntity> selectByOne(BlacklistEntity blacklistEntity);

	/**
	 * 根据黑名单值和活动查询查询黑名单
	 * @param blackEntity
	 * @return
	 */
	public BlacklistEntity selectBlackByValueAndAction(BlacklistEntity blackEntity);

	/**
	 * 新增黑名单
	 * @param modelToEntity
	 * @throws ParseException 
	 */
	public void insertBlack(BlacklistEntity blackEntity) throws ParseException;

	/**
	 * 通过黑名单ID查询黑名单
	 * @param blackid
	 * @return
	 * @throws ParseException 
	 */
	public BlacklistEntity selectBlackByBlackId(String blackid) throws ParseException;

	/**
	 * 修改数据
	 * @param modelToEntity
	 * @throws ParseException 
	 */
	public void updateblack(BlacklistEntity blackEntity) throws ParseException;

	/**
	 * 删除数据
	 * @param blackid
	 */
	public void deleteblack(String blackid);

}

package cn.com.jansh.service.wsfdn;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfProvinceEntity;

/**
 * 省份Service
 * @author duanmuyn
 *
 */
public interface CfProvinceService {
	/**
	 * 查询省份列表
	 * @return
	 */
	public List<CfProvinceEntity> query();
	/**
	 * 根据id查询省份
	 */
	public CfProvinceEntity selectPnameBypno(String pno);
}

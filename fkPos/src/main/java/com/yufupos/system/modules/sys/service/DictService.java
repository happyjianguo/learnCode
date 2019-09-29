/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.common.utils.CacheUtils;
import com.yufupos.system.modules.sys.dao.DictDao;
import com.yufupos.system.modules.sys.entity.Dict;
import com.yufupos.system.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author king
 * @version 2014-05-16
 */
@Service("dictService")
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> implements DictServiceIntf {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public List<Dict> findAllList(Dict dict) {
		return dao.findAllList(dict);
	}

	@Override
	public void deleteOut(Dict dict) {		
		super.delete(dict);
	}

	@Override
	public void saveOut(Dict dict) {
		super.save(dict);		
	}
	
	public Page<Dict> findPage(Page<Dict> page, Dict dict) {
		return super.findPage(page, dict);
	}

	public Dict get(String id){
		return super.get(id);
	}
	
	@Transactional(readOnly = false)
	public List<Dict> findList(Dict dict) {
		return dao.findList(dict);
	}
}

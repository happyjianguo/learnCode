package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.modules.sys.entity.Dict;

public interface DictServiceIntf {
	public List<Dict> findAllList(Dict dict);

	public List<String> findTypeList();

	public void saveOut(Dict dict);

	public void deleteOut(Dict dict);
	
	public Page<Dict> findPage(Page<Dict> page, Dict dict);
	
	public Dict get(String id);
	
	public List<Dict> findList(Dict dict);
}

package com.yufupos.system.modules.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.dao.MerchTerminalAccNoDao;
import com.yufupos.system.modules.pos.entity.MerchTerminalAccNo;

/**
 * 商户终端入账银行账号信息Service
 * @author ZQK
 * @version 2018-07-03
 */
@Service
@Transactional(readOnly = true)
public class MerchTerminalAccNoService extends CrudService<MerchTerminalAccNoDao, MerchTerminalAccNo> {
	
	public MerchTerminalAccNo get(MerchTerminalAccNo merchTerminalAccNo) {
		return super.get(merchTerminalAccNo);
	}
	
	public List<MerchTerminalAccNo> findList(MerchTerminalAccNo merchTerminalAccNo) {
		return super.findList(merchTerminalAccNo);
	}
	
	public Page<MerchTerminalAccNo> findPage(Page<MerchTerminalAccNo> page, MerchTerminalAccNo merchTerminalAccNo) {
		return super.findPage(page, merchTerminalAccNo);
	}
	
	@Transactional(readOnly = false)
	public void save(MerchTerminalAccNo merchTerminalAccNo) {
		super.save(merchTerminalAccNo);
	}
	
}
package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.modules.cortexs.dao.OpencrdbatchDao;
import cn.yufu.system.modules.cortexs.entity.Opencrdbatch;

/**
 * 订单Service
 * @author ZQK
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class OpencrdbatchService extends CrudService<OpencrdbatchDao, Opencrdbatch>{
	
	@Autowired
	private OpencrdbatchDao opencrdbatchDao;
	
	public Opencrdbatch get(String id) {
		return super.get(id);
	}
	
	public List<Opencrdbatch> findList(Opencrdbatch opencrdbatch) {
		return super.findList(opencrdbatch);
	}
	
	public Page<Opencrdbatch> findPage(Page<Opencrdbatch> page, Opencrdbatch opencrdbatch){
		return super.findPage(page, opencrdbatch);
	}
	
	/**
	 * 获取操作员list
	 */
	public List<String> getOperator() {
		return opencrdbatchDao.getOperator();
	}
	
	/**
	 * 根据操作员获取订单list
	 */
	public List<String> getOrderByOper(String oper) {
		return opencrdbatchDao.getOrderByOper(oper);
	}
	
	/**
	 * 根据操作员获取订单list(去重)
	 */
	public List<String> getOrderByOperDistinct(String oper) {
		return opencrdbatchDao.getOrderByOperDistinct(oper);
	}
	
}

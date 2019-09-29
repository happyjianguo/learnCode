package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.cortexs.dao.CrdfeeruleDao;
import cn.yufu.system.modules.cortexs.entity.Crdfeerule;

/**
 * 扣款费率管理DAO接口
 * @author ZQK
 * @version 2017-07-16
 */
@Service
@Transactional(readOnly = true)
public class CrdfeeruleService extends CrudService<CrdfeeruleDao, Crdfeerule>{
	
	@Autowired
	private CrdfeeruleDao crdfeeruleDao;
	
	public Crdfeerule get(String crdfeeruleId) {
		return super.get(crdfeeruleId);
	}
	
	public List<Crdfeerule> getDateByIID(Crdfeerule crdfeerule) {
		return crdfeeruleDao.getDateByIID(crdfeerule);
	}
	
	public Page<Crdfeerule> findPage(Page<Crdfeerule> page, Crdfeerule crdfeerule){
		return super.findPage(page, crdfeerule);
	}
	
	@Transactional(readOnly = false)
	public void save(Crdfeerule crdfeerule){
		if (crdfeerule.getCrdfeeruleId() != null && StringUtils.isNotBlank(crdfeerule.getCrdfeeruleId()+"")) {
			//更新
			crdfeerule.setId(crdfeerule.getIid());
		}else{
			//执行插入
			crdfeerule.setId("");
			crdfeerule.setAdddate(DateUtils.getDate("yyyyMMdd"));
		}
		crdfeerule.setUpdatedate(DateUtils.getDate("yyyyMMdd"));
		super.save(crdfeerule);
	}
	
	public Crdfeerule selectByIIDAndTrueFlag(Crdfeerule crdfeerule) {
		return crdfeeruleDao.selectByIIDAndTrueFlag(crdfeerule);
	}
	
}

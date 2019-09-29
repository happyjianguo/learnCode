package cn.yufu.system.modules.cortexs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.service.CrudService;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.IdGen;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.cortexs.dao.CrdopenDao;
import cn.yufu.system.modules.cortexs.entity.Crdopen;

/**
 * 民生订单码表DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class CrdopenService extends CrudService<CrdopenDao, Crdopen>{

	@Autowired
	private CrdopenDao crdopenDao;
	
	public Crdopen get(String id) {
		return super.get(id);
	}
	
	public List<Crdopen> findList(Crdopen crdopen) {
		return super.findList(crdopen);
	}
	
	public Page<Crdopen> findPage(Page<Crdopen> page, Crdopen crdopen){
		return super.findPage(page, crdopen);
	}
	
	//得到是民生订单的操作员List
	public List<String> getOperator() {
		return crdopenDao.getOperator();
	}
	
	@Transactional(readOnly = false)
	public void saveCrdopen(Crdopen crdopen) throws Exception{
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		crdopen.setCreateTime(date);
		crdopen.setUpdateTime(date);
		if (StringUtils.isNotBlank(crdopen.getOpenId())) {
			//更新
			crdopenDao.update(crdopen);
		}else {
			//插入
			//判断是否存在
			List<Crdopen> oneDate = crdopenDao.getOneDate(crdopen);
			if (oneDate == null || oneDate.size() == 0) {
				crdopen.setOpenId(IdGen.uuid());
				crdopenDao.insert(crdopen);
			}else {
				System.out.println("-----民生订单已存在-----");
				throw new Exception("民生订单已存在");
			}
		}
	}
}

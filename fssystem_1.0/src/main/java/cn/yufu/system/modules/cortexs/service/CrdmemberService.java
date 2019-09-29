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
import cn.yufu.system.modules.cortexs.dao.CrdmemberDao;
import cn.yufu.system.modules.cortexs.entity.Crdmember;

/**
 * 会员(卡BIN)信息DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class CrdmemberService extends CrudService<CrdmemberDao, Crdmember>{
	
	@Autowired
	private CrdmemberDao crdmemberDao;
	
	public Crdmember get(String id) {
		return super.get(id);
	}
	
	/**
	 * 获取会员名称列表
	 * */
	public List<String> getMember() {
		return crdmemberDao.getMember();
	}
	
	public List<Crdmember> findList(Crdmember crdmember) {
		return super.findList(crdmember);
	}
	
	public Page<Crdmember> findPage(Page<Crdmember> page, Crdmember crdmember){
		return super.findPage(page, crdmember);
	}
	
	@Transactional(readOnly = false)
	public void saveCrdmember(Crdmember crdmember) throws Exception{
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		crdmember.setCreateTime(date);
		crdmember.setUpdateTime(date);
		if (StringUtils.isNotBlank(crdmember.getMemberId())) {
			//更新
			crdmemberDao.update(crdmember);
		}else {
			//插入
			//判断是否存在
			List<Crdmember> oneData = crdmemberDao.getOneData(crdmember);
			if (oneData == null || oneData.size() == 0) {
				crdmember.setMemberId(IdGen.uuid());
				crdmemberDao.insert(crdmember);
			}else {
				System.out.println("-----会员信息已存在-----");
				throw new Exception();
			}
		}
	}
}

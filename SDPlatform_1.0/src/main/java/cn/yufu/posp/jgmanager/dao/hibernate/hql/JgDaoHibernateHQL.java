package cn.yufu.posp.jgmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public interface JgDaoHibernateHQL

{
	// 类别
	// 查找机构总数
	public int queryJgCount(JgModel newQueryModel, UserData ud) throws OAException;

	// 查找机构
	public List queryJg(JgModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除机构
	public void deleteJg(List newKeys, UserData ud) throws OAException;

	// 删除用户
	public void deleteUser(List<String> newKeys, UserData ud) throws OAException;

	// 新建机构
	public void createJg(JgModel newModel, UserData ud) throws OAException;

	// 按KEY查找机构
	public JgModel queryJgByKey(String newKey, UserData ud) throws OAException;

	// 保存机构
	public void saveJg(JgModel newModel, UserData ud) throws OAException;

}

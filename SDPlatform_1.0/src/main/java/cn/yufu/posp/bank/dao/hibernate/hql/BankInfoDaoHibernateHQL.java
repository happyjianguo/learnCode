package cn.yufu.posp.bank.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface BankInfoDaoHibernateHQL {

	// 查找记录总数
	public int querySum(BankInfoId queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(BankInfoId newQueryModel, int startIndex,int maxresults, String sortfield, String sortType, UserData ud)throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public BankInfoId findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(BankInfoId newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(BankInfoId newModel, UserData ud) throws OAException;

	// 名称
	public List<Banktype> findMerchantName(UserData ud);

	// 根据银行类型编号找到银行类型名称
	public String findTypeNameById(String bankType, UserData ud);
}

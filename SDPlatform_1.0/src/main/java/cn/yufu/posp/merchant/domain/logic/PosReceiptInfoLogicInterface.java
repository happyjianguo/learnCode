package cn.yufu.posp.merchant.domain.logic;

import java.util.HashMap;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

public interface PosReceiptInfoLogicInterface {

	// 查找所有记录
	public PageInfoModel queryAll(PosReceiptInfoModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;



	// 显示一条记录
	public HashMap findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(PosReceiptInfoModel newModel, UserData ud) throws OAException;


		
}

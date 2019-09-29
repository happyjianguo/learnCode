package cn.yufu.posp.jgmanager.domain.logic;

import java.util.HashMap;
import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public interface JgLogicInterface {

	// ���
	// ���һ���
	public PageInfoModel queryJg(JgModel newQueryModel, PageInfoModel newPageInfoModel, UserData ud) throws OAException;

	// ɾ������
	public void deleteJg(List newKeys, UserData ud) throws OAException;

	// �����µĻ���
	public void createJg(JgModel newModel, UserData ud) throws OAException;

	// ��key�����
	public HashMap queryJgByKey(String newKey, UserData ud) throws OAException;

	// �������
	public void saveJg(JgModel newModel, UserData ud) throws OAException;

}

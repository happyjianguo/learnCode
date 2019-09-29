package cn.yufu.posp.exportAndimport.utils;

import cn.yufu.posp.common.common.exception.OAException;

public interface ExportAndImportDate {
	
	/**
	 * �ѵ����ļ����������鵵�ļ�
	 * @param sourceM�������ļ�����
	 * @param sourceS�������ļ��ӱ�
	 * @param refId�����õ����
	 * @throws OAException
	 */
	public void ExportDateFromDawjtoDgdwj(final String sourceM,final String sourceS, final String sourceSubM,final String refId) throws OAException;
	
	/**
	 * �Ѵ��鵵�ļ����뵽�����ļ���
	 * @param targetM��Ŀ�������
	 * @param targetS��Ŀ����ӱ�
	 * @param refId�����
	 * @throws OAException
	 */
	public void ImportDateFromDgdwjToDawj(String targetM,String targetS,String refId,String dgdwjbh,String targetSub)throws OAException;
	
}

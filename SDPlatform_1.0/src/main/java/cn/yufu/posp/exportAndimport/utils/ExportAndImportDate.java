package cn.yufu.posp.exportAndimport.utils;

import cn.yufu.posp.common.common.exception.OAException;

public interface ExportAndImportDate {
	
	/**
	 * 把档案文件导出到待归档文件
	 * @param sourceM：档案文件主表
	 * @param sourceS：档案文件子表
	 * @param refId：引用的外键
	 * @throws OAException
	 */
	public void ExportDateFromDawjtoDgdwj(final String sourceM,final String sourceS, final String sourceSubM,final String refId) throws OAException;
	
	/**
	 * 把待归档文件导入到档案文件表
	 * @param targetM：目标表主表
	 * @param targetS：目标表子表
	 * @param refId：外键
	 * @throws OAException
	 */
	public void ImportDateFromDgdwjToDawj(String targetM,String targetS,String refId,String dgdwjbh,String targetSub)throws OAException;
	
}

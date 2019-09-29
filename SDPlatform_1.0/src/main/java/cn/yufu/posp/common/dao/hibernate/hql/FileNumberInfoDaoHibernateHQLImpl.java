package cn.yufu.posp.common.dao.hibernate.hql;

import java.util.List;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.FileNumberInfoModel;
import cn.yufu.posp.common.domain.model.UserData;



public class FileNumberInfoDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements FileNumberInfoDaoHibernateHQL
{
    /**
     * 
     * @return 返回收文编号。
     * @throws OAException -
     *             如果数据访问出现异常时抛出。
     * @param yearNum -
     *            当前年；ud - 登录用户的信息。
     */
    public String getNonceYear(int yearNum, UserData ud ,String fileType) throws OAException
    {
        List list = null;
        long nonceNum;
        String num = null;
        try
        {
            daoLog
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：根据当前年号返回收文编号。");

            String sql = "from FileNumberInfoModel fn where fn.fileType='001' and fn.yearNum ="
                    + yearNum + " and fn.templateId = " + fileType ;

            list = findByHQL(sql, ud);
            if (list.size() <= 0)
            {
                
                createFileNumberInfo(yearNum, ud,fileType);
                int nonce = 1;
                num = "" + yearNum + nonce;
            }
            else
            {
                //int nonceNum;
                FileNumberInfoModel fn = (FileNumberInfoModel) list.get(0);
                nonceNum = fn.getNonceNum() + 1;

                fn.setNonceNum(nonceNum);
                update(fn, ud);
                num = "" + yearNum + nonceNum;
            }

            daoLog
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：根据当前年号返回收文编号。");
        }
        catch (RuntimeException e)
        {
            exceptionLog
                    .error("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：根据当前年号返回收文编号。"
                            + e.getMessage() + ")。");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();

            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }

        return num;
    }

    /**
     * 创建新的文号信息
     * 
     * @throws OAException -
     *             如果数据访问出现异常时抛出。
     * @param yearNum -
     *            当前年；ud - 登录用户的信息。
     */

    public void createFileNumberInfo(int yearNum, UserData ud , String fileType)
            throws OAException
    {

        try
        {
            daoLog
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：创建新的文号信息。");
            int i = 1;
            FileNumberInfoModel fileNumberInfo = new FileNumberInfoModel();
            fileNumberInfo.setFileType("001");
            fileNumberInfo.setPrefix("empty");
            fileNumberInfo.setYearNum(yearNum);
            fileNumberInfo.setInceptNum(0);
            fileNumberInfo.setNonceNum(1);
            fileNumberInfo.setTemplateId((new Long(fileType)).longValue());
            save(fileNumberInfo, ud);
            daoLog
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：创建新的文号信息。");

        }
        catch (RuntimeException e)
        {
            exceptionLog
                    .error("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：创建新的文号信息出现异常！("
                            + e.getMessage() + ")。");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();

            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
    }

    /*
     *  
     * 根据模版编号和文件类型得到文号信息
     */
	public FileNumberInfoModel getFileNumberInfo(long tempId, String fileType,int nowYear, UserData ud) throws OAException 
	{
		FileNumberInfoModel reModel = null;
		try
        {
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：根据模版编号和文件类型得到文号信息。");
            String hql = "from FileNumberInfoModel f where f.templateId="+tempId+" and f.fileType='"+fileType+"' and f.yearNum="+nowYear;
    		List list = findByHQL(hql,ud);		
    		if(list.size()>0)
    			reModel = (FileNumberInfoModel) list.get(0);           
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：根据模版编号和文件类型得到文号信息。");
        }
        catch (RuntimeException e)
        {
            exceptionLog.error("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)开始调用：根据模版编号和文件类型得到文号信息出现异常！("+ e.getMessage() + ")。");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();
            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
		return reModel;
	}

	/*
     *  
     * 创建文号信息
     */
	public void createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud) throws OAException {
		try
        {
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)开始调用：创建文号信息。");
            save(newModel,ud);
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)开始调用：创建文号信息。");
        }
        catch (RuntimeException e)
        {
            exceptionLog.error("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)开始调用：创建文号信息！("+ e.getMessage() + ")。");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();
            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
		
	}
	/*
	 * 保存发文文号
	 */
	public void saveSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)throws OAException{
		try
        {
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)开始调用：保存发文文号。");
            saveOrUpdate(newModel,ud);         
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)开始调用：保存发文文号。");
        }
        catch (RuntimeException e)
        {
            exceptionLog.error("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)开始调用：保存发文文号！("+ e.getMessage() + ")。");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();
            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
	}
}

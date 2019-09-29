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
     * @return �������ı�š�
     * @throws OAException -
     *             ������ݷ��ʳ����쳣ʱ�׳���
     * @param yearNum -
     *            ��ǰ�ꣻud - ��¼�û�����Ϣ��
     */
    public String getNonceYear(int yearNum, UserData ud ,String fileType) throws OAException
    {
        List list = null;
        long nonceNum;
        String num = null;
        try
        {
            daoLog
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã����ݵ�ǰ��ŷ������ı�š�");

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
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã����ݵ�ǰ��ŷ������ı�š�");
        }
        catch (RuntimeException e)
        {
            exceptionLog
                    .error("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã����ݵ�ǰ��ŷ������ı�š�"
                            + e.getMessage() + ")��");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();

            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }

        return num;
    }

    /**
     * �����µ��ĺ���Ϣ
     * 
     * @throws OAException -
     *             ������ݷ��ʳ����쳣ʱ�׳���
     * @param yearNum -
     *            ��ǰ�ꣻud - ��¼�û�����Ϣ��
     */

    public void createFileNumberInfo(int yearNum, UserData ud , String fileType)
            throws OAException
    {

        try
        {
            daoLog
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã������µ��ĺ���Ϣ��");
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
                    .info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã������µ��ĺ���Ϣ��");

        }
        catch (RuntimeException e)
        {
            exceptionLog
                    .error("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã������µ��ĺ���Ϣ�����쳣��("
                            + e.getMessage() + ")��");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();

            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
    }

    /*
     *  
     * ����ģ���ź��ļ����͵õ��ĺ���Ϣ
     */
	public FileNumberInfoModel getFileNumberInfo(long tempId, String fileType,int nowYear, UserData ud) throws OAException 
	{
		FileNumberInfoModel reModel = null;
		try
        {
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã�����ģ���ź��ļ����͵õ��ĺ���Ϣ��");
            String hql = "from FileNumberInfoModel f where f.templateId="+tempId+" and f.fileType='"+fileType+"' and f.yearNum="+nowYear;
    		List list = findByHQL(hql,ud);		
    		if(list.size()>0)
    			reModel = (FileNumberInfoModel) list.get(0);           
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã�����ģ���ź��ļ����͵õ��ĺ���Ϣ��");
        }
        catch (RuntimeException e)
        {
            exceptionLog.error("FileNumberInfoDaoHibernateHQLImpl.getNonceYear(Integer yearNum, UserData ud)��ʼ���ã�����ģ���ź��ļ����͵õ��ĺ���Ϣ�����쳣��("+ e.getMessage() + ")��");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();
            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
		return reModel;
	}

	/*
     *  
     * �����ĺ���Ϣ
     */
	public void createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud) throws OAException {
		try
        {
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)��ʼ���ã������ĺ���Ϣ��");
            save(newModel,ud);
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)��ʼ���ã������ĺ���Ϣ��");
        }
        catch (RuntimeException e)
        {
            exceptionLog.error("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)��ʼ���ã������ĺ���Ϣ��("+ e.getMessage() + ")��");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();
            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
		
	}
	/*
	 * ���淢���ĺ�
	 */
	public void saveSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)throws OAException{
		try
        {
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)��ʼ���ã����淢���ĺš�");
            saveOrUpdate(newModel,ud);         
            daoLog.info("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)��ʼ���ã����淢���ĺš�");
        }
        catch (RuntimeException e)
        {
            exceptionLog.error("FileNumberInfoDaoHibernateHQLImpl.createSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)��ʼ���ã����淢���ĺţ�("+ e.getMessage() + ")��");
            if (exceptionLog.isDebugEnabled())
                e.printStackTrace();
            throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
        }
	}
}

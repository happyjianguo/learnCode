package cn.yufu.posp.common.dao.hibernate.hql;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.FileNumberInfoModel;
import cn.yufu.posp.common.domain.model.UserData;

public interface FileNumberInfoDaoHibernateHQL
{
    
    public String getNonceYear(int yearNum, UserData ud,String type) throws OAException;
    
    public void createFileNumberInfo(int yearNum, UserData ud,String str) throws OAException;
    
    public FileNumberInfoModel getFileNumberInfo(long tempId,String fileType,int nowYear,UserData ud)throws OAException;
    
    public void saveSendFileNumInfo(FileNumberInfoModel newModel, UserData ud)throws OAException;
    
    public void createSendFileNumInfo(FileNumberInfoModel newModel,UserData ud)throws OAException;
}

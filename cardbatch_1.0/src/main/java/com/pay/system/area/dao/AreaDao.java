package com.pay.system.area.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.area.bean.AreaBean;
import com.pay.util.DbExec;

/**
 * 
 * @TODO ������Ĳ�ѯ���� 
 *
 * @author zl
 * @created on 2009-11-10
 * @version 1.0
 */
public class AreaDao {
    private static final Logger logger = Logger.getLogger(AreaDao.class);
    private String sql = "";
    private int result = 0;

    /**
     * 
     * @TODO ������ʡ����Ϣ
     *
     * @return ��������б�
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-11-10
     * LastModified:
     * History:
     * </pre>
     */
    public List getAllProvinces() {
        sql = "select * from posp_sys_province order by prov_id";
        AreaBean areaBean = null;
        List list = new ArrayList();
        try {
            Vector areaVector = DbExec.execQuery(sql);
            if (areaVector != null && !areaVector.isEmpty()) {
                for (int i = 0; i < areaVector.size(); i++) {
                	areaBean = new AreaBean((HashMap) areaVector.get(i));
                    list.add(areaBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:", e);
        }catch (Exception e) {
            logger.error("Exception:", e);
        }
        return list;
    }
   
    /**
     * 
     * @TODO ����ʡ�ݱ�Ż����س�����Ϣ
     *
     * @param prov_id
     * @return ��������б�
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-11-10
     * LastModified:
     * History:
     * </pre>
     */
    public List getAreaByProvId(String prov_id) {
        sql = "select * from posp_sys_areacode where prov_id=? order by area_code";
        List condList = new ArrayList();
        List list = new ArrayList();
        condList.add(prov_id);
        AreaBean areaBean = null;
        try {
            Vector areaVector = DbExec.execQuery(sql, condList);
            if (areaVector != null && !areaVector.isEmpty()) {
                for (int i = 0; i < areaVector.size(); i++) {
                	areaBean = new AreaBean((HashMap) areaVector.get(i));
                    list.add(areaBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + prov_id + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + prov_id + "|", e);
        }
        return list;
    }
   
}

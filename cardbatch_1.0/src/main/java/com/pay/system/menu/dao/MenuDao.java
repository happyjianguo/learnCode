package com.pay.system.menu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.menu.bean.MenuBean;
import com.pay.util.DbExec;

/**
 * 
 * @TODO 
 *
 * @author �Ʊ�
 * @created on 2007-12-19  14:32:19
 * @version 1.0
 */
public class MenuDao {  

    private static final Logger logger = Logger.getLogger(MenuDao.class);
    private String sql = "";
    private int result = 0;

    

    /**
     * 
     * @TODO ����Ȩ�ޱ�Ų�ѯ���Ȩ����Ϣ(�޸�ǰʹ��)
     *
     * @param itemno
     * @return Ҫ��ѯ��Ȩ�޶���
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  10:25:56
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public MenuBean getMenuByMenuno(String menuno) {
        sql = "select * from Monmenuinfo where menu_no=?";
        List condList = new ArrayList();
        condList.add(menuno);
        MenuBean menuBean = null;
        try {
            Vector menuVector = DbExec.execQuery(sql, condList);
            if (menuVector != null && !menuVector.isEmpty()) {
            	menuBean = new MenuBean((HashMap) menuVector.get(0));
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + menuno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + menuno + "|", e);
        }
        return menuBean;
    }

    

    /**
     * 
     * @TODO �������Ȩ����Ϣ�б� 
     *
     * @return Ȩ����Ϣ�б�
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  10:20:43
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public List getMenuList() { 
        sql = "select * from Monmenuinfo order by menu_no";
        List list = new ArrayList();
        try {
            Vector menuVector = DbExec.execQuery(sql);
            if (menuVector != null && !menuVector.isEmpty()) {
                for (int i = 0; i < menuVector.size(); i++) {
                    MenuBean menuBean = new MenuBean((HashMap) menuVector.get(i));
                    list.add(menuBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }

    
}

/**
 *����:com.pay.batch.bflowlog.dao
 *����:package com.pay.batch.bflowlog.dao;
 */
package com.pay.batch.bflowlog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.batch.bflowlog.bean.BFlowLogBean;
import com.pay.batch.bflowlog.bean.CrdformatMapBean;
import com.pay.batch.bflowlog.bean.IidPinChkBean;
import com.pay.batch.bflowlog.bean.SixBFlowLogBean;
import com.pay.batch.bflowlog.struts.form.CrdformatMapForm;
import com.pay.batch.bflowlog.struts.form.IidPinChkForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * IidPinChkDao.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��7��4��
 * ����:�����������
 */
public class IidPinChkDao {
	private static final Logger logger = Logger.getLogger(IidPinChkDao.class);
	private int result = 0;	
	/**
	 * ��ѯ��ҳ������
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(IidPinChkForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql = "select count(*) numbers from iid_pin_chk t where 1=1";
		
		if(form!=null){
			if(form.getQueryiid()!=null&&form.getQueryiid().trim().length()>0){
				sql+="and iid= '"+form.getQueryiid().trim()+"' ";
			}
			if(form.getIvrpinfalg()!=null&&form.getIvrpinfalg().trim().length()>0){
				sql+=" and ivrpinfalg= '"+form.getIvrpinfalg().trim()+"' ";
			}
			if(form.getPospinfalg()!=null&&form.getPospinfalg().trim().length()>0){
				sql+=" and pospinfalg= '"+form.getPospinfalg().trim()+"' ";
			}
			if(form.getOper()!=null&&form.getOper().trim().length()>0){
				sql+=" and oper= '"+form.getOper().trim()+"' ";
			}			
		}
		
		try {		
			Vector vector = DbExec.execQuery(sql,param);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getCount", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException--getCount", e);
		}
		return count;
	}
	
	@SuppressWarnings("rawtypes")
	public List getIidPinChkList(PageBean pageBean, IidPinChkForm iidPinChkForm) {
		List<String> param = new ArrayList<String>();
		List<IidPinChkBean> beans = new ArrayList<IidPinChkBean>();
		String sql  ="select m.iid, m.ivrpinfalg, m.pospinfalg, m.adddatetime, m.oper, m.reserved1, m.reserved2, m.reserved3, m.reserved4 from iid_pin_chk m where 1=1";
		sql = getSql(iidPinChkForm,sql,param) + " order by m.iid asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			IidPinChkBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new IidPinChkBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getCrdformatMapList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getCrdformatMapList", e);
		}
		return beans;
	}
	
	public int delIidPinChk(String iid) {
		if(iid==null||"".equals(iid)){
			return -1;
		}
		String sql = " delete from iid_pin_chk where iid='" + iid +"'";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updCrdformatMap", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updCrdformatMap", e);
			result = -1;
		}
		return result;
	}

	public int addIidPinChk(IidPinChkBean iidPinChkBean) {
		String sql = "insert into iid_pin_chk(iid, ivrpinfalg, pospinfalg, adddatetime,oper) values('"
				+ iidPinChkBean.getIid()
				+ "','"
				+ iidPinChkBean.getIvrpinfalg()
				+ "','"
				+ iidPinChkBean.getPospinfalg()
				+ "','"
				+ iidPinChkBean.getAdddatetime()
				+ "','"
				+ iidPinChkBean.getOper() + "')";

		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addIidPinChk", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addIidPinChk", e);
			result = -1;
		}
		return result;
	}
	/**
	 * ����������������ָ����list
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CrdformatMapBean> getCrdformatMapHasNeedDtList(CrdformatMapForm form){
		List<String> param = new ArrayList<String>();
		List<CrdformatMapBean> beans = new ArrayList<CrdformatMapBean>();
		String sql  ="select f.iid||'|'||p.usrdata2 as iid,  f.iid||'|'||p.descr as iid_desc from crdformat f,crdproduct p where f.id=p.crdformat_id ";
		Vector vector = null;
		try {
			vector = DbExec.execCardQuery(sql, param);
			CrdformatMapBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new CrdformatMapBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getCrdformatMapList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getCrdformatMapList", e);
		}
		return beans;
	}
	
	/**
	 * ���ݲ�ѯ�������� ��Ӧ�Ĳ�ѯ���
	 * @param form
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(IidPinChkForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getQueryiid()!=null&&form.getQueryiid().trim().length()>0){
				sql+=" and m.iid= '"+form.getQueryiid().trim()+"' ";
			}
			if(form.getIvrpinfalg()!=null&&form.getIvrpinfalg().trim().length()>0){
				sql+=" and m.ivrpinfalg= '"+form.getIvrpinfalg().trim()+"' ";
			}
			if(form.getPospinfalg()!=null&&form.getPospinfalg().trim().length()>0){
				sql+=" and m.pospinfalg= '"+form.getPospinfalg().trim()+"' ";
			}
			if(form.getOper()!=null&&form.getOper().trim().length()>0){
				sql+=" and m.oper= '"+form.getOper().trim()+"' ";
			}
		}
		return sql;
	}
	
	public CrdformatMapBean getCrdformatMapByID(String iid,String func_flag){		
		String sql  ="select m.iid,m.iid || '|' || p.descr as iid_desc,m.iid_map,t.batchname as iid_map_desc,m.func_flag, m.isuse,m.iid||'|'||m.func_flag as iid_func_flag,m.need_dt "
			+" from crdformat f, crdproduct p, crdformat_map m left join  "
			+" (select a.iid as batchflag, a.iid || '|' || b.descr as batchname from crdformat a, crdproduct b where a.id = b.crdformat_id) t "
			+" on m.iid_map = t.batchflag "
			+" where  m.iid = f.iid  and f.id = p.crdformat_id and m.iid=? and m.func_flag=? ";
		List param = new ArrayList();
		param.add(iid);
		param.add(func_flag);
		CrdformatMapBean CrdformatMapBean = null;
		try {
			Vector vector = DbExec.execCardQuery(sql, param);
			if(vector != null && !vector.isEmpty()){
				CrdformatMapBean = new CrdformatMapBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return CrdformatMapBean;
	}

	public List<BFlowLogBean> getCrdformatMapListByForm(String funcFlag){
		List<BFlowLogBean> beans = new ArrayList<BFlowLogBean>();
		String sql  ="select m.iid||'|'||m.need_dt as batchflag,m.iid || '|' || p.descr as batchname "
			+" from crdformat f, crdproduct p, crdformat_map m left join  "
			+" (select a.iid as batchflag, a.iid || '|' || b.descr as batchname from crdformat a, crdproduct b where a.id = b.crdformat_id) t "
			+" on m.iid_map = t.batchflag "
			+" where  m.iid = f.iid  and f.id = p.crdformat_id and m.isuse='1' ";
			if(funcFlag!=null&&!"".equals(funcFlag)){
				sql=sql+" and m.func_flag='"+funcFlag.trim()+"' ";
			}
			
		Vector vector = null;
		try {
			vector = DbExec.execCardQuery(sql, null);
			BFlowLogBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new BFlowLogBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getCrdformatMapList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getCrdformatMapList", e);
		}
		return beans;
	}
	
	    //����������������batchflag��0������Ч�ڲ��
		public List<SixBFlowLogBean> getSixCrdformatMapListByForm(String funcFlag){
			List<SixBFlowLogBean> beans = new ArrayList<SixBFlowLogBean>();
			String sql  ="select w.iid||'|'||'0' as batchflag,w.iid || '|' || w.describe as batchname  from mkcrd_iid_sixpin w"
				+" where  1=1";
				if(funcFlag!=null&&!"".equals(funcFlag)){
					sql=sql+" and w.flag='"+funcFlag.trim()+"' ";
				}
				
				Vector vector = null;
				try {
					vector = DbExec.execCardQuery(sql, null);
					SixBFlowLogBean bean = null;
					if (vector != null && !vector.isEmpty()) {
						int size = vector.size();
						for(int i=0;i<size;i++){
							HashMap map = (HashMap) vector.get(i);
							bean = new SixBFlowLogBean(map);
							beans.add(bean);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
		            logger.error("SQLException--getSixCrdformatMapListByForm", e);
				} catch (Exception e) {
					e.printStackTrace();
		            logger.error("Exception--getSixCrdformatMapListByForm", e);
				}
				return beans;
			}
		
	
	
	
	/**
	 * ���
	 * @param crdformatMapBean
	 * @return
	 */
	public int addCrdformatMap(CrdformatMapBean crdformatMapBean) {
		String sql = "insert into crdformat_map(iid, iid_map, func_flag, isuse,need_dt) values('"
				+ crdformatMapBean.getIid()
				+ "','"
				+ crdformatMapBean.getIid_map()
				+ "','"
				+ crdformatMapBean.getFunc_flag()
				+ "','"
				+ crdformatMapBean.getIsuse()
				+ "','"
				+ crdformatMapBean.getNeedDt() + "')";

		try {
			result = DbExec.execCardUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addCrdformatMap", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addCrdformatMap", e);
			result = -1;
		}
		return result;
	}
	
	public int updCrdformatMap(CrdformatMapBean crdformatMapBean) {
		String sql = " update crdformat_map set isuse='" + crdformatMapBean.getIsuse() + "' ";
		if (crdformatMapBean.getIid_map() != null
				&& !"".equals(crdformatMapBean.getIid_map())) {
			sql = sql + ", iid_map='" + crdformatMapBean.getIid_map() + "' ";
		}
		sql = sql + "  where iid = '" + crdformatMapBean.getIid()
				+ "' and func_flag ='" + crdformatMapBean.getFunc_flag() + "'";
		try {
			result = DbExec.execCardUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updCrdformatMap", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updCrdformatMap", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 * ͨ��IID��ȡ��binӳ��iid_map
	 * @param iid
	 * @param func_flag Ĭ����1.����ͬ�����󶨵����⿨���� 
	 * @return
	 */
	public String getIidMapStrByIid(String iid){
		String iidMapStr="";
		if(iid!=null&&!"".equals(iid)&&iid.contains("|")){
			iid=iid.split("\\|")[0].toString();
		}
		String sql  ="select t.batchname as iid_map_desc "
			+" from crdformat_map m left join  "
			+" (select a.iid as batchflag, a.iid || '|' || b.descr as batchname from crdformat a, crdproduct b where a.id = b.crdformat_id) t "
			+" on m.iid_map = t.batchflag "
			+" where  m.iid=? and m.func_flag='1' and m.isuse='1'";
		List param = new ArrayList();
		param.add(iid);
		CrdformatMapBean CrdformatMapBean = null;
		try {
			Vector vector = DbExec.execCardQuery(sql, param);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				iidMapStr = ((String) map.get("iid_map_desc")).trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return iidMapStr;
	}
	
	/**
	 *  �жϿ�BINӳ�������Ƿ��ظ�
	 * @param iid
	 * @param func_flag
	 * @return 0.���ظ���1.�ظ� ��
	 */
	public String checkCrdformatMapPK(String iid,String func_flag){	
		if(iid!=null&&!"".equals(iid)&&iid.contains("|")){
			iid=iid.split("\\|")[0].toString();
		}
		String checkFlag="0";
		String sql  ="select m.iid,m.iid || '|' || p.descr as iid_desc,m.iid_map,t.batchname as iid_map_desc,m.func_flag, m.isuse,m.iid||'|'||m.func_flag as iid_func_flag "
			+" from crdformat f, crdproduct p, crdformat_map m left join  "
			+" (select a.iid as batchflag, a.iid || '|' || b.descr as batchname from crdformat a, crdproduct b where a.id = b.crdformat_id) t "
			+" on m.iid_map = t.batchflag "
			+" where  m.iid = f.iid  and f.id = p.crdformat_id and m.iid=? and m.func_flag=? ";
		List param = new ArrayList();
		param.add(iid);
		param.add(func_flag);
		try {
			Vector vector = DbExec.execCardQuery(sql, param);
			if(vector != null && !vector.isEmpty()){
				 checkFlag="1";
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return checkFlag;
	}
}

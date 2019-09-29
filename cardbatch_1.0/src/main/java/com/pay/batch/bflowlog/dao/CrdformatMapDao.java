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
import com.pay.batch.bflowlog.bean.SixBFlowLogBean;
import com.pay.batch.bflowlog.struts.form.CrdformatMapForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class CrdformatMapDao {
	private static final Logger logger = Logger.getLogger(CrdformatMapDao.class);
	private int result = 0;	
	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(CrdformatMapForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql  ="select count(m.iid) as numbers from crdformat f, crdproduct p, crdformat_map m  left join "
			+" (select a.iid as batchflag, a.iid || '|' || b.descr as batchname from crdformat a, crdproduct b where a.id = b.crdformat_id) t "
			+" on m.iid_map = t.batchflag "
			+" where  m.iid = f.iid  and f.id = p.crdformat_id ";
		sql = getSql(form,sql,param);
		try {		
			Vector vector = DbExec.execCardQuery(sql,param);
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
	
	/**
	 * 根据搜索条件返回指定的list
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CrdformatMapBean> getCrdformatMapList(PageBean pageBean,CrdformatMapForm form){
		List<String> param = new ArrayList<String>();
		List<CrdformatMapBean> beans = new ArrayList<CrdformatMapBean>();
		String sql  ="select m.iid,m.iid || '|' || p.descr as iid_desc,m.iid_map,t.batchname as iid_map_desc,m.func_flag, m.isuse,m.iid||'|'||m.func_flag as iid_func_flag,m.need_dt "
			+" from crdformat f, crdproduct p, crdformat_map m  left join  "
			+" (select a.iid as batchflag, a.iid || '|' || b.descr as batchname from crdformat a, crdproduct b where a.id = b.crdformat_id) t "
			+" on m.iid_map = t.batchflag "
			+" where  m.iid = f.iid  and f.id = p.crdformat_id ";
		sql = getSql(form,sql,param) + " order by m.iid,m.func_flag asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execCardQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execCardQuery(sql, param);
			}
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
	 * 根据搜索条件返回指定的list
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<CrdformatMapBean> getCrdformatMapList(CrdformatMapForm form){
		List<String> param = new ArrayList<String>();
		List<CrdformatMapBean> beans = new ArrayList<CrdformatMapBean>();
		String sql  ="select f.iid,  f.iid||'|'||p.descr as iid_desc from crdformat f,crdproduct p where f.id=p.crdformat_id ";
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
	 * 根据搜索条件返回指定的list
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
	 * 根据查询条件返回 相应的查询语句
	 * @param form
	 * @param sql
	 * @param param
	 * @return
	 */
	private String getSql(CrdformatMapForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getQueryIid()!=null&&form.getQueryIid().trim().length()>0){
				sql+=" and m.iid= '"+form.getQueryIid().trim()+"' ";
			}
			if(form.getQueryFuncFlag()!=null&&form.getQueryFuncFlag().trim().length()>0){
				sql+=" and m.func_flag= '"+form.getQueryFuncFlag().trim()+"' ";
			}
			if(form.getQueryIsuse()!=null&&form.getQueryIsuse().trim().length()>0){
				sql+=" and m.isuse= '"+form.getQueryIsuse().trim()+"' ";
			}
			if(form.getQueryNeedDt()!=null&&form.getQueryNeedDt().trim().length()>0){
				sql+=" and m.need_dt= '"+form.getQueryNeedDt().trim()+"' ";
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
	
	    //特制批量发卡管理：batchflag中0代表有效期不填。
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
		
		public List<SixBFlowLogBean> getSixCrdformatMapListByKabin(String funcFlag,String kabin){
			List<SixBFlowLogBean> beans = new ArrayList<SixBFlowLogBean>();
			String sql  ="select w.iid||'|'||'0' as batchflag,w.iid || '|' || w.describe as batchname  from mkcrd_iid_sixpin w"
				+" where  1=1";
				if(funcFlag!=null&&!"".equals(funcFlag)){
					sql=sql+" and w.flag='"+funcFlag.trim()+"' ";
				}
				if(kabin!=null&&!"".equals(kabin)){
					sql=sql+" and w.iid='"+kabin.trim()+"' ";
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
	 * 添加
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
	 * 通过IID获取卡bin映射iid_map
	 * @param iid
	 * @param func_flag 默认是1.生成同福卡绑定的虚拟卡管理 
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
	 *  判断卡BIN映射主键是否重复
	 * @param iid
	 * @param func_flag
	 * @return 0.不重复；1.重复 。
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
	
	/**
	 * delete crdformat_map
	 * @param iid
	 * @return
	 */
	public int delCrdformatMap(String iid,String funcFlag) {
		if(iid==null||"".equals(iid)||funcFlag==null||"".equals(funcFlag)){
			return -1;
		}
		String sql = " delete from crdformat_map where iid='" + iid + "' and func_flag='"+funcFlag+"' ";
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
}

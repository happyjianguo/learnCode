package com.pay.sysParameter.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.struts.form.SysParameterForm;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class SysParameterDao {
	private static final Logger logger = Logger.getLogger(SysParameterDao.class);
	private int result = 0;	
	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(SysParameterForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql  =" select count(1) as numbers from sys_parameter "
			+" where 1=1 ";
		sql = getSql(form,sql,param);
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
	public List<SysParameterBean> getSysParameterIsEnablementListsupdate(SysParameterForm form) {
		List<String> param = new ArrayList<String>();
		List<SysParameterBean> beans = new ArrayList<SysParameterBean>();
		String sql  =" select param_id, param_type, param_name, param_value, param_notes, parent_id, enable,is_enablement from sys_parameter "
			+" where 1=1 ";
		if(form!=null){
			if(form.getParam_type()!=null&&form.getParam_type().trim().length()>0){
				sql+=" and param_type= '"+form.getParam_type().trim()+"' ";
			}
			if(form.getParam_value()!=null&&form.getParam_value().trim().length()>0){
				sql+=" and param_value= '"+form.getParam_value().trim()+"' ";
			}
			if(form.getParam_id()!=null&&form.getParam_id().trim().length()>0){
				sql+=" and param_id != '"+form.getParam_id().trim()+"' ";
			}
		}
		sql += " order by param_type, param_id asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
		}
		return beans;
	}
	public List<SysParameterBean> getSysParameterIsEnablementLists(SysParameterForm form) {
		List<String> param = new ArrayList<String>();
		List<SysParameterBean> beans = new ArrayList<SysParameterBean>();
		String sql  =" select param_id, param_type, param_name, param_value, param_notes, parent_id, enable,is_enablement from sys_parameter "
			+" where 1=1 ";
		if(form!=null){
			if(form.getParam_type()!=null&&form.getParam_type().trim().length()>0){
				sql+=" and param_type= '"+form.getParam_type().trim()+"' ";
			}
			if(form.getParam_value()!=null&&form.getParam_value().trim().length()>0){
				sql+=" and param_value= '"+form.getParam_value().trim()+"' ";
			}
		}
		sql += " order by param_type, param_id asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
		}
		return beans;
	}
	public List<SysParameterBean> getSysParameterIsEnablementList(SysParameterForm form) {
		List<String> param = new ArrayList<String>();
		List<SysParameterBean> beans = new ArrayList<SysParameterBean>();
		String sql  =" select param_id, param_type, param_name, param_value, param_notes, parent_id, enable,is_enablement from sys_parameter "
			+" where 1=1 ";
		sql = getSql(form,sql,param) + " order by param_type, param_id asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
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
	public List<SysParameterBean> getSysParameterList(PageBean pageBean,SysParameterForm form){
		List<String> param = new ArrayList<String>();
		List<SysParameterBean> beans = new ArrayList<SysParameterBean>();
		String sql  =" select param_id, param_type, param_name, param_value, param_notes, parent_id, enable,is_enablement from sys_parameter "
			+" where 1=1 ";
		sql = getSql(form,sql,param) + " order by param_type, param_id asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
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
	private String getSql(SysParameterForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getQueryParamType()!=null&&form.getQueryParamType().trim().length()>0){
				sql+=" and param_type= '"+form.getQueryParamType().trim()+"' ";
			}
			if(form.getQueryParamName()!=null&&form.getQueryParamName().trim().length()>0){
				sql+=" and param_name like '%"+form.getQueryParamName().trim()+"%' ";
			}
			if(form.getQueryParentId()!=null&&form.getQueryParentId().trim().length()>0){
				sql+=" and parent_id= '"+form.getQueryParentId().trim()+"' ";
			}
			if(form.getQueryEnable()!=null&&form.getQueryEnable().trim().length()>0){
				sql+=" and enable= '"+form.getQueryEnable().trim()+"' ";
			}
			if(form.getQueryIs_enablement()!=null&&form.getQueryIs_enablement().trim().length()>0){
				sql+=" and is_enablement= '"+form.getQueryIs_enablement().trim()+"' ";
			}
		}
		return sql;
	}
	
	public SysParameterBean getSysParameterByValue(String param_value){	
		String sql  ="select param_id, param_type, param_name, param_value, param_notes, parent_id, enable,is_enablement from sys_parameter "
				+" where 1=1 ";
			if(param_value!=null&&!"".equals(param_value)){
				sql=sql+" and param_type='SALES_POINT' and param_value='"+param_value+"' ";
			}else{
				return null;
			}
			SysParameterBean SysParameterBean = null;
			try {
				Vector vector = DbExec.execQuery(sql, null);
				if(vector != null && !vector.isEmpty()){
					SysParameterBean = new SysParameterBean((HashMap)vector.get(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			}
			return SysParameterBean;
			
	}
	public SysParameterBean getSysParameterByID(String param_id){		
		String sql  ="select param_id, param_type, param_name, param_value, param_notes, parent_id, enable,is_enablement from sys_parameter "
			+" where 1=1 ";
		if(param_id!=null&&!"".equals(param_id)){
			sql=sql+" and param_id='"+param_id+"' ";
		}else{
			return null;
		}
		SysParameterBean SysParameterBean = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if(vector != null && !vector.isEmpty()){
				SysParameterBean = new SysParameterBean((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return SysParameterBean;
	}
	
	/**
	 * 添加
	 * @param SysParameterBean
	 * @return
	 */
	public int addSysParameter(SysParameterForm SysParameterForm) {
		String sql = "insert into sys_parameter(param_id, param_type, param_name, param_value, param_notes, parent_id, enable) "
				+"values(SYS_PARAMETER_SEQUENCE.NEXTVAL,'"
				+ SysParameterForm.getParam_type()
				+ "','"
				+ SysParameterForm.getParam_name()
				+ "','"
				+ SysParameterForm.getParam_value()
				+ "','"
				+ SysParameterForm.getParam_notes()
				+ "','"
				+ SysParameterForm.getParent_id()
				+ "','"
				+ SysParameterForm.getEnable()
				+ "')";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addSysParameter", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addSysParameter", e);
			result = -1;
		}
		return result;
	}
	
	public int updSysParameters(SysParameterForm SysParameterForm) {
		String sql = " update sys_parameter set param_id='" + SysParameterForm.getParam_id() + "' ";
		if (SysParameterForm.getParam_name() != null
				&& !"".equals(SysParameterForm.getParam_name())) {
			sql = sql + ", param_name='" + SysParameterForm.getParam_name().trim() + "' ";
		}
		if (SysParameterForm.getParam_notes() != null
				&& !"".equals(SysParameterForm.getParam_notes())) {
			sql = sql + ", param_notes='" + SysParameterForm.getParam_notes().trim() + "' ";
		}
		if (SysParameterForm.getEnable() != null
				&& !"".equals(SysParameterForm.getEnable())) {
			sql = sql + ", enable='" + SysParameterForm.getEnable() + "' ";
		}
		if (SysParameterForm.getParam_value() != null
				&& !"".equals(SysParameterForm.getParam_value())) {
			sql = sql + ", param_value='" + SysParameterForm.getParam_value().trim() + "' ";
		}
		if (SysParameterForm.getParent_id() != null
				&& !"".equals(SysParameterForm.getParent_id())) {
			sql = sql + ", parent_id='" + SysParameterForm.getParent_id().trim() + "' ";
		}
		if (SysParameterForm.getIs_enablement() != null
				&& !"".equals(SysParameterForm.getIs_enablement())) {
			sql = sql + ", is_enablement='" + SysParameterForm.getIs_enablement().trim() + "' ";
		}
		sql = sql + "  where param_id = '" + SysParameterForm.getParam_id()+ "' ";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updSysParameter", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updSysParameter", e);
			result = -1;
		}
		return result;
	}
	public int updSysParameter(SysParameterForm SysParameterForm) {
		String sql = " update sys_parameter set param_name='" + SysParameterForm.getParam_name() + "' ";
		if (SysParameterForm.getParam_notes() != null
				&& !"".equals(SysParameterForm.getParam_notes())) {
			sql = sql + ", param_notes='" + SysParameterForm.getParam_notes().trim() + "' ";
		}
		if (SysParameterForm.getEnable() != null
				&& !"".equals(SysParameterForm.getEnable())) {
			sql = sql + ", enable='" + SysParameterForm.getEnable() + "' ";
		}
		if (SysParameterForm.getParam_value() != null
				&& !"".equals(SysParameterForm.getParam_value())) {
			sql = sql + ", param_value='" + SysParameterForm.getParam_value().trim() + "' ";
		}
		if (SysParameterForm.getParent_id() != null
				&& !"".equals(SysParameterForm.getParent_id())) {
			sql = sql + ", parent_id='" + SysParameterForm.getParent_id().trim() + "' ";
		}
		if (SysParameterForm.getIs_enablement() != null
				&& !"".equals(SysParameterForm.getIs_enablement())) {
			sql = sql + ", is_enablement='" + SysParameterForm.getIs_enablement().trim() + "' ";
		}
		sql = sql + "  where param_id = '" + SysParameterForm.getParam_id()+ "' ";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updSysParameter", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updSysParameter", e);
			result = -1;
		}
		return result;
	}

	public int delSysParameter(String param_id) {
		String sql = " delete from sys_parameter where 1=1 " ;
		if(param_id!=null&&!"".equals(param_id)){
			sql = sql + " and param_id = '" + param_id+ "' ";
		}else{
			return -1;
		}
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updSysParameter", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updSysParameter", e);
			result = -1;
		}
		return result;
	}
	
	public int sysParameterHasChild(String param_id) {
		String sql = " select * from sys_parameter where 1=1 " ;
		if(param_id!=null&&!"".equals(param_id)){
			sql = sql + " and parent_id = '" + param_id+ "' ";
		}else{
			return -1;
		}
		try {
			result = DbExec.findRecord(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updSysParameter", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updSysParameter", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 *  判断逻辑主键是否重复
	 * @param param_type  
	 * @param param_name  
	 * @return 0.不重复；1.重复 。
	 */
	public String checkSysParameter(String param_type,String param_name,String param_id){	
		String checkFlag="0";
		String sql  ="select param_id, param_type, param_name, param_value, param_notes, parent_id, enable from sys_parameter "
			+" where 1=1 ";
		if(param_id!=null&&!"".equals(param_id)){
			sql=sql+" and param_id='"+param_id+"' ";
		}
		if(param_type!=null&&!"".equals(param_type)){
			sql=sql+" and param_type='"+param_type+"' ";
		}
		if(param_name!=null&&!"".equals(param_name)){
			sql=sql+" and param_name='"+param_name+"' ";
		}
		try {
			Vector vector = DbExec.execQuery(sql, null);
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
	 * 根据参数类型获取参数LIST
	 * @param param_type 参数类型参数
	 * @return
	 */
	public List<SysParameterBean> getSysParameterList(String param_type){
		List<SysParameterBean> beans = new ArrayList<SysParameterBean>();
		String sql  =" select param_id, param_type, param_name, param_value, param_notes, parent_id, enable from sys_parameter "
			+" where enable='1' and param_name not like '中石化%' ";
		if(param_type!=null&&!"".equals(param_type)){
			sql=sql+" and param_type='"+param_type+"' ";
		}else{
			return null;
		}
		sql = sql + " order by param_type, param_id asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, null);
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
		}
		return beans;
	}
	
	/**
	 * 获取参数码表MAP
	 * @param param_type 参数类型
	 * @return
	 */
	public Map<String,String> getSysParamMap(String param_type){
		Map<String, String> listMap = new HashMap<String, String>();	
		String sql  =" select param_name, param_value from sys_parameter "
			+" where enable='1' ";
		if(param_type!=null&&!"".equals(param_type)){
			sql=sql+" and param_type='"+param_type+"' ";
		}else{
			return null;
		}
		sql = sql + " order by param_type, param_id asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, null);
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					listMap.put(bean.getParam_value(), bean.getParam_name());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
		}
		return listMap;
	}
	/**
	 * 获取参数类型LIST
	 * @return
	 */
	public List<SysParameterBean> getSysParameterTypeList(){
		List<SysParameterBean> beans = new ArrayList<SysParameterBean>();
		String sql  =" select distinct param_type as param_type from sys_parameter order by param_type asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, null);
			SysParameterBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new SysParameterBean(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getSysParameterList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getSysParameterList", e);
		}
		return beans;
	}

	
	
	public SysParameterBean getSysParameterByTypeAndValue(String param_type,String param_value){	
		if(param_type==null||"".equals(param_type)||param_value==null||"".equals(param_value)){
			return null;
		}else{
			String sql  ="select param_id, param_type, param_name, param_value, param_notes, parent_id, enable from sys_parameter "
				+" where enable='1' and param_type='"+param_type+"'  and param_value='"+param_value+"' ";
			SysParameterBean SysParameterBean = null;
			try {
				Vector vector = DbExec.execCardQuery(sql, null);
				if(vector != null && !vector.isEmpty()){
					SysParameterBean = new SysParameterBean((HashMap)vector.get(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			}
			return SysParameterBean;
		}
	}
}

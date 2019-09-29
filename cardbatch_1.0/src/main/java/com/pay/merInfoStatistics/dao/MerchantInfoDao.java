package com.pay.merInfoStatistics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.directwebremoting.util.Logger;

import com.pay.merInfoStatistics.bean.MerchantInfo;
import com.pay.merInfoStatistics.bean.MerchantInfoExcel;
import com.pay.merInfoStatistics.struts.form.MerchantInfoForm;
import com.pay.merchant.bean.AreaBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

public class MerchantInfoDao {
	
	private static final Logger logger = Logger.getLogger(MerchantInfoDao.class);
	
	private int result = 0;

	/**
	 * 查询分页总条数
	 * @param form
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int getCount(MerchantInfoForm form,HttpSession session){
		List<String> param = new ArrayList<String>();
		int count = 0;
		String sql  =" select count(1) as numbers from mis_merchant "
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
	
	/**
	 * 根据搜索条件返回指定的list
	 * @param pageBean
	 * @param form
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<MerchantInfo> getMerchantInfoList(PageBean pageBean,MerchantInfoForm form){
		List<String> param = new ArrayList<String>();
		List<MerchantInfo> beans = new ArrayList<MerchantInfo>();
		String sql  =" select id,name,fullname,type,status,province,city,area from mis_merchant "
			+" where 1=1 ";
		sql = getSql(form,sql,param) + " order by id, name asc ";
		Vector vector = null;
		try {
			if(pageBean != null){
				vector = DbExec.execQuery(sql, param, pageBean.getStart(), pageBean.getPageSize());
			}else{
				vector = DbExec.execQuery(sql, param);
			}
			MerchantInfo bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantInfo(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getMerchantInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getMerchantInfoList", e);
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
	private String getSql(MerchantInfoForm form,String sql,List<String> param){
		if(form!=null){
			if(form.getQid()!=null&&form.getQid().trim().length()>0){
				sql+=" and id= '"+form.getQid().trim()+"' ";
			}
			if(form.getQname()!=null&&form.getQname().trim().length()>0){
				sql+=" and name like '%"+form.getQname().trim()+"%' ";
			}
			if(form.getQfullname()!=null&&form.getQfullname().trim().length()>0){
				sql+=" and fullname like '%"+form.getQfullname().trim()+"%' ";
			}
			if(form.getQtype()!=null&&form.getQtype().trim().length()>0){
				sql+=" and type= '"+form.getQtype().trim()+"' ";
			}
			if(form.getQstatus()!=null&&form.getQstatus().trim().length()>0){
				sql+=" and status= '"+form.getQstatus().trim()+"' ";
			}
			if(form.getQprovince()!=null&&form.getQprovince().trim().length()>0){
				sql+=" and province= '"+form.getQprovince().trim()+"' ";
			}
			if(form.getQcity()!=null&&form.getQcity().trim().length()>0){
				sql+=" and city= '"+form.getQcity().trim()+"' ";
			}
			if(form.getQarea()!=null&&form.getQarea().trim().length()>0){
				sql+=" and area= '"+form.getQarea().trim()+"' ";
			}
		}
		return sql;
	}
	
	/**
	 * 根据上级城市id号获取下级地区
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getCityByFid(String fid) {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		String sql = "select id as aid,province_city from area where fid = ? and isuse = '1' order by aid";
		List params = new ArrayList();
		params.add(fid);
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}
	
	/**
	 * 根据depth get城市
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<AreaBean> getBJCity() {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		// isuse为目前的可用标识
		String sql = "SELECT t.id,t.province_city FROM area t WHERE trim(t.province_city)='北京市'  AND t.isuse='1' ";
		List params = new ArrayList();
		try {
			Vector vector = DbExec.execQuery(sql, params);
			AreaBean bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new AreaBean(map);
					areaList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getCityByParentpath", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getCityByParentpath", e);
		}
		return areaList;
	}
	
	public MerchantInfo getTMerchantInfoByID(String id){		
		String sql ="select id,name,fullname,type,status,province,city,area from mis_merchant "
			+" where 1=1 ";
		if(id != null && !"".equals(id)){
			sql=sql + " and id='" + id + "' ";
		}else{
			return null;
		}
		MerchantInfo merchantInfo = null;
		try {
			Vector vector = DbExec.execQuery(sql, null);
			if(vector != null && !vector.isEmpty()){
				merchantInfo = new MerchantInfo((HashMap)vector.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("SQLException", e);
		}
		return merchantInfo;
	}
	
	/**
	 * 添加
	 * @param MerchantInfo
	 * @return
	 */
	public int addMerchantInfo(MerchantInfoForm merchantInfoForm) {
		String sql = "insert into mis_merchant(id, name, fullname, type, status, province, city, area) values('"
				+ merchantInfoForm.getId()
				+ "','"
				+ merchantInfoForm.getName()
				+ "','"
				+ merchantInfoForm.getFullname()
				+ "','"
				+ merchantInfoForm.getType()
				+ "','"
				+ merchantInfoForm.getStatus()
				+ "','"
				+ merchantInfoForm.getProvince()
				+ "','"
				+ merchantInfoForm.getCity()
				+ "','"
				+ merchantInfoForm.getArea() + "')";
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: addMerchantInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: addMerchantInfo", e);
			result = -1;
		}
		return result;
	}
	
	public int updMerchantInfo(MerchantInfoForm merchantInfoForm) {
		String sql = " update mis_merchant set name='" + merchantInfoForm.getName() + "' ";
		if (merchantInfoForm.getFullname() != null
				&& !"".equals(merchantInfoForm.getFullname())) {
			sql = sql + ", fullname='" + merchantInfoForm.getFullname() + "' ";
		}
		if (merchantInfoForm.getType() != null
				&& !"".equals(merchantInfoForm.getType())) {
			sql = sql + ", type='" + merchantInfoForm.getType() + "' ";
		}
		if (merchantInfoForm.getStatus() != null
				&& !"".equals(merchantInfoForm.getStatus())) {
			sql = sql + ", status='" + merchantInfoForm.getStatus() + "' ";
		}
		if (merchantInfoForm.getProvince() != null
				&& !"".equals(merchantInfoForm.getProvince())) {
			sql = sql + ", province='" + merchantInfoForm.getProvince() + "' ";
		}
		if (merchantInfoForm.getCity() != null
				&& !"".equals(merchantInfoForm.getCity())) {
			sql = sql + ", city='" + merchantInfoForm.getCity() + "' ";
		}
		if (merchantInfoForm.getArea() != null
				&& !"".equals(merchantInfoForm.getArea())) {
			sql = sql + ", area='" + merchantInfoForm.getArea() + "' ";
		}
		sql = sql + "  where id = '" + merchantInfoForm.getId() + "' ";
		
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: updMerchantInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: updMerchantInfo", e);
			result = -1;
		}
		return result;
	}

	public int delMerchantInfo(String id) {
		String sql = " delete from mis_merchant where 1=1 " ;
		if(id != null && !"".equals(id)){
			sql = sql + " and id = '" + id + "' ";
		}else{
			return -1;
		}
		try {
			result = DbExec.execUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException: delMerchantInfo", e);
			result = -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: delMerchantInfo", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 *  判断主键是否重复
	 * @param id
	 * @return 0.不重复；1.重复 。
	 */
	public String checkMerchantInfoPK(String id){	
		String checkFlag="0";
		String sql  ="select id, name, fullname, type, status, province, city, area from mis_merchant "
			+" where id=? ";
		List param = new ArrayList();
		param.add(id);
		try {
			Vector vector = DbExec.execQuery(sql, param);
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
	
	public List<MerchantInfoExcel> getMerchantInfoList(MerchantInfoForm merchantInfoForm){
		List<String> param = new ArrayList<String>();
		List<MerchantInfoExcel> beans = new ArrayList<MerchantInfoExcel>();
		String sql  =" select id,name,fullname,type as type_desc,decode(status,'0','可用','1','不可用','黑名单') as status_desc,(select a.province_city from area a where a.id = m.province ) as province_cn,(select a.province_city from area a where a.id = m.city ) as city_cn,(select a.province_city from area a where a.id = m.area ) as area_cn from mis_merchant m"
			+" where 1=1 ";
		sql = getSql(merchantInfoForm,sql,param) + " order by id,name asc ";
		Vector vector = null;
		try {
			vector = DbExec.execQuery(sql, param);
			MerchantInfoExcel bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for(int i=0;i<size;i++){
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantInfoExcel(map);
					beans.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
            logger.error("SQLException--getMerchantInfoList", e);
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("Exception--getMerchantInfoList", e);
		}
		return beans;
	}
	
	public List<MerchantInfo> getMerchantInfoListByIdOrName(String qmerchantidname){
		List<MerchantInfo> merchantInfoList = new ArrayList<MerchantInfo>();
		String sql = "select id,id||'('||name||')' as name from mis_merchant where 1=1 ";
		if(qmerchantidname != null && qmerchantidname.trim().length() > 0){
			sql = sql + " and (lower(name) like '%"+ qmerchantidname.toLowerCase()+"%' or lower(id) like '%"+qmerchantidname.toLowerCase()+"%') ";
		}
		sql = sql + " order by name desc ";
		try {
			Vector vector = DbExec.execQuery(sql);
			MerchantInfo bean = null;
			if (vector != null && !vector.isEmpty()) {
				int size = vector.size();
				for (int i = 0; i < size; i++) {
					HashMap map = (HashMap) vector.get(i);
					bean = new MerchantInfo(map);
					merchantInfoList.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException--getMerchantInfoListByIdOrName", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception--getMerchantInfoListByIdOrName", e);
		}
		return merchantInfoList;
	}
	
}

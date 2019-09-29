package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jansh.core.entity.sys.PubsSysBase;

/**
 *系统参数
 *
 *@author Mr.wong 
 *
 */
public interface PubsSysBaseMapper {

	/**
	 *通过系统参数名获取参数实体
	 */
	public PubsSysBase selectOneByBaseId(@Param("baseId") String baseId);
	/**
	 *获取全部的系统参数
	 */
	public List<PubsSysBase> selectAllBase();
	/**
	 *获取全部的系统参数(Map(key=value))
	 */
	public Map<String,String> selectAllBaseMap();
	 /**
	  *更新系统参数 
	  */
	public void update(PubsSysBase pubsSysBase);
	/**
	 * 插入系统参数
	 */
	public void insert(PubsSysBase pubsSysBase);
	/**
	    根据Id删除系统参数
	 * 
	 */
	public void delete(String autoId);
	/**
	 *模糊查询 
	 */
	public List<PubsSysBase> selectBasesByBaseId(@Param("baseId")String baseId);
}


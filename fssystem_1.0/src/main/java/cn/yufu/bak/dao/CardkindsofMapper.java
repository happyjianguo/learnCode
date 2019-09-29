package cn.yufu.bak.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.bak.entity.Cardkindsof;
import cn.yufu.bak.entity.CardkindsofExample;

@Repository("bak.CardkindsofDao")
public interface CardkindsofMapper {
   
	/**
	 * 获取卡类型码表 按库查询
	 * @param cardkindsof --> 来源数据库名称或ID
	 */
	public List<Cardkindsof> getCardTypeList(Cardkindsof cardkindsof);
	
	/**
	 * 获取卡类型码表，所有库数据去重后的数据
	 * @param cardkindsof
	 */
	public List<Cardkindsof> getDicCardTypeList(Cardkindsof cardkindsof);
	
	Integer countByExample(@Param("example")CardkindsofExample example);
	
    Integer deleteByPrimaryKey(String cardnumber);

    Integer insert(Cardkindsof record);

    Integer insertSelective(Cardkindsof record);

    List<Cardkindsof> selectByExample(@Param("example")CardkindsofExample example);
    
    List<Cardkindsof> selectPageByExample(@Param("example")CardkindsofExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    Cardkindsof selectByPrimaryKey(String cardnumber);

    Integer updateByPrimaryKeySelective(Cardkindsof record);

    Integer updateByPrimaryKey(Cardkindsof record);
    
    String getMaXCardNum();
	
}
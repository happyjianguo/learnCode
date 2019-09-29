package cn.yufu.fs.dao;

import cn.yufu.fs.entity.MerstlErrorSet;
import cn.yufu.fs.entity.MerstlErrorSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("fs.MerstlErrorSetDao")
public interface MerstlErrorSetMapper {
	
	Integer countByExample(@Param("example")MerstlErrorSetExample example);

	Integer deleteByExample(@Param("example")MerstlErrorSetExample example);

	Integer insert(MerstlErrorSet record);

	Integer insertSelective(MerstlErrorSet record);

	MerstlErrorSet selectByPrimaryKey(String id);
    
    List<MerstlErrorSet> selectByExample(@Param("example")MerstlErrorSetExample example);
    
    List<MerstlErrorSet> selectPageByExample(@Param("example")MerstlErrorSetExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    Integer updateByPrimaryKeySelective( MerstlErrorSet record);

    Integer updateByPrimaryKey(MerstlErrorSet record);
    
    Integer deleteByPrimaryKey(String id);
}
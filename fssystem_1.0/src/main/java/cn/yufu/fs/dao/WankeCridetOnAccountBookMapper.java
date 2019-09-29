package cn.yufu.fs.dao;

import cn.yufu.fs.entity.WankeCridetOnAccountBook;
import cn.yufu.fs.entity.WankeCridetOnAccountBookExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("fs.WankeCridetOnAccountBookDao")
public interface WankeCridetOnAccountBookMapper {
    int countByExample(@Param("example") WankeCridetOnAccountBookExample example);

//    int deleteByExample(WankeCridetOnAccountBookExample example);
//
//    int deleteByPrimaryKey(String id);
//
//    int insert(WankeCridetOnAccountBook record);
//
//    int insertSelective(WankeCridetOnAccountBook record);

    List<WankeCridetOnAccountBook> selectByExample(@Param("example") WankeCridetOnAccountBookExample example);

    WankeCridetOnAccountBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WankeCridetOnAccountBook record, @Param("example") WankeCridetOnAccountBookExample example);

    int updateByExample(@Param("record") WankeCridetOnAccountBook record, @Param("example") WankeCridetOnAccountBookExample example);

    int updateByPrimaryKeySelective(WankeCridetOnAccountBook record);

    int updateByPrimaryKey(WankeCridetOnAccountBook record);
    
    List<WankeCridetOnAccountBook> selectPageByExample(@Param("example") WankeCridetOnAccountBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

}
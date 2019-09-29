package cn.yufu.fs.dao;

import cn.yufu.fs.entity.WankeParkingTranBook;
import cn.yufu.fs.entity.WankeParkingTranBookExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("fs.WankeParkingTranBookDao")
public interface WankeParkingTranBookMapper {
    int countByExample(@Param("example") WankeParkingTranBookExample example);

//    int deleteByExample(WankeParkingTranBookExample example);
//
//    int deleteByPrimaryKey(String id);
//
//    int insert(WankeParkingTranBook record);
//
//    int insertSelective(WankeParkingTranBook record);

    List<WankeParkingTranBook> selectByExample(@Param("example") WankeParkingTranBookExample example);

    WankeParkingTranBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WankeParkingTranBook record, @Param("example") WankeParkingTranBookExample example);

    int updateByExample(@Param("record") WankeParkingTranBook record, @Param("example") WankeParkingTranBookExample example);

    int updateByPrimaryKeySelective(WankeParkingTranBook record);

    int updateByPrimaryKey(WankeParkingTranBook record);
    
    List<WankeParkingTranBook> selectPageByExample(@Param("example") WankeParkingTranBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

}
package cn.yufu.fs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearRegardSalesDetBook;
import cn.yufu.fs.entity.ClearRegardSalesDetBookExample;

@Repository("fs.ClearRegardSalesDetBookDao")
public interface ClearRegardSalesDetBookMapper {
    int countByExample(ClearRegardSalesDetBookExample example);


    List<ClearRegardSalesDetBook> selectByExample(ClearRegardSalesDetBookExample example);
    List<ClearRegardSalesDetBook> selectByExampleSum(ClearRegardSalesDetBookExample example);


}
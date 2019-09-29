package cn.yufu.fs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearRegardSalesBook;
import cn.yufu.fs.entity.ClearRegardSalesBookExample;

@Repository("fs.ClearRegardSalesBookDao")
public interface ClearRegardSalesBookMapper {
    int countByExample(ClearRegardSalesBookExample example);

    List<ClearRegardSalesBook> selectByExample(ClearRegardSalesBookExample example);


}
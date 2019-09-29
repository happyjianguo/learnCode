package cn.yufu.fs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearMermccBook;
import cn.yufu.fs.entity.ClearMermccBookExample;
@Repository("fs.ClearMermccBookDao")
public interface ClearMermccBookMapper {
    int countByExample(ClearMermccBookExample example);

    List<ClearMermccBook> selectByExample(ClearMermccBookExample example);
    
    List<ClearMermccBook> getTotalAmtPage(Map<String, Object> map);

    List<ClearMermccBook> getTotalAmt(ClearMermccBook example);
    
    Integer getCount(ClearMermccBook example);
    
    ClearMermccBook getTotal(ClearMermccBook example);
}
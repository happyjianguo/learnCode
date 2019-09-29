package cn.yufu.fs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearMermccDetailBook;
import cn.yufu.fs.entity.ClearMermccDetailBookExample;
@Repository("fs.ClearMermccDetailBookDao")
public interface ClearMermccDetailBookMapper {
    int countByExample(ClearMermccDetailBookExample example);


    List<ClearMermccDetailBook> selectByExample(ClearMermccDetailBookExample example);
    List<ClearMermccDetailBook> selectByExampleSum(ClearMermccDetailBookExample example);


}
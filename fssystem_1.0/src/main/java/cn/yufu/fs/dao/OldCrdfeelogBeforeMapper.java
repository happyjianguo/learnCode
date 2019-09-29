package cn.yufu.fs.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.OldCrdfeelogBefore;

@Repository("fs.OldCrdfeelogBeforeDao")
public interface OldCrdfeelogBeforeMapper {
	
    Integer countByExample(OldCrdfeelogBefore crdfeelog);

    List<OldCrdfeelogBefore> selectPageByExample(Map<String, Object> map);
    
    List<OldCrdfeelogBefore> selectByExample(OldCrdfeelogBefore crdfeelog);

    OldCrdfeelogBefore selectByPrimaryKey(String id);
    
    public BigDecimal getFeeSum(OldCrdfeelogBefore queryModel);

}
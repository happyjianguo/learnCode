package cn.yufu.fs.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.OldCrdfeelog;

@Repository("fs.OldCrdfeelogDao")
public interface OldCrdfeelogMapper {
	
    Integer countByExample(OldCrdfeelog crdfeelog);

    List<OldCrdfeelog> selectPageByExample(Map<String, Object> map);
    
    List<OldCrdfeelog> selectByExample(OldCrdfeelog crdfeelog);

    public List<OldCrdfeelog> selectByPrimaryKey(OldCrdfeelog queryModel);
    
    public int refund(OldCrdfeelog crdfeelog);
    
    public BigDecimal getFeeSum(OldCrdfeelog queryModel);

}
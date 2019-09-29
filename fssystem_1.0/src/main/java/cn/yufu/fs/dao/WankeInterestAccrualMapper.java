package cn.yufu.fs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.WankeInterestAccrual;

@Repository("fs.WankeInterestAccrualDao")
public interface WankeInterestAccrualMapper {
    Integer countByExample(WankeInterestAccrual wankeInterestAccrual);

    List<WankeInterestAccrual> selectByExample(WankeInterestAccrual wankeInterestAccrual);
    
    List<WankeInterestAccrual> selectPageByExample(Map<String, Object> map);
    
    WankeInterestAccrual selectByPrimaryKey(String id);
    
}
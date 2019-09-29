package cn.yufu.bak.dao;

import cn.yufu.bak.entity.MerchantX;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("bak.MerchantXDao")
public interface MerchantXMapper {
	
	int getLazyMerchantCounts(Map<String, Object> map);
    
	List<String> getMrchtName(String mrchno);
    
    List<String> getMrchno(String mrchtName);
    
    List<MerchantX> selectPageMrchList(Map<String, Object> map);
    
    List<MerchantX> selectMrchList();
}
package cn.yufu.posp.dao;

import cn.yufu.posp.entity.BtsKey;
import cn.yufu.posp.entity.BtsKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.BtsKeyDao")
public interface BtsKeyMapper {
    int countByExample(BtsKeyExample example);

    int deleteByExample(BtsKeyExample example);

    int insert(BtsKey record);

    int insertSelective(BtsKey record);

    List<BtsKey> selectByExample(BtsKeyExample example);

    int updateByExampleSelective(@Param("record") BtsKey record, @Param("example") BtsKeyExample example);

    int updateByExample(@Param("record") BtsKey record, @Param("example") BtsKeyExample example);

	public BtsKey selectByTermCode(BtsKey btsKey);
}
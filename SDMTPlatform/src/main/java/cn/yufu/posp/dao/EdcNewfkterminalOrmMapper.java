package cn.yufu.posp.dao;

import cn.yufu.posp.entity.EdcNewfkterminalOrm;
import cn.yufu.posp.entity.EdcNewfkterminalOrmExample;
import cn.yufu.posp.entity.EdcNewfkterminalOrmKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.EdcNewfkterminalOrmDao")
public interface EdcNewfkterminalOrmMapper {
    int countByExample(EdcNewfkterminalOrmExample example);

    int deleteByExample(EdcNewfkterminalOrmExample example);

    int deleteByPrimaryKey(EdcNewfkterminalOrmKey key);

    int insert(EdcNewfkterminalOrm record);

    int insertSelective(EdcNewfkterminalOrm record);

    List<EdcNewfkterminalOrm> selectByExample(EdcNewfkterminalOrmExample example);

    EdcNewfkterminalOrm selectByPrimaryKey(EdcNewfkterminalOrmKey key);
    
    public EdcNewfkterminalOrm selectByPrimaryTerminalId(EdcNewfkterminalOrmKey key);

    int updateByExampleSelective(@Param("record") EdcNewfkterminalOrm record, @Param("example") EdcNewfkterminalOrmExample example);

    int updateByExample(@Param("record") EdcNewfkterminalOrm record, @Param("example") EdcNewfkterminalOrmExample example);

    int updateByPrimaryKeySelective(EdcNewfkterminalOrm record);

    int updateByPrimaryKey(EdcNewfkterminalOrm record);
}
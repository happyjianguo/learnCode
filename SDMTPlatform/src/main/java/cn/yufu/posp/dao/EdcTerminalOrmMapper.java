package cn.yufu.posp.dao;

import cn.yufu.posp.entity.EdcTerminalOrm;
import cn.yufu.posp.entity.EdcTerminalOrmExample;
import cn.yufu.posp.entity.EdcTerminalOrmKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.EdcTerminalOrmDao")
public interface EdcTerminalOrmMapper {
    int countByExample(EdcTerminalOrmExample example);

    int deleteByExample(EdcTerminalOrmExample example);

    int deleteByPrimaryKey(EdcTerminalOrmKey key);

    int insert(EdcTerminalOrm record);

    int insertSelective(EdcTerminalOrm record);

    List<EdcTerminalOrm> selectByExample(EdcTerminalOrmExample example);

    EdcTerminalOrm selectByPrimaryKey(EdcTerminalOrmKey key);

    int updateByExampleSelective(@Param("record") EdcTerminalOrm record, @Param("example") EdcTerminalOrmExample example);

    int updateByExample(@Param("record") EdcTerminalOrm record, @Param("example") EdcTerminalOrmExample example);

    int updateByPrimaryKeySelective(EdcTerminalOrm record);

    int updateByPrimaryKey(EdcTerminalOrm record);
}
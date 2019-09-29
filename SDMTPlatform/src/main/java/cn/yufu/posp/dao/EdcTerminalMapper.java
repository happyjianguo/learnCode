package cn.yufu.posp.dao;

import cn.yufu.posp.entity.EdcTerminal;
import cn.yufu.posp.entity.EdcTerminalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.EdcTerminalDao")
public interface EdcTerminalMapper {
    int countByExample(EdcTerminalExample example);

    int deleteByExample(EdcTerminalExample example);

    int insert(EdcTerminal record);

    int insertSelective(EdcTerminal record);

    List<EdcTerminal> selectByExample(EdcTerminalExample example);

    int updateByExampleSelective(@Param("record") EdcTerminal record, @Param("example") EdcTerminalExample example);

    int updateByExample(@Param("record") EdcTerminal record, @Param("example") EdcTerminalExample example);

	public EdcTerminal selectByTermCode(EdcTerminal edcTerminal);
}
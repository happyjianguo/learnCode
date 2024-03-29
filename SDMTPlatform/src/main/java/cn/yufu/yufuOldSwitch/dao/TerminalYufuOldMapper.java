package cn.yufu.yufuOldSwitch.dao;

import cn.yufu.yufuOldSwitch.entity.TerminalYufuOld;
import cn.yufu.yufuOldSwitch.entity.TerminalYufuOldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("yufuOldSwitch.TerminalYufuOldDao")
public interface TerminalYufuOldMapper {
    int countByExample(TerminalYufuOldExample example);

    int deleteByExample(TerminalYufuOldExample example);

    int deleteByPrimaryKey(String sserialnum);

    int insert(TerminalYufuOld record);

    int insertSelective(TerminalYufuOld record);

    List<TerminalYufuOld> selectByExample(TerminalYufuOldExample example);

    TerminalYufuOld selectByPrimaryKey(String sserialnum);

    int updateByExampleSelective(@Param("record") TerminalYufuOld record, @Param("example") TerminalYufuOldExample example);

    int updateByExample(@Param("record") TerminalYufuOld record, @Param("example") TerminalYufuOldExample example);

    int updateByPrimaryKeySelective(TerminalYufuOld record);

    int updateByPrimaryKey(TerminalYufuOld record);
}
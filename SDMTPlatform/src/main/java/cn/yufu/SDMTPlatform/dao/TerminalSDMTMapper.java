package cn.yufu.SDMTPlatform.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTExample;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTKey;
@Repository("sdmtpf.TerminalSDMTDao")
public interface TerminalSDMTMapper {
    int countByExample(@Param("example") TerminalSDMTExample example);

    int deleteByExample(TerminalSDMTExample example);

    int deleteByPrimaryKey(TerminalSDMTKey key);

    int insert(TerminalSDMT record);

    int insertSelective(TerminalSDMT record);

    List<TerminalSDMT> selectByExample(@Param("example") TerminalSDMTExample example);

    TerminalSDMT selectByPrimaryKey(TerminalSDMTKey key);

    int updateByExampleSelective(@Param("record") TerminalSDMT record, @Param("example") TerminalSDMTExample example);

    int updateByExample(@Param("record") TerminalSDMT record, @Param("example") TerminalSDMTExample example);

    int updateByPrimaryKeySelective(TerminalSDMT record);

    int updateByPrimaryKey(TerminalSDMT record);
    
	List<TerminalSDMT> selectPageByExample(@Param("example") TerminalSDMTExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

}
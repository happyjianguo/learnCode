package cn.yufu.bak.dao;

import cn.yufu.bak.entity.TermposX;
import cn.yufu.bak.entity.TermposXExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("bak.TermposXDao")
public interface TermposXMapper {
    int countByExample(@Param("example") TermposXExample example);

    int deleteByExample(TermposXExample example);

    int insert(TermposX record);

    int insertSelective(TermposX record);

    List<TermposX> selectByExample(@Param("example") TermposXExample example);

    int updateByExampleSelective(@Param("record") TermposX record, @Param("example") TermposXExample example);

    int updateByExample(@Param("record") TermposX record, @Param("example") TermposXExample example);
    
    List<String> selectByMrchno(String merNo);
    
    /**
	 * 根据终端号号从备库获取终端位置
	 * @author Administrator
	 *
	 */
	public List<String> getTerminalLoc(String terminalNo);
	
	public List<String> getMrchno(String terminalNo);
}
package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.SysParameter;
import cn.yufu.cortex.entity.SysParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.SysParameterDao")
public interface SysParameterMapper {
    int countByExample(SysParameterExample example);

    int deleteByExample(SysParameterExample example);

    int deleteByPrimaryKey(String paramId);

    int insert(SysParameter record);

    int insertSelective(SysParameter record);

    List<SysParameter> selectByExample(SysParameterExample example);

    SysParameter selectByPrimaryKey(String paramId);

    int updateByExampleSelective(@Param("record") SysParameter record, @Param("example") SysParameterExample example);

    int updateByExample(@Param("record") SysParameter record, @Param("example") SysParameterExample example);

    int updateByPrimaryKeySelective(SysParameter record);

    int updateByPrimaryKey(SysParameter record);
}
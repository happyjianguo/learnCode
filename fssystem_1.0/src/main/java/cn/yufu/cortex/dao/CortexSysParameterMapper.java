package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.entity.CortexSysParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexSysParameterDao")

public interface CortexSysParameterMapper {
    int countByExample(CortexSysParameterExample example);

    int deleteByExample(CortexSysParameterExample example);

    int deleteByPrimaryKey(String paramId);

    int insert(CortexSysParameter record);

    int insertSelective(CortexSysParameter record);

    List<CortexSysParameter> selectByExample(@Param("example") CortexSysParameterExample example);

    CortexSysParameter selectByPrimaryKey(String paramId);

    int updateByExampleSelective(@Param("record") CortexSysParameter record, @Param("example") CortexSysParameterExample example);

    int updateByExample(@Param("record") CortexSysParameter record, @Param("example") CortexSysParameterExample example);

    int updateByPrimaryKeySelective(CortexSysParameter record);

    int updateByPrimaryKey(CortexSysParameter record);
}
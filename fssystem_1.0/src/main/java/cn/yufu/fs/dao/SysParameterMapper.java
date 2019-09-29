package cn.yufu.fs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.SysParameter;
import cn.yufu.fs.entity.SysParameterExample;
@Repository("fs.SysParameterDao")
public interface SysParameterMapper {
    

    List<SysParameter> selectByExample(SysParameterExample example);

   
}
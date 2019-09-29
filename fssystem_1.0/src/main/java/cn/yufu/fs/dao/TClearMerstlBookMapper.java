package cn.yufu.fs.dao;

import cn.yufu.fs.entity.TClearMerstlBook;
import cn.yufu.fs.entity.TClearMerstlBookExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("fs.TClearMerstlBookDao")
public interface TClearMerstlBookMapper {
	Integer countByExample(Map<String, Object> map);

	Integer deleteByExample(TClearMerstlBookExample example);

	Integer deleteByPrimaryKey(String id);

	Integer insert(TClearMerstlBook record);

	Integer insertSelective(TClearMerstlBook record);

    List<TClearMerstlBook> selectByExample(@Param("example") TClearMerstlBookExample example);

    TClearMerstlBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TClearMerstlBook record, @Param("example") TClearMerstlBookExample example);

    int updateByExample(@Param("record") TClearMerstlBook record, @Param("example") TClearMerstlBookExample example);

    int updateByPrimaryKeySelective(TClearMerstlBook record);

    int updateByPrimaryKey(TClearMerstlBook record);
    
    List<TClearMerstlBook> selectPageByExample(Map<String, Object> map);
    
    List<TClearMerstlBook> queryAllList(Map<String, Object> map);
    
    List<String> getMrchnoList(TClearMerstlBook queryModel);
}
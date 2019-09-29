package cn.com.jansh.mapper.game;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.GameTemplate;

/**
 * 活动模板管理数据库接口
 * 
 */
public interface GameTemplateMapper {

	public int insert(GameTemplate gameTemplate);

	/**
	 * 根据活动id查询模板
	 * 
	 * @return
	 */
	public List<GameTemplate> queryByGameid(@Param("gameid") String gameid);

	/**
	 * 根据模版名称查询模版
	 * @param templateName
	 * @param tmptypeid 
	 * @param templateName 
	 * @param gameid 
	 * @return
	 */
	public List<GameTemplate> selectBytemplateName(@Param("tempname") String tempname, @Param("tmptypeid")String tmptypeid, @Param("templateName")String templateName, @Param("gameid")String gameid);

	/**
	 * 通过模版id查询模版
	 * @param tempid
	 * @return
	 */
	public GameTemplate selectByTempid(String tempid);

	/**
	 * 修改模版数据
	 * @param gameTemplate
	 * @return
	 */
	public void editdata(GameTemplate gameTemplate);

	/**
	 * 通过模版名和游戏id查询模版
	 * @param map
	 * @return
	 */
	public List<GameTemplate> selectBytmpNameAndGameid(Map<String, String> map);

	/**
	 * 添加游戏模版分类列表通过gameid
	 * @param gameid
	 * @return
	 */
	public List<CloudGameTemplateTypeEntity> selectTmpTypeByGameid(String gameid);

	/**
	 * 删除模版
	 * @param gameid
	 */
	public void deleteTemp(String gameid);
}

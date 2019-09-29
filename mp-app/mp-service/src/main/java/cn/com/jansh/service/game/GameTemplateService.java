package cn.com.jansh.service.game;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;
import cn.com.jansh.entity.game.GameTemplate;
import cn.com.jansh.model.game.CloudGameTemplateModel;

/**
 * 活动模板管理接口
 * 
 * @author panc
 * @version 1.0
 */
public interface GameTemplateService {

	public void saveTemp(String templateId, String tempname, String gameid, String tmptypeid, String templatename);

	public List<GameTemplate> queryByGameid(String gameid);

	/**
	 * 判断模版是否存在
	 * @param templateName
	 * @param tmptypeid 
	 * @param gameid 
	 * @param templatename2 
	 * @return
	 */
	public List<GameTemplate> selectBytemplateName(String tempname, String tmptypeid, String templatename, String gameid);

	/**
	 * 通过模版id查询模版
	 * @param tempid
	 * @return
	 */
	public GameTemplate selectByTempid(String tempid);

	/**
	 * 修改模版
	 * @param gameTemplate
	 */
	public void editdata(GameTemplate gameTemplate);

	/**
	 * 通过模版名和游戏id查询模版
	 * @param cloudGameTemplateModel
	 * @return
	 */
	public List<GameTemplate> selectBytmpNameAndGameid(CloudGameTemplateModel cloudGameTemplateModel);

	/**
	 * 上传图片，并存库
	 * @param cloudGameTemplateModel
	 * @throws Exception 
	 */
	public void uploadspic(String tempid,String tempname,MultipartFile myfiles) throws Exception;

	/**
	 * 添加游戏模版分类列表通过gameid
	 * @param gameid
	 * @return
	 */
	public List<CloudGameTemplateTypeEntity> selectTmpTypeByGameid(String gameid);

	/**
	 * 删除模板
	 * @param delmsgid
	 */
	public void deleteTemp(String gameid);
}

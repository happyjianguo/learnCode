package cn.com.jansh.mapper.weixin;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.com.jansh.entity.weixin.WXDMenu;
import cn.com.jansh.entity.weixin.WXDMessage;

/**
 * 自定义菜单Mapper
 * @author gll
 * @version 1.0
 */
public interface IWXDMenuMapper {
	/**
	 * 获取所有一级菜单
	 * 
	 * @return
	 */
	public List<WXDMenu> getFirstLeaveMenus(WXDMenu model);

	/**
	 * 根据父级菜单ID获取子菜单
	 * 
	 * @param parentId
	 * @return
	 */
	public List<WXDMenu> getChildMenus(WXDMenu model);

	/**
	 * 获取未同步到微信服务的菜单个数
	 * 
	 * @return
	 */
	public List<WXDMenu> getUnSyncMenus(WXDMenu model);

	/**
	 * 更新菜单状态
	 * 
	 * @param toStat
	 * @param stat
	 */
	public void updateMenusStatus(@Param(value = "toStat") String toStat, @Param(value = "stat") String stat);

	/**
	 * 删除子菜单
	 * 
	 * @param id
	 */
	public void delChildren(Integer parentId);


	/**
	 * 根据menuId查询查单
	 * 
	 * @param id
	 * @return
	 */
	public WXDMenu findById(Integer id);

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 */
	public int add(WXDMenu menu);

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 */
	public void updateWXDMenu(WXDMenu menu);
	
	/**
	 * 查询按钮详情
	 * @param params
	 * @return
	 */
	public List<WXDMessage> queryMenuParams(Map<String, Object> params);
	
	/**
	 * 查询微信所有菜单
	 * @return
	 */
	public List<WXDMenu> getAllChindrenMenus(WXDMenu model);
	
	
	/**
	 * 删除公众号下的按钮
	 * @param map
	 * @return
	 */
	public int delMenus(Map<String,String> map);
	
	
}

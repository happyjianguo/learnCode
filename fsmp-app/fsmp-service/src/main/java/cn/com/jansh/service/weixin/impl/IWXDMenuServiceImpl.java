package cn.com.jansh.service.weixin.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.weixin.WXDMenu;
import cn.com.jansh.mapper.weixin.IWXDMenuMapper;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.service.weixin.IWXDMenuService;
import cn.com.jansh.utils.HttpClientUtil;
import cn.com.jansh.utils.JsonUtil;
import cn.com.jansh.utils.WxMenuUtils;
/**
 * 菜单管理接口Impl
 * @author gll
 * @version 1.0
 */
@Service
public class IWXDMenuServiceImpl implements IWXDMenuService {
	
	private static final Logger logger = LogManager.getLogger(IWXDMenuServiceImpl.class);
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private IWXDMenuMapper iwxdmenumapper;
	
	@Autowired
	private WxAuthService wxAuthService;	
	
	@Autowired
	private GlobalProperties globalProperties;

	/**
	 * 获取子菜单(如果没有parentId为空则获取所有一级菜单)
	 */
	@Override
	public List<WXDMenu> getChildMenus(String parentId,WXDMenu model) {
		List<WXDMenu> menus = new ArrayList<WXDMenu>();
		// 如果没有parentId为空则获取所有一级菜单
		if (parentId != null) {
			menus = iwxdmenumapper.getChildMenus(model);
		} else {
			menus = iwxdmenumapper.getFirstLeaveMenus(model);
		}
		return menus;
	}
	/**
	 * 获取未同步的菜单个数
	 */
	@Override
	public List<WXDMenu> getUnSyncMenus(WXDMenu model) {
		return iwxdmenumapper.getUnSyncMenus(model);
	}
	/**
	 * 同步菜单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean syscMenus(WXDMenu model,String appid) {
		// 获取所有一级菜单
		List<WXDMenu> menus = getChildMenus(null,model);
		Map<String, Object> menuMap = new HashMap<String, Object>();
		boolean flag = false;
		// 判断一级菜单列表是否为空
		if (menus.size() > 0) {
			List<Map<String, Object>> listMenus = new ArrayList<Map<String, Object>>();
			Map<String, Object> menuM = null;
			// 循环一级菜单列表
			for (WXDMenu menu : menus) {
				menuM = getMenuTree(menu);
				listMenus.add(menuM);
			}
			menuMap.put("button", listMenus);
			try {
				String reqJson = "";
				try {
					reqJson = JsonUtil.obj2json(menuMap);
				} catch (JsonProcessingException e1) {
					return false;
				}
				try {
					String accessToken = wxAuthService.getAuthAccessToken(appid);
					Map<String, Object> rsp = null;
					if (StringUtils.isNotBlank(accessToken)) {
						String res = HttpClientUtil.httpPost(globalProperties.getMenusUrl() + accessToken, reqJson);
						rsp = new ObjectMapper().readValue(res, Map.class);
						logger.info("reqJson:{}",reqJson);
						if ((Integer) rsp.get("errcode") == 0) {
							flag = true;
							iwxdmenumapper.updateMenusStatus("1", "0");
						} else {
							logger.info("获取失败{}",rsp.get("errcode"));
							return false;
						}
					} else {
						logger.warn("获取access_token值失败");
						flag = false;
					}
				} catch (Exception e) {
					flag = false;
				}
			} catch (RuntimeException e) {
				logger.warn("发送失败：", e);
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 根据父级菜单组装发送到到微信服务的数据 
	 * 
	 * @param pMenu
	 * @return
	 */
	private Map<String, Object> getMenuTree(WXDMenu pMenu) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> subMenus = new ArrayList<Map<String, Object>>();
		map.put("name", pMenu.getMenuName());
		List<WXDMenu> childrenMenus = iwxdmenumapper.getChildMenus(pMenu);
		if (childrenMenus != null && childrenMenus.size() > 0) {
			Map<String, Object> childMap = null;
			for (WXDMenu childMenu : childrenMenus) {
				childMap = getMenuTree(childMenu);
				subMenus.add(childMap);
			}
		}else{
			map.put("type", pMenu.getMenuType());
			map.put("key", pMenu.getMenuKey());
			if("view".equals(pMenu.getMenuType())){
				map.put("url", pMenu.getMenuUrl());
			}
		}
		if (subMenus.size() > 0) {
			map.put("sub_button", subMenus);
		}
		return map;
	}
	//***********************************************************微信二期****************************************************
	//获取所有菜单项
	@Override
	public List<WXDMenu> getAllMenus(String appid) {
		WXDMenu model =new WXDMenu();
		model.setAppid(appid);
		List<WXDMenu> allmenus=iwxdmenumapper.getAllChindrenMenus(model);
		List<WXDMenu> menus = WxMenuUtils.list2Tree(allmenus);
		return menus;
	}
	@Override
	public boolean updateMenus(String data) throws Exception {
			WXDMenu[] arr = mapper.readValue(data, WXDMenu[].class);
			List<WXDMenu> list = Arrays.asList(arr);
			Map<String,String> map = new HashMap<String,String>();
			if(null != list && list.size() !=0 ){
				for(WXDMenu wxdmenumodel : list){
					map.put("appid", wxdmenumodel.getAppid());
				}
			}
			//清除数据库
			iwxdmenumapper.delMenus(map);
			String parentid = "";
			int order1=0;
			int order2=0;
		    for (WXDMenu wxdmenumodel :  list) {
		    	if(wxdmenumodel.getMenuLevel().equals("1")){
		    		order1++;
		    		order2=0;
		    		wxdmenumodel.setParentid("0");
		    		wxdmenumodel.setMenuorder(String.valueOf(order1));
		    		wxdmenumodel.setMenuid(String.valueOf(order1)+"0");
		    		parentid=wxdmenumodel.getMenuid();
		    	}else if(wxdmenumodel.getMenuLevel().equals("2")){
		    		order2++;
		    		wxdmenumodel.setParentid(parentid);
		    		wxdmenumodel.setMenuorder(String.valueOf(order2));
		    		wxdmenumodel.setMenuid(String.valueOf(order1)+String.valueOf(order2));
		    	}
		    	wxdmenumodel.setMenuStatus("1");
		    	if("view".equals(wxdmenumodel.getMenuType())){
		    		wxdmenumodel.setMenuKey(wxdmenumodel.getMenuUrl());
		    	}else{
		    		wxdmenumodel.setMenuKey(wxdmenumodel.getMenuid());
		    	}
		    	
		    	iwxdmenumapper.add(wxdmenumodel);
		        logger.info("对象集合：{}",wxdmenumodel.toString());
		    }
		    WXDMenu model= new WXDMenu();
			model.setAppid(map.get("appid"));
			//获取未同步的菜单个数
			List<WXDMenu> count = getUnSyncMenus(model);
			if (count.size() > 0) {
				logger.info("菜单数据：{},appid:{}",count.toString(),map.get("appid"));
				if(syscMenus(model,map.get("appid"))) {
					return true;
				} else {
					throw new Exception();
				}
			}
		return true;
	}
}

package cn.com.jansh.controller.weixin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.weixin.IWXDMessageService;
import cn.com.jansh.vo.JsonVO;

/**
 * 微信群发消息
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "sendMessage")
public class WXDSendMessageController {
	Log logger = LogFactory.getLog(WXDSendMessageController.class);
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private IWXDMessageService wxBMessageService;
	
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	
	// 初始化群发消息页面
	@RequestMapping("/init")
	public String manager(Model model) {
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		/*判断当前用户是否有默认公众号*/
		if(null != defaultAccount){
			String appid = defaultAccount.getAppid();
			logger.info("跳转到用户分组管理页面");
			model.addAttribute("appid",appid);
			return "weixin/sendMessage";
		}else{
			logger.info("没有选择默认公众号，请选择默认公众号，跳转到公众号管理界面");
			return "fsmp/wechat/skipwxmanage";
		}
	}
	/**
	 * 群发消息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/sendMsg")
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/sendMessage/init")
	@OperationLog(value=Operation.CREATE,key="350600")
	public JsonVO getData(String appid, String msgType, String msgContents, String groupId) {
		logger.info("群发消息页面!");
		JsonVO jsvo = new JsonVO();
		jsvo = wxBMessageService.groupSendMsg(appid, msgType, msgContents, groupId);
		return jsvo;
	}

}

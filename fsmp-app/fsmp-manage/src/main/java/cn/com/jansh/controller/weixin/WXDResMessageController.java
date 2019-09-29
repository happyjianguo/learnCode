package cn.com.jansh.controller.weixin;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import cn.com.jansh.entity.weixin.WXDMessage;
import cn.com.jansh.entity.weixin.WXDResMessage;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.weixin.IWXDMessageService;
import cn.com.jansh.service.weixin.IWXDResMessageService;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 微信智能回复
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "resMessage")
public class WXDResMessageController {
	private static final Logger logger = LogManager.getLogger(WXDResMessageController.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private IWXDResMessageService resMessageService;	//只能回复service
	
	@Autowired
	private IWXDMessageService messageService;	//消息service
	
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	/**
	 * 跳转初始化页面
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(Model model) {
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		/*判断当前用户是否有默认公众号*/
		if(null != defaultAccount){
			String appid = defaultAccount.getAppid();
			logger.info("智能回复init:{}", appid);
			model.addAttribute("appid",appid);
			return "/weixin/resMessageManager";
		}else{
			logger.info("没有选择默认公众号，请选择默认公众号，跳转到公众号管理界面");
			return "fsmp/wechat/skipwxmanage";
		}
	}
	
	/**
	 * 初始化消息数据
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "getData")
	@ResponseBody
	private JsonVO getData(HttpServletRequest request,WXDResMessage message) {
		logger.info("初始化消息数据");
		JsonVO jsvo = new JsonVO();
		if(StringUtils.isNotBlank(request.getParameter("appid"))){
			message.setAppid(request.getParameter("appid"));
		}
		if(StringUtils.isNotBlank(request.getParameter("resMsgname"))){
			message.setResMsgname(request.getParameter("resMsgname"));
		}
		if(StringUtils.isNotBlank(request.getParameter("msgType"))){
			message.setMsgType(request.getParameter("msgType"));
		}
		List<WXDResMessage> messageList = resMessageService.dataGrid(message);
		jsvo.setData(messageList);
		return jsvo;
	}
	
	/**
	 * 获取消息列表
	 * @param delresmsgid
	 * @return
	 */
	@RequestMapping(value = "getmessages")
	@ResponseBody
	public AjaxObj getmessages(String appid, String msgType){
		logger.info("获取消息列表{},{}",appid,msgType);
		WXDMessage message = new WXDMessage();
		message.setAppid(appid);
		message.setMsgType(msgType);
		List<WXDMessage> messageList = messageService.dataGrid(message);
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj.setObj(messageList);
		return ajaxObj;
	}
	
	/**
	 * 删除消息
	 * 
	 * @param msgId
	 * @return
	 */
	@RequestMapping(value = "delete")
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/resMessage/init")
	@OperationLog(value=Operation.DELETE,key="350500")
	public String delete(String delresmsgid,Model model) {
		logger.info("删除微信消息{}",delresmsgid);
		queryAppid(delresmsgid);
		resMessageService.batchDel(delresmsgid);
		return init(model);
	}

	/**
	 * 利用智能回復id查询得到appid（批量删除和单个删除时使用）
	 * @param delresmsgid
	 * @return
	 */
	private String queryAppid(String delresmsgid) {
		String resmsgid = "";
		if (delresmsgid != null && delresmsgid.length() > 0) {
			String[] idstrs = delresmsgid.split(",");
			List<String> idList = Arrays.asList(idstrs);
			for(int i=0; i<idList.size(); i++){
				resmsgid = idList.get(0);
			}
		}
		WXDResMessage wXDResMessage = resMessageService.selectResMByResmsgid(resmsgid);
		return wXDResMessage.getAppid();
	}
	
	/**
	 * 添加智能回复
	 * @param resMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/resMessage/init")
	@OperationLog(value=Operation.CREATE,key="350501")
	public JsonVO save(WXDResMessage resMsg) {
		logger.info("添加智能回复信息");
		JsonVO rsp = new JsonVO();
		WXDResMessage checkResMsg = new WXDResMessage();
		checkResMsg.setResMsgname(resMsg.getResMsgname());
		checkResMsg.setResMsgKey(resMsg.getResMsgKey());
		List<WXDResMessage> resMessageli =resMessageService.searchByResMsgname(checkResMsg);
		if(resMessageli.size()>0){
			rsp.setMsg("智能回复名称或关键词已经被占用");
			return rsp;
		}
		try {
			resMessageService.add(resMsg);
			rsp.setSuccess(true);
			rsp.setMsg("智能回复新增成功");
		} catch (RuntimeException e) {
			logger.error("添加智能回复信息失败:", e);
			rsp.setMsg("智能回复新增失败");
		}
		return rsp;
	}

	/**
	 * 变更智能回复信息
	 * @param resMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/resMessage/init")
	@OperationLog(value=Operation.UPDATE,key="350502")
	public JsonVO update(WXDResMessage resMsg) {
		logger.info("变更智能回复信息");
		JsonVO rsp = new JsonVO();
		try {
			resMessageService.update(resMsg);
			rsp.setMsg("智能回复信息修改成功");
			rsp.setSuccess(true);
		} catch (RuntimeException e) {
			logger.error("智能回复信息修改失败:", e);
			rsp.setMsg("智能回复信息修改失败");
		}
		return rsp;
	}
	
	/**
	 * 删除智能回复
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchDelete")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/resMessage/init")
	@OperationLog(value=Operation.DELETE,key="350503")
	public JsonVO delete1(String ids) {
		logger.info("删除智能回复信息");
		JsonVO rsp = new JsonVO();
		try {
			resMessageService.batchDel(ids);
			rsp.setSuccess(true);
			rsp.setMsg("删除成功");
		} catch (RuntimeException e) {
			logger.error("删除智能回复信息失败:", e);
			rsp.setMsg("删除失败");
		}
		return rsp;
	}
}

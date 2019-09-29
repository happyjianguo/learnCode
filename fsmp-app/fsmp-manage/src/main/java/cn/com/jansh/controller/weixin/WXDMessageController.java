package cn.com.jansh.controller.weixin;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.wechat.DefaultAccount;
import cn.com.jansh.entity.weixin.NewsMaterial;
import cn.com.jansh.entity.weixin.WXDMessage;
import cn.com.jansh.mapper.wechat.DefaultAccountMapper;
import cn.com.jansh.service.weixin.INewsMaterialService;
import cn.com.jansh.service.weixin.IWXDMessageService;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 微信消息管理
 * @author gll
 * @version 1.0
 */
@Controller
@RequestMapping(value = "message")
public class WXDMessageController {

	private static final Logger logger = LogManager.getLogger(WXDMessageController.class);

	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private IWXDMessageService messageService;	//消息service
	
	@Autowired
	private INewsMaterialService inewsmaterialservice;	//图文消息service
	
	@Autowired
	private DefaultAccountMapper defaultAccountMapper;	//默认公众号 Mapper
	/**
	 * 跳转到管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	private String init(Model model) {
		/*获取登录用户信息*/
		String userid = AppUtil.getUserDetail().getUserid();
		//通过userid查询defaultAccount
		DefaultAccount defaultAccount = defaultAccountMapper.selectByUserid(userid);
		/*判断当前用户是否有默认公众号*/
		if(null != defaultAccount){
			String appid = defaultAccount.getAppid();
			logger.info("跳转到消息页面",appid);
			model.addAttribute("appid2",appid);
			return "/weixin/messageManager";
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
	@RequestMapping(value = "ajax/findByMessage")
	@ResponseBody
	private JsonVO findByMessage(WXDMessage wXDMessage) {
		logger.info("wXDMessage:{}",wXDMessage.toString());
		JsonVO jsvo = new JsonVO();
		wXDMessage.setAppid(wXDMessage.getAppid().trim());
		wXDMessage.setMsgName(wXDMessage.getMsgName().trim());
		wXDMessage.setMsgType(wXDMessage.getMsgType());
		List<WXDMessage> messageList = messageService.dataGrid(wXDMessage);
		jsvo.setData(messageList);
		return jsvo;
	}
	/**
	 * 根据platformId获取图文消息列表
	 * @param platformid
	 * @return
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping(value = "getmediaId")
	public AjaxObj getmediaId(String appid){
		logger.info("根据platformId获取图文消息列表{}",appid);
		AjaxObj ajaxObj = new AjaxObj();
		NewsMaterial materialModel = new NewsMaterial();
		materialModel.setAppid(appid);
		List<NewsMaterial> list = inewsmaterialservice.queryNewsMaterialListByplatformId(materialModel);
		ajaxObj.setObj(list);
		return ajaxObj;
	}
	/**
	 * 新增文本/图文消息
	 * @param name
	 * @param centent
	 * @return
	 */
	@RequestMapping(value = "ajax/textMsgAdd")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/message/init")
	@OperationLog(value=Operation.CREATE,key="350400")
	private JsonVO textMsgAdd(WXDMessage wxdmessagemodel1) {
		logger.info("新增文本消息{},{},{}",wxdmessagemodel1.getMsgName(),wxdmessagemodel1.getMsgType());
		JsonVO jsonVO = new JsonVO();
		try {
			if(messageService.addMsg(wxdmessagemodel1)){
				jsonVO.setMsg("文本消息新增成功");
				jsonVO.setSuccess(true);
			}else{
				jsonVO.setMsg("文本消息名已存在");
				jsonVO.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("新增文本消息失败：", e);
			jsonVO.setMsg("文本消息新增失败");
			jsonVO.setSuccess(false);
			throw e;
		}
		return jsonVO;
	}
	/**
	 * 新增文本/图文消息
	 * 
	 * @param name
	 * @param centent
	 * @return
	 */
	@RequestMapping(value = "ajax/picMsgAdd")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/message/init")
	@OperationLog(value=Operation.CREATE,key="350400")
	private JsonVO picMsgAdd(WXDMessage wxdmessagemodel1) {
		logger.info("新增图文消息{},{},{}",wxdmessagemodel1.getMsgName(),wxdmessagemodel1.getMsgType());
		JsonVO jsonVO = new JsonVO();
		try {
			if(messageService.addMsg(wxdmessagemodel1)){
				jsonVO.setMsg("新增图文消息成功");
				jsonVO.setSuccess(true);
			}else{
				jsonVO.setMsg("图文消息名已存在");
				jsonVO.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("新增图文消息失败：", e);
			jsonVO.setMsg("新增图文消息失败");
			jsonVO.setSuccess(false);
			throw e;
		}
		return jsonVO;
	}
	/**
	 * 修改消息内容
	 * @return
	 */
	@RequestMapping(value="ajax/Msgedit")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/message/init")
	@OperationLog(value=Operation.UPDATE,key="350401")
	public JsonVO Msgedit(WXDMessage wxdmessagemodel1){
		logger.info("修改微信文本消息{},{},{}",wxdmessagemodel1.getMsgId(),wxdmessagemodel1.getMsgName(),wxdmessagemodel1.getMsgType());
		JsonVO jsonVO = new JsonVO();
		try {
			messageService.update(wxdmessagemodel1);
			jsonVO.setMsg("消息修改成功");
			jsonVO.setSuccess(true);
		} catch (Exception e) {
			logger.error("新增图文消息失败：", e);
			jsonVO.setMsg("消息修改失败");
			jsonVO.setSuccess(false);
			throw e;
		}
		return jsonVO;
	}
	
	/**
	 * 删除消息
	 * @param msgId
	 * @return0：删除失败，1：删除成功，2：msgid被占用
	 */
	@RequestMapping(value = "ajax/delete")
	@ResponseBody
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/message/init")
	@OperationLog(value=Operation.DELETE,key="350402")
	public JsonVO delete(String delmsgid) {
		JsonVO jsvo = new JsonVO();
		logger.info("删除微信消息模板");
		int i;
		try {
			i = messageService.deleteMsg(delmsgid);
			jsvo.setResult(i);
			if(0==i){
				jsvo.setMsg("消息模板删除失败");
			}else if(1==i){
				jsvo.setMsg("消息模板删除成功");
			}else if(2==i){
				jsvo.setMsg("该消息模板正在被使用，暂时无法删除");
			}
			jsvo.setSuccess(true);
		} catch (Exception e) {
			jsvo.setSuccess(false);
			logger.error("删除微信消息模板失败{}",e);
		}
		return jsvo;
	}
}

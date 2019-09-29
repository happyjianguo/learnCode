package cn.com.jansh.scheduler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.model.wsfdn.WsfdnOrdercb;
import cn.com.jansh.service.scheduler.FdnCallBackService;
/**
 * 欧非话费充值-回掉函数
 * 网宿流量充值-回调函数
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value = "/fdncb")
public class FdnCallBackController {

	private static final Logger logger = LogManager.getLogger(FdnCallBackController.class);
	
	@Autowired
	private FdnCallBackService fdnCallBackService;
	
	/**
	 * 更新流量订购状态
	 * 
	 * @param sender
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wsordercb", method = RequestMethod.POST)
	public String wsordercb(HttpServletRequest request, @RequestBody String msg) {
		String sender = request.getHeader("Sender");
		if (sender == null || !"FDN".equals(sender)) {
			logger.error("http header 属性  Sender error ：{}", sender);
			return "ok";
		}

		logger.info("添加数据:{},{}", sender, msg);
		try {
			WsfdnOrdercb wsfdnOrdercb = JsonUtil.readObject(msg, WsfdnOrdercb.class);
			fdnCallBackService.wsOrdercb(wsfdnOrdercb);
		} catch (Exception e) {
			logger.error("更新流量订购状态失败：{}", e);
			return "error";
		}

		return "ok";
	}
	
	
	/**
	 * 更新话费订购状态
	 * 
	 * @param sender
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ofordercb", method = RequestMethod.POST)
	public String ofordercb(HttpServletRequest request, @RequestBody String msg) {
		
		String ret_code = request.getParameter("ret_code");
		String sporder_id = request.getParameter("sporder_id");
		String ordersuccesstime = request.getParameter("ordersuccesstime");
		String err_msg = request.getParameter("err_msg");
		Map<String , String > map = new HashMap<String,String>();
		logger.info("回调信息ret_code:{},sporder_id:{},ordersuccesstime:{},err_msg{}",ret_code,sporder_id,ordersuccesstime,err_msg);
		
		map.put("ret_code", ret_code);
		map.put("sporder_id", sporder_id);
		map.put("ordersuccesstime", ordersuccesstime);
		map.put("err_msg", err_msg);
		fdnCallBackService.ofOrdercb(map);
		return "ok";
	}
	
}

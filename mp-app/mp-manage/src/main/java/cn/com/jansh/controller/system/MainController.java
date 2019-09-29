package cn.com.jansh.controller.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import cn.com.jansh.service.component.OuFeiComponent;
/**
 * 主页面控制器
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/main")
public class MainController {

	/*@Autowired
	private CfMainService cfMainService;
	
	@Autowired
	private OuFeiComponent ouFeiComponent;*/

	private static final Logger logger = LogManager.getLogger(MainController.class);

	@RequestMapping("/show")
	public String showMain(Model mode) {
		logger.info("main page");
		return "main/wellcome";
	}

	/**
	 * 获取欧飞订单总金额
	 */
	/*@RequestMapping("/ajax/oforderprice")
	@ResponseBody
	public ViewObject oforderprice() {
		logger.info("获取欧飞订单总金额");
		String price = cfMainService.oforderprice();
		if (StringUtils.isBlank(price)) {
			price = "0.000";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("price", price);
		return new ViewObject(map);
	}*/

	/**
	 * 获取网宿订单总金额
	 */
	/*@RequestMapping("/ajax/wsorderprice")
	@ResponseBody
	public ViewObject wsorderprice() {
		logger.info("获取网宿订单总金额");
		String price = cfMainService.wsorderprice();
		if (StringUtils.isBlank(price)) {
			price = "0.000";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("price", price);
		return new ViewObject(map);
	}*/

	/**
	 * 获取欧飞余额
	 * 
	 * @throws Exception
	 */
	/*@RequestMapping("ajax/ofbalance")
	@ResponseBody
	public ViewObject ofbalance() throws Exception {
		logger.info("获取欧飞余额");
		Map<String, String> resmap = new HashMap<String, String>();
		try {
			String s = ouFeiComponent.ofbalance();
			String s = new String();
			resmap = readOuFeiResult(s);
		} catch (Exception e) {
			logger.error("查询欧飞余额异常，｛｝", e);
		}
		if(resmap.isEmpty()){
			resmap.put("ret_leftcredit", "--.--");
		}
		return new ViewObject(resmap);
	}*/

	/**
	 * (欧飞)解析返回结果
	 * 
	 * @param id
	 * @param result
	 * @throws Exception
	 */
	/*private Map<String, String> readOuFeiResult(String result) throws Exception {
		// 解析返回结果
		if (StringUtils.isNotBlank(result)) {
			try {
				return readStringXml(result);
			} catch (Exception e) {
				logger.error("解析返回结果异常：{}", e);
				throw e;
			}
		}
		return new HashMap<String, String>();
	}*/

		/**
		 * 将StringXml转成Map并返回
		 * 
		 * @param xml
		 * @return
		 * @throws DocumentException
		 */
	/*private static Map<String, String> readStringXml(String xml) throws DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
//			String err_msg = rootElt.elementTextTrim("err_msg"); // 拿到head节点下的子节点title值
//			String retcode = rootElt.elementTextTrim("retcode"); // 获取子节点head下的子节点script
			String ret_leftcredit = rootElt.elementTextTrim("ret_leftcredit"); // 用户余额
//			String userid = rootElt.elementTextTrim("userid");

			// map.put("err_msg", err_msg);
			// map.put("retcode", retcode);
			if (StringUtils.isNotBlank(ret_leftcredit)) {
				map.put("ret_leftcredit", ret_leftcredit);
			} else {
				map.put("ret_leftcredit", "--.--");
			}
			// map.put("userid", userid);
			logger.info("map:{}", map.toString());
		} catch (DocumentException e) {
			logger.error("解析欧飞余额xml异常{}", e);
			throw e;
		}
		return map;
	}*/
}

package cn.com.jansh.scheduler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.service.scheduler.QCellCoreService;

/**
 * 归属地查询接口
 * 
 * @author duanmuyn
 *
 */
@Controller
@RequestMapping(value = "/qcell")
public class QCellCoreController {

	private static final Logger logger = LogManager.getLogger(QCellCoreController.class);

	@Autowired
	private QCellCoreService qCellCoreService;

	/**
	 * 归属地查询
	 * 
	 * @param sender
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/qcell", method = RequestMethod.POST)
	public ViewObject QCellCore(@RequestBody String msg) throws Exception {
		logger.info("{}查询电话归属地{}", msg);
		Map<String, String> map = null;
		try {
			map = JsonUtil.readMapString(msg);
		} catch (Exception e) {
			logger.error("解析报文异常，{}", e);
			throw e;
		}
		return new ViewObject(qCellCoreService.resultQCellCore(map.get("phone")));
	}
}

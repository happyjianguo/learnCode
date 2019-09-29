package cn.com.jansh.controller.personcenter;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.web.servlet.ViewObject;

import cn.com.jansh.entity.system.NoticeEntity;
import cn.com.jansh.service.system.NoticeService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 系统管理-公告管理
 * @author duanmu
 *
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	
	private static final Logger logger = LogManager.getLogger(NoticeController.class);
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "/init")
	public String init(){
		logger.info("进入公告管理页面");
		return "sysmanage/noticemanage/noticemanage";
	}
	
	@RequestMapping(value = "/ajax/getdata")
	@ResponseBody
	private ViewObject<List<NoticeEntity>> findByMessage(NoticeEntity noticeEntity) {
		logger.info("公告数据初始化");
		List<NoticeEntity> li = noticeService.selectNotice(noticeEntity);
		return new ViewObject<List<NoticeEntity>>(li);
	}
	
	/**
	 * 显示添加的公告
	 * @param noticeEntity
	 * @return
	 */
	@RequestMapping("/show")
	@ResponseBody
	public AjaxObj showNotice(NoticeEntity noticeEntity){
		logger.info("公告信息数据初始化");
		AjaxObj ajaxObj = new AjaxObj();
		List<NoticeEntity> li = noticeService.selectNotice(noticeEntity);
		ajaxObj.setObj(li);
		ajaxObj.setSuccess(true);
		return ajaxObj;
	}
}

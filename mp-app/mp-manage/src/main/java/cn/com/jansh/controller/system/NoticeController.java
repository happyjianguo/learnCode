package cn.com.jansh.controller.system;

import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jansh.comm.util.DateUtil;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.constant.Operation;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.entity.system.NoticeEntity;
import cn.com.jansh.model.system.NoticeManageModel;
import cn.com.jansh.service.system.NoticeService;

/**
 * 系统管理-公告管理
 * @author gll
 *
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	
	private static final Logger logger = LogManager.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 公告页面跳转
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(){
		logger.info("进入公告管理页面");
		return "sysmanage/noticemanage/noticemanage";
	}
	/**
	 * 公告页面数据初始化
	 * @param noticeEntity
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ajax/getdata")
	@ResponseBody
	private ViewObject findByMessage(NoticeEntity noticeEntity) throws ParseException {
		logger.info("数据初始化");
		return new ViewObject(noticeService.selectNotice(noticeEntity));
	}
	/**
	 * 跳转入新增公告界面
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	public String addinit(NoticeManageModel noticeManageModel){
		logger.info("进入新增公告界面");
		noticeManageModel.setBegintime(DateUtil.getDate());
		noticeManageModel.setEndtime(DateUtil.getLastDayOfMonth());
		return "sysmanage/noticemanage/noticeadd";
	}
	/**
	 * 新增公告数据
	 * @param noticeManageModel
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value = "/adddata")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@ExceptionHandle("/notice/addinit")
	public String adddata(NoticeManageModel noticeManageModel) throws AppException{
		logger.info("新增公告数据:{}",noticeManageModel);
		if(null!=noticeService.selectByNoticename(noticeManageModel.getNoticename())){
			// 公告已存在
			throw new AppException(AppErrorCode.E210001);
		}else{
			//插入数据
			try {
				noticeService.insertNotice(ModelToEntity(noticeManageModel));
			} catch (ParseException e) {
				logger.error("时间格式转换异常",e);
			}
		}
		return init();
	}
	
	/**
	 * 跳入修改公告页面
	 * @param noticeManageModel
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/editinit")
	public String editinit(NoticeManageModel noticeManageModel, Model model) throws ParseException{
		logger.info("进入修改公告界面");
		noticeManageModel = noticeService.selectNoticeById(noticeManageModel.getNoticeid());
		model.addAttribute(noticeManageModel);
		return "sysmanage/noticemanage/noticeedit";
	}
	/**
	 * 修改公告数据
	 * @param noticeManageModel
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/editdata")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	@ExceptionHandle("/notice/editinit")
	public String editdata(NoticeManageModel noticeManageModel) throws ParseException{
		logger.info("修改公告数据:{}",noticeManageModel);
		noticeService.editinotice(ModelToEntity(noticeManageModel));
		return init();
	}
	/**
	 * 删除页面跳转
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/delinit")
	public String delinit(NoticeManageModel noticeManageModel,Model model) throws ParseException{
		logger.info("跳转删除公告页面:{}",noticeManageModel);
		noticeManageModel = noticeService.selectNoticeById(noticeManageModel.getNoticeid());
		model.addAttribute(noticeManageModel);
		return "sysmanage/noticemanage/noticedel";
	}
	
	/**
	 * 删除公告数据
	 * @return
	 */
	@RequestMapping(value = "/deldata")
	@OperationResult(value = Operation.DELETE)
	@OperationLog(value = Operation.DELETE)
	@ExceptionHandle("/notice/delinit")
	public String deldata(NoticeManageModel noticeManageModel){
		logger.info("跳转删除公告数据:{}",noticeManageModel);
		noticeService.delnotice(noticeManageModel.getNoticeid());
		return init();
	}
	
	/**
	 * 发布公告跳转
	 * @param noticeManageModel
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/fabuinit")
	public String fabuinit(NoticeManageModel noticeManageModel,Model model) throws ParseException{
		logger.info("跳转发布公告页面:{}",noticeManageModel);
		noticeManageModel = noticeService.selectNoticeById(noticeManageModel.getNoticeid());
		model.addAttribute(noticeManageModel);
		return "sysmanage/noticemanage/noticefabu";
	}
	/**
	 * 发布公告数据
	 * @param noticeManageModel
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping(value = "/fabudata")
	@OperationResult(value = Operation.DEFULT)
	@OperationLog(value = Operation.DEFULT)
	@ExceptionHandle("/notice/fabuinit")
	public String fabudata(NoticeManageModel noticeManageModel) throws AppException{
		logger.info("发布公告数据:{}",noticeManageModel);
		NoticeEntity noticeEntity = noticeService.queryNoticeById(noticeManageModel.getNoticeid());
		String status = noticeEntity.getStatus();
		//判断公告状态是否为空
		if(status !=null && status.length() !=0){
			//判断公告状态是否为0，未发布
			if(status.equals(ContextCode.NOTICE_STATUS_WEIFABU.value())){
				noticeService.fabuNotice(noticeManageModel.getNoticeid());
				//判断公告状态是否为1，已发布
			}else if(status.equals(ContextCode.NOTICE_STATUS_YIFABU.value())){
				//公告已发布
				throw new AppException(AppErrorCode.E210002);
			}else{
				//公告状态未知
				throw new AppException(AppErrorCode.E210003);
			}
		}
		return init();
	}
	/**
	 * Model转Entity
	 * @param noticeManageModel
	 * @return
	 */
	private NoticeEntity ModelToEntity(NoticeManageModel noticeManageModel){
		NoticeEntity noticeEntitylist = new NoticeEntity();
		noticeEntitylist.setBegintime(noticeManageModel.getBegintime());
		noticeEntitylist.setCreatetime(noticeManageModel.getCreatetime());
		noticeEntitylist.setEndtime(noticeManageModel.getEndtime());
		noticeEntitylist.setNoticeid(noticeManageModel.getNoticeid());
		noticeEntitylist.setNoticename(noticeManageModel.getNoticename());
		noticeEntitylist.setNoticevalue(noticeManageModel.getNoticevalue());
		noticeEntitylist.setStatus(noticeManageModel.getStatus());
		noticeEntitylist.setUpdatetime(noticeManageModel.getUpdatetime());
		return noticeEntitylist;
	}
}

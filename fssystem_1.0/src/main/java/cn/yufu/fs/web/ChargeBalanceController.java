package cn.yufu.fs.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.yufu.bak.entity.ViewTLogMrchno;
import cn.yufu.bak.service.MerchantXService;
import cn.yufu.bak.service.TermposXService;
import cn.yufu.bak.service.ViewTLogMrchnoService;
import cn.yufu.fs.entity.ChargeBalance;
import cn.yufu.fs.service.ChargeBalanceService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.IdGen;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.mina.CommonTrans;
import cn.yufu.system.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "/ChargeBalance")
public class ChargeBalanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChargeBalanceController.class);
	
	@Autowired
	@Qualifier("fs.ChargeBalanceService")	
	private ChargeBalanceService chargeBalanceService;
	
	@Autowired
	@Qualifier("bak.ViewTLogMrchnoService")	
	private ViewTLogMrchnoService viewTLogMrchnoService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")	
	private MerchantXService merchantXService;
	
	@Autowired
	@Qualifier("bak.TermposXService")	
	private TermposXService termposXService;
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, 
				ChargeBalance queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("query", queryModel);
		
		return "modules/fs/chargeBalance/chargeBalanceList";
	}

	/**
	 * 分页查询明细
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, 
			int curPage, int pageSize, ChargeBalance queryModel) {
		try{
			// 分页设置
			int count = chargeBalanceService.countByExample(queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<ChargeBalance> list = chargeBalanceService.selectPageByExample(queryModel, startResult, endResult);
			
			model.addAttribute("chargeBalanceList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("ChargeBalanceController.getList()调用出现异常。");
		}
		
		return "modules/fs/chargeBalance/chargeBalanceList";
	}
	
	/**
	 * 跳转修改或添加页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance queryModel) {
		
		if (StringUtils.isNotBlank(queryModel.getId())) {
			//更新
			queryModel = chargeBalanceService.selectByPrimaryKey(queryModel.getId());
		}	
		model.addAttribute("info", queryModel);		
		return "modules/fs/chargeBalance/chargeBalanceShow";
	}
	
	/**
	 * 跳转详细页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toDetail" })
	public String toDetail(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance queryModel) {
		
		if (StringUtils.isNotBlank(queryModel.getId())) {
			//更新
			queryModel = chargeBalanceService.selectByPrimaryKey(queryModel.getId());
		}	
		model.addAttribute("info", queryModel);		
		return "modules/fs/chargeBalance/chargeBalanceDetail";
	}
	
	/**
	 * 添加信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"save"})
	@ResponseBody
	public String save(HttpServletRequest req, HttpServletResponse resp, 
			ChargeBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null != info.getVoidTraceNo()
					&& StringUtils.isNotEmpty(info.getVoidSysDate())) {
				map = getTLogMap(info);
				if (SysConst.RESULT_FAIL.equals(map.get(SysConst.RESULT))) {
					return JsonUtil.getJsonString(map);
				}
				ViewTLogMrchno viewTLogMrchno = (ViewTLogMrchno)map.get(SysConst.RESULT_MSG);
				info.setVoidAmt(viewTLogMrchno.getAmttxn());
				info.setVoidTraceCode(viewTLogMrchno.getTxncode() == null ? "" : viewTLogMrchno.getTxncode().toPlainString());
				info.setVoidSysTime(viewTLogMrchno.getTransactiontime());
				info.setVoidRespCode(viewTLogMrchno.getTxnstatus() == null ? "" : viewTLogMrchno.getTxnstatus().toPlainString());
			}
			
			info.setCreateBy(UserUtils.getPrincipal().getLoginName());
			info.setCreateDate(DateUtil.getNow("yyyyMMddHHmmss"));
			if (StringUtils.isEmpty(info.getId())) {
				String sequenceId = chargeBalanceService.getSequenceId();
				info.setId(sequenceId);
				map = chargeBalanceService.insert(info);
			} else {
				map = chargeBalanceService.updateByPrimaryKeySelective(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存手工调账信息失败。");
		}
		return JsonUtil.getJsonString(map);
	}
	
	/**
	 * 调账操作
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"toRegulAcc"})
	@ResponseBody
	public Map<String, Object> toRegulAcc(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
		
		try {
			if (StringUtils.isEmpty(info.getId())) {
				map.put(SysConst.RESULT_MSG, "调账失败，流水ID不存在。");
				return map;
			}
			ChargeBalance chargeBalance = chargeBalanceService.selectByPrimaryKey(info.getId());
			if (StringUtils.isEmpty(chargeBalance.getCreateDate())
					|| StringUtils.isEmpty(chargeBalance.getCardNo())
					|| StringUtils.isEmpty(chargeBalance.getMerNo())
					|| StringUtils.isEmpty(chargeBalance.getTerminalId())) {
				map.put(SysConst.RESULT_MSG, "调账失败，非法参数。");
				return map;
			}
			String now = DateUtil.getNow("yyyyMMddHHmmss");
			String chargeAmt = null == chargeBalance.getChargeAmt() ? "0.00" : chargeBalance.getChargeAmt().setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString();
			String sendMsg = now + "|" + chargeBalance.getId() + "|"
					+ chargeBalance.getCardNo() + "|" + chargeBalance.getMerNo() + "|"
					+ chargeBalance.getTerminalId() + "|" + chargeAmt + "|"
					+ chargeBalance.getId().substring(8);
			if ("0".equals(chargeBalance.getChargeFlag())) {	//减钱
				sendMsg += "|21";	//下调
			} else if ("1".equals(chargeBalance.getChargeFlag())) {
				sendMsg += "|12";	//上调
			} else {
				map.put(SysConst.RESULT_MSG, "调账失败，金额未确定。");
				return map;
			}
			// 发送报文
			CommonTrans con = new CommonTrans();
			String respMsg = con.sendMessage(sendMsg + "|" + chargeBalance.getRemarks());
			chargeBalance.setCreateDate(now);
			if (StringUtils.isNotBlank(respMsg)) {
				String[] split = respMsg.split("\\|");
				if (null != split && split.length > 4) {
					chargeBalance.setRespMsg(split[4]);
					if ("0".equals(split[3])) {
						chargeBalance.setRespCode("0");
						chargeBalanceService.updateByPrimaryKeySelective(chargeBalance);
						map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
						map.put(SysConst.RESULT_MSG, "调账成功。");
						return map;
					}
				}
			}
			chargeBalance.setRespCode("1");
			if (StringUtils.isEmpty(chargeBalance.getRespMsg())) {
				chargeBalance.setRespMsg("调账失败");
			}
			chargeBalanceService.updateByPrimaryKeySelective(chargeBalance);
			map.put(SysConst.RESULT_MSG, "调账失败。");
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT_MSG, "调账失败。");
		}
		return map;
	}
	
	/**
	 * 删除信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"delete"})
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
		
		try {
			if (StringUtils.isNotEmpty(info.getId())) {
				map = chargeBalanceService.deleteByPrimaryKey(info.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 作废信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"cancel"})
	@ResponseBody
	public Map<String, Object> cancel(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
		
		try {
			if (StringUtils.isNotEmpty(info.getId())) {
				map = chargeBalanceService.cancelByPrimaryKey(info.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 图片上传，返回图片所在链接
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"imgUpload"})
	@ResponseBody
	public Map<String, Object> imgUpload(@RequestParam(value="file",required=false)MultipartFile file, 
			HttpServletResponse resp) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == file || file.isEmpty()) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "文件为空");
			return map;
		}
		String imgFilePath = "";
		try {
			String fileName = file.getOriginalFilename();	//获取文件名加后缀
			if (StringUtils.isNotEmpty(fileName)) {
				String tomcatPath = System.getProperty("catalina.home");
				tomcatPath = tomcatPath.replace("\\", "/"); 	//替换 /
				String loginName = UserUtils.getPrincipal().getLoginName();
				imgFilePath = "/fssystem_file/image/" + loginName + "/" + IdGen.uuid() + fileName.substring(fileName.lastIndexOf("."));
				tomcatPath =  tomcatPath + "/webapps" + imgFilePath;
				File img = new File(tomcatPath);
				if (!img.exists()) {
					img.mkdirs();
				}
				file.transferTo(img);
			}
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, imgFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "文件上传失败。");
		}
		return map;
	}
	
	/**
	 * 获取商户名称--备库
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getMerName"})
	@ResponseBody
	public Map<String, Object> getMerName(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
		
		try {
			if (StringUtils.isNotEmpty(info.getMerNo())) {
				List<String> list = merchantXService.getMrchtName(info.getMerNo());
				if (null != list) {
					map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
					map.put(SysConst.RESULT_MSG, list.get(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 判断商户、终端的合法性
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"verifyMer"})
	@ResponseBody
	public Map<String, Object> verifyMer(HttpServletRequest req, HttpServletResponse resp, 
			Model model, ChargeBalance info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
		
		try {
			if (StringUtils.isNotEmpty(info.getMerNo())) {
				List<String> list = termposXService.getMrchno(info.getTerminalId());
				if (null != list && list.size() > 0 && list.get(0).equals(info.getMerNo())) {
					map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = {"getTLogInfo"})
	@ResponseBody
	public  Map<String, Object> getTLogInfo(ChargeBalance info){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = getTLogMap(info);
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "该商户号该终端该卡号不存在该流水");
			e.printStackTrace();
		}
		return map;
	}
	
	private  Map<String, Object> getTLogMap(ChargeBalance info) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ViewTLogMrchno> tLogList = getTLogList(info);
		if (null == tLogList || tLogList.isEmpty()) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "该商户号该终端该卡号不存在该流水");
		} else if (tLogList.size() > 1) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "该商户号该终端该卡号存在多条流水，请检查输入信息");
		} else {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, tLogList.get(0));
		}
		return map;
	}

	private List<ViewTLogMrchno> getTLogList(ChargeBalance info) {
		ViewTLogMrchno queryModel = new ViewTLogMrchno();
		queryModel.setMrchno(info.getMerNo());
		queryModel.setTermcode(info.getTerminalId());
		queryModel.setPan(info.getCardNo());
		if (null != info.getVoidTraceNo()
				&& StringUtils.isNotEmpty(info.getVoidSysDate())) {
			queryModel.setStan(info.getVoidTraceNo().toPlainString());
			queryModel.setTransactiondate(info.getVoidSysDate());
		}
		List<ViewTLogMrchno> example = viewTLogMrchnoService.selectByExample(queryModel);
		return example;
	}
	
}

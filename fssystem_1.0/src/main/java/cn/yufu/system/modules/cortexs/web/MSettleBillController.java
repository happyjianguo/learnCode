package cn.yufu.system.modules.cortexs.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.MACUtil;
import cn.yufu.system.common.utils.MacUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.MSettleBill;
import cn.yufu.system.modules.cortexs.entity.MSettleBillRecord;
import cn.yufu.system.modules.cortexs.entity.SysParameterBean;
import cn.yufu.system.modules.cortexs.service.MSettleBillRecordService;
import cn.yufu.system.modules.cortexs.service.MSettleBillService;
import cn.yufu.system.modules.sys.entity.User;
import cn.yufu.system.modules.sys.utils.UserUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 商户结算开票Controller
 * @author LLG
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "/cortexs/mSettleBill")
public class MSettleBillController extends BaseController {

	@Autowired
	private MSettleBillService mSettleBillService;
	
	@Autowired
	private MSettleBillRecordService mSettleBillRecordService;
	
	@ModelAttribute
	public MSettleBill get(@RequestParam(required=false) String id) {
		MSettleBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mSettleBillService.get(id);
		}
		if (entity == null){
			entity = new MSettleBill();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(MSettleBill mSettleBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MSettleBill> page = mSettleBillService.findPage(new Page<MSettleBill>(request, response), mSettleBill); 
		model.addAttribute("page", page);
		model.addAttribute("MSettleBill", mSettleBill);
		return "modules/cortexs/mSettleBillList";
	}

	@RequestMapping(value = "show")
	public String show(MSettleBill mSettleBill, Model model) {
		//结算周期(存到sys_parameter表)
		List<SysParameterBean> fs_cycleList=mSettleBillService.getSysParameterList("FS_CYCLE");
		if(fs_cycleList!=null&&!fs_cycleList.isEmpty()){
			model.addAttribute("fs_cycleList", fs_cycleList);
		}
		//开票周期
		List<SysParameterBean> fs_kp_cycleList=mSettleBillService.getSysParameterList("FS_KP_CYCLE");
		if(fs_kp_cycleList!=null&&!fs_kp_cycleList.isEmpty()){
			model.addAttribute("fs_kp_cycleList", fs_kp_cycleList);
		}	
		
		model.addAttribute("mSettleBill", mSettleBill);
		return "modules/cortexs/mSettleBillShow";
	}
	
	@RequestMapping(value = "form")
	public String form(MSettleBill mSettleBill, Model model) {
		//结算周期(存到sys_parameter表)
		List<SysParameterBean> fs_cycleList=mSettleBillService.getSysParameterList("FS_CYCLE");
		if(fs_cycleList!=null&&!fs_cycleList.isEmpty()){
			model.addAttribute("fs_cycleList", fs_cycleList);
		}
		//开票周期
		List<SysParameterBean> fs_kp_cycleList=mSettleBillService.getSysParameterList("FS_KP_CYCLE");
		if(fs_kp_cycleList!=null&&!fs_kp_cycleList.isEmpty()){
			model.addAttribute("fs_kp_cycleList", fs_kp_cycleList);
		}	
		
		model.addAttribute("mSettleBill", mSettleBill);
		return "modules/cortexs/mSettleBillForm";
	}

	@RequestMapping(value = "save")
	public String save(MSettleBill mSettleBill, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, mSettleBill)){
			return form(mSettleBill, model);
		}
		//原数据
		MSettleBill settleBill = mSettleBillService.get(mSettleBill.getMrchno());
		//String differentDate = this.getDifferentDate(settleBill, mSettleBill);
		//修改后数据
		mSettleBillService.save(mSettleBill);
		MSettleBillRecord mSettleBillRecord = this.getMSettleBillRecord(request, 
				response, settleBill, mSettleBill);
		//保存修改记录
		mSettleBillRecordService.save(mSettleBillRecord);
		addMessage(redirectAttributes, "保存商户结算开票成功");
		return "redirect:/cortexs/mSettleBill/?repage";
	}
	
	/**
	 * 封装 MSettleBillRecord 属性
	 * @return MSettleBillRecord
	 */
	private MSettleBillRecord getMSettleBillRecord(HttpServletRequest request, 
			HttpServletResponse response, MSettleBill...obj){
		MSettleBillRecord mSettleBillRecord = new MSettleBillRecord();
		User user = new User();
		
		mSettleBillRecord.setMrchName(obj[0].getMrchtName());
		mSettleBillRecord.setMrchno(obj[0].getMrchno());
		mSettleBillRecord.setCreateTime(DateUtils.formatDateTime(new Date()));
		//获取远程客户端信息
		String remoteAddr = StringUtils.getRemoteAddr(request);
		mSettleBillRecord.setRemoteAddr(remoteAddr);
		mSettleBillRecord.setUserAgent(request.getHeader("user-agent"));
		mSettleBillRecord.setRemoteMac(this.getMAC(remoteAddr));
		
		user.setName(UserUtils.getPrincipal().getName());
		mSettleBillRecord.setCreateBy(user);
		mSettleBillRecord.setCreateDate(new Date());
		mSettleBillRecord.setIsNewRecord(true);
		//修改模块
		mSettleBillRecord.setModifyModule("财务结算-商户结算开票信息");
		//原数据
		mSettleBillRecord.setOriginalRecord(getJsonString(obj[0],new JsonConfig()));
		//修改记录
		mSettleBillRecord.setModifyRecord(getJsonString(obj[1],new JsonConfig()));
		
		return mSettleBillRecord;
	}
	
	/**
	 * 取MAC字符串
	 * 
	 * @param obj
	 * @param cfg
	 * @return
	 */
	private  String getMAC(String remoteAddr) {
		String os = MacUtils.getOSName();
		if (os.startsWith("windows")) {
			return MACUtil.getMACAddressInWin(remoteAddr);
		} else if (os.startsWith("linux")) {
			return MACUtil.getMACAddressInLinux(remoteAddr);
		} 
		return null;
	}
	
	/**
	 * 取json字符串
	 * 
	 * @param obj
	 * @param cfg
	 * @return
	 */
	public static String getJsonString(Object obj, JsonConfig cfg) {
		if (obj != null) {
			cfg.setExcludes(new String[]{"createBy","currentUser","page"});
			JSONObject jsonObject = JSONObject.fromObject(obj, cfg);
			return jsonObject.toString();
		}
		return "{}";
	}
}
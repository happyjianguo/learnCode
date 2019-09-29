package cn.yufu.system.modules.cortexs.web;


import java.text.DecimalFormat;
import java.util.Date;

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
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CAccount;
import cn.yufu.system.modules.cortexs.entity.CAccountLog;
import cn.yufu.system.modules.cortexs.service.CAccountLogService;
import cn.yufu.system.modules.cortexs.service.CAccountService;
import cn.yufu.system.modules.sys.utils.UserUtils;


/**
 * CAccount交易类型Controller
 * @author zbq
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "/cortexs/cAccount")
public class CAccountController extends BaseController {

	@Autowired
	private CAccountService cAccountService;
	@Autowired
	private CAccountLogService cAccountLogService;
	
	@ModelAttribute
	public CAccount get(@RequestParam(required=false) String id) {
		CAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cAccountService.get(id);
		}else{
			entity = new CAccount();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CAccount cAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CAccount> page = cAccountService.findPage(new Page<CAccount>(request, response), cAccount); 
		model.addAttribute("page", page);
		model.addAttribute("CAccount", cAccount);
		return "modules/cortexs/cAccountList";
	}

	@RequestMapping(value = "form")
	public String form(CAccount cAccount, Model model) {
		model.addAttribute("cAccount", cAccount);
		return "modules/cortexs/cAccountForm";
	}

	@RequestMapping(value = "save")
	public String save(CAccount cAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cAccount)){
			return form(cAccount, model);
		}
		CAccountLog cAccountLog = new CAccountLog();
		
		cAccountLog.setTranType("调账");
		cAccountLog.setPan(cAccount.getPan());
		cAccountLog.setAccno(cAccount.getAccno());
		cAccountLog.setAmtFlag(cAccount.getAmtFlag());
		DecimalFormat myformat = new DecimalFormat("0.00");
		cAccountLog.setTransferAmt(myformat.format(Float.valueOf(cAccount.getTransferAmt())));
		cAccountLog.setBeforeAvlbal(String.valueOf(myformat.format(Float.valueOf(cAccount.getAvlbal()))));
		if(cAccount.getTransferAmt()!=null||!"".equals(cAccount.getTransferAmt())){
			if("1".equals(cAccount.getAmtFlag())){
				cAccountLog.setAvlbal(String.valueOf(myformat.format(Float.valueOf(cAccount.getAvlbal())+Float.valueOf(cAccount.getTransferAmt()))));
			}
			if("-1".equals(cAccount.getAmtFlag())){
				cAccountLog.setAvlbal(String.valueOf(myformat.format(Float.valueOf(cAccount.getAvlbal())-Float.valueOf(cAccount.getTransferAmt()))));
			}
		}
		cAccountLog.setComments(cAccount.getComments());
		//获取单点登录的用户名和ip。
		String UpdateOper=cn.yufu.system.common.shiro.UserUtils.getLoginName();
		if(UpdateOper.length()>=6){
			cAccountLog.setUpdateOper(UpdateOper.substring(0,6));
		}else{
			cAccountLog.setUpdateOper(UpdateOper);
		}
		cAccountLog.setIp(UserUtils.getSession().getHost());

		cAccountLog.setUpdateLogDate(String.format("%1$tY%1$te%1$tm", new Date()));
		cAccountLog.setUpdateLogTime(String.format("%1$tH%1$tM%1$tS", new Date()));
		cAccountLogService.save(cAccountLog);
		
		if(cAccount.getTransferAmt()!=null||!"".equals(cAccount.getTransferAmt())){
			if("1".equals(cAccount.getAmtFlag())){
				cAccount.setAvlbal(String.valueOf(Float.valueOf(cAccount.getAvlbal())+Float.valueOf(cAccount.getTransferAmt())));
			}
			if("-1".equals(cAccount.getAmtFlag())){
				cAccount.setAvlbal(String.valueOf(Float.valueOf(cAccount.getAvlbal())-Float.valueOf(cAccount.getTransferAmt())));
			}
		}
		cAccount.setTypecode("01");
		cAccountService.save(cAccount);
		addMessage(redirectAttributes, "保存账户余额变更成功");
		return "redirect:/cortexs/cAccount/?repage";
	}


}
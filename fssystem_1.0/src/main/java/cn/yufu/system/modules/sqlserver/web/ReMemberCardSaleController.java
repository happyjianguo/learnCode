package cn.yufu.system.modules.sqlserver.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcel;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.sqlserver.entity.ReMemberCardSale;
import cn.yufu.system.modules.sqlserver.service.ReMemberCardSaleService;

/**
 * 老福卡管理费退款功能
 * @author ZQK
 * @version 2018-06-25
 */
@Controller
@RequestMapping(value = "/sqlServer/ReMemberCardSale")
public class ReMemberCardSaleController extends BaseController {

	@Autowired
	private ReMemberCardSaleService ReMemberCardSaleService;
	
	@RequestMapping(value = {"page"})
	public String page(ReMemberCardSale reMemberCardSale, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<ReMemberCardSale> page = new Page<ReMemberCardSale>(request, response); 
		model.addAttribute("page", page);
		model.addAttribute("refundSumAmt", 0.00);
		model.addAttribute("reMemberCardSale", reMemberCardSale);
		return "modules/sqlServer/reMemberCardSale/reMemberCardSaleList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ReMemberCardSale reMemberCardSale, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		ReMemberCardSale refuntAmt = ReMemberCardSaleService.getRefuntAmt(reMemberCardSale);
		model.addAttribute("refundSumAmt", refuntAmt.getaSaleInegral());
		
		Page<ReMemberCardSale> page = ReMemberCardSaleService.findPage(new Page<ReMemberCardSale>(request, response), reMemberCardSale); 
		model.addAttribute("page", page);
		model.addAttribute("reMemberCardSale", reMemberCardSale);
		return "modules/sqlServer/reMemberCardSale/reMemberCardSaleList";
	}
	
	@RequestMapping(value = "form")
	public String form(Model model, ReMemberCardSale reMemberCardSale) {
		if (null != reMemberCardSale.getPrimayKey() 
				&& StringUtils.isEmpty(reMemberCardSale.getPrimayKey().toPlainString())) {
			//修改
			reMemberCardSale = ReMemberCardSaleService.get(reMemberCardSale);
		}
		model.addAttribute("reMemberCardSale", reMemberCardSale);
		return "modules/sqlServer/reMemberCardSale/reMemberCardSaleForm";
	}
	
	@RequestMapping(value = "save")
	public String save(ReMemberCardSale reMemberCardSale, Model model, 
			RedirectAttributes redirectAttributes) {
		try {
			if (null != reMemberCardSale.getPrimayKey() 
					&& StringUtils.isEmpty(reMemberCardSale.getPrimayKey().toPlainString())) {
				reMemberCardSale.setIsNewRecord(true);
			}
			ReMemberCardSaleService.save(reMemberCardSale);
			addMessage(redirectAttributes, "保存退款信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存退款信息失败，请您稍后再试！");
		}
		return "redirect:/sqlServer/ReMemberCardSale/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ReMemberCardSale reMemberCardSale, Model model, 
			RedirectAttributes redirectAttributes) {
		try {
			if (null != reMemberCardSale.getPrimayKey() 
					&& StringUtils.isEmpty(reMemberCardSale.getPrimayKey().toPlainString())) {
				addMessage(redirectAttributes, "删除退款信息失败，ID不可为空");
				return "redirect:/sqlServer/ReMemberCardSale/?repage";
			} 
			ReMemberCardSaleService.delete(reMemberCardSale);
			addMessage(redirectAttributes, "删除退款信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "删除退款信息失败，请您稍后再试");
		}
		return "redirect:/sqlServer/ReMemberCardSale/?repage";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ReMemberCardSale reMemberCardSale, HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "老福卡退款明细信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<ReMemberCardSale> list = ReMemberCardSaleService.findList(reMemberCardSale);
            new ExportExcel("老福卡退款明细信息", ReMemberCardSale.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出老福卡退款明细信息！失败信息："+e.getMessage());
		}
		return "redirect:/sqlServer/ReMemberCardSale/?repage";
    }
	
}
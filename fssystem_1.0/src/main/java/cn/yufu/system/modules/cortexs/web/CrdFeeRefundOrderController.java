package cn.yufu.system.modules.cortexs.web;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.shiro.UserUtils;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.excel.ExportExcel;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CrdFeeRefundOrder;
import cn.yufu.system.modules.cortexs.service.CrdFeeRefundOrderService;

/**
 * 新福卡退款信息管理Controller
 * @author ZQK
 * @version 2018-06-29
 */
@Controller
@RequestMapping(value = "/cortexs/CrdFeeRefundOrder")
public class CrdFeeRefundOrderController extends BaseController {

	@Autowired
	private CrdFeeRefundOrderService crdFeeRefundOrderService;
	
	@RequestMapping(value = {"page"})
	public String page(CrdFeeRefundOrder crdFeeRefundOrder, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdFeeRefundOrder> page = new Page<CrdFeeRefundOrder>(request, response);
		model.addAttribute("refundSum", 0);
		model.addAttribute("page", page);
		model.addAttribute("crdFeeRefundOrder", crdFeeRefundOrder);
		return "modules/cortexs/crdfeeDetail/crdFeeRefundOrderSaleList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CrdFeeRefundOrder crdFeeRefundOrder, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<CrdFeeRefundOrder> page = crdFeeRefundOrderService.findPage(new Page<CrdFeeRefundOrder>(request, response), crdFeeRefundOrder); 
		
		BigDecimal refundSum = crdFeeRefundOrderService.getRefundSum(crdFeeRefundOrder);
		if (refundSum == null) refundSum = new BigDecimal(0);
		
		model.addAttribute("refundSum", refundSum);
		model.addAttribute("page", page);
		model.addAttribute("crdFeeRefundOrder", crdFeeRefundOrder);
		return "modules/cortexs/crdfeeDetail/crdFeeRefundOrderSaleList";
	}
	
	@RequiresPermissions("cortexs:CrdFeeRefundOrder:edit")
	@RequestMapping(value = "show")
	public String show(CrdFeeRefundOrder qyeryModel, Model model) {
		model.addAttribute("CrdFeeRefundOrder", crdFeeRefundOrderService.get(qyeryModel));
		return "modules/cortexs/crdfeeDetail/CrdFeeRefundOrderShow";
	}
	
	@RequiresPermissions("cortexs:CrdFeeRefundOrder:edit")
	@RequestMapping(value = "form")
	public String form(Model model) {
		model.addAttribute("crdFeeRefundOrder", new CrdFeeRefundOrder());
		return "modules/cortexs/crdfeeDetail/CrdFeeRefundOrderForm";
	}
	
	@RequiresPermissions("cortexs:CrdFeeRefundOrder:edit")
	@RequestMapping(value = "save")
	public String save(CrdFeeRefundOrder crdFeeRefundOrder, Model model,RedirectAttributes redirectAttributes) {
		crdFeeRefundOrder.setOper(UserUtils.getPrincipal().getName());
		try {
			crdFeeRefundOrderService.save(crdFeeRefundOrder);
			addMessage(redirectAttributes, "保存退款信息成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存退款信息失败");
		}
		return "redirect:/cortexs/CrdFeeRefundOrder/?repage";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CrdFeeRefundOrder crdFeeRefundOrder, HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "新福卡退款明细信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CrdFeeRefundOrder> list = crdFeeRefundOrderService.findList(crdFeeRefundOrder);
            new ExportExcel("新福卡退款明细信息", CrdFeeRefundOrder.class).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出退款明细信息！失败信息："+e.getMessage());
		}
		return "redirect:/cortexs/CrdFeeRefundOrder/?repage";
    }
	
}
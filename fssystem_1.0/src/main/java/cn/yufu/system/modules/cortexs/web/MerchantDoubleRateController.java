package cn.yufu.system.modules.cortexs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.cortex.entity.CortexMerchantX;
import cn.yufu.cortex.entity.CortexMerchantXExample;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcel;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.Crdrouting;
import cn.yufu.system.modules.cortexs.entity.MerchantDoubleRate;
import cn.yufu.system.modules.cortexs.service.CrdroutingService;
import cn.yufu.system.modules.cortexs.service.MerchantDoubleRateService;

import com.google.common.base.Strings;

/**
 * 商户双费率Controller
 * @author LLG
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "/cortexs/merchantDoubleRate")
public class MerchantDoubleRateController extends BaseController {

	@Autowired
	private MerchantDoubleRateService merchantDoubleRateService;
	@Autowired
	private CrdroutingService crdroutingService;
	
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService cortexService;
	
	@ModelAttribute
	public MerchantDoubleRate get(@RequestParam(required=false) String id) {
		MerchantDoubleRate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = merchantDoubleRateService.get(id);
		}
		if (entity == null){
			entity = new MerchantDoubleRate();
		}
		return entity;
	}
	
	@RequiresPermissions("cortexs:merchantDoubleRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(MerchantDoubleRate merchantDoubleRate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MerchantDoubleRate> page = merchantDoubleRateService.findPage(new Page<MerchantDoubleRate>(request, response), merchantDoubleRate); 
		List<MerchantDoubleRate> list=page.getList();
		if(list!=null&&list.size()>0){
			Map<String,String> cardBinMap=this.getCardBinMap();
			if(cardBinMap.size()>0){
				for(MerchantDoubleRate mRote:list){
					mRote.setCardBin(cardBinMap.get(mRote.getCardBin().trim())+"("+mRote.getCardBin()+")");
				}
			}
 		}
		model.addAttribute("page", page);
		model.addAttribute("cardBinList", this.crdroutingService.findList(null));		
		return "modules/cortexs/merchantDoubleRateList";
	}

	@RequiresPermissions("cortexs:merchantDoubleRate:view")
	@RequestMapping(value = "form")
	public String form(MerchantDoubleRate merchantDoubleRate, Model model) {
		if(merchantDoubleRate!=null&&!Strings.isNullOrEmpty(merchantDoubleRate.getMerchantId())){
			CortexMerchantXExample example=new CortexMerchantXExample();
			CortexMerchantXExample.Criteria criteria=example.createCriteria();			
			criteria.andMrchnoEqualTo(merchantDoubleRate.getMerchantId());
			List<CortexMerchantX> list=this.cortexService.getCortexMerchantXList(example);
			merchantDoubleRate.setMerchantIdLike(merchantDoubleRate.getMerchantId());
			model.addAttribute("merchantList", list);
		}
		model.addAttribute("cardBinList", this.crdroutingService.findList(null));
		model.addAttribute("merchantDoubleRate", merchantDoubleRate);
		return "modules/cortexs/merchantDoubleRateForm";
	}

	@RequiresPermissions("cortexs:merchantDoubleRate:edit")
	@RequestMapping(value = "save")
	public String save(MerchantDoubleRate merchantDoubleRate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, merchantDoubleRate)){
			return form(merchantDoubleRate, model);
		}
		if(merchantDoubleRate.getIsNewRecord()){
			if("false".equals(this.checkMrch(merchantDoubleRate.getMerchantId(), merchantDoubleRate.getCardBin()))){
				addMessage(redirectAttributes, "保存失败,该商户、该卡bin已设置费率,不能重复设置!");
				return "redirect:/cortexs/merchantDoubleRate/?repage";
			}
		}
		merchantDoubleRateService.save(merchantDoubleRate);
		addMessage(redirectAttributes, "保存商户双费率成功");
		return "redirect:/cortexs/merchantDoubleRate/?repage";
	}
	
	@RequiresPermissions("cortexs:merchantDoubleRate:edit")
	@RequestMapping(value = "delete")
	public String delete(MerchantDoubleRate merchantDoubleRate, RedirectAttributes redirectAttributes) {
		merchantDoubleRateService.delete(merchantDoubleRate);
		addMessage(redirectAttributes, "删除商户双费率成功");
		return "redirect:/cortexs/merchantDoubleRate/?repage";
	}
	
	@RequiresPermissions("cortexs:merchantDoubleRate:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(MerchantDoubleRate merchantDoubleRate, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "商户双费率信息" + DateUtils.getDate("yyyyMMddHHmmss")+ ".xlsx";
			Page<MerchantDoubleRate> page = merchantDoubleRateService.findPage(new Page<MerchantDoubleRate>(request, response,-1), merchantDoubleRate); 
			List<MerchantDoubleRate> list=page.getList();
			if(list!=null&&list.size()>0){
				Map<String,String> cardBinMap=this.getCardBinMap();
				if(cardBinMap.size()>0){
					for(MerchantDoubleRate mRote:list){
						mRote.setCardBin(cardBinMap.get(mRote.getCardBin().trim()));
					}
				}
	 		}
			new ExportExcel("商户双费率信息", MerchantDoubleRate.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商户双费率信息！失败信息：" + e.getMessage());
		}
		return "redirect:/cortexs/merchantDoubleRate/?repage";
	}	
	

	public Map<String,String> getCardBinMap(){
		Map<String,String> map=new HashMap<String,String>();
		List<Crdrouting> list=this.crdroutingService.findList(null);
		for(Crdrouting entity:list){
			map.put(entity.getIid().trim(), entity.getDescr().trim());
		}
		return map;
	}
	
	@ResponseBody
	@RequiresPermissions("cortexs:merchantDoubleRate:edit")
	@RequestMapping(value = "checkMrch")
	public String checkMrch(String merchantId,String cardBin){
		String msg="true";
		MerchantDoubleRate merchantDoubleRate=new MerchantDoubleRate();
		merchantDoubleRate.setMerchantId(merchantId);
		merchantDoubleRate.setCardBin(cardBin);
		List<MerchantDoubleRate> list=this.merchantDoubleRateService.findList(merchantDoubleRate);
		if(list!=null&&list.size()>0){
			msg="false";
		}
		return msg;
	}
	
	@RequestMapping(value = { "getMrchList" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getMrchList(HttpServletRequest req,
			HttpServletResponse resp, Model model, String merchantId) {
		CortexMerchantXExample example=new CortexMerchantXExample();
		CortexMerchantXExample.Criteria criteria=example.createCriteria();			
		criteria.andMrchnoLike("%"+merchantId+"%");
		List<CortexMerchantX> list=this.cortexService.getCortexMerchantXList(example);
		return JsonUtil.getJsonString(list);
	}

}
package cn.yufu.system.modules.cortexs.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.Crdmember;
import cn.yufu.system.modules.cortexs.service.CrdmemberService;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * 会员(卡BIN)信息DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@Controller
@RequestMapping(value = "/cortexs/Crdmember")
public class CrdmemberController extends BaseController {
	
	@Autowired
	private CrdmemberService crdmemberService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Crdmember crdmember, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<Crdmember> page = crdmemberService.findPage(new Page<Crdmember>(request, response), crdmember); 
		model.addAttribute("page", page);
		model.addAttribute("crdmember", crdmember);
		return "modules/cortexs/codeTable/crdmemberList";
	}
	
	@RequiresPermissions("cortexs:crdmember:edit")
	@RequestMapping(value = "show")
	public String show(Crdmember qyeryModel, Model model) {
		model.addAttribute("crdmember", crdmemberService.get(qyeryModel));
		return "modules/cortexs/codeTable/crdmemberForm";
	}
	
	@RequiresPermissions("cortexs:crdmember:edit")
	@RequestMapping(value = "form")
	public String form(Model model) {
		model.addAttribute("crdmember", new Crdmember());
		return "modules/cortexs/codeTable/crdmemberForm";
	}
	
	@RequiresPermissions("cortexs:crdmember:edit")
	@RequestMapping(value = "save")
	public String save(Crdmember crdmember, Model model, RedirectAttributes redirectAttributes) {
		crdmember.setCreateBy(UserUtils.getUser());
		crdmember.setUpdateBy(UserUtils.getUser());
		try {
			crdmemberService.saveCrdmember(crdmember);
			addMessage(redirectAttributes, "保存会员信息成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存会员信息失败");
		}
		return "redirect:/cortexs/Crdmember/?repage";
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(Crdmember crdmember, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			crdmemberService.delete(crdmember);
			map.put("result", "0");
			map.put("desc", "删除会员信息成功!");
		} catch (Exception e) {
			map.put("result", "1");
			map.put("desc", "删除会员信息失败!");
		}
		return map;
	}
	
}

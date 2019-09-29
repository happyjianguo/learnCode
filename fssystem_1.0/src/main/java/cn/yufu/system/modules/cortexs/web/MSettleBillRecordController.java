package cn.yufu.system.modules.cortexs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.MSettleBill;
import cn.yufu.system.modules.cortexs.entity.MSettleBillRecord;
import cn.yufu.system.modules.cortexs.entity.SysParameterBean;
import cn.yufu.system.modules.cortexs.service.MSettleBillRecordService;
import cn.yufu.system.modules.cortexs.service.MSettleBillService;

/**
 * 商户结算开票修改记录Controller
 * @author ZQK
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "/cortexs/mSettleBillRecord")
public class MSettleBillRecordController extends BaseController {
	
	@Autowired
	private MSettleBillService mSettleBillService;

	@Autowired
	private MSettleBillRecordService mSettleBillRecordService;
	
	@ModelAttribute
	public MSettleBillRecord get(@RequestParam(required=false) String id) {
		MSettleBillRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mSettleBillRecordService.get(id);
		}
		if (entity == null){
			entity = new MSettleBillRecord();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(MSettleBillRecord mSettleBillRecord, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		mSettleBillRecord.setModifyModule("财务结算-商户结算开票信息");
		Page<MSettleBillRecord> page = mSettleBillRecordService.findPage(new Page<MSettleBillRecord>(request, response), mSettleBillRecord); 
		model.addAttribute("page", page);
		model.addAttribute("mSettleBillRecord", mSettleBillRecord);
		return "modules/cortexs/mSettleBillRecordList";
	}
	
	@RequestMapping(value = "getList")
	public String getList(MSettleBillRecord mSettleBillRecord, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		List<String> modifyModule = mSettleBillRecordService.getModifyModule(mSettleBillRecord);
		model.addAttribute("modifyModuleList", modifyModule);
		Page<MSettleBillRecord> page = mSettleBillRecordService.findPage(new Page<MSettleBillRecord>(request, response), mSettleBillRecord); 
		model.addAttribute("page", page);
		model.addAttribute("mSettleBillRecord", mSettleBillRecord);
		return "modules/cortexs/merchantModifyRecordList";
	}
	
	@RequestMapping(value = "show")
	public String show(MSettleBillRecord mSettleBillRecord, Model model) {
		//原数据
		MSettleBill originalData = (MSettleBill)JsonUtil.getObject(mSettleBillRecord.getOriginalRecord(), MSettleBill.class);
		//修改后数据
		MSettleBill modifyData = (MSettleBill)JsonUtil.getObject(mSettleBillRecord.getModifyRecord(), MSettleBill.class);
				
		//结算周期(存到sys_parameter表)
		List<SysParameterBean> fs_cycleList = mSettleBillService.getSysParameterList("FS_CYCLE");
		if(fs_cycleList!=null&&!fs_cycleList.isEmpty()){
			for (SysParameterBean sysParameterBean : fs_cycleList) {
				if (sysParameterBean.getParamValue().equals(originalData.getFsCycle())) {
					originalData.setFsCycle(sysParameterBean.getParamName().trim());
				}
				if (sysParameterBean.getParamValue().equals(modifyData.getFsCycle())) {
					modifyData.setFsCycle(sysParameterBean.getParamName().trim());
				}
			}
		}
		//开票周期
		List<SysParameterBean> fs_kp_cycleList = mSettleBillService.getSysParameterList("FS_KP_CYCLE");
		if(fs_kp_cycleList!=null&&!fs_kp_cycleList.isEmpty()){
			for (SysParameterBean sysParameterBean : fs_kp_cycleList) {
				if (sysParameterBean.getParamValue().equals(originalData.getFsKpCycle())) {
					originalData.setFsKpCycle(sysParameterBean.getParamName().trim());
				}
				if (sysParameterBean.getParamValue().equals(modifyData.getFsKpCycle())) {
					modifyData.setFsKpCycle(sysParameterBean.getParamName().trim());
				}
			}
		}
		model.addAttribute("originalData", originalData);
		model.addAttribute("modifyData", modifyData);
		return "modules/cortexs/mSettleBillRecordShow";
	}
}
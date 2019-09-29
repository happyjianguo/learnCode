package com.yufupos.system.modules.pos.web;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;
import com.yufupos.system.common.SysConst;
import com.yufupos.system.common.config.Global;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.utils.DateUtils;
import com.yufupos.system.common.utils.JsonUtil;
import com.yufupos.system.common.utils.POIUtils;
import com.yufupos.system.common.utils.StringUtils;
import com.yufupos.system.common.utils.excel.ExportExcel;
import com.yufupos.system.common.utils.excel.ImportExcel;
import com.yufupos.system.common.web.BaseController;
import com.yufupos.system.modules.cortex.entity.CortexArea;
import com.yufupos.system.modules.cortex.service.CortexAreaService;
import com.yufupos.system.modules.pos.entity.EdcTerminal;
import com.yufupos.system.modules.pos.entity.EdcTerminalX;
import com.yufupos.system.modules.pos.entity.EdcTerminalXExcel;
import com.yufupos.system.modules.pos.entity.PManager;
import com.yufupos.system.modules.pos.service.EdcTerminalService;
import com.yufupos.system.modules.pos.service.PManagerService;
import com.yufupos.system.modules.sys.entity.Dict;
import com.yufupos.system.modules.sys.utils.DictUtils;

/**
 * 终端补充信息Controller
 * @author llg
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "/pos/edcTerminalX")
public class EdcTerminalXController extends BaseController {

	@Autowired
	private EdcTerminalService EdcTerminalService;
	
	@Autowired
	private PManagerService pManagerService;
	
	@Autowired
	private CortexAreaService cortexAreaService;
	
	@ModelAttribute
	public EdcTerminal get(@RequestParam(required=false) String terminalId,
			@RequestParam(required=false) String merchantId) {
		EdcTerminal entity = null;
		List<EdcTerminal> list = null;
		if (StringUtils.isNotBlank(terminalId)){
			list = EdcTerminalService.getOneData(terminalId, merchantId);
		}
		if (list == null || list.size() == 0){
			entity = new EdcTerminal();
		} else{
			entity = list.get(0);
			entity.setId(terminalId);
		}
		return entity;
	}
	
	@RequiresPermissions("pos:edcTerminalX:view")
	@RequestMapping(value = {"list", ""})
	public String list(EdcTerminal EdcTerminal, HttpServletRequest request, HttpServletResponse response, Model model) {
		//设置商户顾问	
		if(!Strings.isNullOrEmpty(EdcTerminal.getTerminalArea())){
			model.addAttribute("managerList", pManagerService.findListByRoleId(null, EdcTerminal.getTerminalArea()));
		}
		model.addAttribute("provinceList", cortexAreaService.findList(new CortexArea(null,1)));
		if (StringUtils.isNotBlank(EdcTerminal.getTerminalProvince())) {
			//设置省份
			Integer provinceId=Strings.isNullOrEmpty(EdcTerminal.getTerminalProvince())?null:Integer.valueOf(EdcTerminal.getTerminalProvince());
			model.addAttribute("cityList", cortexAreaService.findList(new CortexArea(provinceId,2)));		
			if (StringUtils.isNotBlank(EdcTerminal.getTerminalCity())) {
				//设置区域
				Integer cityId=Strings.isNullOrEmpty(EdcTerminal.getTerminalCity())?null:Integer.valueOf(EdcTerminal.getTerminalCity());
				model.addAttribute("zoneList", cortexAreaService.findList(new CortexArea(cityId,null)));
			}
		}
		
		Page<EdcTerminal> page = EdcTerminalService.findPage(new Page<EdcTerminal>(request, response), EdcTerminal); 
		List<EdcTerminal> list = page.getList();
		if (list == null || list.size() == 0) {
			
		}else {
			//获取商户经理集合
			Map<String, String> managerMap = getManagerMap();
			//获取省市区集合
			Map<String, String> areaMap = getAreaMap();
			
			for (EdcTerminal edcTerminalX : list) {
				//更改商户顾问为具体名称
				edcTerminalX.setMerchantAdvisor(managerMap.get(edcTerminalX.getMerchantAdvisor()));
				//更改省市区为对应的名称
				edcTerminalX.setTerminalProvince(areaMap.get(edcTerminalX.getTerminalProvince()));
				edcTerminalX.setTerminalCity(areaMap.get(edcTerminalX.getTerminalCity()));
				edcTerminalX.setTerminalZone(areaMap.get(edcTerminalX.getTerminalZone()));
			}
		}
		model.addAttribute("page", page);
		return "modules/pos/edcTerminalXList";
	}

	@RequiresPermissions("pos:edcTerminalX:view")
	@RequestMapping(value = "form")
	public String form(EdcTerminal edcTerminal, Model model) {
		//设置省份
		model.addAttribute("provinceList", cortexAreaService.findList(new CortexArea(null,1)));
		//设置城市
		Integer provinceId=Strings.isNullOrEmpty(edcTerminal.getTerminalProvince())?null:Integer.valueOf(edcTerminal.getTerminalProvince());
		model.addAttribute("cityList", cortexAreaService.findList(new CortexArea(provinceId,2)));		
		//设置区域
		Integer cityId=Strings.isNullOrEmpty(edcTerminal.getTerminalCity())?null:Integer.valueOf(edcTerminal.getTerminalCity());
		model.addAttribute("zoneList", cortexAreaService.findList(new CortexArea(cityId,null)));
		//设置商户顾问	
		if(!Strings.isNullOrEmpty(edcTerminal.getTerminalArea())){
			model.addAttribute("managerList", pManagerService.findListByRoleId(null, edcTerminal.getTerminalArea()));
		}
		model.addAttribute("edcTerminal", edcTerminal);
		return "modules/pos/edcTerminalXForm";
	}


	@RequiresPermissions("pos:edcTerminalX:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(EdcTerminal edcTerminal) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EdcTerminalService.save(edcTerminal);
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "保存终端补充信息成功");			
		} catch (Exception e) {
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存终端补充信息失败");
		}
		return JsonUtil.getJsonString(map);

	}	
	
	@RequiresPermissions("pos:edcTerminalX:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EdcTerminal edcTerminal, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "终端补充信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			//Page<EdcTerminal> page = EdcTerminalService.findPage(new Page<EdcTerminal>(request, response ,-1), edcTerminal); 
			List<EdcTerminal> findList = EdcTerminalService.findList(edcTerminal);
			new ExportExcel("终端补充信息", EdcTerminal.class).setDataList(findList).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出终端补充信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/edcTerminalX/list?repage";
    }
	
	/**
	 * 导入终端补充信息
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:edcTerminalX:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+"/pos/edcTerminalX/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EdcTerminalXExcel> list = ei.getDataList(EdcTerminalXExcel.class);
			EdcTerminalX edcTerminalX = null;
			for (EdcTerminalXExcel entity : list) {
				try {
					if (entity != null && !Strings.isNullOrEmpty(entity.getTerminalId())) {
						//判断终端号是否已存在(基础表不存在,失败)
						List<EdcTerminal> oneData = EdcTerminalService.getOneData(entity.getTerminalId(),null);
						if (oneData != null && oneData.size() > 0) {
							edcTerminalX = new EdcTerminalX();
							edcTerminalX.setTerminalId(entity.getTerminalId());
							edcTerminalX.setStoreContacts(entity.getStoreContacts());
							edcTerminalX.setStorePhone(entity.getStorePhone());
							if (StringUtils.isNotBlank(entity.getTerminalArea())) {
								edcTerminalX.setTerminalArea(entity.getTerminalArea().split("_")[1]);
							}
							if (StringUtils.isNotBlank(entity.getMerchantAdvisor())) {
								edcTerminalX.setMerchantAdvisor(entity.getMerchantAdvisor().split("_")[1]);
							}
							if (StringUtils.isNotBlank(entity.getTerminalProvince())) {
								edcTerminalX.setTerminalProvince(entity.getTerminalProvince().split("_")[1]);
							}
							if (StringUtils.isNotBlank(entity.getTerminalCity())) {
								edcTerminalX.setTerminalCity(entity.getTerminalCity().split("_")[1]);
							}
							if (StringUtils.isNotBlank(entity.getTerminalZone())) {
								edcTerminalX.setTerminalZone(entity.getTerminalZone().split("_")[1]);
							}
							edcTerminalX.setTerminalPosition(entity.getTerminalPosition());
							
							List<EdcTerminalX> terminalX = EdcTerminalService.getEdcTerminalX(entity.getTerminalId());
							if (terminalX != null && terminalX.size() > 0) {
								//更新
								edcTerminalX.preUpdate();
								EdcTerminalService.importUpdate(edcTerminalX);
								successNum++;
							}else{
								//插入
								edcTerminalX.setUpgradeVersion("0");
								edcTerminalX.setUpgradeDate(DateUtils.getDate("yyyyMMdd"));
								edcTerminalX.preInsert();
								EdcTerminalService.importInsert(edcTerminalX);
								successNum++;
							}
						}else{
							failureMsg.append("<br/>"+entity.getTerminalId()+"终端号不存在基础信息表中!");
							failureNum++;
						}
					}else{
						failureMsg.append("<br/>失败原因:终端号为必填项!");
						failureNum++;
					}
				} catch (Exception e) {
					failureMsg.append("<br/>" + entity.getTerminalId() + " 导入失败："
							+ e.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条终端补充信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条终端补充信息"
					+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入终端补充信息！失败信息："+e.getMessage());
		}
		return "redirect:"+"/pos/edcTerminalX/list?repage";
    }
	
	/**
	 * 下载导入终端补充信息数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pos:edcTerminalX:view")
	@RequestMapping(value = "templateTerminalX")
	public String importTemplate(HttpServletRequest req,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			//String headName="终端补充信息 ";
			// 选择模板文件：				
			//String path =req.getSession().getServletContext().getRealPath("/")+ "excelExportTemp/TERMINALX_EXPORT_TEMP.xls"; // excel模板
			//InputStream in = new FileInputStream(new File(path));
			workbook = new HSSFWorkbook();// excel文件对象
			HSSFSheet sheet1 = workbook.createSheet("终端补充信息");// 工作表对象
			// 设置标题样式
			this.setHeadCellStyles(workbook, sheet1);
			// 设置列头样式
			this.setTitleCellStyles(workbook, sheet1);
			// 设置数据样式
			this.setDataCellStyles(workbook, sheet1);
			// 创建一个隐藏页、隐藏数据集和名称管理
			this.creatHideSheet(workbook);
			// 创建标题和列头数据
			String headName = "终端补充信息";
			this.creatAppRowHead(sheet1, headName);
			// 设置下拉框
			this.createSelectValidate(sheet1);

			/**************************** 输出流 *****************************************/
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filename = POIUtils.encodeFileName(req, headName);
			OutputStream os = response.getOutputStream();// 取得输出流
			response.setHeader("Content-disposition", "attachment;filename="
					+ filename + ".xls");
			workbook.write(os);
			
			os.close();
			System.out.println("导出成功!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:"+"/pos/edcTerminalX/list?repage";
	}
	
	@RequiresPermissions("pos:edcTerminalX:edit")
	@RequestMapping(value = "delete")
	public String delete(EdcTerminal EdcTerminal, RedirectAttributes redirectAttributes) {
		EdcTerminalService.delete(EdcTerminal);
		addMessage(redirectAttributes, "删除终端补充信息成功");
		return "redirect:"+"/pos/edcTerminalX/list?repage";
	}
	
	/**
	 * 升级终端
	 * @param terminalIds 终端编号
	 * @author Administrator
	 *
	 */
	@RequiresPermissions("pos:edcTerminalX:edit")
	@RequestMapping(value = "toUpgradeTerminal")
	@ResponseBody
	public Map<String, String> toUpgradeTerminal(String terminalIds, String upgradeDate) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			//得到要升级的终端编号
			String terminalIdsStr[] = StringUtils.split(terminalIds, '|');
			for (int i = 0; i < terminalIdsStr.length; i++) {
				String terminalId = terminalIdsStr[i];
				EdcTerminalService.upgradeTerminal(terminalId, upgradeDate);
			}
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "升级终端成功");			
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "升级终端失败");
		}
		return map;
	}
	
	/**
	 * 重置终端的历史版本号，以便对所有的终端再次进行版本升级
	 * @param terminalIds 终端编号
	 * @author Administrator
	 *
	 */
	@RequiresPermissions("pos:edcTerminalX:edit")
	@RequestMapping(value = "toReset")
	@ResponseBody
	public Map<String, String> toReset() {
		Map<String,String> map = new HashMap<String,String>();
		try {
			EdcTerminalService.reset();
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "重置终端信息成功");			
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "重置终端信息失败");
		}
		return map;
	}

	/**
	 * 获取商户经理集合
	 * @author Administrator
	 *
	 */
	private Map<String, String> getManagerMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<PManager> listByRoleId = pManagerService.findListByRoleId("", "");
		for (PManager pManager : listByRoleId) {
			map.put(pManager.getId(), pManager.getManagerName());
		}
		return map;
	}
	
	/**
	 * 获取省、市、区集合
	 * @author Administrator
	 *
	 */
	private Map<String, String> getAreaMap() {
		Map<String, String> map = new HashMap<String, String>();
		CortexArea cortexArea=new CortexArea();
		cortexArea.setIsuse("1");
		List<CortexArea> list = cortexAreaService.findList(cortexArea);
		for (CortexArea area : list) {
			map.put(area.getId(), area.getProvinceCity());
		}
		return map;
	}
	
	/*********************** EXCEL导出模板 ********************************/
	private HSSFWorkbook workbook = null;
	private HSSFCellStyle titleStyle = null;
	private HSSFCellStyle dataStyle = null;
	private HSSFCellStyle headStyle = null;
	
	/**
	 * HEAD样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	public void setHeadCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
		headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		headStyle.setFont(font);
	}
	
	/**
	 * 列头样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	public void setTitleCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
		titleStyle = workbook.createCellStyle();

		// 设置边框
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置背景色
		titleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置居中
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11); // 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		titleStyle.setFont(font);// 选择需要用到的字体格式
		// 设置自动换行
		titleStyle.setWrapText(true);
		// 设置列宽 ,第一个参数代表列id(从0开始),第2个参数代表宽度值
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 7000);
		sheet.setColumnWidth(2, 7000);
		sheet.setColumnWidth(3, 7000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 7000);
	}
	
	/**
	 * 数据样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	public void setDataCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
		dataStyle = workbook.createCellStyle();

		// 设置边框
		dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置背景色
		dataStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置居中
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11); // 设置字体大小
		dataStyle.setFont(font);// 选择需要用到的字体格式
		// 设置自动换行
		dataStyle.setWrapText(true);
	}
	
	/**
	 * 创建隐藏页和数据域
	 * 
	 * @param workbook
	 * @param hideSheetName
	 */
	public void creatHideSheet(HSSFWorkbook workbook) {
		/**************** 创建区域和商户顾问sheet *****************/
		HSSFSheet areaAndMerchantSheet = workbook
				.createSheet("areaAndMerchantSheet");// 隐藏一些信息
		//获取区域列表
		List<Dict> dictList = DictUtils.getDictList("MERCHANT_AREA");
		List<String> areaList = null;
		String dictValue = "";
		String dictLabel = "";
		Name name = null;
		for (int i = 0; i < dictList.size(); i++) {
			HSSFRow areaAndMerchantRow = areaAndMerchantSheet.createRow(i);
			dictValue = dictList.get(i).getValue().toString();
			dictLabel = dictList.get(i).getLabel().toString();
			areaList = new ArrayList<>();
			areaList.add(dictLabel + "_" + dictValue);
			// 获取商户顾问LIST
			List<PManager> storeManagerList = pManagerService.findListByRoleId("",dictValue);
			//添加“区域--->商户顾问” 名称
			name = workbook.createName();
			name.setNameName(dictLabel.trim() + "_" + dictValue.trim());
			for (PManager pManager : storeManagerList) {
				areaList.add(pManager.getManagerName().trim() + "_" + pManager.getId().trim());
			}
			this.creatRow(areaAndMerchantRow, areaList);
			name.setRefersToFormula("areaAndMerchantSheet!$B$" + (i + 1) + ":$"
					+ this.getcellColumnFlag(storeManagerList.size() + 1) + "$"
					+ (i + 1));
			
		}
		name = workbook.createName();
		name.setNameName("terminalArea");
		name.setRefersToFormula("areaAndMerchantSheet!$A$1:$A$"+ dictList.size());
		// 设置隐藏页标志
		workbook.setSheetHidden(workbook.getSheetIndex("areaAndMerchantSheet"),true);
		
		/****************************************************** 创建省市区 ***************************************************/
		HSSFSheet provinceAndCityAndZoneSheet = workbook.createSheet("provinceAndCityAndZoneSheet");// 隐藏一些信息
		//省份
		List<CortexArea> provinceList = cortexAreaService.findList(new CortexArea(null,1));
		List<String> rowProvinceList = null;
		Map<Integer, List<CortexArea>> map = new HashMap<>();
		String provinceId = "";
		String provinceCity = "";
		for(int i = 0; i < provinceList.size(); i++){
			HSSFRow provinceAndCityRow = provinceAndCityAndZoneSheet.createRow(i);
			provinceId = provinceList.get(i).getId().toString();
			provinceCity = provinceList.get(i).getProvinceCity();
			rowProvinceList = new ArrayList<>();
			rowProvinceList.add(provinceCity + "_" + provinceId);
			//根据省获取城市List
			List<CortexArea> cityList = cortexAreaService.findList(new CortexArea(Integer.parseInt(provinceId), 2));
			map.put(i, cityList);
			// 添加“省--->市” 名称
			name = workbook.createName();
			name.setNameName(provinceCity + "_" + provinceId);
			for(int j = 0; j < cityList.size(); j++){
				rowProvinceList.add(cityList.get(j).getProvinceCity() + "_" + cityList.get(j).getId().toString());
			}
			this.creatRow(provinceAndCityRow, rowProvinceList);
			name.setRefersToFormula("provinceAndCityAndZoneSheet!$B$" + (i + 1) + ":$"
					+ this.getcellColumnFlag(cityList.size() + 1) + "$"
					+ (i + 1));
		}
		name = workbook.createName();
		name.setNameName("terminalProvince");
		name.setRefersToFormula("provinceAndCityAndZoneSheet!$A$1:$A$"+ provinceList.size());
		/*************************************************** 市-区 start ***************************************************/
		int total = provinceList.size();
		List<String> cityZoneList = null;
		int count = 0;
		String cityId = "";
		String cityZone = "";
		for(int i = 0; i < total; i++){
			List<CortexArea> cityCity = map.get(i);
			for(int j = 0; j < cityCity.size(); j++){
				count++;
				HSSFRow cityZoneRow = provinceAndCityAndZoneSheet.createRow(total + count);
				cityId = cityCity.get(j).getId().toString();
				cityZone = cityCity.get(j).getProvinceCity().trim().toString();
				cityZoneList = new ArrayList<>();
				cityZoneList.add(cityZone + "_" + cityId);
				List<CortexArea> zoneList = cortexAreaService.findList(new CortexArea(Integer.parseInt(cityId),null));
				// 添加“市--->区” 名称
				name = workbook.createName();
				name.setNameName(cityZone + "_" + cityId);
				for(int k = 0; k < zoneList.size(); k++){
					cityZoneList.add(zoneList.get(k).getProvinceCity() + "_" + zoneList.get(k).getId());
				}
				this.creatRow(cityZoneRow, cityZoneList);
				name.setRefersToFormula("provinceAndCityAndZoneSheet!$B$" + (total + count + 1) + ":$"
						+ this.getcellColumnFlag(zoneList.size() + 1) + "$"
						+ (total + count + 1));
			}
		}
		name = workbook.createName();
		name.setNameName("cityAndZone");
		name.setRefersToFormula("provinceAndCityAndZoneSheet!$A$"+(total+1)+":$A$"+ count);
		/*************************************************** 市-区 end ***************************************************/
		// 设置隐藏页标志
		workbook.setSheetHidden(workbook.getSheetIndex("provinceAndCityAndZoneSheet"),true);
	}
	
	/**
	 * 创建标题应用列头
	 * 
	 * @param userinfosheet1
	 * @param userName
	 */
	public void creatAppRowHead(HSSFSheet userinfosheet1, String headName) {
		// 设置标题
		HSSFRow rowHead = userinfosheet1.createRow(0);
		userinfosheet1.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));
		POIUtils.createCell(rowHead, (short) 0, headName, headStyle);
		rowHead.setHeight((short) (27 * 20));

		// 设置表头
		HSSFRow row = userinfosheet1.createRow(1);

		HSSFCell serialNumberCell = row.createCell(0);
		serialNumberCell.setCellValue("终端号");
		serialNumberCell.setCellStyle(titleStyle);

		HSSFCell titleCell = row.createCell(1);
		titleCell.setCellValue("门店联系人");
		titleCell.setCellStyle(titleStyle);

		HSSFCell proposeTimeCell = row.createCell(2);
		proposeTimeCell.setCellValue("门店电话");
		proposeTimeCell.setCellStyle(titleStyle);

		HSSFCell systemCell = row.createCell(3);
		systemCell.setCellValue("区域");
		systemCell.setCellStyle(titleStyle);

		HSSFCell groupCell = row.createCell(4);
		groupCell.setCellValue("商户顾问");
		groupCell.setCellStyle(titleStyle);

		HSSFCell Cell5 = row.createCell(5);
		Cell5.setCellValue("省份");
		Cell5.setCellStyle(titleStyle);

		HSSFCell Cell7 = row.createCell(6);
		Cell7.setCellValue("城市");
		Cell7.setCellStyle(titleStyle);

		HSSFCell groupCell8 = row.createCell(7);
		groupCell8.setCellValue("区");
		groupCell8.setCellStyle(titleStyle);

		HSSFCell groupCell9 = row.createCell(8);
		groupCell9.setCellValue("终端装机地址");
		groupCell9.setCellStyle(titleStyle);
	}
	
	/**
	 * 添加下拉框限制
	 * 
	 * @param sheet1
	 */
	public void createSelectValidate(HSSFSheet sheet1) {
		// 第四列:区域
		DVConstraint constraint = DVConstraint.createFormulaListConstraint("terminalArea");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(2, 499, 3, 3);
		DataValidation data_validation_list = new HSSFDataValidation(regions,constraint);
		sheet1.addValidationData(data_validation_list);
		
		// 第五列:商户顾问
		DVConstraint constraintModel = DVConstraint.createFormulaListConstraint("INDIRECT($D$3:$D$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsModel = new CellRangeAddressList(2, 499, 4,4);
		DataValidation data_validation_Model = new HSSFDataValidation(
				regionsModel, constraintModel);
		sheet1.addValidationData(data_validation_Model);

		// 第六列:省份
		DVConstraint constraintTerminalProvince = DVConstraint.createFormulaListConstraint("terminalProvince");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsTerminalProvince = new CellRangeAddressList(2, 499,5, 5);
		DataValidation data_validation_TerminalProvince = new HSSFDataValidation(
				regionsTerminalProvince, constraintTerminalProvince);
		sheet1.addValidationData(data_validation_TerminalProvince);

		// 第七列:城市
		DVConstraint constraintTerminalCity = DVConstraint.createFormulaListConstraint("INDIRECT($F$3:$F$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsTerminalCity = new CellRangeAddressList(2,499, 6, 6);
		DataValidation data_validation_TerminalCity = new HSSFDataValidation(
				regionsTerminalCity, constraintTerminalCity);
		sheet1.addValidationData(data_validation_TerminalCity);
		
		// 第八列:区
		DVConstraint constraintTerminalZone = DVConstraint.createFormulaListConstraint("INDIRECT($G$3:$G$500)");
		// 作用域：起始行、终止行、起始列、终止列
		CellRangeAddressList regionsTerminalZone = new CellRangeAddressList(2,499, 7, 7);
		DataValidation data_validation_TerminalZone = new HSSFDataValidation(
				regionsTerminalZone, constraintTerminalZone);
		sheet1.addValidationData(data_validation_TerminalZone);
	}
	
	/**
	 * 创建一列数据
	 * 
	 * @param currentRow
	 * @param textList
	 */
	public void creatRow(HSSFRow currentRow, List<String> textList) {
		if (textList != null && textList.size() > 0) {
			int i = 0;
			for (String cellValue : textList) {
				HSSFCell userNameLableCell = currentRow.createCell(i++);
				userNameLableCell.setCellValue(cellValue);
			}
		}
	}
	
	// 根据数据值确定单元格位置（比如：28-AB）
	private String getcellColumnFlag(int num) {
		String columFiled = "";
		int chuNum = 0;
		int yuNum = 0;
		if (num == 1) {
			columFiled = this.doHandle(2);
			return columFiled;
		}
		if (num > 1 && num <= 26) {
			columFiled = this.doHandle(num);
		} else {
			chuNum = num / 26;
			yuNum = num % 26;

			columFiled += this.doHandle(chuNum);
			columFiled += this.doHandle(yuNum);
		}
		return columFiled;
	}
	
	private String doHandle(final int num) {
		String[] charArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		return charArr[num - 1].toString();
	}
}
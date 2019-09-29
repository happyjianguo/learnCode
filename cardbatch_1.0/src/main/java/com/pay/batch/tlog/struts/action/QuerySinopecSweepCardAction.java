/**
 *包名:com.pay.batch.tlog.struts.action
 *描述:package com.pay.batch.tlog.struts.action;
 */
package com.pay.batch.tlog.struts.action;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.Region;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.tlog.bean.QuerySinopecSweepCardBean;
import com.pay.batch.tlog.dao.QuerySinopecSweepCardDao;
import com.pay.batch.tlog.struts.form.SinopecSweepCardUploadForm;
import com.pay.merInfoStatistics.bean.MerchantInfo;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.POIUtils;
import com.pay.util.PageBean;

/**
 * QuerySinopecSweepCardAction.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年5月16日
 * 描述:中石化扫码刷卡统计
 */
public class QuerySinopecSweepCardAction extends BaseDispatchAction{
	
	private static final Logger logger = Logger.getLogger(QuerySinopecSweepCardAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	public ActionForward toQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = 0;
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<MerchantInfo> merchantInfoList = new LinkedList<MerchantInfo>();
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("info", "请导入查询模板进行查询");
			if (merchantInfoList != null && !merchantInfoList.isEmpty()) {
				request.setAttribute("merchantInfoList", merchantInfoList);
			}
		} catch (Exception e) {
			logger.error("QuerySinopecSweepCardAction--toQuery--Exception:", e);
		}
		return mapping.findForward("showSinopecSweepCardList");
	}
	
	
	// 导出
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		log.info("开始下载excel模板");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("请按模板整理数据！商户号、终端号、起止时间必填且必须存在！注意：起止时间不能超过31天，时间格式为YYYYMMDD");
//			cell.setCellValue("请按模板整理数据！商户类型、商户状态、地区必填且必须存在！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "商户编号（15位数字）", "终端编号", "开始时间", "结束时间"};
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "12345678");
			POIUtils.createTextCell(book, row, (short) 2, "20180101");
			POIUtils.createTextCell(book, row, (short) 3, "20180131");
			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("中石化扫码刷卡统计查询模板", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("QuerySinopecSweepCardAction.downloadTemplate()调用出现异常。");
			log.error(e, e.fillInStackTrace());
		}
		return null;
	}
	
	// 上传ActionForward
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		log.info("开始导入数据");
		//得到Dao
		String info = "";
//		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		QuerySinopecSweepCardDao dao = new QuerySinopecSweepCardDao();
		response.setContentType("text/html;charset=GBK");
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		List<SinopecSweepCardUploadForm> sinopecSweepCardUploadList = new LinkedList<SinopecSweepCardUploadForm>();
		try {
//			writer = response.getWriter();
			SinopecSweepCardUploadForm uf = (SinopecSweepCardUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
//				jsonObj.put("success", "上传失败！");
//				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
				info = "文件解析失败,文件大小不能超过5M!";
			} else {
				List<SinopecSweepCardUploadForm> listUpload = new LinkedList<SinopecSweepCardUploadForm>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				//1、值传递
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					//获取总列数
//					int columnNum=row.getPhysicalNumberOfCells();
					// 新建一个Model
					SinopecSweepCardUploadForm sinopecSweepCardUploadForm = new SinopecSweepCardUploadForm();
					sinopecSweepCardUploadForm.setMerchant_no(POIUtils.getStringFromExcelCell(row.getCell(0)));
					sinopecSweepCardUploadForm.setTermial_no(POIUtils.getStringFromExcelCell(row.getCell(1)));
					sinopecSweepCardUploadForm.setStarttime(POIUtils.getStringFromExcelCell(row.getCell(2)));
					sinopecSweepCardUploadForm.setEndtime(POIUtils.getStringFromExcelCell(row.getCell(3)));
					if(!isAllFieldNull(sinopecSweepCardUploadForm)){
						listUpload.add(sinopecSweepCardUploadForm);
					}
				}
				//2、判断查询模版中各参数的有效性
					//一行数据是否完整
					//商户号是否合规（商户号需一致）
					//终端号是否重复
					//起止时间是否有误（不能超过31天）
				Map<String,Object> isflag = checkSinopecSweepCard(listUpload);
				boolean os =(Boolean)isflag.get("flag");
				if(os){
					//进行第三步处理
					//3、查询
					//3、1查询条件整理
					SinopecSweepCardUploadForm querycondition = new SinopecSweepCardUploadForm();
					StringBuilder st = new StringBuilder();
					for(int k=0;k<listUpload.size();k++){
						if(k==0){
							st.append("'"+listUpload.get(k).getTermial_no()+"'");
						}else{
							st.append(","+"'"+listUpload.get(k).getTermial_no()+"'");
						}
					}
					querycondition.setMerchant_no(listUpload.get(0).getMerchant_no());
					querycondition.setTermial_no(st.toString());
					querycondition.setStarttime(listUpload.get(0).getStarttime());
					querycondition.setEndtime(listUpload.get(0).getEndtime());
					//3、2查询
					List<QuerySinopecSweepCardBean> lst = dao.getSinopecSweepCard(querycondition);
					for(int q=0;q<lst.size();q++){
						SinopecSweepCardUploadForm frm = new SinopecSweepCardUploadForm();
						frm.setTradingType(lst.get(q).getTradingtype().equals("SM")?"扫码":"刷卡");
						frm.setTradingNumber(lst.get(q).getTradingnumber());
						frm.setTradingAmt(lst.get(q).getTradingamt());
						sinopecSweepCardUploadList.add(frm);
					}
					
					info = (String) isflag.get("content");
				}else{
					//退出，告知用户查询失败原因
					info = (String) isflag.get("content");
				}
				//3、查询
				//4、显示
			}
		} catch (Exception e) {
			info = "中石化扫码刷卡统计Excel文件查询Action异常: {}"+ e;
		}
		 // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = 0;
		if (sinopecSweepCardUploadList != null && !sinopecSweepCardUploadList.isEmpty()) {
			request.setAttribute("sinopecSweepCardUploadList", sinopecSweepCardUploadList);
			count = sinopecSweepCardUploadList.size();
		}
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("info", info);
		return mapping.findForward("showSinopecSweepCardList");
	}


	private Map<String, Object> checkSinopecSweepCard(List<SinopecSweepCardUploadForm> listUpload) {
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean b = true;
		String content = "校验通过";
		//一行数据是否完整
		//商户号是否合规（商户号需一致）
		//终端号是否重复
		//起止时间是否有误（不能超过31天）
		//小于1000、数据完整性、数据有效性
		if(listUpload.size() > 1000){
			b = false;
			content = "不能超过1000个终端号";
		}else if(listUpload.size() == 0){
			b = false;
			content = "excel为空";
		}else{
			String merchant_no = listUpload.get(0).getMerchant_no(); //商户号 ;
			String termial_no = listUpload.get(0).getTermial_no(); //终端号 ;
			String starttime = listUpload.get(0).getStarttime(); //开始时间 ;
			String endtime = listUpload.get(0).getEndtime(); //结束时间 ;
			for(int i=0;i<listUpload.size();i++){
				SinopecSweepCardUploadForm sinopecSweepCardUploadForm = listUpload.get(i);
				if(StringUtils.isNotBlank(sinopecSweepCardUploadForm.getMerchant_no()) 
						&& StringUtils.isNotBlank(sinopecSweepCardUploadForm.getTermial_no()) 
						&& StringUtils.isNotBlank(sinopecSweepCardUploadForm.getStarttime()) 
						&& StringUtils.isNotBlank(sinopecSweepCardUploadForm.getEndtime())){
					/*******************************************************************************************/
					if(!merchant_no.equals(sinopecSweepCardUploadForm.getMerchant_no())){
						b = false;
						content = "此商户号："+sinopecSweepCardUploadForm.getMerchant_no()+"与其他商户号不一致，请仔细检查";
						break;
					}
					if(i != 0){
						if(termial_no.equals(sinopecSweepCardUploadForm.getTermial_no())){
							b = false;
							content = "此终端号："+sinopecSweepCardUploadForm.getTermial_no()+"所在行，与其他行终端号一致，请仔细检查";
							break;
						}
					}
					if(!starttime.equals(sinopecSweepCardUploadForm.getStarttime())){
						b = false;
						content = "开始时间："+sinopecSweepCardUploadForm.getStarttime()+"所在行，与其他行开始时间不一致，请仔细检查";
						break;
					}
					if(!endtime.equals(sinopecSweepCardUploadForm.getEndtime())){
						b = false;
						content = "结束时间："+sinopecSweepCardUploadForm.getEndtime()+"所在行，与其他行结束时间不一致，请仔细检查";
						break;
					}
					/*******************************************************************************************/
					if(!isNumeric(sinopecSweepCardUploadForm.getMerchant_no(),15)){
						b = false;
						content = "此商户号："+sinopecSweepCardUploadForm.getMerchant_no()+"必须是15位数字，请仔细检查";
						break;
					}
					if(sinopecSweepCardUploadForm.getTermial_no().length() != 8){
						b = false;
						content = "此终端号："+sinopecSweepCardUploadForm.getTermial_no()+"必须是8位，请仔细检查";
						break;
					}
					if(!isNumeric(sinopecSweepCardUploadForm.getStarttime(),8)){
						b = false;
						content = "开始时间："+sinopecSweepCardUploadForm.getStarttime()+"必须是8位数字，请仔细检查";
						break;
					}
					if(!isNumeric(sinopecSweepCardUploadForm.getEndtime(),8)){
						b = false;
						content = "结束时间："+sinopecSweepCardUploadForm.getEndtime()+"必须是8位数字，请仔细检查";
						break;
					}
					/*******************************************************************************************/
					if(!isValidDate(sinopecSweepCardUploadForm.getStarttime())){
						b = false;
						content = "开始时间："+sinopecSweepCardUploadForm.getStarttime()+"格式有误，请仔细检查";
						break;
					}
					if(!isValidDate(sinopecSweepCardUploadForm.getEndtime())){
						b = false;
						content = "结束时间："+sinopecSweepCardUploadForm.getEndtime()+"格式有误，请仔细检查";
						break;
					}
					/*******************************************************************************************/
				}else{
					b = false;
					content = "第："+(i+1)+"行数据不完整";
					break;
				}
			}
			//起止时间是否有误（不能超过31天）
			if(b){
				if(compareNum(starttime,endtime)==0){
					b = false;
					content = "日期相差天数计算出错";
				}else if(compareNum(starttime,endtime)  < 0){
					b = false;
					content = "开始时间大于结束时间";
				}else if(compareNum(starttime,endtime)  > 31){
					b = false;
					content = "不能查询超过31天的数据";
				}
			}
		}
		map.put("flag", b);
		map.put("content", content);
		return map;
	}
	/*****************************************************工具小函数***************************************************/
	//判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
    public static boolean isAllFieldNull(Object obj) throws Exception{
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if(val!=null && val!="") {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }
	public static Long compareNum(String starttime,String endtime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		long d = 0;
		try {
			d = (sf.parse(endtime).getTime()-sf.parse(starttime).getTime())/1000/60/60/24;//天
		} catch (ParseException e) {
			System.out.println(d+"天");
		}
		return d;
	}
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
	//判断字符串是否为数字，并校验位数
	public static boolean isNumeric(String str,int pos){
	   for (int i = str.length();--i>=0;){  
	       if (!Character.isDigit(str.charAt(i))){
	           return false;
	       }
	   }
	   if(str.length() != pos){
		   return false;
	   }
	   return true;
	}
	/*****************************************************工具小函数***************************************************/
}

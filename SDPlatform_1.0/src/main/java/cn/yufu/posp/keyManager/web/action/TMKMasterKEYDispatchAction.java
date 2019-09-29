package cn.yufu.posp.keyManager.web.action;
//package cn.yufu.posp.keyManager.web.action;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.keyManager.domain.logic.TMKMasterKEYLogicInterface;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.keyManager.web.form.BtsKeyForm;
import cn.yufu.posp.merchant.domain.logic.MerchantLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.util.HttpRequestUtil;

public class TMKMasterKEYDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("key");

	/*** 查询 **/
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("TMKMasterKEYDispatchAction.queryAll()开始调用：查找符合条件的记录。");

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// 设置查询条件
			BtsKey queryModel = new BtsKey();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			if (request.getParameter("querySettleFlag") != null && !"".equals(request.getParameter("querySettleFlag"))) {
				String querySettleFlag = request.getParameter("querySettleFlag");
				queryModel.setSettleFlag(querySettleFlag);
			}else{
				//查询状态为0的新终端记录
				queryModel.setSettleFlag("0");
			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TMKMasterKEYDispatchAction.queryAll()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.queryAll()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/*** change查询 **/
	public org.apache.struts.action.ActionForward queryChangeAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		String pathForward = "";

		try {
			log.info("TMKMasterKEYDispatchAction.queryChangeAll()开始调用：查找符合条件的记录。");

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// 设置查询条件
			BtsKey queryModel = new BtsKey();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			if (request.getParameter("querySettleFlag") != null && !"".equals(request.getParameter("querySettleFlag"))) {
				String querySettleFlag = request.getParameter("querySettleFlag");
				queryModel.setSettleFlag(querySettleFlag);
			}else{
				//查询状态为0的新终端记录
				queryModel.setSettleFlag("1");
			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("changequery");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TMKMasterKEYDispatchAction.queryAll()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.queryAll()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	
	}
	/**
	 * 显示修改界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findChangeItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String forwarStr = "changeedit";
		try {
			log.info("TMKMasterKEYDispatchAction.findChangeItem()开始调用：显示修改界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// 分解参数
			String[] tt = strId.split("#");

			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			BtsKey btsKey = new BtsKey();

			if (tt.length == 3) {
				btsKey.setMerchantId(tt[0].trim());
				btsKey.setTerminalId(tt[1].trim());
			}

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// 得到Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;

			map = logic.findItemByKey(btsKey, ud);

			BtsKey model = (BtsKey) map.get("Infolist");

			BeanUtils.copyProperties(btsKeyForm, model);

			if (request.getParameter("showFlag") != null && "changeshow".equals(request.getParameter("showFlag"))) {
				forwarStr = "changeshow";
			}
			log.info("TMKMasterKEYDispatchAction.findChangeItem()结束调用：显示修改界面。");

		} catch (Exception e) {

			log.info("TMKMasterKEYDispatchAction.findChangeItem()显示修改界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(forwarStr);
	}
	
	/**
	 * 显示修改界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String forwarStr = "edit";
		try {
			log.info("TMKMasterKEYDispatchAction.findItem()开始调用：显示修改界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// 分解参数
			String[] tt = strId.split("#");

			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			BtsKey btsKey = new BtsKey();

			if (tt.length == 3) {
				btsKey.setMerchantId(tt[0].trim());
				btsKey.setTerminalId(tt[1].trim());
			}

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// 得到Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;

			map = logic.findItemByKey(btsKey, ud);

			BtsKey model = (BtsKey) map.get("Infolist");

			BeanUtils.copyProperties(btsKeyForm, model);

			if (request.getParameter("showFlag") != null && "show".equals(request.getParameter("showFlag"))) {
				forwarStr = "show";
			}
			log.info("TMKMasterKEYDispatchAction.findItem()结束调用：显示修改界面。");

		} catch (Exception e) {

			log.info("TMKMasterKEYDispatchAction.findItem()显示修改界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(forwarStr);
	}
	
	/**
	 * 修改
	 */
	public org.apache.struts.action.ActionForward saveChangeItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// 新建一个Model
		BtsKey btsKey = new BtsKey();
		try {
			log.info("TMKMasterKEYDispatchAction.saveItem()开始调用：修改定。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// 得到Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;
			btsKeyForm.setMasterKey(btsKeyForm.getMasterKey().trim());
			btsKeyForm.setTmkmasterKey(btsKeyForm.getTmkmasterKey().trim());
			
			if (ud != null) {
				BeanUtils.copyProperties(btsKey, btsKeyForm);
				btsKey.setSettleFlag("1");//0--新终端 1--生成终端主密钥 2--已签到
				logic.saveItem(btsKey, ud);
			}

			log.info("TMKMasterKEYDispatchAction.saveItem()结束调用：修改。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.saveItem()修改终端资料设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToChangeQuery");
	}
	
	/**
	 * 修改
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// 新建一个Model
		BtsKey btsKey = new BtsKey();
		try {
			log.info("TMKMasterKEYDispatchAction.saveItem()开始调用：修改定。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// 得到Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(btsKey, btsKeyForm);
				btsKey.setSettleFlag("1");//0--新终端 1--生成终端主密钥 2--已签到
				logic.saveItem(btsKey, ud);
			}

			log.info("TMKMasterKEYDispatchAction.saveItem()结束调用：修改。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.saveItem()修改终端资料设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException TODO
	 */
	public org.apache.struts.action.ActionForward createBatch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TMKMasterKEYDispatchAction.createBatch()开始调用：批量生成主密钥。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				String[] tt = InfoIdStrs[i].split("#");
				BtsKey btsKey = new BtsKey();

				if (tt.length == 3) {
					btsKey.setMerchantId(tt[0].trim());
					btsKey.setTerminalId(tt[1].trim());
				}
				// 得到Logic
				TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
				// 得到Form
				BtsKeyForm btsKeyForm = (BtsKeyForm) form;
				HashMap map = logic.findItemByKey(btsKey, ud);
				BtsKey model = (BtsKey) map.get("Infolist");
				
				//调用动态库
				/*
				String[] result = GetHsmKey.INSTANCE.GetHsmKeyDouble(Integer.parseInt(CommonDomain.CONST_GET_HSM_KEY_PARAM.replace("0x",""),16), "", "", "").split("\\|");
				model.setMasterKey(result[1]);
				model.setTmkmasterKey(result[2]);
				 */
				//发送http请求，得到终端主密钥
		       
				Map<String,String> maps = jsonTarray();
		        
		        if("true".equals(maps.get("isboolean"))){
		        	if("200".equals(maps.get("resp_code"))){
		        		model.setMasterKey(maps.get("sLmk"));
						model.setTmkmasterKey(maps.get("sTmk"));
		        	}else{
		        		throw new OAException("返回状态异常");
		        	}
		        }else if("false".equals(maps.get("isboolean"))){
		        	throw new OAException("返回报文异常");
		        }else{
		        	throw new OAException("获取http-URL和param异常");
		        }
		       
				model.setSettleFlag("1");//0--新终端 1--生成终端主密钥 2--已签到
				logic.saveItem(model, ud);
			}
			log.info("TMKMasterKEYDispatchAction.createBatch()结束调用：批量生成主密钥。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("TMKMasterKEYDispatchAction.createBatch()批量生成主密钥。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}
	/**
	 * 从配置文件中读取发送http所需URL和param
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,String> readProperties() throws FileNotFoundException, IOException {
		
		Map<String,String> map = new HashMap<String,String>();
		
		Properties pps = new Properties();
		
		String ur = this.getClass().getResource("/").getPath();
//		ur = ur.substring(1);
		
		log.info("properties文件地址："+ur);
		
        pps.load(new FileInputStream(ur+"sendHttpParam.properties"));
        
        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
        while(enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = pps.getProperty(strKey);
            if(!StringUtil.isNull(strKey)){
            	map.put(strKey, strValue);
            }
        }
        return map;
     }
	/**
	 * http请求，json转Map
	 * 用于生成终端主密钥
	 * @param sr
	 * @return
	 */
	public Map<String,String> jsonTarray(){
		
//		String url = "http://192.168.10.74:8600";
//		String para = "Chanel=FUKAWEB1&Pay_type=Crt_Key"+"&Trace_no=20170721143300";
//		String sr=HttpRequestUtil.sendGet(url,para);
		
		Map<String,String> map = new HashMap<String,String>();
		String url = null;
		String para = null;
		try{
			//得到动态获取终端主密钥所需参数和URL
			Map<String,String> ms = readProperties();
			System.out.println(ms.toString());
			
		    //生成当前时间yyyyMMddHHmmss+六位随机数的20位
		    String dt = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000));
		    
		    //判断从properties文件中获得的结果是否为空
			if(!ms.isEmpty()){
				url = ms.get("TMK_KEY_HTTP_URL");
				para = ms.get("TMK_KEY_HTTP_URL_PARAM")+"&Trace_no="+dt;
			}else{
				map.put("isboolean", "exception");
				return map;
			}
		}catch(Exception e1){
			map.put("isboolean", "exception");
			return map;
		}
		System.out.println(map.toString());
		try{
			System.out.println(url.toString());
			String sr=HttpRequestUtil.sendGet(url,para);
			System.out.println(sr.toString());
			int code = sr.indexOf("Resp_code")+"Resp_code".length()+3;
	        String resp_code = sr.substring(code, code+3);
	        map.put("resp_code",resp_code);
	        
	        int tmk = sr.indexOf("sTmk")+"sTmk".length()+3;
	        String sTmk = sr.substring(tmk,tmk+32);
	        map.put("sTmk",sTmk);
	        
	        int lmk = sr.indexOf("sLmk")+"sLmk".length()+3;
	        String sLmk = sr.substring(lmk,lmk+32);
	        map.put("sLmk",sLmk);
	        
	        map.put("isboolean", "true");
		}catch(Exception e){
			System.out.println("http异常");
			e.printStackTrace();;
			map.put("isboolean", "false");
		}
		
        return map;
        
	}
	public org.apache.struts.action.ActionForward updateBatch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TMKMasterKEYDispatchAction.updateBatch()开始调用：批量销毁主密钥。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				String[] tt = InfoIdStrs[i].split("#");
				BtsKey btsKey = new BtsKey();
				if (tt.length == 3) {
					btsKey.setMerchantId(tt[0].trim());
					btsKey.setTerminalId(tt[1].trim());
				}
				// 得到Logic
				TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
				HashMap map = logic.findItemByKey(btsKey, ud);
				BtsKey model = (BtsKey) map.get("Infolist");
				model.setSettleFlag("0");// 0--新终端 1--生成终端主密钥 2--已签到
				model.setMasterKey("");
				model.setTmkmasterKey("");
				logic.saveItem(model, ud);
			}
			log.info("TMKMasterKEYDispatchAction.updateBatch()结束调用：批量销毁主密钥。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("TMKMasterKEYDispatchAction.updateBatch()批量销毁主密钥。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}
	
	public org.apache.struts.action.ActionForward getKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		/**
		 * TODO
		 * 1、http
		 * 2、解析json
		 * 3、得到密钥
		 */
		System.out.println("TMKMasterKEYDispatchAction.getKey()开始调用：调用动态库文件。");
		try {
			log.info("TMKMasterKEYDispatchAction.getKey()开始调用：调用动态库文件。");
			// String[] result =
			// GetHsmKey.INSTANCE.GetHsmKeyDouble(Integer.parseInt(CommonDomain.CONST_GET_HSM_KEY_PARAM.replace("0x",""),16),
			// "", "", "").split("\\|");
			// out.print(result[2]+","+result[1]);

			//调用动态库
			/*
			int in = Integer.parseInt(CommonDomain.CONST_GET_HSM_KEY_PARAM.replace("0x", ""), 16);
			String[] result = GetHsmKey.INSTANCE.GetHsmKeyDouble(in, "", "", "").split("\\|");
			response.setContentType("text/html;charset=utf-8");
			if ("0".equals(result[0])) {
				out.print(result[2] + "," + result[1]);
			} else {
				out.print(false);
			}
			 */
			// String url = "http://192.168.10.74:8600";
			// String para =
			// "Chanel=FUKAWEB1&Trace_no=20170721143300&Pay_type=Crt_Key";
			// String sr=HttpRequestUtil.sendGet(url,para);
	        
			//发送http请求，得到加密之后的串
			
	        Map<String,String> maps = jsonTarray();
			
			response.setContentType("text/html;charset=utf-8");
			
			if("true".equals(maps.get("isboolean"))){
	        	if("200".equals(maps.get("resp_code"))){
					out.print(maps.get("sTmk")+","+maps.get("sLmk"));
	        	}else{
	        		out.print(false);
	        	}
	        }else{
	        	out.print(false);
	        }
			System.out.println("TMKMasterKEYDispatchAction.getKey()结束调用：调用动态库文件。");
			log.info("TMKMasterKEYDispatchAction.getKey()结束调用：调用动态库文件。");
		} catch (Throwable t) {
			log.error("TMKMasterKEYDispatchAction.getKey()调用动态库文件，出现异常");
			if (log.isDebugEnabled())
			log.error(t.fillInStackTrace());
			out.print(false);
		} 
		
		return mapping.findForward(null);

	}
	
	public org.apache.struts.action.ActionForward queryExport(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
		String pathForward = "";

		try {
			log.info("TMKMasterKEYDispatchAction.queryAll()开始调用：查找符合条件的记录。");

			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// 设置查询条件
			BtsKey queryModel = new BtsKey();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//目前只查询状态为1 的未签到记录
			//queryModel.setSettleFlag("1");
			//按状态查询
			String settleFlag=request.getParameter("querySettleFlag");
			if (settleFlag != null && !"".equals(settleFlag)) {
				queryModel.setSettleFlag(settleFlag);
			}else{
				queryModel.setSettleFlag("1");
			}
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("queryExport");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TMKMasterKEYDispatchAction.queryAll()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.queryAll()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}
	
	// 导出
	public org.apache.struts.action.ActionForward exportExcel(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			UserData ud = getSessionUserData(request);
			// 设置查询条件
			BtsKey queryModel = new BtsKey();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//目前只查询状态为1 的未签到记录
			//queryModel.setSettleFlag("1");
			String settleFlag=request.getParameter("querySettleFlag");
			if (settleFlag != null && !"".equals(settleFlag)) {
				queryModel.setSettleFlag(settleFlag);
			}else{
				queryModel.setSettleFlag("1");
			}
			
			List list = logic.queryExport(queryModel, ud);
			
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("POS装机单");
			cell.setCellStyle(getTipFontStyle(book));
			sheet.addMergedRegion(new Region(0,(short)0,0,(short)11));
			//行高
	        sheet.setDefaultRowHeightInPoints(10);
	        //列款宽
	        sheet.setDefaultColumnWidth((short) 10);
			row = sheet.createRow(1);
			cell = null;
			String[] headCol = { "商户编号", "终端号", "商户名称 ", "商户地址", "商户联系人", "联系方式", "终端密钥" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			
			// 生成数据
			for (int i = 0; i < list.size(); i++) {
				BtsKey btsKey = (BtsKey) list.get(i);
				row = sheet.createRow(i + 2);
				MerchantLogicInterface merchantLogic = (MerchantLogicInterface) getBean("MerchantLogic");
				HashMap hashMap = merchantLogic.findItem(btsKey.getMerchantId(), ud);
				// 新建一个Model
				MerchantBaseModel model = (MerchantBaseModel) hashMap.get("Infolist");
				POIUtils.createCell(row, (short) 0, btsKey.getMerchantId());
				POIUtils.createCell(row, (short) 1, btsKey.getTerminalId());
				POIUtils.createCell(row, (short) 2, model.getMerchantCname());
				POIUtils.createCell(row, (short) 3, model.getAddressChn());
				POIUtils.createCell(row, (short) 4, model.getManager());
				POIUtils.createCell(row, (short) 5, model.getTelephone());
				POIUtils.createCell(row, (short) 6, btsKey.getTmkmasterKey());
			}
			
			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("POS装机单", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}
	
	/**
	 * 提示内容单元格样式
	 * @param book
	 * @return
	 */
	private HSSFCellStyle getTipFontStyle(HSSFWorkbook book) {
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFFont font = book.createFont();
		// 粗体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 红字
		font.setColor(HSSFColor.RED.index);
		cellStyle.setFont(font);
		return cellStyle;
	}
	private void createHiddenRow(HSSFRow row_2,String[][] tplBaseHeadData,List catgList,int colMax){
		row_2.setZeroHeight(true);
		for(int i=0;i<tplBaseHeadData.length;i++){
			HSSFCell cell = (HSSFCell) row_2.createCell((short) i);
			cell.setCellValue(tplBaseHeadData[i][0]);
		}
	}
	
	// 导出TxT
	public org.apache.struts.action.ActionForward exportTxT(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		List list = null;
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Date now = new Date();
		// 可以方便地修改日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"_yyyy_MM_dd_HH_mm_ss");
		String now_time = dateFormat.format(now);
		String fileName = "";
		try {
			fileName = URLEncoder.encode("TMKMasterKEY" + now_time, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// 设置导入TxT文件浏览器中存储
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName + ".txt");
		try {
			// 得到Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			UserData ud = getSessionUserData(request);
			// 设置查询条件
			BtsKey queryModel = new BtsKey();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			// 目前只查询状态为1 的未签到记录
			// queryModel.setSettleFlag("1");
			String settleFlag = request.getParameter("querySettleFlag");
			if (settleFlag != null && !"".equals(settleFlag)) {
				queryModel.setSettleFlag(settleFlag);
			} else {
				queryModel.setSettleFlag("1");
			}

			list = logic.queryExport(queryModel, ud);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String nextLine = "\r\n";
		ServletOutputStream outSTr = null;
		try {
			outSTr = response.getOutputStream(); // 建立
			buff = new BufferedOutputStream(outSTr);
			// 标题
			// StringBuffer shead = new StringBuffer();
			// write.append(shead.append("商户号           ,终端号      ,机构号    ,终端主密钥密文,终端主密钥密文"
			// +nextLine).toString());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					write.append("\"" + ((BtsKey) list.get(i)).getMerchantId()
							+ "\",\"" + ((BtsKey) list.get(i)).getTerminalId()
							+ "\",\"" + "000000" + "\",\""
							+ ((BtsKey) list.get(i)).getTmkmasterKey()
							+ "\",\""
							+ ((BtsKey) list.get(i)).getTmkmasterKey() + "\""
							+ nextLine);
				}
			}

			buff.write(write.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}	
}

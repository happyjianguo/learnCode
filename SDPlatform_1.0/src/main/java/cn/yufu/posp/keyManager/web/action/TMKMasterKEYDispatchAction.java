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

	/*** ��ѯ **/
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("TMKMasterKEYDispatchAction.queryAll()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// ���ò�ѯ����
			BtsKey queryModel = new BtsKey();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
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
				//��ѯ״̬Ϊ0�����ն˼�¼
				queryModel.setSettleFlag("0");
			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TMKMasterKEYDispatchAction.queryAll()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.queryAll()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/*** change��ѯ **/
	public org.apache.struts.action.ActionForward queryChangeAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		String pathForward = "";

		try {
			log.info("TMKMasterKEYDispatchAction.queryChangeAll()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// ���ò�ѯ����
			BtsKey queryModel = new BtsKey();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
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
				//��ѯ״̬Ϊ0�����ն˼�¼
				queryModel.setSettleFlag("1");
			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("changequery");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TMKMasterKEYDispatchAction.queryAll()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.queryAll()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	
	}
	/**
	 * ��ʾ�޸Ľ���
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findChangeItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String forwarStr = "changeedit";
		try {
			log.info("TMKMasterKEYDispatchAction.findChangeItem()��ʼ���ã���ʾ�޸Ľ��档");

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			BtsKey btsKey = new BtsKey();

			if (tt.length == 3) {
				btsKey.setMerchantId(tt[0].trim());
				btsKey.setTerminalId(tt[1].trim());
			}

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// �õ�Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;

			map = logic.findItemByKey(btsKey, ud);

			BtsKey model = (BtsKey) map.get("Infolist");

			BeanUtils.copyProperties(btsKeyForm, model);

			if (request.getParameter("showFlag") != null && "changeshow".equals(request.getParameter("showFlag"))) {
				forwarStr = "changeshow";
			}
			log.info("TMKMasterKEYDispatchAction.findChangeItem()�������ã���ʾ�޸Ľ��档");

		} catch (Exception e) {

			log.info("TMKMasterKEYDispatchAction.findChangeItem()��ʾ�޸Ľ��棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(forwarStr);
	}
	
	/**
	 * ��ʾ�޸Ľ���
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String forwarStr = "edit";
		try {
			log.info("TMKMasterKEYDispatchAction.findItem()��ʼ���ã���ʾ�޸Ľ��档");

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			BtsKey btsKey = new BtsKey();

			if (tt.length == 3) {
				btsKey.setMerchantId(tt[0].trim());
				btsKey.setTerminalId(tt[1].trim());
			}

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// �õ�Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;

			map = logic.findItemByKey(btsKey, ud);

			BtsKey model = (BtsKey) map.get("Infolist");

			BeanUtils.copyProperties(btsKeyForm, model);

			if (request.getParameter("showFlag") != null && "show".equals(request.getParameter("showFlag"))) {
				forwarStr = "show";
			}
			log.info("TMKMasterKEYDispatchAction.findItem()�������ã���ʾ�޸Ľ��档");

		} catch (Exception e) {

			log.info("TMKMasterKEYDispatchAction.findItem()��ʾ�޸Ľ��棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(forwarStr);
	}
	
	/**
	 * �޸�
	 */
	public org.apache.struts.action.ActionForward saveChangeItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// �½�һ��Model
		BtsKey btsKey = new BtsKey();
		try {
			log.info("TMKMasterKEYDispatchAction.saveItem()��ʼ���ã��޸Ķ���");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// �õ�Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;
			btsKeyForm.setMasterKey(btsKeyForm.getMasterKey().trim());
			btsKeyForm.setTmkmasterKey(btsKeyForm.getTmkmasterKey().trim());
			
			if (ud != null) {
				BeanUtils.copyProperties(btsKey, btsKeyForm);
				btsKey.setSettleFlag("1");//0--���ն� 1--�����ն�����Կ 2--��ǩ��
				logic.saveItem(btsKey, ud);
			}

			log.info("TMKMasterKEYDispatchAction.saveItem()�������ã��޸ġ�");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.saveItem()�޸��ն������趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToChangeQuery");
	}
	
	/**
	 * �޸�
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// �½�һ��Model
		BtsKey btsKey = new BtsKey();
		try {
			log.info("TMKMasterKEYDispatchAction.saveItem()��ʼ���ã��޸Ķ���");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			// �õ�Form
			BtsKeyForm btsKeyForm = (BtsKeyForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(btsKey, btsKeyForm);
				btsKey.setSettleFlag("1");//0--���ն� 1--�����ն�����Կ 2--��ǩ��
				logic.saveItem(btsKey, ud);
			}

			log.info("TMKMasterKEYDispatchAction.saveItem()�������ã��޸ġ�");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.saveItem()�޸��ն������趨�������쳣��");
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
			log.info("TMKMasterKEYDispatchAction.createBatch()��ʼ���ã�������������Կ��" + getSessionUserData(request).getUserId());
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
				// �õ�Logic
				TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
				// �õ�Form
				BtsKeyForm btsKeyForm = (BtsKeyForm) form;
				HashMap map = logic.findItemByKey(btsKey, ud);
				BtsKey model = (BtsKey) map.get("Infolist");
				
				//���ö�̬��
				/*
				String[] result = GetHsmKey.INSTANCE.GetHsmKeyDouble(Integer.parseInt(CommonDomain.CONST_GET_HSM_KEY_PARAM.replace("0x",""),16), "", "", "").split("\\|");
				model.setMasterKey(result[1]);
				model.setTmkmasterKey(result[2]);
				 */
				//����http���󣬵õ��ն�����Կ
		       
				Map<String,String> maps = jsonTarray();
		        
		        if("true".equals(maps.get("isboolean"))){
		        	if("200".equals(maps.get("resp_code"))){
		        		model.setMasterKey(maps.get("sLmk"));
						model.setTmkmasterKey(maps.get("sTmk"));
		        	}else{
		        		throw new OAException("����״̬�쳣");
		        	}
		        }else if("false".equals(maps.get("isboolean"))){
		        	throw new OAException("���ر����쳣");
		        }else{
		        	throw new OAException("��ȡhttp-URL��param�쳣");
		        }
		       
				model.setSettleFlag("1");//0--���ն� 1--�����ն�����Կ 2--��ǩ��
				logic.saveItem(model, ud);
			}
			log.info("TMKMasterKEYDispatchAction.createBatch()�������ã�������������Կ��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("TMKMasterKEYDispatchAction.createBatch()������������Կ�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}
	/**
	 * �������ļ��ж�ȡ����http����URL��param
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
		
		log.info("properties�ļ���ַ��"+ur);
		
        pps.load(new FileInputStream(ur+"sendHttpParam.properties"));
        
        Enumeration enum1 = pps.propertyNames();//�õ������ļ�������
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
	 * http����jsonתMap
	 * ���������ն�����Կ
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
			//�õ���̬��ȡ�ն�����Կ���������URL
			Map<String,String> ms = readProperties();
			System.out.println(ms.toString());
			
		    //���ɵ�ǰʱ��yyyyMMddHHmmss+��λ�������20λ
		    String dt = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000));
		    
		    //�жϴ�properties�ļ��л�õĽ���Ƿ�Ϊ��
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
			System.out.println("http�쳣");
			e.printStackTrace();;
			map.put("isboolean", "false");
		}
		
        return map;
        
	}
	public org.apache.struts.action.ActionForward updateBatch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TMKMasterKEYDispatchAction.updateBatch()��ʼ���ã�������������Կ��" + getSessionUserData(request).getUserId());
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
				// �õ�Logic
				TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
				HashMap map = logic.findItemByKey(btsKey, ud);
				BtsKey model = (BtsKey) map.get("Infolist");
				model.setSettleFlag("0");// 0--���ն� 1--�����ն�����Կ 2--��ǩ��
				model.setMasterKey("");
				model.setTmkmasterKey("");
				logic.saveItem(model, ud);
			}
			log.info("TMKMasterKEYDispatchAction.updateBatch()�������ã�������������Կ��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("TMKMasterKEYDispatchAction.updateBatch()������������Կ�������쳣��(" + e.getMessage() + ")��");
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
		 * 1��http
		 * 2������json
		 * 3���õ���Կ
		 */
		System.out.println("TMKMasterKEYDispatchAction.getKey()��ʼ���ã����ö�̬���ļ���");
		try {
			log.info("TMKMasterKEYDispatchAction.getKey()��ʼ���ã����ö�̬���ļ���");
			// String[] result =
			// GetHsmKey.INSTANCE.GetHsmKeyDouble(Integer.parseInt(CommonDomain.CONST_GET_HSM_KEY_PARAM.replace("0x",""),16),
			// "", "", "").split("\\|");
			// out.print(result[2]+","+result[1]);

			//���ö�̬��
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
	        
			//����http���󣬵õ�����֮��Ĵ�
			
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
			System.out.println("TMKMasterKEYDispatchAction.getKey()�������ã����ö�̬���ļ���");
			log.info("TMKMasterKEYDispatchAction.getKey()�������ã����ö�̬���ļ���");
		} catch (Throwable t) {
			log.error("TMKMasterKEYDispatchAction.getKey()���ö�̬���ļ��������쳣");
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
			log.info("TMKMasterKEYDispatchAction.queryAll()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// ���ò�ѯ����
			BtsKey queryModel = new BtsKey();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//Ŀǰֻ��ѯ״̬Ϊ1 ��δǩ����¼
			//queryModel.setSettleFlag("1");
			//��״̬��ѯ
			String settleFlag=request.getParameter("querySettleFlag");
			if (settleFlag != null && !"".equals(settleFlag)) {
				queryModel.setSettleFlag(settleFlag);
			}else{
				queryModel.setSettleFlag("1");
			}
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("queryExport");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TMKMasterKEYDispatchAction.queryAll()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("TMKMasterKEYDispatchAction.queryAll()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}
	
	// ����
	public org.apache.struts.action.ActionForward exportExcel(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			UserData ud = getSessionUserData(request);
			// ���ò�ѯ����
			BtsKey queryModel = new BtsKey();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//Ŀǰֻ��ѯ״̬Ϊ1 ��δǩ����¼
			//queryModel.setSettleFlag("1");
			String settleFlag=request.getParameter("querySettleFlag");
			if (settleFlag != null && !"".equals(settleFlag)) {
				queryModel.setSettleFlag(settleFlag);
			}else{
				queryModel.setSettleFlag("1");
			}
			
			List list = logic.queryExport(queryModel, ud);
			
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("POSװ����");
			cell.setCellStyle(getTipFontStyle(book));
			sheet.addMergedRegion(new Region(0,(short)0,0,(short)11));
			//�и�
	        sheet.setDefaultRowHeightInPoints(10);
	        //�п��
	        sheet.setDefaultColumnWidth((short) 10);
			row = sheet.createRow(1);
			cell = null;
			String[] headCol = { "�̻����", "�ն˺�", "�̻����� ", "�̻���ַ", "�̻���ϵ��", "��ϵ��ʽ", "�ն���Կ" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			
			// ��������
			for (int i = 0; i < list.size(); i++) {
				BtsKey btsKey = (BtsKey) list.get(i);
				row = sheet.createRow(i + 2);
				MerchantLogicInterface merchantLogic = (MerchantLogicInterface) getBean("MerchantLogic");
				HashMap hashMap = merchantLogic.findItem(btsKey.getMerchantId(), ud);
				// �½�һ��Model
				MerchantBaseModel model = (MerchantBaseModel) hashMap.get("Infolist");
				POIUtils.createCell(row, (short) 0, btsKey.getMerchantId());
				POIUtils.createCell(row, (short) 1, btsKey.getTerminalId());
				POIUtils.createCell(row, (short) 2, model.getMerchantCname());
				POIUtils.createCell(row, (short) 3, model.getAddressChn());
				POIUtils.createCell(row, (short) 4, model.getManager());
				POIUtils.createCell(row, (short) 5, model.getTelephone());
				POIUtils.createCell(row, (short) 6, btsKey.getTmkmasterKey());
			}
			
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("POSװ����", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}
	
	/**
	 * ��ʾ���ݵ�Ԫ����ʽ
	 * @param book
	 * @return
	 */
	private HSSFCellStyle getTipFontStyle(HSSFWorkbook book) {
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFFont font = book.createFont();
		// ����
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ����
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
	
	// ����TxT
	public org.apache.struts.action.ActionForward exportTxT(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		List list = null;
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Date now = new Date();
		// ���Է�����޸����ڸ�ʽ
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"_yyyy_MM_dd_HH_mm_ss");
		String now_time = dateFormat.format(now);
		String fileName = "";
		try {
			fileName = URLEncoder.encode("TMKMasterKEY" + now_time, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// ���õ���TxT�ļ�������д洢
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName + ".txt");
		try {
			// �õ�Logic
			TMKMasterKEYLogicInterface logic = (TMKMasterKEYLogicInterface) getBean("TMKMasterKEYLogic");
			UserData ud = getSessionUserData(request);
			// ���ò�ѯ����
			BtsKey queryModel = new BtsKey();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			// Ŀǰֻ��ѯ״̬Ϊ1 ��δǩ����¼
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

			log.error("CurTranLsDispatchAction.queryDetail()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String nextLine = "\r\n";
		ServletOutputStream outSTr = null;
		try {
			outSTr = response.getOutputStream(); // ����
			buff = new BufferedOutputStream(outSTr);
			// ����
			// StringBuffer shead = new StringBuffer();
			// write.append(shead.append("�̻���           ,�ն˺�      ,������    ,�ն�����Կ����,�ն�����Կ����"
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

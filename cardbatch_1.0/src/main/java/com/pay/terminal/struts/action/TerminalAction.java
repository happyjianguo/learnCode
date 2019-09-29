package com.pay.terminal.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.tlog.bean.TlogBean;
import com.pay.batch.tlog.dao.TlogDao;
import com.pay.merchant.bean.AreaBean;
import com.pay.merchant.bean.CountryBean;
import com.pay.merchant.bean.CurrencyBean;
import com.pay.merchant.bean.MerchantBean;
import com.pay.merchant.bean.Merchant_xBean;
import com.pay.merchant.bean.Mrch_acc_xBean;
import com.pay.merchant.bean.WankeMerBookBean;
import com.pay.merchant.dao.Merchantdao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.terminal.bean.Enckeybean;
import com.pay.terminal.bean.MrchAccxBean;
import com.pay.terminal.bean.PosdefBean;
import com.pay.terminal.bean.TerminalBean;
import com.pay.terminal.bean.TerminalExportBean;
import com.pay.terminal.bean.Termpos_xBean;
import com.pay.terminal.bean.Termposbean;
import com.pay.terminal.dao.TerminalDao;
import com.pay.terminal.struts.form.TerminalForm;
import com.pay.util.Constant;
import com.pay.util.ExportExcel;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;


public class TerminalAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(TerminalAction.class);
	String info="";
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	public ActionForward getTerminalList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
	        HttpSession session = request.getSession();
	        TerminalDao terminalDao = new TerminalDao();
			// ����ն���Ϣ�б��¼
	        TerminalForm terminalform = (TerminalForm)form;
	        beforAddorModTerminal(request,null);	        
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = terminalDao.getCount(terminalform,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			List terminalList = null;
			if(count>0)
				terminalList = terminalDao.getTerminalList(pageBean, terminalform);
			if (terminalList != null && !terminalList.isEmpty()) {
				request.setAttribute("terminalList", terminalList);
			}
		} catch (Exception e) {
			logger.error("TerminalAction--getTerminalList--Exception:", e);
		}
        return mapping.findForward("terminalList");
    }
	
	
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
        TerminalDao terminalDao = new TerminalDao();
		// ����̻���Ϣ�б����м�¼
        TerminalForm terminalform = (TerminalForm)form;
        List<TerminalExportBean> terminalList =terminalDao.getTerminalList(terminalform);	

		String docsPath = session.getServletContext().getRealPath("docs");
		String fileName = "terminal"+System.currentTimeMillis()+".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]"+filePath);
		OutputStream out = null;
		ExportExcel<TerminalExportBean> expexl = new ExportExcel<TerminalExportBean>();			
		try {
			out = new FileOutputStream(filePath);
			
			String[] headers = { "�ն�ID","�ն˺�","�ն����к�","�ն�����λ��","�ն�״̬","�����̻�","�����˺�","���ʣ���λ��%��","��������","ͣ��ʱ��","����ʱ��"};
			expexl.exportExcel("�ն���Ϣ", headers, terminalList, out, "yyyy-MM-dd");			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		expexl.download(filePath, response);
		return null;
	}	
	/**
	 * ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryAgentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// ������ҳǰ���Ѳ�ѯ��������Ϊ��
		try {
	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
	        TerminalDao terminalDao = new TerminalDao();
	        beforAddorModTerminal(request,null);
			// ����ն���Ϣ�б��¼
	        TerminalForm terminalform = (TerminalForm)form;
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = terminalDao.getCount(terminalform,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			List terminalList = null;
			if(count>0)
				terminalList = terminalDao.getTerminalList(pageBean, terminalform);
			if (terminalList != null && !terminalList.isEmpty()) {
				request.setAttribute("terminalList", terminalList);
			}
		} catch (Exception e) {
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		return mapping.findForward("terminalList");
	}
	
	
	/**
     * �̻��������޸�ʱ����������������б���Ϣ�����request��
     * @param request
     */
	private void beforAddorModTerminal(HttpServletRequest request,String mrchNo) {
		TerminalDao dao = new TerminalDao();
		//List<MrchListBean> mrchList = dao.getMrchListBeanList(null,null,"0","yes");
		//List<MrchListBean> mrchJSList = dao.getMrchListBeanList(null,null,"1","yes");
		List<PosdefBean> typeidList = dao.getPosdefBeanList();

		if (typeidList != null && !typeidList.isEmpty()) {
			request.setAttribute("typeidList", typeidList);
		}
		
		Merchantdao mcdao = new Merchantdao();
		
		List<CurrencyBean> currcodeList = mcdao.getCurrencyBeanList();
		
		//��ȡ����һ�߳���
		List<AreaBean> provinList = mcdao.getCityByFid("0");
		//��ȡ���߳���
		List<AreaBean> city_noList = mcdao.getCityByFid("1");
		if(city_noList==null){
			city_noList = mcdao.getBJCity();
		}
		List<CountryBean> CountryList = mcdao.getCountryList();
		
		if (currcodeList != null && !currcodeList.isEmpty()) {
			request.setAttribute("currcodeList", currcodeList);
		}
		if (provinList != null && !provinList.isEmpty()) {
			request.setAttribute("provinList", provinList);
		}
		if (city_noList != null && !city_noList.isEmpty()) {
			request.setAttribute("city_noList", city_noList);
		}
		if (CountryList != null && !CountryList.isEmpty()) {
			request.setAttribute("CountryList", CountryList);
		}
//		if (mrchList != null && !mrchList.isEmpty()) {
//			request.setAttribute("mrchList", mrchList);
//		}
//		if (mrchJSList != null && !mrchJSList.isEmpty()) {
//			request.setAttribute("mrchJSList", mrchJSList);
//		}
		//���ѳ���
		SysParameterDao sysParameterDao=new SysParameterDao();
		List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CONSUMP_CATEGORY");
		if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
			request.setAttribute("consump_categoryList", consump_categoryList);
		}
	}
	
	/**
	 * ��ʼ�������̻�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		beforAddorModTerminal(request,null);
		return mapping.findForward("addterminal.jsp");
	}
	
	/**
	 * ����̻���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTerminalInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		String info = "";
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
        String flushdo = "/terminal.do?method=getTerminalList";
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
        	TerminalDao terminalDao = new TerminalDao();
    		TerminalForm terminalForm = (TerminalForm) form;
    		
    		Termposbean termposBean = new Termposbean();
    		filledTermposbean(terminalForm, termposBean,"insert");
    		
    		Termpos_xBean termpos_xBean = new Termpos_xBean();
    		filledTermpos_xBean(terminalForm, termpos_xBean,"insert");
    		
    		Enckeybean enckeybean = new Enckeybean();
    		filledEnckeybean(terminalForm, enckeybean);
    		
    		WankeMerBookBean wanke_MerBookBean = new WankeMerBookBean();
    		if(terminalForm.getYard_mer_type()!=null){
    			if("0".equals(terminalForm.getYard_mer_type())){
    				filledWankeMerBookBean(terminalForm, wanke_MerBookBean);
    			}
    		}
    		
    		int result = terminalDao.addTerminalInfo(termposBean,termpos_xBean,enckeybean,wanke_MerBookBean);
    		if (result >= 0) {
	            info = "���ն���ӳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "���ݿ��쳣���ն����ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner("�ն˺�Ϊ"+terminalForm.getTermcode()+info));
	        request.setAttribute("flushdo", flushdo);
//	        if(terminalForm.getTermcode()!=null&&terminalForm.getTermcode().length()>4){
//		        terminalForm.setTermcode(terminalForm.getTermcode().substring(0,4));        	
//	        }
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
		beforAddorModTerminal(request,null);
		
        return mapping.findForward("addterminal.jsp");
    }
	
	public ActionForward preQueryTerminal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        TerminalForm terminalForm = (TerminalForm) form;
        
        String id = ParamUtils.getParameter(request, "id");
        request.setAttribute("id", id);
        
        TerminalDao dao = new TerminalDao();
        Merchantdao merchantDao = new Merchantdao();
        TerminalBean terminalBean = dao.getTerminalByID(id);
        if(terminalBean!=null){ 
            //��������б���Ϣ
            beforAddorModTerminal(request,terminalBean.getMrchno());
            if (null != terminalBean.getProvince() && !terminalBean.getProvince().equals("")) {
            	List<AreaBean> city_noList = merchantDao.getCityByFid(terminalBean.getProvince());
            	if (city_noList != null && !city_noList.isEmpty()) {
        			request.setAttribute("city_noList", city_noList);
        		}
            }
            if (null != terminalBean.getCity_no() && !terminalBean.getCity_no().equals("")) {
            	List<AreaBean> zoneList = merchantDao.getCityByFid(terminalBean.getCity_no());
            	if (zoneList != null && !zoneList.isEmpty()) {
         			request.setAttribute("zoneList", zoneList);
         		}
            }
        }
        try {
            if (terminalBean != null) {
                BeanUtils.copyProperties(terminalForm, terminalBean);
            	//���û��˹��˵Ľ����˺�
				Mrch_acc_xBean mrchaccxBean = merchantDao.getMrchaccxById(terminalBean.getSettle_mrch_acc_id());
				if(mrchaccxBean!=null){
					terminalForm.setSettle_mrch_acc_id("("+mrchaccxBean.getId()+")"+mrchaccxBean.getAccno()+"("+mrchaccxBean.getAcc_name()+")");
				}
				//���������̻�
				Merchant_xBean mxBean=merchantDao.getMerchantXById(terminalBean.getMerchant_id());
				if(mxBean!=null){
					terminalForm.setMerchant_id(mxBean.getMrchno()+"("+mxBean.getMrcht_name()+")");
				}
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("queryterminal.jsp");
    }
	
	/**
     * @TODO ׼���޸��ն���Ϣ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     */
    public ActionForward preModTerminal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        TerminalForm terminalForm = (TerminalForm) form;
        
        String id = ParamUtils.getParameter(request, "id");
        request.setAttribute("id", id);
        
        TerminalDao dao = new TerminalDao();
        Merchantdao merchantDao = new Merchantdao();
        TerminalBean terminalBean = dao.getTerminalByID(id);
        if(terminalBean!=null){
            //��������б���Ϣ
            beforAddorModTerminal(request,terminalBean.getMrchno());
            if (null != terminalBean.getSettle_mrch_acc_id() && !terminalBean.getSettle_mrch_acc_id().equals("")) {
            	List<MrchAccxBean> mrchaccxList = dao.getMrchAccxBeanListbyAccId(terminalBean.getSettle_mrch_acc_id(),null);
            	if (mrchaccxList != null && !mrchaccxList.isEmpty()) {
         			request.setAttribute("mrchaccxList", mrchaccxList);
         		}
            }else{
            	List<MrchAccxBean> mrchaccxList = dao.getMrchAccxBeanListbyAccId(null,terminalBean.getMrchno());
            	if (mrchaccxList != null && !mrchaccxList.isEmpty()) {
         			request.setAttribute("mrchaccxList", mrchaccxList);
         		}
            }
            if (null != terminalBean.getProvince() && !terminalBean.getProvince().equals("")) {
            	List<AreaBean> city_noList = merchantDao.getCityByFid(terminalBean.getProvince());
            	if (city_noList != null && !city_noList.isEmpty()) {
        			request.setAttribute("city_noList", city_noList);
        		}
            }else{
            	List<AreaBean> city_noList = merchantDao.getBJCity();
            	if (city_noList != null && !city_noList.isEmpty()) {
        			request.setAttribute("city_noList", city_noList);
        		}
            }
            if (null != terminalBean.getCity_no() && !terminalBean.getCity_no().equals("")) {
            	List<AreaBean> zoneList = merchantDao.getCityByFid(terminalBean.getCity_no());
            	if (zoneList != null && !zoneList.isEmpty()) {
         			request.setAttribute("zoneList", zoneList);
         		}
            }
        }

     // ��ȡ�ն˽�����̻��ź��ն˺Ų��жϴ��̻��Ŵ�������̻���
        List<WankeMerBookBean> wankeList = merchantDao.findWanKe(terminalBean.getMrchno());
        if(wankeList!=null){
        	if(wankeList.size()>0){
        		String yard_mer_type=wankeList.get(0).getYard_mer_type();
        		terminalForm.setYard_mer_type(yard_mer_type);
            }
        }
        try {
            if (terminalBean != null) {
                BeanUtils.copyProperties(terminalForm, terminalBean);
                terminalForm.setMrchnoSelQ(terminalBean.getMrchno());
                //���������̻�������Ϊ�̻���mrchNo�����̻����mrch_id
                terminalForm.setMerchant_id(terminalBean.getMrchno());
                //�����޸�ҳ��ǰ�ж��ն������̻��Ƿ���Ч���Ƿ����ڽ����̻�����Ч���ǽ����̻��Ļ��������̻����óɲ����޸ģ������ʱ���޸��̻���
                
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("modifyterminal.jsp");
    }
    
    public ActionForward modTerminalInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
		
		String info = "";
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo="/terminal.do?method=getTerminalList";
		//String flushdo = "closewindow";
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {  
    		TerminalDao terminalDao = new TerminalDao();
    		TerminalForm terminalForm = (TerminalForm) form; 
    		if(terminalForm.getMerchant_id()!=null&&!"".equals(terminalForm.getMerchant_id().trim())){
        		//ͨ���ն˺Ż�ȡԭ�����̻��ţ������������̻��ź����̻��Ų�һ���жϸ��ն������޽�����ˮ
        		TerminalBean term=terminalDao.getTerminalByTermcode(terminalForm.getTermcode());
        		String oldMerchantId=term==null?"":term.getMrchno();
    			if(!oldMerchantId.equals(terminalForm.getMerchant_id().trim())){
        			TlogDao td=new TlogDao();
            		TlogBean querybean=new TlogBean();
            		querybean.setTermcode(terminalForm.getTermcode());
            		int count=td.getCount(querybean);
        			if(count>0){
                    	request.setAttribute("info", "���ն˴��ڽ�����ˮ�����ܹҵ������̻��£�");
            	        request.setAttribute("flushdo", flushdo);
                        request.setAttribute("result", "1");   
                        return mapping.findForward("result.jsp");   				
        			}   				
    			}
    		}
    		String terminalStat=terminalForm.getTerm_stat();
    		if(terminalStat!=null){
    			terminalForm.setStatusid(terminalStat);
    		}
    		Termposbean termposBean = new Termposbean();
    		
    		filledTermposbean(terminalForm, termposBean,"update");
    		
    		Termpos_xBean termpos_xBean = new Termpos_xBean();
    		filledTermpos_xBean(terminalForm, termpos_xBean,"update");
    		
    		Enckeybean enckeybean = new Enckeybean();
    		filledEnckeybean(terminalForm, enckeybean);
    		
    		termposBean.setId(ParamUtils.getParameter(request, "id"));
    		enckeybean.setId(ParamUtils.getParameter(request, "enckey_id"));
    		//System.out.println("ID==="+ParamUtils.getParameter(request, "id"));

    		//�޸�����̻���
    		WankeMerBookBean wanke_MerBookBean = new WankeMerBookBean();
    		if(terminalForm.getYard_mer_type()!=null){
    			if("0".equals(terminalForm.getYard_mer_type())){
    				filledWankeMerBookBean(terminalForm, wanke_MerBookBean);
    			}
    		}else{
    			//��ע��terminalForm.getMrchnoSelQBak()ͬ����terminalForm.getMrchno()
    			wanke_MerBookBean.setYard_mer_no(terminalForm.getMrchnoSelQBak());
    			wanke_MerBookBean.setYard_ter_no(terminalForm.getTermcode());
    			//ͨ���̻��ź��ն˺Ż�ȡIDȻ����и���ֵ��
    			List<WankeMerBookBean> wankeList=terminalDao.getTermYardId(wanke_MerBookBean);
    			String YardId="";
    			if(wankeList!=null){
    	        	if(wankeList.size()>0){
    	        		YardId=wankeList.get(0).getYard_id();
    	            }
    	        }
    			wanke_MerBookBean.setYard_id(YardId);
    		}
    		int result = terminalDao.updTerminalInfo(termposBean, termpos_xBean, enckeybean,wanke_MerBookBean);
    		if (result >= 0) {
	            info = "�ն���Ϣ�޸ĳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "���ݿ��쳣���ն���Ϣ�޸ļ�ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("resultOfUpdate.jsp");   				
	}
    
    /**
	 * �ն˱���Ϣ��ֵ
	 * @param terminalForm
	 * @param termposBean
	 */
	private void filledTermposbean(TerminalForm terminalForm, Termposbean termposBean,String updateOrInsert) {
		Merchantdao merchantdao = new Merchantdao();
		MerchantBean merchantB=merchantdao.getMerchantBeanListByMrchNoOrName(terminalForm.getMerchant_id(),null,null,null,"no",null).get(0);
		termposBean.setTypeid(terminalForm.getTypeid());
		termposBean.setTermcode(terminalForm.getTermcode());
		termposBean.setTestflag(terminalForm.getTestflag());
		termposBean.setStatusid(terminalForm.getStatusid());
		termposBean.setCurrcode(terminalForm.getCurrcode());
		//��ӵ�ʱ���ն˺�ȡ��ǰ�̻����ն˸�ֵ���ֵ��1
		//������termno Ϊnumber(5) �������5λ����ν����
		if(null!=updateOrInsert&&"insert".equals(updateOrInsert)){
			TerminalDao terminalDao = new TerminalDao();
			String currentTerNO=terminalDao.getCurrentTermNoByMrchId(merchantB.getId());
			termposBean.setTermno(currentTerNO);
		}
		if(null!=updateOrInsert&&"update".equals(updateOrInsert)){
			//�޸������̻�
			if(terminalForm.getMerchant_id()!=null&&terminalForm.getMerchant_id().trim().length()>0){
				TerminalDao terminalDao = new TerminalDao();
				String currentTerNO=terminalDao.getCurrentTermNoByMrchId(merchantB.getId());
				termposBean.setTermno(currentTerNO);				
			}else{//���޸������̻�
				termposBean.setTermno(terminalForm.getTermno());				
			}
		}
		//��������̻���Чʱ�����ı������̻�������
		if(null!=updateOrInsert&&"update".equals(updateOrInsert)){
			if(terminalForm.getMerchant_id()!=null&&terminalForm.getMerchant_id().trim().length()>0){
				termposBean.setMerchant_id(merchantB.getId());
			}else{
				termposBean.setMerchant_id(null);
			}
		}else{
			termposBean.setMerchant_id(merchantB.getId());
		}
		String location=terminalForm.getX_location();
		if(location!=null&&location.length()>10){
			location=location.substring(0,10);
		}
		termposBean.setLocation(location);
		termposBean.setConaccno(terminalForm.getConaccno());
		termposBean.setConcur(terminalForm.getConcur());
		termposBean.setPoschic(terminalForm.getPoschic());
		termposBean.setPoschac(terminalForm.getPoschac());
		termposBean.setPoscrc(terminalForm.getPoscrc());
		termposBean.setPosoe(terminalForm.getPosoe());
		termposBean.setPoscdoc(terminalForm.getPoscdoc());
		termposBean.setPostoc(terminalForm.getPostoc());
		termposBean.setPospcc(terminalForm.getPospcc());
		termposBean.setTimezone(terminalForm.getX_timezone());
		termposBean.setCat_params(terminalForm.getCat_params());
		termposBean.setVerno_ctx(terminalForm.getVerno_ctx());

		termposBean.setTermtype(terminalForm.getTermtype());
		
	}
	
	/**
	 * �ն˱�����Ϣ��ֵ
	 * @param terminalForm
	 * @param termposBean
	 */
	private void filledTermpos_xBean(TerminalForm terminalForm, Termpos_xBean termpos_xBean,String updateOrInsert) {
		termpos_xBean.setPos_tel(terminalForm.getPos_tel());
		termpos_xBean.setBatch_no("0");
		termpos_xBean.setAdd_date(terminalForm.getAdd_date());//
		termpos_xBean.setLocation(terminalForm.getX_location());
		termpos_xBean.setState(terminalForm.getState());
		termpos_xBean.setCity_no(terminalForm.getCity_no());
		termpos_xBean.setProvince(terminalForm.getProvince());
		termpos_xBean.setZone(terminalForm.getZone()==null?"":terminalForm.getZone());
		termpos_xBean.setSettle_mrch_acc_id(terminalForm.getSettle_mrch_acc_id());
		termpos_xBean.setTermcode(terminalForm.getTermcode());
		termpos_xBean.setX_timezone(terminalForm.getX_timezone());
		//��������̻���Чʱ�����ı������̻�������
		if(null!=updateOrInsert&&"update".equals(updateOrInsert)){
			if(terminalForm.getMerchant_id()!=null&&terminalForm.getMerchant_id().trim().length()>0){
				termpos_xBean.setMrchno(terminalForm.getMerchant_id());
			}else{
				termpos_xBean.setMrchno(null);
			}
		}else{
			termpos_xBean.setMrchno(terminalForm.getMerchant_id());
		}
		termpos_xBean.setTerm_stat(terminalForm.getTerm_stat());
		if(terminalForm.getMerchant_id()!=null&&terminalForm.getMerchant_id().trim().length()>0){
			Merchantdao merchantdao = new Merchantdao();
			MerchantBean merchantB=merchantdao.getMerchantBeanListByMrchNoOrName(terminalForm.getMerchant_id(),null,null,null,"no",null).get(0);
			termpos_xBean.setInst_id(merchantB.getInst_id());
		}else{
			termpos_xBean.setInst_id(null);
		}
		termpos_xBean.setConsump_category(terminalForm.getConsump_category());
		termpos_xBean.setDisabled_date(terminalForm.getDisabled_date());
		termpos_xBean.setEnable_date(terminalForm.getEnable_date());
	}
	
	/**
	 * ENCKEY����Ϣ��ֵ
	 * @param terminalForm
	 * @param termposBean
	 */
	private void filledEnckeybean(TerminalForm terminalForm, Enckeybean enckeybean) {
		
		enckeybean.setActdate(terminalForm.getActdate());
		enckeybean.setActtime(terminalForm.getActtime());
		
	}
	
	
	
	/**
	 * ����̻�����Ϣ��ֵ
	 * @param terminalForm
	 * @param termposBean
	 */
	private void filledWankeMerBookBean(TerminalForm terminalForm, WankeMerBookBean wanke_MerBookBean) {
		
		if(null!=terminalForm.getYard_mer_type()){
			wanke_MerBookBean.setYard_mer_no(terminalForm.getMerchant_id());
			//��ȡ�̻�����
			Merchantdao merchantdao = new Merchantdao();
			MerchantBean merchantB=merchantdao.getMerchantBeanListByMrchNoOrName(terminalForm.getMerchant_id(),null,null,null,"no",null).get(0);

			wanke_MerBookBean.setYard_mer_name(merchantB.getName().split("\\(|\\)")[1]);
			wanke_MerBookBean.setYard_mer_type(terminalForm.getYard_mer_type());
			//����̻���������
			if("0".equals(terminalForm.getYard_mer_type())){
				wanke_MerBookBean.setYard_mer_type_name("��Ƴ���");
			}
		}
		
		wanke_MerBookBean.setYard_ter_no(terminalForm.getTermcode());
		
		//���ѳ���
		SysParameterDao sysParameterDao=new SysParameterDao();
		List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CONSUMP_CATEGORY");

		wanke_MerBookBean.setYard_scene_id(terminalForm.getConsump_category());
		for(int i=0;i<consump_categoryList.size();i++){
			if(terminalForm.getConsump_category().equals(consump_categoryList.get(i).getParam_value())){
				wanke_MerBookBean.setYard_scene_name(consump_categoryList.get(i).getParam_name());
			}
		}
	
	}
	
	/**
	 * �ж��Ƿ��̻�������̻���
	 */
	public org.apache.struts.action.ActionForward findmerchantNo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
				javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		
		try {

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String merchantNo = request.getParameter("merchantNo");
			
			Merchantdao merchantDao = new Merchantdao();
			List<WankeMerBookBean> wankeList = merchantDao.findWanKe(merchantNo);
			String yard_mer_type="";
			if(wankeList!=null){
	        	if(wankeList.size()>0){
	        		yard_mer_type=wankeList.get(0).getYard_mer_type();
	            }
	        }
			// ����
			if((yard_mer_type!="")&&(yard_mer_type!=null)){
				out.print(yard_mer_type);		
			}else{
				out.print(false);//������
			}

			log.info("TerminalAction.findmerchantNo()��������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TerminalAction.findmerchantNo()���ó����쳣��");

		}
		return mapping.findForward(null);

	}
	
	
}
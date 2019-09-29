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
	        // 进入主页前，把查询条件设置为空
	        HttpSession session = request.getSession();
	        TerminalDao terminalDao = new TerminalDao();
			// 获得终端信息列表记录
	        TerminalForm terminalform = (TerminalForm)form;
	        beforAddorModTerminal(request,null);	        
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = terminalDao.getCount(terminalform,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
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
        // 进入主页前，把查询条件设置为空
        TerminalDao terminalDao = new TerminalDao();
		// 获得商户信息列表所有记录
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
			
			String[] headers = { "终端ID","终端号","终端序列号","终端所在位置","终端状态","所属商户","结算账号","费率（单位：%）","增加日期","停用时间","启用时间"};
			expexl.exportExcel("终端信息", headers, terminalList, out, "yyyy-MM-dd");			
			
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
	 * 查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryAgentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 进入主页前，把查询条件设置为空
		try {
	        // 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
	        TerminalDao terminalDao = new TerminalDao();
	        beforAddorModTerminal(request,null);
			// 获得终端信息列表记录
	        TerminalForm terminalform = (TerminalForm)form;
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = terminalDao.getCount(terminalform,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
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
     * 商户新增或修改时，将其所需的下拉列表信息存放于request中
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
		
		//获取所有一线城市
		List<AreaBean> provinList = mcdao.getCityByFid("0");
		//获取二线城市
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
		//消费场景
		SysParameterDao sysParameterDao=new SysParameterDao();
		List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CONSUMP_CATEGORY");
		if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
			request.setAttribute("consump_categoryList", consump_categoryList);
		}
	}
	
	/**
	 * 初始化增加商户页面
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
	 * 添加商户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTerminalInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		String info = "";
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
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
	            info = "的终端添加成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "数据库异常，终端添加失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner("终端号为"+terminalForm.getTermcode()+info));
	        request.setAttribute("flushdo", flushdo);
//	        if(terminalForm.getTermcode()!=null&&terminalForm.getTermcode().length()>4){
//		        terminalForm.setTermcode(terminalForm.getTermcode().substring(0,4));        	
//	        }
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
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
            //存放下拉列表信息
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
            	//设置划账归账的结算账号
				Mrch_acc_xBean mrchaccxBean = merchantDao.getMrchaccxById(terminalBean.getSettle_mrch_acc_id());
				if(mrchaccxBean!=null){
					terminalForm.setSettle_mrch_acc_id("("+mrchaccxBean.getId()+")"+mrchaccxBean.getAccno()+"("+mrchaccxBean.getAcc_name()+")");
				}
				//设置所属商户
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
     * @TODO 准备修改终端信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
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
            //存放下拉列表信息
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

     // 获取终端界面的商户号和终端号并判断此商户号存在万科商户表
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
                //设置所属商户下拉框为商户号mrchNo，非商户编号mrch_id
                terminalForm.setMerchant_id(terminalBean.getMrchno());
                //进入修改页面前判断终端所属商户是否有效和是否属于结算商户，无效或是结算商户的话把所属商户设置成不可修改；保存的时候不修改商户。
                
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
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo="/terminal.do?method=getTerminalList";
		//String flushdo = "closewindow";
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {  
    		TerminalDao terminalDao = new TerminalDao();
    		TerminalForm terminalForm = (TerminalForm) form; 
    		if(terminalForm.getMerchant_id()!=null&&!"".equals(terminalForm.getMerchant_id().trim())){
        		//通过终端号获取原本的商户号，如果新输入的商户号和老商户号不一致判断该终端下有无交易流水
        		TerminalBean term=terminalDao.getTerminalByTermcode(terminalForm.getTermcode());
        		String oldMerchantId=term==null?"":term.getMrchno();
    			if(!oldMerchantId.equals(terminalForm.getMerchant_id().trim())){
        			TlogDao td=new TlogDao();
            		TlogBean querybean=new TlogBean();
            		querybean.setTermcode(terminalForm.getTermcode());
            		int count=td.getCount(querybean);
        			if(count>0){
                    	request.setAttribute("info", "该终端存在交易流水，不能挂到其他商户下！");
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

    		//修改万科商户表
    		WankeMerBookBean wanke_MerBookBean = new WankeMerBookBean();
    		if(terminalForm.getYard_mer_type()!=null){
    			if("0".equals(terminalForm.getYard_mer_type())){
    				filledWankeMerBookBean(terminalForm, wanke_MerBookBean);
    			}
    		}else{
    			//备注：terminalForm.getMrchnoSelQBak()同等于terminalForm.getMrchno()
    			wanke_MerBookBean.setYard_mer_no(terminalForm.getMrchnoSelQBak());
    			wanke_MerBookBean.setYard_ter_no(terminalForm.getTermcode());
    			//通过商户号和终端号获取ID然后进行更新值。
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
	            info = "终端信息修改成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "数据库异常，终端信息修改加失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("resultOfUpdate.jsp");   				
	}
    
    /**
	 * 终端表信息赋值
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
		//添加的时候终端号取当前商户下终端该值最大值加1
		//隐患：termno 为number(5) 如果超出5位数如何解决？
		if(null!=updateOrInsert&&"insert".equals(updateOrInsert)){
			TerminalDao terminalDao = new TerminalDao();
			String currentTerNO=terminalDao.getCurrentTermNoByMrchId(merchantB.getId());
			termposBean.setTermno(currentTerNO);
		}
		if(null!=updateOrInsert&&"update".equals(updateOrInsert)){
			//修改所属商户
			if(terminalForm.getMerchant_id()!=null&&terminalForm.getMerchant_id().trim().length()>0){
				TerminalDao terminalDao = new TerminalDao();
				String currentTerNO=terminalDao.getCurrentTermNoByMrchId(merchantB.getId());
				termposBean.setTermno(currentTerNO);				
			}else{//不修改所属商户
				termposBean.setTermno(terminalForm.getTermno());				
			}
		}
		//解决所属商户无效时，不改变所属商户的问题
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
	 * 终端表补充信息赋值
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
		//解决所属商户无效时，不改变所属商户的问题
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
	 * ENCKEY表信息赋值
	 * @param terminalForm
	 * @param termposBean
	 */
	private void filledEnckeybean(TerminalForm terminalForm, Enckeybean enckeybean) {
		
		enckeybean.setActdate(terminalForm.getActdate());
		enckeybean.setActtime(terminalForm.getActtime());
		
	}
	
	
	
	/**
	 * 万科商户表信息赋值
	 * @param terminalForm
	 * @param termposBean
	 */
	private void filledWankeMerBookBean(TerminalForm terminalForm, WankeMerBookBean wanke_MerBookBean) {
		
		if(null!=terminalForm.getYard_mer_type()){
			wanke_MerBookBean.setYard_mer_no(terminalForm.getMerchant_id());
			//获取商户名称
			Merchantdao merchantdao = new Merchantdao();
			MerchantBean merchantB=merchantdao.getMerchantBeanListByMrchNoOrName(terminalForm.getMerchant_id(),null,null,null,"no",null).get(0);

			wanke_MerBookBean.setYard_mer_name(merchantB.getName().split("\\(|\\)")[1]);
			wanke_MerBookBean.setYard_mer_type(terminalForm.getYard_mer_type());
			//万科商户类型名称
			if("0".equals(terminalForm.getYard_mer_type())){
				wanke_MerBookBean.setYard_mer_type_name("万科车场");
			}
		}
		
		wanke_MerBookBean.setYard_ter_no(terminalForm.getTermcode());
		
		//消费场景
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
	 * 判断是否商户在万科商户表
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
			// 存在
			if((yard_mer_type!="")&&(yard_mer_type!=null)){
				out.print(yard_mer_type);		
			}else{
				out.print(false);//不存在
			}

			log.info("TerminalAction.findmerchantNo()结束调用");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TerminalAction.findmerchantNo()调用出现异常。");

		}
		return mapping.findForward(null);

	}
	
	
}
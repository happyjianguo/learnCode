package cn.yufu.posp.common.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.actions.DispatchAction;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.yufu.posp.common.common.util.SystemConstants;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.system.common.shiro.UserUtils;

public class OABaseDispatchAction extends DispatchAction
{

	protected static final Log	webLog			= LogFactory.getLog("web");

	protected static final Log	exceptionLog	= LogFactory.getLog("exception");

	protected static final Log	debugLog		= LogFactory.getLog("debug");

	public Object getBean(String newBeanName)
	{
		ApplicationContext ctx = 
            WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
        return ctx.getBean(newBeanName);
	}

	/**
	 * ��ȡ��ǰ�û���Ϣ
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return UserData
	 */
	protected UserData getSessionUserData(HttpServletRequest request)
	{	
		UserData ud = null;
		HttpSession session = request.getSession(false);
		if(null != session && session.getAttribute(SystemConstants.CURRENT_USER_DATA)!=null)
			ud = (UserData) session.getAttribute(SystemConstants.CURRENT_USER_DATA);
		else ud = new UserData();
		
		ud.setUserId(UserUtils.getLoginName().length()>6?UserUtils.getLoginName().substring(0, 6):UserUtils.getLoginName());
		ud.setUserName(UserUtils.getUserName());
		//ud.set
		
		return ud;
	}

	/**
	 * �õ�DisplayTag����������
	 * 
	 * @param tableId -
	 *            ����ID
	 * @return ����һ�����顣��1��Ԫ����Page����2��Ԫ����Order����3��Ԫ����Sort
	 */
	protected String[] getDisplayParams(String tableId)
	{
		String[] params = new String[3];

		String dpPageName = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String dpPageOrder = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
		String dpPageSort = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT);

		params[0] = dpPageName;
		params[1] = dpPageOrder;
		params[2] = dpPageSort;

		return params;
	}

	/**
	 * �õ�DisplayTag����������(Ĭ��Display�ı��Id��displayTable)
	 * 
	 * @return ����һ�����顣��1��Ԫ����Page����2��Ԫ����Order����3��Ԫ����Sort
	 */
	protected String[] getDisplayParams()
	{
		return getDisplayParams("displayTable");
	}
    
    /**
     * ��ȡ����������
     * 
     * @param request
     *            HttpServletRequest
     * @return UserData
     */
//    protected MFExecutionService getMFExecutionService(HttpServletRequest request)
//    {   
//        //�õ�����
//        HttpSession session = request.getSession(false);
//        MFExecutionService newMFExecutionService = (MFExecutionService) session.getAttribute(SystemConstants.CURRENT_MFEXECUTION_SERVICE);
//        
//        
//        return newMFExecutionService;
//    }
    public synchronized boolean isTokenValid(
            HttpServletRequest request,
            String token)  {

            // Retrieve the current session for this request
            HttpSession session = request.getSession(false);
            if (session == null) {
                return false;
            }

            // Retrieve the transaction token from this session, and
            // reset it if requested
            String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
            if (saved == null) {
                return false;
            }

//            if (reset) {
//                this.resetToken(request);
//            }

            // Retrieve the transaction token included in this request
//            String token = request.getParameter(Constants.TOKEN_KEY);
            if (token == null) {
                return false;
            }
            return saved.equals(token);
        }
}

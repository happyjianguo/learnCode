package cn.yufu.core.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * <p>Title: 权限验证标记</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author
 * @version
 */
public class AuthTag
    extends TagSupport
{
    /**
     *权限代码属性
     */
    private int authID;
    /**
     * 操作权限对象
     */
    private AuthBean ab = null;

    /**
     * Tag的初始方法
     * @return 标记体返回信息
     * @throws JspException
     */
    @Override
	public int doStartTag()
        throws JspException
    {
        //读取操作节点
        String jdid = (String) pageContext.getSession().getAttribute("jdid");
        //获取用户操作权限
        ab = (AuthBean) pageContext.getSession().getAttribute("authinfo");

        if (ab == null)
        {
            return SKIP_BODY;
        }
        //如果有相应的权限返回标记体
        if (true)
        {
            return EVAL_BODY_INCLUDE;
        }

        //否则忽略标记体
        return SKIP_BODY;
    }

    /**
     * 权限代码属性设置
     * @param ID 用户权限代码
     */
    public void setAuthID(int ID)
    {
        this.authID = ID;
    }
}

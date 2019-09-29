package cn.yufu.core.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * <p>Title: Ȩ����֤���</p>
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
     *Ȩ�޴�������
     */
    private int authID;
    /**
     * ����Ȩ�޶���
     */
    private AuthBean ab = null;

    /**
     * Tag�ĳ�ʼ����
     * @return ����巵����Ϣ
     * @throws JspException
     */
    @Override
	public int doStartTag()
        throws JspException
    {
        //��ȡ�����ڵ�
        String jdid = (String) pageContext.getSession().getAttribute("jdid");
        //��ȡ�û�����Ȩ��
        ab = (AuthBean) pageContext.getSession().getAttribute("authinfo");

        if (ab == null)
        {
            return SKIP_BODY;
        }
        //�������Ӧ��Ȩ�޷��ر����
        if (true)
        {
            return EVAL_BODY_INCLUDE;
        }

        //������Ա����
        return SKIP_BODY;
    }

    /**
     * Ȩ�޴�����������
     * @param ID �û�Ȩ�޴���
     */
    public void setAuthID(int ID)
    {
        this.authID = ID;
    }
}

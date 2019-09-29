package com.jansh.core.web.servlet.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 脚本标签
 * 
 * @author nfx
 */
public class AppScriptTag extends BasicTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1501208408640720649L;

	/**
	 * Process the start of this tag.
	 * 
	 * @return int status
	 * @exception JspException
	 *                if a JSP exception has occurred
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			write("<appscript>");
		} catch (IOException io) {
			throw new JspException(io);
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			write("</appscript>");
		} catch (IOException io) {
			throw new JspException(io);
		}
		return super.doEndTag();
	}
}

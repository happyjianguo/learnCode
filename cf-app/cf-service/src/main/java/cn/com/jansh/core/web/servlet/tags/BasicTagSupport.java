package cn.com.jansh.core.web.servlet.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * JSP标签实现类的基础类，提供一些公共的方法
 * 
 * @version 1.0
 */
public class BasicTagSupport extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 880949202097024860L;
	/**
	 * 指定保存当前应用参数列表的key的名称，或者生成HTML元素的名称
	 */
	protected String name;
	/**
	 * 用户自定义属性，将直接作为select的属性输出
	 */
	protected String customerAtt;

	/**
	 * @param str
	 * @throws IOException
	 */
	protected void write(String str) throws IOException {
		pageContext.getOut().write(str);
	}

	/**
	 * Release aquired resources to enable tag reusage.
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	@Override
	public void release() {
		super.release();
	}
}

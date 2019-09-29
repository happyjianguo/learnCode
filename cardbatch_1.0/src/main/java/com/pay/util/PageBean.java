package com.pay.util;


public class PageBean {

	private int totalRecords = 0;//�ܼ�¼��

	private int pageSize = 10;//ÿҳ��ʾ�ļ�¼��

	private int currentPage = 1;//��ǰ��ʾ��ҳ��

	private int totalPage = 0;//��ҳ��

	public PageBean()
	{}
	
	/**
     * 
     * @TODO �����ҳ���󣬳�ʼ����ҳ����ÿҳ��ʾ��¼������ǰҳ��
     *
     * @param totalRecords
     * @param pageSize
     * @param currentPage
     
     * </pre>
	 */
	public PageBean(int totalRecords,int pageSize,int currentPage)
	{
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.pageSize = pageSize;
		//��ҳ�� = ���ܼ�¼�� + ÿҳ��ʾ��¼���� - 1��/ÿҳ��ʾ��¼��
		this.totalPage = (this.totalRecords + this.pageSize - 1)/this.pageSize;
		if(this.currentPage >= this.totalPage)
		{
			this.currentPage = this.totalPage;
		}
	}

	/**
     * 
     * @TODO ��ȡ��ǰ��ʾҳ�ĵ�һ����¼��λ��
     *
     * @return ȡ��¼�Ŀ�ʼλ��
     * 
     * </pre>
	 */
	public int getStart()
	{
		return pageSize * (currentPage - 1) + 1;
	}
    
	/**
     * 
     * @TODO ��ȡ��ǰ��ʾҳ�����һ����¼��λ��
     *
     * @return ���һ����¼��λ��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-27  11:44:51
     * LastModified
     * History:
     * </pre>
	 */
	public int getEnd()
	{
		return pageSize * currentPage;
	}
	
	/**
     * 
     * @TODO �ж��Ƿ������һҳ
     *
     * @return �жϽ��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-27  11:45:19
     * LastModified
     * History:
     * </pre>
	 */
	public boolean hasNextPage()
	{
		if(this.currentPage >= this.totalPage)
			return false;
		else
			return true;
	}
	
	/**
     * 
     * @TODO �ж��Ƿ������һҳ
     *
     * @return �жϽ��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-27  11:45:40
     * LastModified
     * History:
     * </pre>
	 */
	public boolean hasUppage()
	{
		if(this.currentPage <= 1)
			return false;
		else
			return true;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}

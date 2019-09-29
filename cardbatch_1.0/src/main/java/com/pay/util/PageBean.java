package com.pay.util;


public class PageBean {

	private int totalRecords = 0;//总记录数

	private int pageSize = 10;//每页显示的记录数

	private int currentPage = 1;//当前显示的页数

	private int totalPage = 0;//总页数

	public PageBean()
	{}
	
	/**
     * 
     * @TODO 构造分页对象，初始化总页数、每页显示记录数、当前页数
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
		//总页数 = （总记录数 + 每页显示记录条数 - 1）/每页显示记录数
		this.totalPage = (this.totalRecords + this.pageSize - 1)/this.pageSize;
		if(this.currentPage >= this.totalPage)
		{
			this.currentPage = this.totalPage;
		}
	}

	/**
     * 
     * @TODO 获取当前显示页的第一条记录的位置
     *
     * @return 取记录的开始位置
     * 
     * </pre>
	 */
	public int getStart()
	{
		return pageSize * (currentPage - 1) + 1;
	}
    
	/**
     * 
     * @TODO 获取当前显示页的最后一条记录的位置
     *
     * @return 最后一条记录的位置
     * @author 黄斌
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
     * @TODO 判断是否存在下一页
     *
     * @return 判断结果
     * @author 黄斌
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
     * @TODO 判断是否存在上一页
     *
     * @return 判断结果
     * @author 黄斌
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

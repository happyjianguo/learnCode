package cn.yufu.posp.common.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageInfoModel implements Serializable
{
	/**
	 * ��¼��������
	 */
	private int totalCount;
	
	/**
	 * ÿҳ��ʾ������
	 */
	private int pageSize = 8;
	
	/**
	 * ��ǰ��ҳ��
	 */
	private int currentPage=1;
	
	/**
	 * �����
	 */
	private List resultItems = new ArrayList();
	
	/**
	 * �����ֶΣ����ԣ�
	 */
	private String orderField;
	
	/**
	 * ����ʽ��˳����asc - ˳��desc - ����
	 */
	private String orderType;

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public String getOrderField()
	{
		return orderField;
	}

	public void setOrderField(String orderField)
	{
		this.orderField = orderField;
	}

	public String getOrderType()
	{
		return orderType;
	}

	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public List getResultItems()
	{
		return resultItems;
	}

	public void setResultItems(List resultItems)
	{
		this.resultItems = resultItems;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}	
	
	public static int currentPage(PageInfoModel pageInfo, int currentPage,
			int pageSize, int totalCount) {
		if (currentPage != 1 && (currentPage * pageSize - totalCount >= pageSize)) {
			if(totalCount % pageSize!=0||totalCount==0){
				currentPage = totalCount / pageSize + 1;
			}else{
				currentPage = totalCount / pageSize;
			}
			pageInfo.setCurrentPage(currentPage);
		}
		return currentPage;
	}
}

package com.pay.query.struts;

import org.apache.struts.actions.DispatchAction;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

public class BaseDispatchAction extends DispatchAction {
	/**
	 * �õ�DisplayTag����������
	 * 
	 * @param tableId
	 *            - ����ID
	 * @return ����һ�����顣��1��Ԫ����Page����2��Ԫ����Order����3��Ԫ����Sort
	 */
	protected String[] getDisplayParams(String tableId) {
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
	protected String[] getDisplayParams() {
		return getDisplayParams("displayTable");
	}
}

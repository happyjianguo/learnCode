package com.pay.query.struts;

import org.apache.struts.actions.DispatchAction;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

public class BaseDispatchAction extends DispatchAction {
	/**
	 * 得到DisplayTag的三个参数
	 * 
	 * @param tableId
	 *            - 表格的ID
	 * @return 返回一个数组。第1个元素是Page，第2个元素是Order，第3个元素是Sort
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
	 * 得到DisplayTag的三个参数(默认Display的表格Id是displayTable)
	 * 
	 * @return 返回一个数组。第1个元素是Page，第2个元素是Order，第3个元素是Sort
	 */
	protected String[] getDisplayParams() {
		return getDisplayParams("displayTable");
	}
}

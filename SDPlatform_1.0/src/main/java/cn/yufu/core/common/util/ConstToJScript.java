package cn.yufu.core.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author mengfp
 * @version 2014-08-21
 */
public class ConstToJScript {

	ConstManager manager = ConstManagerFactory.getConstManagerImpl();

	public String createScript() {
		Map map = manager.getConstMap();
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<script>\n");
		Set keys = map.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			buffer.append("var " + key + "= '" + map.get(key) + "';\n");
		}
		buffer.append("</script> \n");
		return buffer.toString();
	}

	public String createAreaScript() {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<script>\n");
		boolean isQH = false;
		boolean isNM = false;
		boolean isJL = false;
		boolean isHN = false;
		if (manager.getString("CONST_IS_AREA").equals("QH"))
			isQH = true;
		if (manager.getString("CONST_IS_AREA").equals("NM"))
			isNM = true;
		if (manager.getString("CONST_IS_AREA").equals("JL"))
			isJL = true;
		if (manager.getString("CONST_IS_AREA").equals("HN"))
			isHN = true;
		buffer.append(" function isQH(){return " + isQH + "} \n");
		buffer.append(" function isNM(){return " + isNM + "} \n");
		buffer.append(" function isJL(){return " + isJL + "} \n");
		buffer.append(" function isHN(){return " + isHN + "} \n");
		buffer.append("</script> \n");
		return buffer.toString();
	}

	public static void main(String[] args) {
		ConstToJScript reader = new ConstToJScript();
	}
}

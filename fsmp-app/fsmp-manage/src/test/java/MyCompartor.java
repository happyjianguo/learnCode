import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

/**
 * 流量套餐排序算法
 * 
 * @author gll
 *
 */
public class MyCompartor implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		String s1dw = s1.substring(s1.length() - 1);
		String s2dw = s2.substring(s2.length() - 1);
		if (s1dw.compareTo(s2dw) == 0) {
			int length = s1.length() > s2.length() ? s1.length() : s2.length();
			if (StringUtils.leftPad(s1, length, "0").compareTo(StringUtils.leftPad(s2, length, "0")) > 0) {
				return 1;
			} else {
				return -1;
			}
		} else if (s1dw.compareTo(s2dw) > 0) {
			return -1;
		} else {
			return 1;
		}
	}

}

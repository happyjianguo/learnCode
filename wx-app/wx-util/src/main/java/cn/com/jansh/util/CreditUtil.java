/**
 * CreditUtil.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司 
 * 创建:YangFan 2015-5-21
 */
package cn.com.jansh.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 金额工具类
 * @author YangFan
 * @version 1.0.0
 */
public class CreditUtil {

	/**
	 * 将数据库金额格式化为只带小数点的金额
	 * 
	 * @param credit
	 * @return
	 */
	public static String creditToFormatStr(long credit) {
		String sign = credit < 0 ? "-" : "";
		String value = Long.toString(Math.abs(credit));

		if (value.length() >= 3) {
			value = value.substring(0, value.length() - 2) + "."
					+ value.substring(value.length() - 2);

		} else if (value.length() == 2) {
			value = "0." + value;
		} else {
			value = "0.0" + value;
		}

		return sign + value;
	}

	/**
	 * 将只带小数点的金额转换成数据库的金额
	 * 
	 * @param credit
	 * @return
	 */
	public static long formatStrToCredit(String credit) {
		if (null == credit || credit.equals("")) {
			return 0L;
		}

		String value = credit.replaceAll("\\.", "");
		return Long.parseLong(value);
	}
	
	/**
	 * 
	 * 将带小数点的金额转换国际金融的金额
	 * @param credit
	 * @return
	 */
	public static String creditFormat(String credit) {
		String creditStyle = "###,##0.00";
		double amount = Double.parseDouble(credit);
		if (credit.indexOf(".") == -1) {
			amount = amount / 100;
		}
		DecimalFormat f = (DecimalFormat) NumberFormat.getInstance();
		f.applyLocalizedPattern(creditStyle);
		return f.format(amount);
	}
	

	/**
	 * 金额转换
	 * 
	 * @param focus
	 *            true为获得焦点 false为失去焦点
	 * @param credit
	 *            返回金额但如果发生错误时会返回原输入的金额不做任修改
	 * @return 经过处理的金额映射，可用的键有且仅有两个：OLD和NEW <br>
	 *         OLD保存录入的值，NEW保存经过修正的值<br>
	 *         当focus参数为true时,会去掉小数点和逗号<br>
	 *         例如:1,000.00转换为100000<br>
	 *         当focus参数为false时,会在没有小数点和逗号的数字上加上逗号和小数点<br>
	 *         例如:100000转换为1,000.00,1转换为0.01<br>
	 * 
	 */
	public static Map<String, String> creditFormat(boolean focus, String credit) {
		Map<String, String> creditMap = new HashMap<String, String>();
		creditMap.put("OLD", credit);
		String returnCredit = "";
		// 判断输入的是否为空
		if (null == credit || "".equals(credit)) {
			creditMap.put("NEW", credit);
			return creditMap;
		}
		// 去除千分位和小数点
		if (focus == true) {
			returnCredit = credit.replaceAll(",", "");
			returnCredit = returnCredit.replace(".", "");
			returnCredit = String.valueOf(Long.valueOf(returnCredit));
			// 将金额加上千分位和小数点
		} else {
			int creditLength = 0;
			String tmpCredit = "";
			// 取得金额总长度
			creditLength = credit.length();
			// 取得小数点在第几位
			int decimal = credit.lastIndexOf(".");
			if (decimal == -1) {
				// 判断总长度等于1的时候
				if (creditLength == 1) {
					// 转换成.0+?
					returnCredit = ".0" + credit;
					tmpCredit = "0";
				} else if (creditLength == 2) {
					// 转换成.+??
					returnCredit = "." + credit;
					tmpCredit = "0";
				} else {
					// 取得两位为角和分
					returnCredit = "." + credit.substring(creditLength - 2);
					tmpCredit = credit.substring(0, creditLength - 2);
				}
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				// 金额转换转换后形式是例$100,000.00
				tmpCredit = nf.format(Long.valueOf(tmpCredit));
				// 去掉转换后的货币符号
				// tmpCredit = tmpCredit.substring(1);
				String regEx = "[^0-9,.]";
				Pattern p = Pattern.compile(regEx);
				Matcher matcher = p.matcher(tmpCredit);
				tmpCredit = matcher.replaceAll("");
				// 因前面已经得到小数点后的值在转换的时候nf.format方法会自动加上.00所以要将.00去掉
				tmpCredit = tmpCredit.substring(0, tmpCredit.length() - 3);
				// 拼接后形成最终的显示金额
				returnCredit = tmpCredit + returnCredit;
			} else {
				// 取得小数点后的位数
				returnCredit = credit.substring(decimal);
				// 判断小数点后的长度
				if (credit.substring(0, decimal).lastIndexOf(".") != -1) {
					creditMap.put("NEW", credit);
					return creditMap;
				} else if (returnCredit.length() > 3) {
					creditMap.put("NEW", credit);
					return creditMap;
				} else {
					returnCredit = returnCredit.substring(1);
					// 格式化小数点后的字符
					for (int i = returnCredit.length(); i < 2; i++) {
						returnCredit = returnCredit + "0";
					}
					returnCredit = "." + returnCredit;
					// 得取小数点前的所有字符
					// 如果小数点前没有数字,则自动加0;
					if (decimal == 0) {
						tmpCredit = "0";
					} else {
						tmpCredit = credit.substring(0, decimal);
					}
					NumberFormat nf = NumberFormat.getCurrencyInstance();
					// 金额转换转换后形式是例$100,000.00
					tmpCredit = nf.format(Long.valueOf(tmpCredit));
					// 去掉转换后的货币符号
					// tmpCredit = tmpCredit.substring(1);
					String regEx = "[^0-9,.]";
					Pattern p = Pattern.compile(regEx);
					Matcher matcher = p.matcher(tmpCredit);
					tmpCredit = matcher.replaceAll("");
					// 因前面已经得到小数点后的值在转换的时候nf.format方法会自动加上.00所以要将.00去掉
					tmpCredit = tmpCredit.substring(0, tmpCredit.length() - 3);
					// 拼接后形成最终的显示金额
					returnCredit = tmpCredit + returnCredit;
				}
			}
		}
		creditMap.put("NEW", returnCredit);
		return creditMap;
	}
	
}

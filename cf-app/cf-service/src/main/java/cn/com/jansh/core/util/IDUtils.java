package cn.com.jansh.core.util;

/**
 * 各种id生成策略
 */
public class IDUtils {

	/**
	 * 根据当前时间+5位随机字符串
	 * 
	 * @return
	 */
	public static String getTimeRandon() {
		long time = DateUtil.currentTimeMillis();
		return StringUtil.base10to62(time) + StringUtil.randomCharNum(5);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 400; i++)
			System.out.println(getTimeRandon());
	}
}

package cn.com.jansh.util;

import java.util.Random;

/**
 * 各种id生成策略
 */
public class IDUtils {

	/**
	 * msg_seqid生成 当前系统时间戳+随机3位数 去掉整个数据的前俩位
	 */
	public static String getMsgId() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		// 如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		String id = str.substring(2);
		return id;
	}

	/**
	 * 根据当前时间+3位随机字符串
	 * 
	 * 
	 * @return
	 */
/*	public static String getTimeRandon() {
		long time = DateUtil.getCurrentDate().getTime();
		return StringUtil.base10to62(time) + StringUtil.randomCharNum(3);
	}*/

	public static void main(String[] args) {
		for (int i = 0; i < 400; i++)
			System.out.println(getMsgId());
	}
}

/**
 * RandomNumUtil.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月19日
 */
package cn.com.jansh.utils;

import java.util.Random;

/**
 * 获取6位数验证码工具类
 * @author Mr.wong
 * @version 1.0
 */
public class RandomNumUtil {

	public static final String[] NUMBERS = {"0","1","2","3","4","5","6","7","8","9"};
	
	public static String getRandomNum(int length){
		StringBuilder builder = new StringBuilder();
		int size = NUMBERS.length;
		for(int i = 0 ;i<length;i++){
			Random random = new Random();
			int index = random.nextInt(size);
			builder.append(NUMBERS[index]);
		}
		
		return builder.toString();
		
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getRandomNum(6));
		}
	}
	
}

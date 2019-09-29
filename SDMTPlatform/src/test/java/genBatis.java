import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

public class genBatis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "http://192.168.10.75:9002";
		System.out.println(s.length());
	}
	/**
	 * @param args
	 */
	public static void main1(String[] args) {
		System.out.println(isNumberMoney("",5));
		System.out.println(isNumberMoney(".",5));
		System.out.println(isNumberMoney(".1",5));
		System.out.println(isNumberMoney("1.",5));
		System.out.println(isNumberMoney("12",5));
		System.out.println(isNumberMoney("01",5));
		System.out.println(isNumberMoney("12",2));
		System.out.println(isNumberMoney("12",4));
	}
	/**
	 * 验证number（x,y）类型数据格式
	 * @param str
	 * @param i,目标位数
	 * @return
	 */
	public static boolean isNumberMoney(String str,int i){
		if(!(Strings.isNullOrEmpty(str))){
			if( i < str.length() ){
				return false; 
			}else{
				Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
				Matcher match = pattern.matcher(str);
				if (match.matches() == false) {
					return false;
				} else {
					int rn = str.length();
					int pos = str.indexOf(".");
					if(pos > 0){
//							有小数点
						if(pos > i-3 || pos+1 == rn)
							return false;
					}else{
						//wu
						if(rn > i-3)
							return false;
					}
					return true;
				}
			}
		}
		return false;
	}
}

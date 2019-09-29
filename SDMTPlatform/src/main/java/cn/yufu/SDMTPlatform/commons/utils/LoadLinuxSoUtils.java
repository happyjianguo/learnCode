package cn.yufu.SDMTPlatform.commons.utils;
/**
 * 加载Linux so文件调用动态库
 * 这个类千万什么都不要动！！！！！
 * @author mengfp
 * @date 2014年9月22日
 */
public class LoadLinuxSoUtils {
	static {
		System.loadLibrary("GetHsmKeyDouble");
	}
	/*************************************************************************************
	 *功能：加密终端主密钥
	 *入参：int iIndex: 密钥索引；
	 *出参：
	 *	String code|caCipherKeyUndLMK|caCipherKeyUndTMK|caCheckValue
	 *	 code : 0 成功
	 *   caCipherKeyUndLMK：LMK加密的工作密钥
	 *   caCipherKeyUndTMK：TMK加密的工作密钥
	 *   caCheckValue：工作密钥的校验值
	 *************************************************************************************/
	/*方法名不能变 ！！！*/
	public native static String GetHsmKeyDouble(int iIndex);
}

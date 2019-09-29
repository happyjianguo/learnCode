package cn.yufu.posp.common.util;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
public class CallLinuxSo {


		// 定义接口CLibrary，继承自com.sun.jna.Library
		public interface CLibrary extends Library {
			// 定义并初始化接口的静态变量
			CLibrary Instance = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

			// printf函数声明
			void printf(String format, Object... args);
		}

		public interface GetHsmKey extends Library {
			/**
			 * 当前路径是在项目下，而不是bin输出目录下。
			 */
			GetHsmKey INSTANCE = (GetHsmKey) Native.loadLibrary("GetHsmKeyDouble", GetHsmKey.class);

			public String GetHsmKeyDouble(int i, String caCipherKeyUndLMK, String caCipherKeyUndTMK, String caCheckValue);

		}

		public String GetHsmKeyDouble(int i,String caCipherKeyUndLMK,String caCipherKeyUndTMK,String caCheckValue){
			return GetHsmKey.INSTANCE.GetHsmKeyDouble(i, caCipherKeyUndLMK, caCipherKeyUndTMK, caCheckValue);
		}
		
		public static void main(String[] args) {
			for (int j = 0;j < 12; j++) {

				// 调用printf打印信息
				int i = 0x07;
				String caCipherKeyUndLMK = "", caCipherKeyUndTMK = "", caCheckValue = "";
				GetHsmKey.INSTANCE.GetHsmKeyDouble(i, caCipherKeyUndLMK, caCipherKeyUndTMK, caCheckValue);
				System.out.println("RETURN ERROR?");
				System.out.println("caCipherKeyUndLMK..."+caCipherKeyUndLMK);
				System.out.println("caCipherKeyUndTMK..."+caCipherKeyUndTMK);
				System.out.println("caCheckValue..."+caCheckValue);
				String[] ss = GetHsmKey.INSTANCE.GetHsmKeyDouble(i, caCipherKeyUndLMK, caCipherKeyUndTMK, caCheckValue).split("\\|");
				System.out.println("返回值Code"+ss[0]);
				System.out.println("返回值caCipherKeyUndLMK"+ss[1]);
				System.out.println("返回值caCipherKeyUndTMK"+ss[2]);
				System.out.println("返回值caCheckValue"+ss[3]);
			}
		}

}

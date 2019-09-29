package cn.yufu.posp.common.util;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
public class CallLinuxSo {


		// ����ӿ�CLibrary���̳���com.sun.jna.Library
		public interface CLibrary extends Library {
			// ���岢��ʼ���ӿڵľ�̬����
			CLibrary Instance = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

			// printf��������
			void printf(String format, Object... args);
		}

		public interface GetHsmKey extends Library {
			/**
			 * ��ǰ·��������Ŀ�£�������bin���Ŀ¼�¡�
			 */
			GetHsmKey INSTANCE = (GetHsmKey) Native.loadLibrary("GetHsmKeyDouble", GetHsmKey.class);

			public String GetHsmKeyDouble(int i, String caCipherKeyUndLMK, String caCipherKeyUndTMK, String caCheckValue);

		}

		public String GetHsmKeyDouble(int i,String caCipherKeyUndLMK,String caCipherKeyUndTMK,String caCheckValue){
			return GetHsmKey.INSTANCE.GetHsmKeyDouble(i, caCipherKeyUndLMK, caCipherKeyUndTMK, caCheckValue);
		}
		
		public static void main(String[] args) {
			for (int j = 0;j < 12; j++) {

				// ����printf��ӡ��Ϣ
				int i = 0x07;
				String caCipherKeyUndLMK = "", caCipherKeyUndTMK = "", caCheckValue = "";
				GetHsmKey.INSTANCE.GetHsmKeyDouble(i, caCipherKeyUndLMK, caCipherKeyUndTMK, caCheckValue);
				System.out.println("RETURN ERROR?");
				System.out.println("caCipherKeyUndLMK..."+caCipherKeyUndLMK);
				System.out.println("caCipherKeyUndTMK..."+caCipherKeyUndTMK);
				System.out.println("caCheckValue..."+caCheckValue);
				String[] ss = GetHsmKey.INSTANCE.GetHsmKeyDouble(i, caCipherKeyUndLMK, caCipherKeyUndTMK, caCheckValue).split("\\|");
				System.out.println("����ֵCode"+ss[0]);
				System.out.println("����ֵcaCipherKeyUndLMK"+ss[1]);
				System.out.println("����ֵcaCipherKeyUndTMK"+ss[2]);
				System.out.println("����ֵcaCheckValue"+ss[3]);
			}
		}

}

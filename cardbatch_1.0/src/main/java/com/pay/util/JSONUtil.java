package com.pay.util;

public class JSONUtil {

	/**
	 * ��JSON�ַ����е������ַ�����ת��
	 * @param s
	 * @return
	 */
	public static String normalizeString(String s) {

		StringBuffer str = new StringBuffer();
		int len = (s != null) ? s.length() : 0;
		for (int i = 0; i < len; i++) {
			final char lChar = s.charAt(i);
            if(lChar == '\n') str.append("\\n");
            else if(lChar == '\r') str.append("\\r");
            else if(lChar == '\t') str.append("\\t");
            else if(lChar == '\b') str.append("\\b");
            else if(lChar == '\f') str.append("\\f");
//            else if(lChar == '/') lBuf.append("\\/");
            else if(lChar == '\'') str.append("\\'");
            else if(lChar == '\"') str.append("\\\"");
            else if(lChar == '\\') str.append("\\\\");
            else str.append(lChar);
			
		}
		return (str.toString());
	}

	/**
	 * �ж��ַ����Ƿ���JS��JSON�еķǷ��ַ���
	 * @param s
	 * @return
	 */
	public static boolean isInvalidate(String s) {

		//���Ϊ�ջ�ո���Ϊ�Ϸ��ַ���
		if (s == null || "".equals(s.trim()))
			return false;

		// Javascript�������б�
		if ("break".equals(s) || "delete".equals(s) || "function".equals(s)
				|| "return".equals(s) || "typeof".equals(s) || "case".equals(s)
				|| "do".equals(s) || "if".equals(s) || "switch".equals(s)
				|| "var".equals(s) || "catch".equals(s) || "else".equals(s)
				|| "in".equals(s) || "this".equals(s) || "void".equals(s)
				|| "continue".equals(s) || "false".equals(s)
				|| "instanceof".equals(s) || "throw".equals(s)
				|| "while".equals(s) || "debugger".equals(s)
				|| "finally".equals(s) || "new".equals(s) || "true".equals(s)
				|| "with".equals(s) || "default".equals(s) || "for".equals(s)
				|| "null".equals(s) || "try".equals(s) || "enum".equals(s)
				|| "super".equals(s) || "export".equals(s)
				|| "import".equals(s) || "extends".equals(s)
				|| "class".equals(s) || "const".equals(s))
			return true;

		
		return false;
	}
}
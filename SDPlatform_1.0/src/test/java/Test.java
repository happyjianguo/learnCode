import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.web.action.EdcTerminalDispatchAction;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalForm;

/**
 *����
:
 *����:
 */
/**
 * Test.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��4��12��
 * ����:TODO
 */
public class Test {
	public static void main(String[] args){
//		int in = Integer.parseInt("0x06".replace("0x", ""), 16);
		System.out.println(Integer.toString(9));
		System.out.println(Integer.toString(16,8));
		System.out.println(Integer.toString(20,16));
		System.out.println(Integer.parseInt("11", 16));
		System.out.println(Integer.valueOf("11", 16));
		System.out.println(Integer.toString(111,16));
		System.out.println(Integer.toString(211,16));
		System.out.println(Integer.valueOf("11"));
//		System.out.println(Integer.valueOf("b"));
//		System.out.println(stringSize(777));
		
	
	}
	 final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
             99999999, 999999999, Integer.MAX_VALUE };

		// Requires positive x
		static int stringSize(int x) {
		for (int i=0; ; i++)
		if (x <= sizeTable[i])
		return i+1;
		}
	public EdcTerminalForm edcTerminalModelToFrom(EdcTerminal model) {
		EdcTerminalForm froms = new EdcTerminalForm();
		froms.setId(model.getId());
		/**
		 * EDC�豸���˵��
		 * <p>
		 * �ն������õ�EDC�豸�Ĺ�񡢳��ҡ����ܵ�˵�������֣���������ֲ�ͬ�����ͺŵ�EDC�豸��
		 * </p>
		 **/
		froms.setEdcDoc(model.getEdcDoc());
		/**
		 * ��ӡ������
		 * <p>
		 * EDC�ն������õĴ�ӡ���ͺ�
		 * </p>
		 */
		froms.setPrinterType(model.getPrinterType());
		/** PIN PAD���� */
		froms.setPinpadType(model.getPinpadType());
		/** ��װ���� YYYYMMDD */
		froms.setSetDate(model.getSetDate());
		/**
		 * ��װ�ص�
		 * <p>
		 * EDC�������̻�λ�õ�˵�������֣�����ά��ʱ�ο���
		 * </p>
		 */
		froms.setSetAddr(model.getSetAddr());
		/**
		 * EDC�ն�״̬
		 * <p>
		 * Y��-����,��N��-����<br>
		 * <br>
		 * �����ᡱ��־��EDC�ն˷��ϵĽ��׾ܾ�<br>
		 * </p>
		 */
		froms.setTerminalStat(model.getTerminalStat());
		/**
		 * EDC�豸�ͺ�
		 * <p>
		 * Լ����EDC�豸���ͱ�ʶ�����ݴ˿��б��EDCʹ���������͵ĸ�ʽ���н���������
		 * <p>
		 */
		froms.setEdcType(model.getEdcType());
		/** EDC����汾 */
		froms.setSoftVer(model.getSoftVer());
		/** �������ر�־ */
		froms.setDownloadFlag(model.getDownloadFlag());
		/** ��������ģʽ */
		froms.setDownloadMode(model.getDownloadMode());
		/** ������ */
		froms.setUpdateOper(model.getUpdateOper());
		/** �������� YYYYMMDD */
		froms.setUpdateDate(model.getUpdateDate());
		/** ����ʱ�� hhmmss */
		froms.setUpdateTime(model.getUpdateTime());
		/** �ն�״̬ ---ҳ����ʾ��--- */
		froms.setCh_terminalStat(model.getCh_terminalStat());
		/** ��ǰ��ʱ����� ---ҳ����ʾ��--- */
		froms.setCh_dateAndTime(model.getCh_dateAndTime());
		/** ҵ���ɫ ---ҳ����ʾ��--- */
		froms.setBusRoleId(model.getBusRoleId());
		/** ҵ���ɫ�������� ---ҳ����ʾ��--- */
		froms.setBusRoleName(model.getBusRoleName());
		return froms;
	}
}

import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.web.action.EdcTerminalDispatchAction;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalForm;

/**
 *包名
:
 *描述:
 */
/**
 * Test.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年4月12日
 * 描述:TODO
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
		 * EDC设备规格说明
		 * <p>
		 * 终端所配置的EDC设备的规格、厂家、性能等说明性文字，以区别各种不同厂家型号的EDC设备。
		 * </p>
		 **/
		froms.setEdcDoc(model.getEdcDoc());
		/**
		 * 打印机类型
		 * <p>
		 * EDC终端所配置的打印机型号
		 * </p>
		 */
		froms.setPrinterType(model.getPrinterType());
		/** PIN PAD类型 */
		froms.setPinpadType(model.getPinpadType());
		/** 安装日期 YYYYMMDD */
		froms.setSetDate(model.getSetDate());
		/**
		 * 安装地点
		 * <p>
		 * EDC所放置商户位置的说明性文字，便于维护时参考。
		 * </p>
		 */
		froms.setSetAddr(model.getSetAddr());
		/**
		 * EDC终端状态
		 * <p>
		 * Y’-正常,’N’-冻结<br>
		 * <br>
		 * “冻结”标志的EDC终端发上的交易拒绝<br>
		 * </p>
		 */
		froms.setTerminalStat(model.getTerminalStat());
		/**
		 * EDC设备型号
		 * <p>
		 * 约定的EDC设备类型标识串，据此刻判别此EDC使用哪种类型的格式进行解包、组包。
		 * <p>
		 */
		froms.setEdcType(model.getEdcType());
		/** EDC软件版本 */
		froms.setSoftVer(model.getSoftVer());
		/** 参数下载标志 */
		froms.setDownloadFlag(model.getDownloadFlag());
		/** 参数下载模式 */
		froms.setDownloadMode(model.getDownloadMode());
		/** 创建者 */
		froms.setUpdateOper(model.getUpdateOper());
		/** 创建日期 YYYYMMDD */
		froms.setUpdateDate(model.getUpdateDate());
		/** 创建时间 hhmmss */
		froms.setUpdateTime(model.getUpdateTime());
		/** 终端状态 ---页面显示用--- */
		froms.setCh_terminalStat(model.getCh_terminalStat());
		/** 日前和时间组合 ---页面显示用--- */
		froms.setCh_dateAndTime(model.getCh_dateAndTime());
		/** 业务角色 ---页面显示用--- */
		froms.setBusRoleId(model.getBusRoleId());
		/** 业务角色中文名字 ---页面显示用--- */
		froms.setBusRoleName(model.getBusRoleName());
		return froms;
	}
}

package cn.yufu.posp.terminalmanager.domain.model;

/**
 * EdcSwitch entity. @author MyEclipse Persistence Tools
 */

public class EdcSwitch implements java.io.Serializable {

	// Fields

	private EdcSwitchId id;

	/**À˚––÷’∂À∫≈*/
	private String othTerminalId;
	
	// Constructors

	public String getOthTerminalId() {
		return othTerminalId;
	}

	public void setOthTerminalId(String othTerminalId) {
		this.othTerminalId = othTerminalId;
	}

	/** default constructor */
	public EdcSwitch() {
	}

	/** full constructor */
	public EdcSwitch(EdcSwitchId id, String othTerminalId) {
		super();
		this.id = id;
		this.othTerminalId = othTerminalId;
	}

	// Property accessors

	public EdcSwitchId getId() {
		return this.id;
	}

	public void setId(EdcSwitchId id) {
		this.id = id;
	}

}
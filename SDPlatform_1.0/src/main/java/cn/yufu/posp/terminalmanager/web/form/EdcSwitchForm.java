package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.posp.terminalmanager.domain.model.EdcSwitchId;
import cn.yufu.core.web.form.BaseForm;

public class EdcSwitchForm extends BaseForm {
	private EdcSwitchId id= new EdcSwitchId();
	
	/**À˚––÷’∂À∫≈*/
	private String othTerminalId;
	
	
	public String getOthTerminalId() {
		return othTerminalId;
	}

	public void setOthTerminalId(String othTerminalId) {
		this.othTerminalId = othTerminalId;
	}

	public EdcSwitchId getId() {
		return id;
	}

	public void setId(EdcSwitchId id) {
		this.id = id;
	}
	
}

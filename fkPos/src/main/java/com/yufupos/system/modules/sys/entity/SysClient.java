/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;

/**
 * 接入工程客户端Entity
 * @author mengfp
 * @version 2015-06-30
 */
public class SysClient extends DataEntity<SysClient> {
	
	private static final long serialVersionUID = 1L;
	private String clientid;		// 客户端ID
	private String clientname;		// 客户端名称
	private String clientsecret;		// 客户端密串
	
	public SysClient() {
		super();
	}

	public SysClient(String id){
		super(id);
	}

	@Length(min=1, max=100, message="客户端ID长度必须介于 1 和 100 之间")
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	
	@Length(min=1, max=100, message="客户端名称长度必须介于 1 和 100 之间")
	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	
	@Length(min=1, max=100, message="客户端密串长度必须介于 1 和 100 之间")
	public String getClientsecret() {
		return clientsecret;
	}

	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
	}
	
}
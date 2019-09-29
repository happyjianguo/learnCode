package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.SysParameter;


public interface SysParameterService {

	
	public List<SysParameter> queryList(SysParameter queryModel);
	
	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */

	
}

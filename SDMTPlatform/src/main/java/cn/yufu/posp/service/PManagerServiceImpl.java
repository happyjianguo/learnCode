/**
 *包名:cn.yufu.posp.service
 *描述:package cn.yufu.posp.service;
 */
package cn.yufu.posp.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.posp.dao.MerchantBaseMapper;
import cn.yufu.posp.entity.PManager;

/**
 * PManagerServiceImpl.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月6日
 * 描述:商户信息Service实现类
 */
@Service("posp.pManagerService")
@Transactional("transactionManagerPosp")
public class PManagerServiceImpl implements PManagerService {
	
	Log log = Log.getLog(PManagerServiceImpl.class);
	
	@Autowired
	@Qualifier("posp.MerchantBaseDao")
	private MerchantBaseMapper MerchantBaseDao;
	
	public List<PManager> findListByRoleId(String role) {
		log.debug("查询店长或者普通员工", role);
		PManager pManagerSM=new PManager();
		if(StringUtils.isNotEmpty(role)){
			pManagerSM.setManagerRole(role);	
		}
		pManagerSM.setManagerStatus("0");
		pManagerSM.setDelFlag("0");
		return MerchantBaseDao.findListByRoleId(pManagerSM);
	}

	public List<PManager> findListByRoleIdAndArea(String roleId, String managerArea) {
		//获取店长码表
		PManager pManagerSM=new PManager();
		if(StringUtils.isNotEmpty(roleId)){
			if(roleId.trim().equals("0")){
				pManagerSM.setManagerRole("");
			}else{
				pManagerSM.setManagerRole(roleId);	
			}
		}
		if(StringUtils.isNotEmpty(managerArea)){
			pManagerSM.setManagerArea(managerArea);
		}
		pManagerSM.setManagerStatus("0");
		pManagerSM.setDelFlag("0");
		return MerchantBaseDao.findListByRoleIdAndArea(pManagerSM);
	}

}

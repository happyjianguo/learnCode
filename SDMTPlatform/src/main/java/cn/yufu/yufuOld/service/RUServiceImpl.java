package cn.yufu.yufuOld.service;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.yufuOld.dao.RUMapper;
import cn.yufu.yufuOld.entity.RU;
import cn.yufu.yufuOld.entity.RUExample;

@Service("yufuOld.RUService")
@Transactional("transactionManagerYufuOld")
public class RUServiceImpl implements RUService {
	Log log = Log.getLog(RUServiceImpl.class);

	@Autowired
	@Qualifier("yufuOld.RUDao")
	private RUMapper RUDao;
	
	public List<RU> queryList(String lruid) {
		RUExample example = new RUExample();
		RUExample.Criteria criteria = example.createCriteria();
		criteria.andLruidEqualTo(lruid);
		return RUDao.selectByExample(example);
	}

	public void synchronousYufuOldMerchant(MerchantSDMT info) {
		RU ru=new RU();
		ru.setLruid(info.getMerchantId());
		if(StringUtils.isNotEmpty(info.getMrchtName())){
			if(info.getMrchtName().length() > 8){
				ru.setSru(info.getMrchtName().substring(0,8));
			}else{
				ru.setSru(info.getMrchtName());//商户名称
			}
		}else{
			ru.setSru(info.getMrchtName());
		}
		if(StringUtils.isNotEmpty(info.getAddress())){
			if(info.getAddress().length() > 10){
				ru.setSaddr1(info.getAddress().substring(0,10));
			}else{
				ru.setSaddr1(info.getAddress());//地址
			}
		}else{
			ru.setSaddr1(info.getAddress());
		}
//		ru.setSru(info.getMrchtName());
//		ru.setSaddr1(info.getAddress());
		ru.setCardprefectureid(Integer.valueOf(info.getProvince()));
		ru.setCardareaid(Integer.valueOf(info.getCityNo()));
		ru.setCityid(Integer.valueOf(info.getCityNo()));
		ru.setScontact(info.getManager());
		ru.setSphone(info.getTelephone());
		ru.setBstate(0);
		try {
			ru.setDtime(DateUtil.getDateFromString(info.getAddDate().toString(), "yyyyMMddHHmmss"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ru.setRutype(info.getTypeYf());
		ru.setRulegalperson(info.getLegalRep());
		ru.setRuacount(info.getEnterpriseNo());
		ru.setZshflag(0);
		ru.setFid(info.getMerchantId());
		ru.setAdminbstate(0);
		ru.setRuflag(0);
		//ru.setId(Integer.valueOf(this.RUDao.getMaxRuId()+1));
		log.info("查询老福卡系统RUDao");
		this.RUDao.insertSelective(ru);
	}
	
}

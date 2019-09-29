package cn.yufu.yufuOld2.service;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.yufuOld2.dao.TerminalYufuOldMapper;
import cn.yufu.yufuOld2.entity.TerminalYufuOld;
import cn.yufu.yufuOld2.entity.TerminalYufuOldExample;

@Service("yufuOld2.YufuOld2TerminalService")
@Transactional("transactionManagerYufuOld2")
public class YufuOld2TerminalServiceImpl implements YufuOld2TerminalService {
	Log log = Log.getLog(YufuOld2TerminalServiceImpl.class);

	@Autowired
	@Qualifier("yufuOld2.TerminalYufuOldDao")
	private TerminalYufuOldMapper TerminalYufuOldDao;
	
	public void synchronousYufuOldTerminal(TerminalSDMT info) {
		TerminalYufuOld terminalYufuOld=new TerminalYufuOld();
		try {
			terminalYufuOld.setLruid(info.getMerchantId());
			terminalYufuOld.setSserialnum(info.getTermCode());
			terminalYufuOld.setBstate(0);		
			terminalYufuOld.setEmploydate(DateUtil.getDateFromString(info.getAddDate().toString(), "yyyyMMdd"));
//			terminalYufuOld.setTeraddress(info.getTermAddress());
			if(StringUtils.isNotEmpty(info.getTermAddress())){
				if(info.getTermAddress().length() > 10){
					terminalYufuOld.setTeraddress(info.getTermAddress().substring(0,10));
				}else{
					terminalYufuOld.setTeraddress(info.getTermAddress());
				}
			}else{
				terminalYufuOld.setTeraddress(info.getTermAddress());
			}
			terminalYufuOld.setTel(info.getTermTel());
			terminalYufuOld.setCardprefectureid(Integer.valueOf(info.getProvince()));
			terminalYufuOld.setCardcityid(Integer.valueOf(info.getCityNo()));
			terminalYufuOld.setCardareaid(Integer.valueOf(info.getZone()));
			terminalYufuOld.setAccountsflag(0);
			terminalYufuOld.setAccountsdate(DateUtil.getDateFromString(info.getAddDate().toString(), "yyyyMMdd"));
			terminalYufuOld.setBatchno("1");
			TerminalYufuOldDao.insertSelective(terminalYufuOld);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int getTerminalListByTermCode(String termCode) {
		TerminalYufuOldExample example = new TerminalYufuOldExample();
		TerminalYufuOldExample.Criteria criteria = example.createCriteria();
		criteria.andSserialnumEqualTo(termCode);
		List<TerminalYufuOld> terminallist=TerminalYufuOldDao.selectByExample(example);
		return terminallist.size();
	}

}

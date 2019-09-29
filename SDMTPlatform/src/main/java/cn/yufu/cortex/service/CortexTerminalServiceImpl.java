package cn.yufu.cortex.service;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.SDMTPlatform.commons.SysConst;
import cn.yufu.SDMTPlatform.commons.utils.DateUtil;
import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.cortex.dao.EnckeyMapper;
import cn.yufu.cortex.dao.TermPosMapper;
import cn.yufu.cortex.dao.TermPosXMapper;
import cn.yufu.cortex.entity.Enckey;
import cn.yufu.cortex.entity.TermPos;
import cn.yufu.cortex.entity.TermPosX;

@Service("cortex.CortexTerminalService")
@Transactional("transactionManagerCortex")
public class CortexTerminalServiceImpl implements CortexTerminalService {
	Log log = Log.getLog(CortexTerminalServiceImpl.class);

	@Autowired
	@Qualifier("cortex.TermPosDao")
	private TermPosMapper TermPosDao;
	@Autowired
	@Qualifier("cortex.TermPosXDao")
	private TermPosXMapper TermPosXDao;	
	@Autowired
	@Qualifier("cortex.EnckeyDao")
	private EnckeyMapper EnckeyDao;
	
	public String synchronousCortexTerminal(MerchantSDMT merchant,TerminalSDMT info) {
		log.info("同步终端TERMPOS");
		//cortex TERMPOS
		String seqTermPosIdAndTermNo=this.synchronousTermPos(merchant,info);
		String seqTermPosId=seqTermPosIdAndTermNo.split("#")[0].toString();
		log.info("同步终端TERMPOS_X");
		//cortex TERMPOS_X
		this.synchronousTermPosX(merchant, info, seqTermPosId);
		log.info("同步终端ENCKEY");
		//cortex ENCKEY
		String seqEnckeyId=this.synchronousEnckey(info);
		return seqTermPosIdAndTermNo+"#"+seqEnckeyId;
	}
	

	public String synchronousTermPos(MerchantSDMT merchant,TerminalSDMT info) {
		log.info("同步终端TERMPOS start");
		/*info.getTermAddress();*/
		String seqTermPosId=this.TermPosDao.getCortexTermPosId();
		Integer termNo=this.TermPosDao.getTermNo(merchant.getSeqMrchId().toString());
		TermPos termPos=new TermPos();
		termPos.setId(Long.valueOf(seqTermPosId));
		termPos.setTypeid(Integer.valueOf("1"));
		termPos.setTermcode(info.getTermCode());
		termPos.setTestflag(Integer.valueOf("0"));
		termPos.setStatusid(Integer.valueOf("0"));
		termPos.setCurrcode(SysConst.CONST_X_CURRCODE);
		termPos.setTermno(termNo);
		if(StringUtils.isNotEmpty(info.getTermAddress())){
			if(info.getTermAddress().length() > 10){
				termPos.setLocation(info.getTermAddress().substring(0,10));
			}else{
				termPos.setLocation(info.getTermAddress());
			}
		}else{
			termPos.setLocation(info.getTermAddress());
		}
		termPos.setConaccno("0");
		termPos.setConcur("0");
		termPos.setPoscdoc("1");
		termPos.setPoschac("0");
		termPos.setPoschic("5");
		termPos.setPoscrc("0");
		termPos.setPosoe("1");
		termPos.setPospcc("1");
		termPos.setPostoc("4");
		termPos.setTimezone(info.getxTimezone());
		termPos.setCatParams("0");
		termPos.setMerchantId(Long.valueOf(merchant.getSeqMrchId()));
		termPos.setTermtype("0");
		termPos.setVernoCtx(1l);
		TermPos TermPos = this.TermPosDao.selectByTermCode(termPos);
		log.info("同步终端TERMPOS end");
		if(null == TermPos){
			this.TermPosDao.insertSelective(termPos);
			return seqTermPosId+"#"+termNo;
		}else{
			return TermPos.getId()+"#"+TermPos.getTermno();
		}
	}
	
	public void synchronousTermPosX(MerchantSDMT merchant,TerminalSDMT info,String seqTermPosId) {
		log.info("同步终端TERMPOS_X start");
		TermPosX termPosX=new TermPosX();
		termPosX.setId(Long.valueOf(seqTermPosId));
		termPosX.setPosTel(info.getTermTel());
		termPosX.setBatchNo(0l);
		try {
			termPosX.setAddDate(DateUtil.getDateFromString(info.getAddDate(), "yyyyMMdd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		termPosX.setLocation(info.getTermAddress());
		termPosX.setState(info.getState());
		termPosX.setCityNo(Long.valueOf(info.getCityNo()));
		termPosX.setProvince(Long.valueOf(info.getProvince()));
		termPosX.setZone(Long.valueOf(info.getZone()));
		termPosX.setSettleMrchAccId(merchant.getSeqMrchAccXId());
		termPosX.setTermcode(info.getTermCode());
		termPosX.setTimezone(info.getxTimezone());
		termPosX.setInstId(Long.valueOf(SysConst.CONST_X_INST_ID));
		termPosX.setMrchno(info.getMerchantId());
		termPosX.setTermStat("0");
		termPosX.setConsumpCategory(info.getConsumpCategory());
		TermPosX TermPosX = this.TermPosXDao.selectByTermPos(termPosX);
		log.info("同步终端TERMPOS_X end");
		if(null == TermPosX){
			this.TermPosXDao.insertSelective(termPosX);		
		}
	}
	
	public String synchronousEnckey(TerminalSDMT info) {
		log.info("同步终端ENCKEY start");
		String seqEnckeyId=this.EnckeyDao.getCortexEnckeyId();
		Enckey enckey=new Enckey();
		enckey.setVernoCtx(1l);
		enckey.setKeytype(Integer.valueOf(3));
		enckey.setKeystatus(Integer.valueOf(0));
		enckey.setTstflag(Integer.valueOf(0));
		enckey.setRefcode("POS"+info.getTermCode());
		enckey.setKeyseqno(Integer.valueOf(1));
		enckey.setKeyvalue("260ADEB6F102825B62D13FE21E56ED2D");
		enckey.setPrevalue("  ");
		enckey.setCheckvalue("7A9EE0BA6D0350A0");
		try {
			enckey.setActdate(DateUtil.getDateFromString(info.getAddDate().toString(), "yyyyMMdd"));
			enckey.setExpiry(DateUtil.getDateFromString("20991231", "yyyyMMdd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		enckey.setActtime(info.getActTime());
		enckey.setLongkeyval("  ");
		enckey.setId(Long.valueOf(seqEnckeyId));
		Enckey Enckey = this.EnckeyDao.selectByTermCode(enckey);
		log.info("同步终端ENCKEY end");
		if(null == Enckey){
			this.EnckeyDao.insertSelective(enckey);
			return seqEnckeyId;
		}else{
			return Enckey.getId()+"";
		}
	}


}

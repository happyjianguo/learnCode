package cn.yufu.cortexBak.service;

import java.text.ParseException;
import java.util.List;

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
import cn.yufu.cortexBak.dao.EnckeyBakMapper;
import cn.yufu.cortexBak.dao.TermPosBakMapper;
import cn.yufu.cortexBak.dao.TermPosXBakMapper;
import cn.yufu.cortexBak.entity.EnckeyBak;
import cn.yufu.cortexBak.entity.TermPosBak;
import cn.yufu.cortexBak.entity.TermPosBakExample;
import cn.yufu.cortexBak.entity.TermPosXBak;

@Service("cortexBak.CortexBakTerminalService")
@Transactional("transactionManagerCortexBak")
public class CortexBakTerminalServiceImpl implements CortexBakTerminalService {
	Log log = Log.getLog(CortexBakTerminalServiceImpl.class);

	@Autowired
	@Qualifier("cortexBak.TermPosBakDao")
	private TermPosBakMapper TermPosBakDao;
	@Autowired
	@Qualifier("cortexBak.TermPosXBakDao")
	private TermPosXBakMapper TermPosXBakDao;	
	@Autowired
	@Qualifier("cortexBak.EnckeyBakDao")
	private EnckeyBakMapper EnckeyBakDao;

	public void synchronousCortexBakTerminal(MerchantSDMT merchant,TerminalSDMT info,String seqTermPosIdAndTermNoAndseqEnckeyId) {
		if(seqTermPosIdAndTermNoAndseqEnckeyId!=null&&seqTermPosIdAndTermNoAndseqEnckeyId.contains("#")){
			String[] str=seqTermPosIdAndTermNoAndseqEnckeyId.split("#");			
			String seqTermPosId=str[0];
			String termNo=str[1];
			String seqEnckeyId=str[2];	
			//cortexBak TERMPOS
			this.synchronousTermPosBak(merchant,info,seqTermPosId,termNo);
			//cortexBak TERMPOS_X
			this.synchronousTermPosXBak(merchant, info, seqTermPosId);
			//cortexBak ENCKEY
			this.synchronousEnckey(info, seqEnckeyId);
		}
	}
	

	public void synchronousTermPosBak(MerchantSDMT merchant,TerminalSDMT info,String seqTermPosId,String termNo) {
		/*info.getTermAddress();*/
		TermPosBak termPos=new TermPosBak();
		termPos.setId(Long.valueOf(seqTermPosId));
		termPos.setTypeid(Integer.valueOf("1"));
		termPos.setTermcode(info.getTermCode());
		termPos.setTestflag(Integer.valueOf("0"));
		termPos.setStatusid(Integer.valueOf("0"));
		termPos.setCurrcode(SysConst.CONST_X_CURRCODE);
		termPos.setTermno(Integer.valueOf(termNo));
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
		TermPosBak TermPosBak = this.TermPosBakDao.selectByTermcode(termPos);
		if(null == TermPosBak){
			this.TermPosBakDao.insertSelective(termPos);
		}
	}
	
	public void synchronousTermPosXBak(MerchantSDMT merchant,TerminalSDMT info,String seqTermPosId) {
		/*info.getTermAddress();*/
		TermPosXBak termPosX=new TermPosXBak();
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
		TermPosXBak TermPosXBak = this.TermPosXBakDao.selectByTermcode(termPosX);
		if(null == TermPosXBak){
			this.TermPosXBakDao.insertSelective(termPosX);		
		}
	}
	
	public void synchronousEnckey(TerminalSDMT info,String seqEnckeyId) {
		EnckeyBak enckey=new EnckeyBak();
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
		EnckeyBak EnckeyBak = this.EnckeyBakDao.selectByTermcode(enckey);
		if(null == EnckeyBak){
			this.EnckeyBakDao.insertSelective(enckey);
		}
	}

	public int getTerminalListByTermCode(String termCode) {
		TermPosBakExample example = new TermPosBakExample();
		TermPosBakExample.Criteria criteria = example.createCriteria();
		criteria.andTermcodeEqualTo(termCode);
		List<TermPosBak> TerminalList = TermPosBakDao.selectByExample(example);
		return TerminalList.size();
	}
}

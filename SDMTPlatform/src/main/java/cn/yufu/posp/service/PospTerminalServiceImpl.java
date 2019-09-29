package cn.yufu.posp.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yufu.SDMTPlatform.commons.utils.Log;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.posp.dao.BtsKeyMapper;
import cn.yufu.posp.dao.EdcNewfkterminalOrmMapper;
import cn.yufu.posp.dao.EdcTerminalMapper;
import cn.yufu.posp.dao.EdcTerminalOrmMapper;
import cn.yufu.posp.dao.EdcTerminalXMapper;
import cn.yufu.posp.entity.BtsKey;
import cn.yufu.posp.entity.EdcNewfkterminalOrm;
import cn.yufu.posp.entity.EdcTerminal;
import cn.yufu.posp.entity.EdcTerminalOrm;
import cn.yufu.posp.entity.EdcTerminalOrmKey;
import cn.yufu.posp.entity.EdcTerminalX;


@Service("posp.TerminalService")
@Transactional("transactionManagerPosp")
public class PospTerminalServiceImpl implements PospTerminalService {
	Log log = Log.getLog(PospTerminalService.class);

	@Autowired
	@Qualifier("posp.EdcTerminalDao")
	private EdcTerminalMapper EdcTerminalDao;
	@Autowired
	@Qualifier("posp.EdcTerminalXDao")
	private EdcTerminalXMapper EdcTerminalXDao;
	@Autowired
	@Qualifier("posp.BtsKeyDao")
	private BtsKeyMapper BtsKeyDao;
	@Autowired
	@Qualifier("posp.EdcNewfkterminalOrmDao")
	private EdcNewfkterminalOrmMapper EdcNewfkterminalOrmDao;
	@Autowired
	@Qualifier("posp.EdcTerminalOrmDao")
	private EdcTerminalOrmMapper EdcTerminalOrmDao;
	
	public void synchronousPospTerminal(TerminalSDMT info) {
		//75，EDC_TERMINAL表
		this.synchronousEdcTerminal(info);
		//EDC_TERMINAL_X
		this.synchronousEdcTerminalX(info);
		//75,BTS_KEY表
		this.synchronousBtsKey(info);
		//this.synchronousEdcNewfkterminalOrm(info);
	}
	
	//向扩展表中插入数据
	public void synchronousEdcTerminalX(TerminalSDMT info) {
		/*info.getTermAddress();*/
		EdcTerminalX edcTerminalX = new EdcTerminalX();
		edcTerminalX.setTerminalId(info.getTermCode());//终端号
		edcTerminalX.setUpgradeDate(info.getUpgradeDate());//升级日期
		edcTerminalX.setUpgradeVersion(info.getUpgradeVersion());//升级版本号
		edcTerminalX.setStoreContacts(info.getStoreContacts());//门店联系人
		edcTerminalX.setStorePhone(info.getStorePhone());//门店电话
		edcTerminalX.setMerchantAdvisor(info.getMerchantAdvisor());//商户顾问
		edcTerminalX.setTerminalArea(info.getTerminalArea());//所在区域
		edcTerminalX.setTerminalProvince(info.getProvince());//省
		edcTerminalX.setTerminalCity(info.getCityNo());//市
		edcTerminalX.setTerminalZone(info.getZone());//区
		edcTerminalX.setTerminalPosition(info.getTermAddress());//位置
		edcTerminalX.setUpdateDate(new Date());
		edcTerminalX.setCreateDate(new Date());
		edcTerminalX.setTerminalPosition(info.getTerminalPosition());
		this.EdcTerminalXDao.delete(edcTerminalX);
		this.EdcTerminalXDao.insertSelective(edcTerminalX);
	}

	public void synchronousEdcTerminal(TerminalSDMT info){
		/*info.getTermAddress();*/
		EdcTerminal edcTerminal=new EdcTerminal();
		edcTerminal.setSoftVer("common");
		edcTerminal.setMerchantId(info.getMerchantId());
		edcTerminal.setTerminalId(info.getTermCode());
		edcTerminal.setTerminalStat("Y");
		edcTerminal.setEdcType(info.getEdcType());
//		edcTerminal.setSoftVer(info.getSoftVer());
		edcTerminal.setDownloadFlag(info.getDownloadFlag());
		edcTerminal.setDownloadMode(BigDecimal.ZERO);
		edcTerminal.setSetDate(info.getAddDate());
		if(StringUtils.isNotEmpty(info.getTermAddress())){
			if(info.getTermAddress().length() > 10){
				edcTerminal.setSetAddr(info.getTermAddress().substring(0,10));
			}else{
				edcTerminal.setSetAddr(info.getTermAddress());
			}
		}else{
			edcTerminal.setSetAddr(info.getTermAddress());
		}
//		edcTerminal.setSetAddr(info.getTermAddress());
		edcTerminal.setUpdateOper("admin");
		edcTerminal.setUpdateDate(info.getAddDate());
		edcTerminal.setUpdateTime("101828");
		EdcTerminal EdcTerminal = this.EdcTerminalDao.selectByTermCode(edcTerminal);
		if(null == EdcTerminal){
			this.EdcTerminalDao.insertSelective(edcTerminal);		
		}
	}

	public void synchronousBtsKey(TerminalSDMT info){
		BtsKey btsKey =new BtsKey();
		btsKey.setMerchantId(info.getMerchantId());
		btsKey.setTerminalId(info.getTermCode());
		btsKey.setSettleFlag("0");
		btsKey.setBatchNo(BigDecimal.ONE);
		btsKey.setOperNo("001");
		btsKey.setOperPasswd("00000000");
		BtsKey BtsKey = this.BtsKeyDao.selectByTermCode(btsKey);
		if(null == BtsKey){
			this.BtsKeyDao.insertSelective(btsKey);
		}
	}
	
	public void synchronousEdcNewfkterminalOrm(TerminalSDMT info){
		EdcNewfkterminalOrm edcNewfkterminalOrm=new EdcNewfkterminalOrm();
		edcNewfkterminalOrm.setMerchantId(info.getMerchantId());
		edcNewfkterminalOrm.setTerminalId(info.getTermCode());
		edcNewfkterminalOrm.setBankId("11111111");
		edcNewfkterminalOrm.setBankMerchantId(info.getMerchantId());
		edcNewfkterminalOrm.setBankTerminalId(info.getTermCode());
		edcNewfkterminalOrm.setModuleId(BigDecimal.valueOf(76l));
		edcNewfkterminalOrm.setSysTrace(BigDecimal.valueOf(1l));
		edcNewfkterminalOrm.setBankTrace(BigDecimal.valueOf(1l));
		edcNewfkterminalOrm.setPinFmt(BigDecimal.valueOf(2l));
		edcNewfkterminalOrm.setEncMethod(BigDecimal.valueOf(6l));
		edcNewfkterminalOrm.setMacFlag(BigDecimal.valueOf(1l));
		edcNewfkterminalOrm.setBatchNo(BigDecimal.valueOf(1l));
		edcNewfkterminalOrm.setTmk("1111111111111111111111111111111");
		edcNewfkterminalOrm.setKeyIndex("01");
		edcNewfkterminalOrm.setSettStatus(BigDecimal.valueOf(0l));
		edcNewfkterminalOrm.setLogonStatus(BigDecimal.valueOf(0l));
		edcNewfkterminalOrm.setFlag(BigDecimal.valueOf(1l));
		EdcNewfkterminalOrm EdcNewfkterminalOrm = this.EdcNewfkterminalOrmDao.selectByPrimaryTerminalId(edcNewfkterminalOrm);
		if(null == EdcNewfkterminalOrm){
			this.EdcNewfkterminalOrmDao.insertSelective(edcNewfkterminalOrm);		
		}
	}
	
	public void synchronousEdcTerminalOrm(TerminalSDMT info){
		EdcTerminalOrm edcTerminalOrm=new EdcTerminalOrm();
		edcTerminalOrm.setMerchantId(info.getMerchantId());
		edcTerminalOrm.setBankId("99999999");
		edcTerminalOrm.setTerminalId(info.getTermCode());
		edcTerminalOrm.setBankMerchantId(info.getMerchantId());
		edcTerminalOrm.setBankTerminalId(info.getTermCode());
		edcTerminalOrm.setModuleId(BigDecimal.valueOf(66l));
		edcTerminalOrm.setSysTrace(BigDecimal.valueOf(1l));
		edcTerminalOrm.setBankTrace(BigDecimal.valueOf(1l));
		edcTerminalOrm.setPinFmt(BigDecimal.valueOf(2l));
		edcTerminalOrm.setEncMethod(BigDecimal.valueOf(6l));
		edcTerminalOrm.setMacFlag(BigDecimal.valueOf(1l));
		edcTerminalOrm.setBatchNo(BigDecimal.valueOf(1l));
		edcTerminalOrm.setKeyIndex("01");
		edcTerminalOrm.setSettStatus(BigDecimal.valueOf(0l));
		edcTerminalOrm.setLogonStatus(BigDecimal.valueOf(0l));
		edcTerminalOrm.setFlag(BigDecimal.valueOf(1l));
		EdcTerminalOrmKey EdcTerminalOrmKey = new EdcTerminalOrmKey();
		EdcTerminalOrmKey.setMerchantId(edcTerminalOrm.getMerchantId());
		EdcTerminalOrmKey.setTerminalId(edcTerminalOrm.getTerminalId());
		EdcTerminalOrmKey.setModuleId(edcTerminalOrm.getModuleId());
		this.EdcTerminalOrmDao.insertSelective(edcTerminalOrm);		
	}
	
}

package com.yufupos.system.modules.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.dao.PSimDao;
import com.yufupos.system.modules.pos.entity.PSim;
import com.yufupos.system.modules.pos.entity.PSimReport;

/**
 * SIM卡信息Service
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PSimService extends CrudService<PSimDao, PSim> {
	@Autowired
	private PSimDao pSimDao;
	
	public PSim get(String id) {
		return super.get(id);
	}
	
	public List<PSim> findList(PSim pSim) {
		return super.findList(pSim);
	}
	
	public Page<PSim> findPage(Page<PSim> page, PSim pSim) {
		return super.findPage(page, pSim);
	}
	
	@Transactional(readOnly = false)
	public void save(PSim pSim) {
		super.save(pSim);
	}
	
	@Transactional(readOnly = false)
	public void disablePSim(PSim pSim) {
		pSim.preUpdate();
		pSimDao.disablePSim(pSim);
	}
	
	@Transactional(readOnly = false)
	public void usablePSim(PSim pSim) {
		pSim.preUpdate();
		pSimDao.usablePSim(pSim);
	}
	
	@Transactional(readOnly = false)
	public void delete(PSim pSim) {
		super.delete(pSim);
	}
	/**
	 * 根据posSn获取POS SIM关联信息
	 * @param pSim
	 * @return
	 */
	public List<PSim> findPosSim(PSim pSim){
		return this.pSimDao.findPosSim(pSim);
	}
	/**
	 * 获取SIM统计数据
	 * @return
	 */
	public List<PSimReport> findSimSum(PSim PSim){		
		List<PSim> list=pSimDao.findSimSum(PSim);
		List<PSimReport> reportList=new ArrayList<PSimReport>();	
		
		String simCommunicationTemp="";		
		String simCommunication="";
		String simStatus="";
		String num="";
		
		PSim pp = null;
		PSimReport pr=null;
		for(int i=0;i<list.size();i++){
			pp=list.get(i);			
			simCommunication=pp.getSimCommunication();
			simStatus=pp.getSimStatus();
			num=pp.getPhoneNumber();			
			if(Strings.isNullOrEmpty(simCommunicationTemp)){
				simCommunicationTemp=simCommunication;
			}
			//比较运营商:相同的属于一个PSimReport实体,不同的时候添加到reportList中,同时创建新的PSimReport实体
			if(simCommunicationTemp.equals(simCommunication)){
				if(pr==null){
					pr=new PSimReport();
					pr.setSimCommunication(simCommunication);					
				}					
				if("IDLE".equals(simStatus)){
					pr.setIdleNum(num);						
				}else if("USE".equals(simStatus)){
					pr.setUseNum(num);
				}else if("MALFUNC".equals(simStatus)){
					pr.setMalfuncNum(num);
				}else if("SCRAP".equals(simStatus)){
					pr.setScrapNum(num);
				}		
				if(i==list.size()-1){
					reportList.add(pr);
				}				
			}else{
				reportList.add(pr);
				pr=new PSimReport();
				//重置临时比较值
				simCommunicationTemp=simCommunication;
				//重置运营商
				pr.setSimCommunication(simCommunication);
				if("IDLE".equals(simStatus)){
					pr.setIdleNum(num);						
				}else if("USE".equals(simStatus)){
					pr.setUseNum(num);
				}else if("MALFUNC".equals(simStatus)){
					pr.setMalfuncNum(num);
				}else if("SCRAP".equals(simStatus)){
					pr.setScrapNum(num);
				}				
			}
		}
		//求各合计值
		int totleNum=0;	//每行总数量
		int totleIdleNum=0; //所有空闲总数量
		int totleUseNum=0;	//所有在用总数量
		int totleMalfuncNum=0;//所有故障总数量
		int totleScrapNum=0;//所有报废总数量
		int totleTotleNum=0;//所有总数量
		
		int idleNum=0;
		int useNum=0;
		int scrapNum=0;
		int malfuncNum=0;
		
		for(PSimReport p:reportList){
			idleNum=Integer.valueOf(Strings.isNullOrEmpty(p.getIdleNum())?"0":p.getIdleNum()) ;
			useNum=Integer.valueOf(Strings.isNullOrEmpty(p.getUseNum())?"0":p.getUseNum());
			malfuncNum=Integer.valueOf(Strings.isNullOrEmpty(p.getMalfuncNum())?"0":p.getMalfuncNum());
			scrapNum=Integer.valueOf(Strings.isNullOrEmpty(p.getScrapNum())?"0":p.getScrapNum());
			
			totleIdleNum=totleIdleNum+idleNum; 
			totleUseNum=totleUseNum+useNum;	
			totleMalfuncNum=totleMalfuncNum+malfuncNum;
			totleScrapNum=totleScrapNum+scrapNum;
			totleNum=idleNum+useNum+malfuncNum+scrapNum;	
			p.setTotleNum(String.valueOf(totleNum));
		}
		//总合计
		totleTotleNum=totleIdleNum+totleUseNum+totleMalfuncNum+totleScrapNum;
		PSimReport totlePr=new PSimReport();
		totlePr.setSimCommunication("合计:");
		totlePr.setIdleNum(String.valueOf(totleIdleNum));
		totlePr.setUseNum(String.valueOf(totleUseNum));
		totlePr.setMalfuncNum(String.valueOf(totleMalfuncNum));
		totlePr.setScrapNum(String.valueOf(totleScrapNum));		
		totlePr.setTotleNum(String.valueOf(totleTotleNum));
		reportList.add(totlePr);		
		return reportList;
	}
	
	
}
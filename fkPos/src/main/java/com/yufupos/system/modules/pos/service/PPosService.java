package com.yufupos.system.modules.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.yufupos.system.common.persistence.Page;
import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.pos.dao.PPosDao;
import com.yufupos.system.modules.pos.entity.PPos;
import com.yufupos.system.modules.pos.entity.PPosReport;

/**
 * POS机信息Service
 * 
 * @author llg
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class PPosService extends CrudService<PPosDao, PPos> {
	@Autowired 
	private PPosDao pPosDao;
	
	public PPos get(String id) {
		return super.get(id);
	}

	public List<PPos> findList(PPos pPos) {
		return super.findList(pPos);
	}

	public Page<PPos> findPage(Page<PPos> page, PPos pPos) {
		return super.findPage(page, pPos);
	}

	@Transactional(readOnly = false)
	public void save(PPos pPos) {
		super.save(pPos);
	}
	
	@Transactional(readOnly = false)
	public void disablePPos(PPos pPos) {
		pPos.preUpdate();
		pPosDao.disablePPos(pPos);
	}
	
	@Transactional(readOnly = false)
	public void usablePPos(PPos pPos) {
		pPos.preUpdate();
		pPosDao.usablePPos(pPos);
	}

	@Transactional(readOnly = false)
	public void delete(PPos pPos) {
		super.delete(pPos);
	}
	
	/**
	 * 通过终端号查询终端POS相关信息
	 * @param pPos
	 * @return
	 */
	public List<PPos> findTermPos(PPos pPos){
		return pPosDao.findTermPos(pPos);
	}

	/**
	 * 获取POS统计数据
	 * @return
	 */
	public List<PPosReport> findPosSum(PPos pPos){		
		List<PPos> list=pPosDao.findPosSum(pPos);
		List<PPosReport> reportList=new ArrayList<PPosReport>();	
		
		String factoryIdTemp="";
		String modelIdTemp="";
		
		String factoryId="";
		String factoryName="";
		String modelId="";
		String posStatus="";
		String num="";
		
		PPos pp = null;
		PPosReport pr=null;

		for(int i=0;i<list.size();i++){
			pp=list.get(i);			
			factoryId=pp.getFactoryId();
			factoryName=pp.getFactoryName();
			modelId=pp.getModelId();
			posStatus=pp.getPosStatus();
			num=pp.getPosSn();			
			if(Strings.isNullOrEmpty(factoryIdTemp)&&Strings.isNullOrEmpty(modelIdTemp)){
				factoryIdTemp=factoryId;
				modelIdTemp=modelId;
			}
			//比较厂商和型号:相同的属于一个PPosReport实体,不同的时候添加到reportList中,同时创建新的PPosReport实体
			if(factoryIdTemp.equals(factoryId)&&modelIdTemp.equals(modelId)){
				if(pr==null){
					pr=new PPosReport();
					pr.setFactoryId(factoryId);
					pr.setFactoryName(factoryName);
					pr.setModelId(modelId);						
				}					
				if("IDLE".equals(posStatus)){
					pr.setIdleNum(num);						
				}else if("USE".equals(posStatus)){
					pr.setUseNum(num);
				}else if("MALFUNC".equals(posStatus)){
					pr.setMalfuncNum(num);
				}else if("SCRAP".equals(posStatus)){
					pr.setScrapNum(num);
				}		
				if(i==list.size()-1){
					reportList.add(pr);
				}				
			}else{
				reportList.add(pr);
				pr=new PPosReport();
				//重置临时比较值
				factoryIdTemp=factoryId;
				modelIdTemp=modelId;
				//重置厂商ID、厂商名称和型号
				pr.setFactoryId(factoryId);
				pr.setFactoryName(factoryName);
				pr.setModelId(modelId);	
				if("IDLE".equals(posStatus)){
					pr.setIdleNum(num);						
				}else if("USE".equals(posStatus)){
					pr.setUseNum(num);
				}else if("MALFUNC".equals(posStatus)){
					pr.setMalfuncNum(num);
				}else if("SCRAP".equals(posStatus)){
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
		
		for(PPosReport p:reportList){
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
		PPosReport totlePr=new PPosReport();
		totlePr.setFactoryName("合计:");
		totlePr.setIdleNum(String.valueOf(totleIdleNum));
		totlePr.setUseNum(String.valueOf(totleUseNum));
		totlePr.setMalfuncNum(String.valueOf(totleMalfuncNum));
		totlePr.setScrapNum(String.valueOf(totleScrapNum));		
		totlePr.setTotleNum(String.valueOf(totleTotleNum));
		reportList.add(totlePr);		
		return reportList;
	}
	
	

}
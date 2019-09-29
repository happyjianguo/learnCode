package cn.yufu.bak.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.ViewTLogMrchnoMapper;
import cn.yufu.bak.entity.ViewTLogMrchno;
import cn.yufu.bak.entity.ViewTLogMrchnoExample;
import cn.yufu.system.common.utils.StringUtils;


@Service("bak.ViewTLogMrchnoService")
public class ViewTLogMrchnoServiceImpl implements ViewTLogMrchnoService {
	
	@Autowired
	@Qualifier("bak.ViewTLogMrchnoDao")
	private ViewTLogMrchnoMapper viewTLogMrchnoMapper;

	@Override
	public int countByExample(ViewTLogMrchno queryModel) {
		if(null == queryModel) return 0;
		Integer count = viewTLogMrchnoMapper.countByExample(getExample(queryModel));
		if (null == count) return 0;
		return count;
	}
	
	@Override
	public ViewTLogMrchno selectByPrimaryKey(BigDecimal id) {
		return viewTLogMrchnoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ViewTLogMrchno> selectPageByExample(ViewTLogMrchno queryModel, int startResult, int endResult) {
		return viewTLogMrchnoMapper.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<ViewTLogMrchno> selectByExample(ViewTLogMrchno queryModel) {
		return viewTLogMrchnoMapper.selectByExample(getExample(queryModel));
	}
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private ViewTLogMrchnoExample getExample(ViewTLogMrchno queryModel){
		ViewTLogMrchnoExample example = new ViewTLogMrchnoExample();
		ViewTLogMrchnoExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(queryModel.getMrchno())) {
			criteria.andMrchnoEqualTo(queryModel.getMrchno());
		}
		if (StringUtils.isNotBlank(queryModel.getTermcode())) {
			criteria.andTermcodeEqualTo(queryModel.getTermcode());
		}
		if (StringUtils.isNotBlank(queryModel.getPan())) {
			criteria.andPanEqualTo(queryModel.getPan());
		}
		if (StringUtils.isNotBlank(queryModel.getStan())) {
			criteria.andStanEqualTo(queryModel.getStan());
		}
		if (StringUtils.isNotBlank(queryModel.getTransactiondate())) {
			criteria.andTransactiondateEqualTo(queryModel.getTransactiondate());
		}
		
		example.setOrderByClause(" TRANSACTIONDATE DESC, ID DESC ");
		return example;
	}

}

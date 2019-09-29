package cn.yufu.bak.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.ViewRealNameAccnoAvlBalMapper;
import cn.yufu.bak.entity.ViewRealNameAccnoAvlBal;
import cn.yufu.bak.entity.ViewRealNameAccnoAvlBalExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.modules.sys.utils.DictUtils;


@Service("bak.ViewRealNameAccnoAvlBalService")
public class ViewRealNameAccnoAvlBalServiceImpl implements ViewRealNameAccnoAvlBalService {
	
	Log log = Log.getLog(ViewRealNameAccnoAvlBalServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.ViewRealNameAccnoAvlBalDao")
	private ViewRealNameAccnoAvlBalMapper viewRealNameAccnoAvlBalDao;

	@Override
	public int countByExample(ViewRealNameAccnoAvlBal queryModel) {
		if(null == queryModel) return 0;
		Integer count = viewRealNameAccnoAvlBalDao.countByExample(getExample(queryModel));
		if (null == count) return 0;
		return count;
	}

	@Override
	public List<ViewRealNameAccnoAvlBal> selectPageByExample(ViewRealNameAccnoAvlBal queryModel, int startResult, int endResult) {
		return viewRealNameAccnoAvlBalDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<ViewRealNameAccnoAvlBal> selectByExample(ViewRealNameAccnoAvlBal queryModel) {
		return viewRealNameAccnoAvlBalDao.selectByExample(getExample(queryModel));
	}

	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private ViewRealNameAccnoAvlBalExample getExample(ViewRealNameAccnoAvlBal queryModel){
		ViewRealNameAccnoAvlBalExample example = new ViewRealNameAccnoAvlBalExample();
		ViewRealNameAccnoAvlBalExample.Criteria criteria = example.createCriteria();
		
		List<String> cardBin = getCardBin();
		criteria.andCardbinIn(cardBin);
		
		if (StringUtils.isNotBlank(queryModel.getCardbin())) {
			criteria.andCardbinEqualTo(queryModel.getCardbin());
		}
		if (StringUtils.isNotBlank(queryModel.getStatcode())) {
			criteria.andStatcodeEqualTo(queryModel.getStatcode());
		}
		if (StringUtils.isNotBlank(queryModel.getStatcode())) {
			criteria.andStatcodeEqualTo(queryModel.getStatcode());
		}
		if (StringUtils.isNotBlank(queryModel.getPhone())) {
			criteria.andPhoneEqualTo(queryModel.getPhone());
		}
		
		String accnoType = queryModel.getAccnoType();
		String symbol = queryModel.getSymbol();
		BigDecimal avlThreshold = queryModel.getAvlThreshold();
		if (null != avlThreshold) {
			setAvlBalCondition(criteria, accnoType, symbol, avlThreshold);
		}
		
		example.setOrderByClause(" CARDBIN DESC ");
		return example;
	}

	private void setAvlBalCondition(ViewRealNameAccnoAvlBalExample.Criteria criteria, 
			String accnoType, String symbol, BigDecimal avlThreshold) {
		if (StringUtils.isEmpty(symbol)) return;
		
		if (StringUtils.isEmpty(accnoType)) {
			if(symbol.equals("0")) {
				criteria.andTotalBalGreaterThan(avlThreshold);
			} else if (symbol.equals("1")) {
				criteria.andTotalBalGreaterThanOrEqualTo(avlThreshold);
			} else if (symbol.equals("2")) {
				criteria.andTotalBalEqualTo(avlThreshold);
			} else if (symbol.equals("3")) {
				criteria.andTotalBalLessThanOrEqualTo(avlThreshold);
			} else if (symbol.equals("4")) {
				criteria.andTotalBalLessThan(avlThreshold);
			}
		}
		
		if(symbol.equals("0")) {
			if (accnoType.equals("1")) {
				criteria.andAv01GreaterThan(avlThreshold);
			} else if (accnoType.equals("2")) {
				criteria.andAv02GreaterThan(avlThreshold);
			} else if (accnoType.equals("4")) {
				criteria.andAv04GreaterThan(avlThreshold);
			} else if (accnoType.equals("9")) {
				criteria.andAv09GreaterThan(avlThreshold);
			} else if (accnoType.equals("12")) {
				criteria.andAv01AndAv02GreaterThan(avlThreshold);
			} else if (accnoType.equals("49")) {
				criteria.andAv04AndAv09GreaterThan(avlThreshold);
			}
		} else if (symbol.equals("1")) {
			if (accnoType.equals("1")) {
				criteria.andAv01GreaterThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("2")) {
				criteria.andAv02GreaterThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("4")) {
				criteria.andAv04GreaterThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("9")) {
				criteria.andAv09GreaterThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("12")) {
				criteria.andAv01AndAv02GreaterThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("49")) {
				criteria.andAv04AndAv09GreaterThanOrEqualTo(avlThreshold);
			}
		} else if (symbol.equals("2")) {
			if (accnoType.equals("1")) {
				criteria.andAv01EqualTo(avlThreshold);
			} else if (accnoType.equals("2")) {
				criteria.andAv02EqualTo(avlThreshold);
			} else if (accnoType.equals("4")) {
				criteria.andAv04EqualTo(avlThreshold);
			} else if (accnoType.equals("9")) {
				criteria.andAv09EqualTo(avlThreshold);
			} else if (accnoType.equals("12")) {
				criteria.andAv01AndAv02EqualTo(avlThreshold);
			} else if (accnoType.equals("49")) {
				criteria.andAv04AndAv09EqualTo(avlThreshold);
			}
		} else if (symbol.equals("3")) {
			if (accnoType.equals("1")) {
				criteria.andAv01LessThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("2")) {
				criteria.andAv02LessThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("4")) {
				criteria.andAv04LessThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("9")) {
				criteria.andAv09LessThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("12")) {
				criteria.andAv01AndAv02LessThanOrEqualTo(avlThreshold);
			} else if (accnoType.equals("49")) {
				criteria.andAv04AndAv09LessThanOrEqualTo(avlThreshold);
			}
		} else if (symbol.equals("4")) {
			if (accnoType.equals("1")) {
				criteria.andAv01LessThan(avlThreshold);
			} else if (accnoType.equals("2")) {
				criteria.andAv02LessThan(avlThreshold);
			} else if (accnoType.equals("4")) {
				criteria.andAv04LessThan(avlThreshold);
			} else if (accnoType.equals("9")) {
				criteria.andAv09LessThan(avlThreshold);
			} else if (accnoType.equals("12")) {
				criteria.andAv01AndAv02LessThan(avlThreshold);
			} else if (accnoType.equals("49")) {
				criteria.andAv04AndAv09LessThan(avlThreshold);
			}
		}
	}
	
	private List<String> getCardBin(){
		String dictValue = DictUtils.getDictValue("卡BIN集合", "CARD_BINS", "");
		List<String> result = new ArrayList<String>();
		if (StringUtils.isNotEmpty(dictValue)) {
			String[] split = dictValue.split("\\|");
			result = Arrays.asList(split);
		}
		return result;
	}
}

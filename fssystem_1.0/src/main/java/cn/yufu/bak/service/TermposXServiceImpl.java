package cn.yufu.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.TermposXMapper;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;


@Service("bak.TermposXService")
public class TermposXServiceImpl implements TermposXService {
	
	Log log = Log.getLog(TermposXServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.TermposXDao")
	private TermposXMapper termposXDao;
	
	@Override
	public List<String> getTimezoneList(String merNo) {
		if (StringUtils.isEmpty(merNo)) return null;
		List<String> list = termposXDao.selectByMrchno(merNo);
		list = list == null || list.size() == 0 ? null :list;
		return list;
	}

	@Override
	public List<String> getTerminalLoc(String terminalNo) {
		if (StringUtils.isEmpty(terminalNo)) return null;
		List<String> list = termposXDao.getTerminalLoc(terminalNo);
		list = list == null || list.size() == 0 ? null :list;
		return list;
	}

	@Override
	public List<String> getMrchno(String terminalNo) {
		if (StringUtils.isEmpty(terminalNo)) return null;
		List<String> list = termposXDao.getMrchno(terminalNo);
		list = list == null || list.size() == 0 ? null :list;
		return list;
	}

}

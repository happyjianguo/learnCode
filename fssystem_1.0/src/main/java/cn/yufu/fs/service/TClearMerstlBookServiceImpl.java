package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.TClearMerstlBookMapper;
import cn.yufu.fs.entity.TClearMerstlBook;
import cn.yufu.system.common.utils.Log;

@Service("fs.TClearMerstlBookService")
public class TClearMerstlBookServiceImpl implements TClearMerstlBookService {
	
	Log log = Log.getLog(TClearMerstlBookServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.TClearMerstlBookDao")
	private TClearMerstlBookMapper tClearMerstlBookDao;
	
	@Override
	public int queryCnt(TClearMerstlBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		return tClearMerstlBookDao.countByExample(map);
	}
	
	@Override
	public List<TClearMerstlBook> queryList(TClearMerstlBook queryModel, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tClearMerstlBookDao.selectPageByExample(map);
	}
	
	@Override
	public List<TClearMerstlBook> queryAllList(TClearMerstlBook queryModel) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		return tClearMerstlBookDao.queryAllList(map);
	}
	
	@Override
	public List<String> getMrchnoList(TClearMerstlBook queryModel) {
		return tClearMerstlBookDao.getMrchnoList(queryModel);
	}

}

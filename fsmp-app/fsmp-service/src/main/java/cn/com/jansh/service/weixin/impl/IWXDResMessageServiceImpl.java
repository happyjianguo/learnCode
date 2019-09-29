package cn.com.jansh.service.weixin.impl;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.jansh.entity.weixin.WXDResMessage;
import cn.com.jansh.mapper.weixin.IWXDResMessageMapper;
import cn.com.jansh.service.weixin.IWXDResMessageService;
import cn.com.jansh.utils.DateUtil;
import cn.com.jansh.utils.IDUtils;
/**
 * 智能回复业务处理接口Impl
 * @author gll
 * @version 1.0
 */
@Service
public class IWXDResMessageServiceImpl implements IWXDResMessageService {
	@Autowired
	private IWXDResMessageMapper iwxdresmessagemapper;

	/**
	 * easy dataGrid格式数据 
	 * 初始化查詢自动回复列表
	 * @param resMsg
	 * @return
	 */
	@Override
	public List<WXDResMessage> dataGrid(WXDResMessage resMsg) {
		List<WXDResMessage> list = iwxdresmessagemapper.queryPageResMessage(resMsg);
		return list;
	}
	/**
	 * 新增
	 */
	@Override
	public void add(WXDResMessage resMsg) {
		resMsg.setCreateTime(DateUtil.getDate());
		resMsg.setUpdateTime(DateUtil.getDate());
		if(!StringUtils.isEmpty(resMsg.getResMsgKey())){
			resMsg.setResType("2");
		}else{
			resMsg.setResType("1");
		}
		resMsg.setResmsgId(IDUtils.getTimeRandon());
		iwxdresmessagemapper.save(resMsg);
	}
	@Override
	public void update(WXDResMessage resMsg) {
		resMsg.setUpdateTime(DateUtil.getDate());
		if(!StringUtils.isEmpty(resMsg.getResMsgKey())){
			resMsg.setResType("2");
		}else{
			resMsg.setResType("1");
		}
		iwxdresmessagemapper.update(resMsg);
	}
	/**
	 * 批量删除
	 */
	@Override
	public void batchDel(String ids) {
		if (ids != null && ids.length() > 0) {
			String[] idstrs = ids.split(",");
			List<String> idList = Arrays.asList(idstrs);
			iwxdresmessagemapper.deleteByIdS(idList);
		}
	}
	/**
	 * 根据智能回复名称查询
	 * @param resMsg
	 * @return
	 */
	public List<WXDResMessage> searchByResMsgname(WXDResMessage resMsg) {
		return iwxdresmessagemapper.searchByResMsgname(resMsg);
	}
	/**
	 * 根据智能回复id查询智能回复信息
	 * @param resmsgid
	 * @return
	 */
	@Override
	public WXDResMessage selectResMByResmsgid(String resmsgid) {
		return iwxdresmessagemapper.selectResMByResmsgid(resmsgid);
	}
}
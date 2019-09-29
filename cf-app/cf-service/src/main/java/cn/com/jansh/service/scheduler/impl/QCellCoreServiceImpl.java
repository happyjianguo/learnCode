package cn.com.jansh.service.scheduler.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.entity.wsfdn.CfPhoneNoInfoEntity;
import cn.com.jansh.entity.wsfdn.CfProvinceEntity;
import cn.com.jansh.mapper.wsfdn.CfPhoneNoInfoMapper;
import cn.com.jansh.mapper.wsfdn.CfProvinceMapper;
import cn.com.jansh.service.component.QCellCoreComponent;
import cn.com.jansh.service.scheduler.QCellCoreService;

@Service
public class QCellCoreServiceImpl implements QCellCoreService {

	private static final Logger logger = LogManager.getLogger(QCellCoreServiceImpl.class);

	@Autowired
	private QCellCoreComponent qCellCoreComponent;

	@Autowired
	private CfProvinceMapper cfProvinceMapper;

	@Autowired
	private CfPhoneNoInfoMapper cfHomeownershipMapper;

	@Override
	public Map<String, String> resultQCellCore(String phone) {
		Map<String, String> map = new HashMap<String, String>();
		CfPhoneNoInfoEntity cfHomeownership = null;
		try {
			cfHomeownership = cfHomeownershipMapper.queryByPhone(phone);
		} catch (Exception e) {
			logger.error("查询数据库异常：{}", e);
		}

		if (null != cfHomeownership) {
			try {
				map = readStringXml(null, cfHomeownership);
			} catch (Exception e) {
				logger.error("数据库-查询归属地异常{}:{}", phone, e);
			}
		} else {
			try {
				map = readStringXml(qCellCoreComponent.qCellCore(phone), null);
			} catch (Exception e) {
				logger.error("网络-查询归属地异常{}:{}", phone, e);
			}
		}
		return map;
	}

	/**
	 * 解析并存储手机号归属地
	 * 
	 * @param xml
	 * @return
	 */
	private Map<String, String> readStringXml(String xml, CfPhoneNoInfoEntity phonecity) {
		if (phonecity == null) {
			phonecity = new CfPhoneNoInfoEntity();
			try {
				Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML
				Element rootElt = doc.getRootElement(); // 获取根节点
				if (!"OK".equals(rootElt.elementTextTrim("retmsg"))) {
					return new HashMap<String, String>();
				}
				// 城市
				phonecity.setCity(rootElt.elementTextTrim("city"));
				// 运营商
				phonecity.setSupplier(rootElt.elementTextTrim("supplier"));
				// 归属地
				phonecity.setProvince(rootElt.elementTextTrim("province"));
				// 手机号
				phonecity.setPhone(rootElt.elementTextTrim("chgmobile"));
			} catch (DocumentException e) {
				logger.error("解析电话归属地异常{}", e);
			}
			// 记录手机号归属地
			try {
				cfHomeownershipMapper.save(phonecity);
			} catch (Exception e) {
				logger.error("手机号归属地insert异常，{}", e);
			}
		}

		// 封装返回数据
		Map<String, String> map = new HashMap<String, String>();
		try {
			CfProvinceEntity CfProvinceEntity = new CfProvinceEntity();
			CfProvinceEntity = cfProvinceMapper.queryByPname(phonecity.getProvince());
			map.put("province", CfProvinceEntity.getPno());

			// 运营商
			String supplier = phonecity.getSupplier();
			if (supplier.equals("移动")) {
				map.put("supplier", AppCommonsCode.CHINA_MOBILE_COMMUNICATIONS_CORPORATION.value());
			} else if (supplier.equals("联通")) {
				map.put("supplier", AppCommonsCode.CHINA_UNICOM.value());
			} else if (supplier.equals("电信")) {
				map.put("supplier", AppCommonsCode.CHINA_TELICOM.value());
			} else {
				map.put("supplier", supplier);
			}
		} catch (Exception e) {
			logger.error("转换手机号归属地异常，{}", e);
		}

		return map;
	}
}

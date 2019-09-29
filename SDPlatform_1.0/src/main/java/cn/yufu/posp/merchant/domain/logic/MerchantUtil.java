package cn.yufu.posp.merchant.domain.logic;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class MerchantUtil {
	public static String merchantToJson(List<MerchantBaseModel> list ,int type){
		JSONObject object = new JSONObject();
		switch (type) {
		case 1:
			object.put("type", "ADD");
			break;
		case 2:
			object.put("type", "DEL");
			break;
		case 3:
			object.put("type", "UP");
			break;
		default:
			return "";
		}
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			MerchantBaseModel model = list.get(i);
			JSONObject temp = JSONObject.fromObject(model);
			array.add(temp);
		}
		object.put("models", array);
		return object.toString();
	}
	
	public static String edcTerminalToJson(List<EdcTerminal> list, int type) {
		JSONObject object = new JSONObject();
		switch (type) {
		case 1:
			object.put("type", "ADD");
			break;
		case 2:
			object.put("type", "DEL");
			break;
		case 3:
			object.put("type", "UP");
			break;
		default:
			return "";
		}
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			EdcTerminal model = list.get(i);
			JSONObject temp = JSONObject.fromObject(model);
			array.add(temp);
		}
		object.put("models", array);
		return object.toString();
	}

	public static void main(String[] args) {
		List<EdcTerminal> list = new ArrayList<EdcTerminal>();
		EdcTerminal e = new EdcTerminal();
		EdcTerminalId id = new EdcTerminalId();
		e.setId(id );
		list.add(e);
		System.out.println(MerchantUtil.edcTerminalToJson(list, 1));
	}

}

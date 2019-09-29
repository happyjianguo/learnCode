package cn.yufu.posp.cardtype.domain.logic;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.yufu.posp.cardtype.domain.model.CardType;

public class CardTypeUtil {

	public static String cardTypeToJson(List<CardType> list, int type) {
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
			CardType model = list.get(i);
			JSONObject temp = JSONObject.fromObject(model);
			array.add(temp);
		}
		object.put("models", array);
		return object.toString();
	}

}

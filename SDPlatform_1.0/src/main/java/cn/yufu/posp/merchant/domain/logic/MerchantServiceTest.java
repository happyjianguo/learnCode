package cn.yufu.posp.merchant.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public class MerchantServiceTest {
	public static void main(String[] args) {
		String[] paths = { "spring/applicationContext.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(paths);
		MerchantService service = (MerchantService) context.getBean("syncMerchant");
		List<MerchantBaseModel> list = new ArrayList<MerchantBaseModel>();
		list.add(new MerchantBaseModel());
		String result = service.syncMerchant(MerchantUtil.merchantToJson(list, 1));
		System.out.println(result);
//		JSONObject ret = JSONObject.fromString(result);
//		if("0".equals(ret.get("code"))){
//			//同步成功
//			System.out.println("asdfasdfasdf");
//		}else{
//		}
	}
}

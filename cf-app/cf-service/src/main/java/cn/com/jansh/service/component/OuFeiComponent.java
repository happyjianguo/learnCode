package cn.com.jansh.service.component;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import cn.com.jansh.core.entity.sys.PubsSysBase;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.HttpClientRequest;
import cn.com.jansh.core.util.HttpClientUtil;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;

@Service
public class OuFeiComponent {
	/**
	 * 默认为 OFCARD, 实际上线时可以修改
	 */
	@Autowired
	private PubsSysBaseMapper pubsSysBaseMapper;

	/**
	 * 话费订购
	 * @param cardnum 面值
	 * @param sporder_id Sp商家的订单号
	 * @param game_userid 手机号
	 * @return
	 * @throws Exception
	 */
	public String order(String cardnum,String sporder_id, String game_userid) throws Exception {
		String sporder_time=DateUtil.getTimestamp();
		String body = "userid="+getUserid()+"&userpws="+getUserpws()+"&cardid="+getCardid()+"&cardnum="+cardnum+
						"&sporder_id="+sporder_id+"&sporder_time="+sporder_time+"&game_userid="+game_userid+
						"&md5_str="+orderMd5Hex(cardnum,sporder_id,sporder_time,game_userid)+
						"&ret_url="+getRet_url()+"&version="+getVersion();
		HttpClientRequest request = new HttpClientRequest();
		request.setBody(body);
		return HttpClientUtil.httpPost(getOrderUrl(), request);
	}
	/**
	 * dm5加密
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	public String orderMd5Hex(String cardnum,String sporder_id,String sporder_time, String game_userid) {
		return DigestUtils.md5Hex(getUserid()+getUserpws()+getCardid()+cardnum+sporder_id+sporder_time+game_userid+getKeyStr());
	}

	/**
	 * 用户订购详情查询接口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryOrderDetails(String sporder_id) throws Exception{
		String body="userid="+getUserid()+"&userpws="+getUserpws()+"&sporder_id="+sporder_id+"&md5_str="+queryOrderMd5Hex(getUserid(),getUserpws(),sporder_id)+"&version="+getVersion();
		HttpClientRequest request = new HttpClientRequest();
		request.setBody(body);
		return HttpClientUtil.httpPost(getQueryDetailsUrl(), request);
	}
	public String getQueryDetailsUrl() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("queryDetailsUrl".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}
	/**
	 * dm5加密
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	public String queryOrderMd5Hex(String userid,String userpws,String sporder_id){
		return DigestUtils.md5Hex(userid+userpws+sporder_id+getKeyStr());
	}
	
	/**
	 * 订单状态查询接口
	 * @param sporder_id
	 * @return
	 * @throws Exception 
	 */
	public String queryOrder(String sporder_id) throws Exception{
		String body="userid="+getUserid()+"&sporder_id="+sporder_id;
		HttpClientRequest request = new HttpClientRequest();
		request.setBody(body);
		return  HttpClientUtil.httpPost(getQueryUrl(), request);
	}

	/**
     * 欧非对账接口	
     * @param cardid
     * @return
     * @throws Exception
     */
	public String billOrder() throws Exception {
		
		String endtime = DateUtil.getTimestamp();	
		Date date1 = DateUtil.parseDateTime2(endtime);				
		Date date2 = DateUtil.addDate(date1, -1);
		String starttime = DateUtil.getDateTime2(date2);		
		String body = "userid="+getUserid()+"&userpws="+getUserpws()+"&cardid="+getCardid()+"&starttime="+starttime+"&endtime="+endtime+
						"&md5_str="+billmd5(getUserid(),getUserpws(),getCardid(),starttime,endtime,getKeyStr())+
						"&version="+getVersion();
		HttpClientRequest request = new HttpClientRequest();
		request.setBody(body);
		return HttpClientUtil.httpPost(getBillUrl(), request);
	}	
	
   /**
    * md5加密	
    * @param map
    * @return
    * @throws JsonProcessingException
    */
	private String billmd5(String userid, String userpws, String cardid, String starttime, String endtime,
			String keyStr) {
		return DigestUtils.md5Hex(userid+userpws+cardid+starttime+endtime+getKeyStr());
	}

	public static void main(String[] args) {
		OuFeiComponent OuFeiComponent = new OuFeiComponent();
		try {
			OuFeiComponent.order("1","dfasdfadsfasdfaqwe", "13021012885");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/********************************************************************从数据库中获取OuFeiComponent组件所需要的参数******************************************************************/
	
	/**
	 * KeyStr，默认为 OFCARD
	 * @return
	 */
	public String getKeyStr() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_KeyStr".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)手机充值接口地址
	 * @return
	 */
	public String getOrderUrl() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_orderUrl".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)订单充值结果回调URL地址
	 * @return
	 */
	public String getRet_url() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_ret_url".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)自动对账接口地址
	 * @return
	 */
	public String getBillUrl() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_billUrl".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}
	
	/**
	 * (欧飞)SP编码
	 * @return
	 */
	public String getUserid() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_userid".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)SP接入密码(账户密码的MD5值，小写）
	 * @return
	 */
	public String getUserpws() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_userpws".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)接口版本
	 * @return
	 */
	public String getVersion() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_version".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)商品编码，话费
	 * @return
	 */
	public String getCardid() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_cardid".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}

	/**
	 * (欧飞)查询充值状态接口地址
	 * @return
	 */
	public String getQueryUrl() {
		List<PubsSysBase> li =pubsSysBaseMapper.selectAllBase();
		for(PubsSysBase  pubsSysBase : li){
			if("OF_queryUrl".equals(pubsSysBase.getSysBaseId())){
				return pubsSysBase.getSysBaseValue();
			}
		}
		return "";
	}
}

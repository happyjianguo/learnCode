package cn.yufu.core.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 汉字转化为全拼
 * 
 * @author mengfanpeng
 * @modify 
 */
public final class Cn2Spell {
	/**
	 * 构造函数
	 */
	private Cn2Spell() {
	}

	/**
	 * 将汉字转化为第一个字母,其它字符不进行转换
	 * 
	 * @param 字符串
	 * @return 转换成全拼后的字符串
	 */
	public static String getCapSpell(final String cnStr) {
		final StringBuilder retuBuf = new StringBuilder();
		final char[] chars = cnStr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			final int ascii = getCnAscii(chars[i]);
			// 取ascii时出错
			if (ascii == 0) {
				retuBuf.append(chars[i]);
				continue;
			}
			String spell;
			final String strResult = getSpellByAscii(ascii);
			if (strResult == null) {
				spell = getCapSpellFromFile(String.valueOf(chars[i]));
			} else {
				spell = getSpellByAscii(ascii).substring(0, 1).toUpperCase();
			}
			if (spell == null) {
				retuBuf.append(chars[i]);
			} else {
				retuBuf.append(spell);
				// end of if spell == null
			}
			// end of for
		}
		return retuBuf.toString();
	}

	/**
	 * 获得单个汉字的Ascii
	 * 
	 * @param 汉字字符
	 * @return 错误返回 0,否则返回ascii
	 */
	public static int getCnAscii(final char pcn) {
		final byte[] bytes = String.valueOf(pcn).getBytes();
		int intResult;
		switch (bytes.length) {
		// 英文字符
		case 1:
			intResult = bytes[0];
			break;
		// 中文字符
		case 2:
			final int hightByte = 256 + bytes[0];
			final int lowByte = 256 + bytes[1];
			final int ascii = (256 * hightByte + lowByte) - 256 * 256;
			intResult = ascii;
			break;
		// 错误
		default:
			intResult = 0;
			break;
		}
		return intResult;
	}

	/**
	 * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
	 * 
	 * @param 字符串
	 * @return 转换成全拼后的字符串
	 */
	public static String getFullSpell(final String cnStr) {
		final StringBuilder retuBuf = new StringBuilder();
		final char[] chars = cnStr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			final int ascii = getCnAscii(chars[i]);
			// 取ascii时出错
			if (ascii == 0) {
				retuBuf.append(chars[i]);
			} else {
				final String spell = getSpellByAscii(ascii);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		return retuBuf.toString();
	}

	/**
	 * 获得全拼音
	 * 
	 * @param 字符串
	 * @return 转换成全拼后的字符串
	 */
	@SuppressWarnings("null")
	public static String getFullSpellFromFile(String sf) {
		Resource rs;
		ResourceLoader rl = new DefaultResourceLoader();
		InputStreamReader in = null;
		BufferedReader reader = null;
		try {
			rs = rl.getResource("BK2Spell.txt");
			in = new InputStreamReader(rs.getInputStream());
			reader = new BufferedReader(in);
			String s = null;
			while ((s = reader.readLine()) != null) {
				if (s.indexOf(sf) > 0) {
					return s.substring(0, s.indexOf("=>") - 1).toUpperCase();
				}
			}
		} catch (IOException e) {
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR, e);
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException e) {
				// throw new BssRuntimeException(Result.BSS_SYS_ERROR, e);
			}
		}
		return sf;
	}

	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param 字符对应的ASCII
	 * @return 拼音,首先判断ASCII是否>0& <160,如果是返回对应的字符,
	 *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	@SuppressWarnings("cast")
	public static String getSpellByAscii(final int ascii) {
		// 单字符
		if (ascii > 0 && ascii < 160) {
			return String.valueOf((char) ascii);
		}
		// 不知道的字符
		if (ascii < -20319 || ascii > -10247) {
			return null;
		}
		final Map<String, Number> spellMap = initialize();
		final Set<String> keySet = spellMap.keySet();
		final Iterator<String> iter = keySet.iterator();
		String spell0 = null;
		String spell = null;
		int asciiRang0 = -20319;
		int asciiRang;
		while (iter.hasNext()) {
			spell = (String) iter.next();
			final Object valObj = spellMap.get(spell);
			if (valObj instanceof Integer) {
				asciiRang = ((Integer) valObj).intValue();
				// 区间找到
				if (ascii >= asciiRang0 && ascii < asciiRang) {
					return (spell0 == null) ? spell : spell0;
				} else {
					spell0 = spell;
					asciiRang0 = asciiRang;
				}
			}
		}
		return null;
	}

	/**
	 * 获得简拼
	 * 
	 * @param 字符串
	 * @return 转换成简拼后的字符串
	 */
	@SuppressWarnings("null")
	private static String getCapSpellFromFile(final String pStr) {
		Resource resource = null;
		final ResourceLoader resourceLoader = new DefaultResourceLoader();
		InputStreamReader in = null;
		BufferedReader reader = null;
		try {
			resource = resourceLoader.getResource("GBK2Spell.txt");
			in = new InputStreamReader(resource.getInputStream());
			reader = new BufferedReader(in);
			String s = null;
			while ((s = reader.readLine()) != null) {
				if (s.indexOf(pStr) >= 0) {
					return s.substring(0, 1).toUpperCase();
				}
			}
		} catch (IOException e) {
			// throw new BssRuntimeException(Result.BSS_SYS_ERROR, e);
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException e) {
				// throw new BssRuntimeException(Result.BSS_SYS_ERROR, e);
			}
		}
		return pStr;
	}

	/**
	 * 加载ascii和拼音的对应关系
	 * 
	 * @param 字母
	 * @param 数字
	 * @return 记载后返回的MAP集合
	 */
	private static Map<String, Number> initialize() {
		final Map<String, Number> spellMap = new LinkedHashMap<String, Number>(400);
		initializeFromAToL(spellMap);
		initializeFromMToT(spellMap);
		initializeFromWToZ(spellMap);
		return spellMap;
	}

	/**
	 * 加载ascii和拼音的对应关系
	 * 
	 * @param 字母
	 * @param 数字
	 */
	private static void initializeFromAToL(Map<String, Number> pSpellMap) {
		pSpellMap.put("a", -20319);
		pSpellMap.put("ai", -20317);
		pSpellMap.put("an", -20304);
		pSpellMap.put("ang", -20295);
		pSpellMap.put("ao", -20292);
		pSpellMap.put("ba", -20283);
		pSpellMap.put("bai", -20265);
		pSpellMap.put("ban", -20257);
		pSpellMap.put("bang", -20242);
		pSpellMap.put("bao", -20230);
		pSpellMap.put("bei", -20051);
		pSpellMap.put("ben", -20036);
		pSpellMap.put("beng", -20032);
		pSpellMap.put("bi", -20026);
		pSpellMap.put("bian", -20002);
		pSpellMap.put("biao", -19990);
		pSpellMap.put("bie", -19986);
		pSpellMap.put("bin", -19982);
		pSpellMap.put("bing", -19976);
		pSpellMap.put("bo", -19805);
		pSpellMap.put("bu", -19784);
		pSpellMap.put("ca", -19775);
		pSpellMap.put("cai", -19774);
		pSpellMap.put("can", -19763);
		pSpellMap.put("cang", -19756);
		pSpellMap.put("cao", -19751);
		pSpellMap.put("ce", -19746);
		pSpellMap.put("ceng", -19741);
		pSpellMap.put("cha", -19739);
		pSpellMap.put("chai", -19728);
		pSpellMap.put("chan", -19725);
		pSpellMap.put("chang", -19715);
		pSpellMap.put("chao", -19540);
		pSpellMap.put("che", -19531);
		pSpellMap.put("chen", -19525);
		pSpellMap.put("cheng", -19515);
		pSpellMap.put("chi", -19500);
		pSpellMap.put("chong", -19484);
		pSpellMap.put("chou", -19479);
		pSpellMap.put("chu", -19467);
		pSpellMap.put("chuai", -19289);
		pSpellMap.put("chuan", -19288);
		pSpellMap.put("chuang", -19281);
		pSpellMap.put("chui", -19275);
		pSpellMap.put("chun", -19270);
		pSpellMap.put("chuo", -19263);
		pSpellMap.put("ci", -19261);
		pSpellMap.put("cong", -19249);
		pSpellMap.put("cou", -19243);
		pSpellMap.put("cu", -19242);
		pSpellMap.put("cuan", -19238);
		pSpellMap.put("cui", -19235);
		pSpellMap.put("cun", -19227);
		pSpellMap.put("cuo", -19224);
		pSpellMap.put("da", -19218);
		pSpellMap.put("dai", -19212);
		pSpellMap.put("dan", -19038);
		pSpellMap.put("dang", -19023);
		pSpellMap.put("dao", -19018);
		pSpellMap.put("de", -19006);
		pSpellMap.put("deng", -19003);
		pSpellMap.put("di", -18996);
		pSpellMap.put("dian", -18977);
		pSpellMap.put("diao", -18961);
		pSpellMap.put("die", -18952);
		pSpellMap.put("ding", -18783);
		pSpellMap.put("diu", -18774);
		pSpellMap.put("dong", -18773);
		pSpellMap.put("dou", -18763);
		pSpellMap.put("du", -18756);
		pSpellMap.put("duan", -18741);
		pSpellMap.put("dui", -18735);
		pSpellMap.put("dun", -18731);
		pSpellMap.put("duo", -18722);
		pSpellMap.put("e", -18710);
		pSpellMap.put("en", -18697);
		pSpellMap.put("er", -18696);
		pSpellMap.put("fa", -18526);
		pSpellMap.put("fan", -18518);
		pSpellMap.put("fang", -18501);
		pSpellMap.put("fei", -18490);
		pSpellMap.put("fen", -18478);
		pSpellMap.put("feng", -18463);
		pSpellMap.put("fo", -18448);
		pSpellMap.put("fou", -18447);
		pSpellMap.put("fu", -18446);
		pSpellMap.put("ga", -18239);
		pSpellMap.put("gai", -18237);
		pSpellMap.put("gan", -18231);
		pSpellMap.put("gang", -18220);
		pSpellMap.put("gao", -18211);
		pSpellMap.put("ge", -18201);
		pSpellMap.put("gei", -18184);
		pSpellMap.put("gen", -18183);
		pSpellMap.put("geng", -18181);
		pSpellMap.put("gong", -18012);
		pSpellMap.put("gou", -17997);
		pSpellMap.put("gu", -17988);
		pSpellMap.put("gua", -17970);
		pSpellMap.put("guai", -17964);
		pSpellMap.put("guan", -17961);
		pSpellMap.put("guang", -17950);
		pSpellMap.put("gui", -17947);
		pSpellMap.put("gun", -17931);
		pSpellMap.put("guo", -17928);
		pSpellMap.put("ha", -17922);
		pSpellMap.put("hai", -17759);
		pSpellMap.put("han", -17752);
		pSpellMap.put("hang", -17733);
		pSpellMap.put("hao", -17730);
		pSpellMap.put("he", -17721);
		pSpellMap.put("hei", -17703);
		pSpellMap.put("hen", -17701);
		pSpellMap.put("heng", -17697);
		pSpellMap.put("hong", -17692);
		pSpellMap.put("hou", -17683);
		pSpellMap.put("hu", -17676);
		pSpellMap.put("hua", -17496);
		pSpellMap.put("huai", -17487);
		pSpellMap.put("huan", -17482);
		pSpellMap.put("huang", -17468);
		pSpellMap.put("hui", -17454);
		pSpellMap.put("hun", -17433);
		pSpellMap.put("huo", -17427);
		pSpellMap.put("ji", -17417);
		pSpellMap.put("jia", -17202);
		pSpellMap.put("jian", -17185);
		pSpellMap.put("jiang", -16983);
		pSpellMap.put("jiao", -16970);
		pSpellMap.put("jie", -16942);
		pSpellMap.put("jin", -16915);
		pSpellMap.put("jing", -16733);
		pSpellMap.put("jiong", -16708);
		pSpellMap.put("jiu", -16706);
		pSpellMap.put("ju", -16689);
		pSpellMap.put("juan", -16664);
		pSpellMap.put("jue", -16657);
		pSpellMap.put("jun", -16647);
		pSpellMap.put("ka", -16474);
		pSpellMap.put("kai", -16470);
		pSpellMap.put("kan", -16465);
		pSpellMap.put("kang", -16459);
		pSpellMap.put("kao", -16452);
		pSpellMap.put("ke", -16448);
		pSpellMap.put("ken", -16433);
		pSpellMap.put("keng", -16429);
		pSpellMap.put("kong", -16427);
		pSpellMap.put("kou", -16423);
		pSpellMap.put("ku", -16419);
		pSpellMap.put("kua", -16412);
		pSpellMap.put("kuai", -16407);
		pSpellMap.put("kuan", -16403);
		pSpellMap.put("kuang", -16401);
		pSpellMap.put("kui", -16393);
		pSpellMap.put("kun", -16220);
		pSpellMap.put("kuo", -16216);
		pSpellMap.put("la", -16212);
		pSpellMap.put("lai", -16205);
		pSpellMap.put("lan", -16202);
		pSpellMap.put("lang", -16187);
		pSpellMap.put("lao", -16180);
		pSpellMap.put("le", -16171);
		pSpellMap.put("lei", -16169);
		pSpellMap.put("leng", -16158);
		pSpellMap.put("li", -16155);
		pSpellMap.put("lia", -15959);
		pSpellMap.put("lian", -15958);
		pSpellMap.put("liang", -15944);
		pSpellMap.put("liao", -15933);
		pSpellMap.put("lie", -15920);
		pSpellMap.put("lin", -15915);
		pSpellMap.put("ling", -15903);
		pSpellMap.put("liu", -15889);
		pSpellMap.put("long", -15878);
		pSpellMap.put("lou", -15707);
		pSpellMap.put("lu", -15701);
		pSpellMap.put("lv", -15681);
		pSpellMap.put("luan", -15667);
		pSpellMap.put("lue", -15661);
		pSpellMap.put("lun", -15659);
		pSpellMap.put("luo", -15652);
	}

	/**
	 * 加载ascii和拼音的对应关系
	 * 
	 * @param 字母
	 * @param 数字
	 */
	private static void initializeFromMToT(Map<String, Number> pSpellMap) {
		pSpellMap.put("ma", -15640);
		pSpellMap.put("mai", -15631);
		pSpellMap.put("man", -15625);
		pSpellMap.put("mang", -15454);
		pSpellMap.put("mao", -15448);
		pSpellMap.put("me", -15436);
		pSpellMap.put("mei", -15435);
		pSpellMap.put("men", -15419);
		pSpellMap.put("meng", -15416);
		pSpellMap.put("mi", -15408);
		pSpellMap.put("mian", -15394);
		pSpellMap.put("miao", -15385);
		pSpellMap.put("mie", -15377);
		pSpellMap.put("min", -15375);
		pSpellMap.put("ming", -15369);
		pSpellMap.put("miu", -15363);
		pSpellMap.put("mo", -15362);
		pSpellMap.put("mou", -15183);
		pSpellMap.put("mu", -15180);
		pSpellMap.put("na", -15165);
		pSpellMap.put("nai", -15158);
		pSpellMap.put("nan", -15153);
		pSpellMap.put("nang", -15150);
		pSpellMap.put("nao", -15149);
		pSpellMap.put("ne", -15144);
		pSpellMap.put("nei", -15143);
		pSpellMap.put("nen", -15141);
		pSpellMap.put("neng", -15140);
		pSpellMap.put("ni", -15139);
		pSpellMap.put("nian", -15128);
		pSpellMap.put("niang", -15121);
		pSpellMap.put("niao", -15119);
		pSpellMap.put("nie", -15117);
		pSpellMap.put("nin", -15110);
		pSpellMap.put("ning", -15109);
		pSpellMap.put("niu", -14941);
		pSpellMap.put("nong", -14937);
		pSpellMap.put("nu", -14933);
		pSpellMap.put("nv", -14930);
		pSpellMap.put("nuan", -14929);
		pSpellMap.put("nue", -14928);
		pSpellMap.put("nuo", -14926);
		pSpellMap.put("o", -14922);
		pSpellMap.put("ou", -14921);
		pSpellMap.put("pa", -14914);
		pSpellMap.put("pai", -14908);
		pSpellMap.put("pan", -14902);
		pSpellMap.put("pang", -14894);
		pSpellMap.put("pao", -14889);
		pSpellMap.put("pei", -14882);
		pSpellMap.put("pen", -14873);
		pSpellMap.put("peng", -14871);
		pSpellMap.put("pi", -14857);
		pSpellMap.put("pian", -14678);
		pSpellMap.put("piao", -14674);
		pSpellMap.put("pie", -14670);
		pSpellMap.put("pin", -14668);
		pSpellMap.put("ping", -14663);
		pSpellMap.put("po", -14654);
		pSpellMap.put("pu", -14645);
		pSpellMap.put("qi", -14630);
		pSpellMap.put("qia", -14594);
		pSpellMap.put("qian", -14429);
		pSpellMap.put("qiang", -14407);
		pSpellMap.put("qiao", -14399);
		pSpellMap.put("qie", -14384);
		pSpellMap.put("qin", -14379);
		pSpellMap.put("qing", -14368);
		pSpellMap.put("qiong", -14355);
		pSpellMap.put("qiu", -14353);
		pSpellMap.put("qu", -14345);
		pSpellMap.put("quan", -14170);
		pSpellMap.put("que", -14159);
		pSpellMap.put("qun", -14151);
		pSpellMap.put("ran", -14149);
		pSpellMap.put("rang", -14145);
		pSpellMap.put("rao", -14140);
		pSpellMap.put("re", -14137);
		pSpellMap.put("ren", -14135);
		pSpellMap.put("reng", -14125);
		pSpellMap.put("ri", -14123);
		pSpellMap.put("rong", -14122);
		pSpellMap.put("rou", -14112);
		pSpellMap.put("ru", -14109);
		pSpellMap.put("ruan", -14099);
		pSpellMap.put("rui", -14097);
		pSpellMap.put("run", -14094);
		pSpellMap.put("ruo", -14092);
		pSpellMap.put("sa", -14090);
		pSpellMap.put("sai", -14087);
		pSpellMap.put("san", -14083);
		pSpellMap.put("sang", -13917);
		pSpellMap.put("sao", -13914);
		pSpellMap.put("se", -13910);
		pSpellMap.put("sen", -13907);
		pSpellMap.put("seng", -13906);
		pSpellMap.put("sha", -13905);
		pSpellMap.put("shai", -13896);
		pSpellMap.put("shan", -13894);
		pSpellMap.put("shang", -13878);
		pSpellMap.put("shao", -13870);
		pSpellMap.put("she", -13859);
		pSpellMap.put("shen", -13847);
		pSpellMap.put("sheng", -13831);
		pSpellMap.put("shi", -13658);
		pSpellMap.put("shou", -13611);
		pSpellMap.put("shu", -13601);
		pSpellMap.put("shua", -13406);
		pSpellMap.put("shuai", -13404);
		pSpellMap.put("shuan", -13400);
		pSpellMap.put("shuang", -13398);
		pSpellMap.put("shui", -13395);
		pSpellMap.put("shun", -13391);
		pSpellMap.put("shuo", -13387);
		pSpellMap.put("si", -13383);
		pSpellMap.put("song", -13367);
		pSpellMap.put("sou", -13359);
		pSpellMap.put("su", -13356);
		pSpellMap.put("suan", -13343);
		pSpellMap.put("sui", -13340);
		pSpellMap.put("sun", -13329);
		pSpellMap.put("suo", -13326);
		pSpellMap.put("ta", -13318);
		pSpellMap.put("tai", -13147);
		pSpellMap.put("tan", -13138);
		pSpellMap.put("tang", -13120);
		pSpellMap.put("tao", -13107);
		pSpellMap.put("te", -13096);
		pSpellMap.put("teng", -13095);
		pSpellMap.put("ti", -13091);
		pSpellMap.put("tian", -13076);
		pSpellMap.put("tiao", -13068);
		pSpellMap.put("tie", -13063);
		pSpellMap.put("ting", -13060);
		pSpellMap.put("tong", -12888);
		pSpellMap.put("tou", -12875);
		pSpellMap.put("tu", -12871);
		pSpellMap.put("tuan", -12860);
		pSpellMap.put("tui", -12858);
		pSpellMap.put("tun", -12852);
		pSpellMap.put("tuo", -12849);
	}

	/**
	 * 加载ascii和拼音的对应关系
	 * 
	 * @param 字母
	 * @param 数字
	 */
	private static void initializeFromWToZ(Map<String, Number> pSpellMap) {
		pSpellMap.put("wa", -12838);
		pSpellMap.put("wai", -12831);
		pSpellMap.put("wan", -12829);
		pSpellMap.put("wang", -12812);
		pSpellMap.put("wei", -12802);
		pSpellMap.put("wen", -12607);
		pSpellMap.put("weng", -12597);
		pSpellMap.put("wo", -12594);
		pSpellMap.put("wu", -12585);
		pSpellMap.put("xi", -12556);
		pSpellMap.put("xia", -12359);
		pSpellMap.put("xian", -12346);
		pSpellMap.put("xiang", -12320);
		pSpellMap.put("xiao", -12300);
		pSpellMap.put("xie", -12120);
		pSpellMap.put("xin", -12099);
		pSpellMap.put("xing", -12089);
		pSpellMap.put("xiong", -12074);
		pSpellMap.put("xiu", -12067);
		pSpellMap.put("xu", -12058);
		pSpellMap.put("xuan", -12039);
		pSpellMap.put("xue", -11867);
		pSpellMap.put("xun", -11861);
		pSpellMap.put("ya", -11847);
		pSpellMap.put("yan", -11831);
		pSpellMap.put("yang", -11798);
		pSpellMap.put("yao", -11781);
		pSpellMap.put("ye", -11604);
		pSpellMap.put("yi", -11589);
		pSpellMap.put("yin", -11536);
		pSpellMap.put("ying", -11358);
		pSpellMap.put("yo", -11340);
		pSpellMap.put("yong", -11339);
		pSpellMap.put("you", -11324);
		pSpellMap.put("yu", -11303);
		pSpellMap.put("yuan", -11097);
		pSpellMap.put("yue", -11077);
		pSpellMap.put("yun", -11067);
		pSpellMap.put("za", -11055);
		pSpellMap.put("zai", -11052);
		pSpellMap.put("zan", -11045);
		pSpellMap.put("zang", -11041);
		pSpellMap.put("zao", -11038);
		pSpellMap.put("ze", -11024);
		pSpellMap.put("zei", -11020);
		pSpellMap.put("zen", -11019);
		pSpellMap.put("zeng", -11018);
		pSpellMap.put("zha", -11014);
		pSpellMap.put("zhai", -10838);
		pSpellMap.put("zhan", -10832);
		pSpellMap.put("zhang", -10815);
		pSpellMap.put("zhao", -10800);
		pSpellMap.put("zhe", -10790);
		pSpellMap.put("zhen", -10780);
		pSpellMap.put("zheng", -10764);
		pSpellMap.put("zhi", -10587);
		pSpellMap.put("zhong", -10544);
		pSpellMap.put("zhou", -10533);
		pSpellMap.put("zhu", -10519);
		pSpellMap.put("zhua", -10331);
		pSpellMap.put("zhuai", -10329);
		pSpellMap.put("zhuan", -10328);
		pSpellMap.put("zhuang", -10322);
		pSpellMap.put("zhui", -10315);
		pSpellMap.put("zhun", -10309);
		pSpellMap.put("zhuo", -10307);
		pSpellMap.put("zi", -10296);
		pSpellMap.put("zong", -10281);
		pSpellMap.put("zou", -10274);
		pSpellMap.put("zu", -10270);
		pSpellMap.put("zuan", -10262);
		pSpellMap.put("zui", -10260);
		pSpellMap.put("zun", -10256);
		pSpellMap.put("zuo", -10254);
	}
}

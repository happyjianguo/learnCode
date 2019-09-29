/**
 * ZXingCodeUtil.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年12月6日
 */
package cn.com.jansh.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import cn.com.jansh.model.zxing.LogoConfig;
import cn.com.jansh.model.zxing.ZXingConfig;

/**
 * 二维码生成google zxing
 * 
 * @author Mr.wong
 * @version 1.0
 */
public class ZXingCodeUtil {

	/**
	 * 二维码图片加入Logo
	 * 
	 * @param bim
	 *            图片流
	 * @param logoPic
	 *            Logo图片物理位置
	 * @param logoConfig
	 *            Logo图片设置參数
	 * @throws Exception
	 *             异常上抛
	 */
	private void addLogo_QRCode(BufferedImage bim, File logoPic, LogoConfig logoConfig) throws Exception {
		try {
			// 对象流传输
			BufferedImage image = bim;
			Graphics2D g = image.createGraphics();

			// 读取Logo图片
			BufferedImage logo = ImageIO.read(logoPic);

			// 设置logo的大小,本人设置为二维码图片的20%,由于过大会盖掉二维码
			int widthLogo = logo.getWidth(null) > image.getWidth() * 2 / 12 ? (image.getWidth() * 2 / 12)
					: logo.getWidth(null),
					heightLogo = logo.getHeight(null) > image.getHeight() * 2 / 12 ? (image.getHeight() * 2 / 12)
							: logo.getWidth(null);

			// 计算图片放置位置
			// logo放在中心
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;
			// 開始绘制图片
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
			g.setStroke(new BasicStroke(logoConfig.getBorder()));
			g.setColor(logoConfig.getBorderColor());
			g.drawRect(x, y, widthLogo, heightLogo);

			g.dispose();
			logo.flush();
			image.flush();

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 生成二维码bufferedImage图片
	 * 
	 * @param zxingconfig
	 *            二维码配置信息
	 * @return 生成后的 BufferedImage
	 * @throws Exception
	 *             异常上抛
	 */
	public BufferedImage getQR_CODEBufferedImage(ZXingConfig zxingconfig) throws Exception {
		// Google配置文件
		MultiFormatWriter multiFormatWriter = null;
		BitMatrix bm = null;
		BufferedImage image = null;
		try {
			multiFormatWriter = new MultiFormatWriter();
			// 參数顺序分别为：编码内容。编码类型，生成图片宽度，生成图片高度，设置參数
			bm = multiFormatWriter.encode(zxingconfig.getContent(), zxingconfig.getBarcodeformat(),
					zxingconfig.getWidth(), zxingconfig.getHeight());

			int w = bm.getWidth();
			int h = bm.getHeight();
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			// 開始利用二维码数据创建Bitmap图片，分别设为黑白两色
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					image.setRGB(x, y, bm.get(x, y) ?
					Color.BLACK.getRGB() : Color.WHITE.getRGB());
				}
			}
			// 是否设置Logo图片
			if (zxingconfig.isLogoFlg()) {
				this.addLogo_QRCode(image, new File(zxingconfig.getLogoPath()), zxingconfig.getLogoConfig());
			}
		} catch (WriterException e) {
			throw e;
		}
		return image;
	}

	/**
	 * 设置二维码的格式參数
	 * 
	 * @return
	 */
	public Map<EncodeHintType, Object> getDecodeHintType() {
		// 用于设置QR二维码參数
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 设置QR二维码的纠错级别（H为最高级别）详细级别信息
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 设置编码方式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

		return hints;
	}

	/**
	 * 这个工具类 ZXingCodeUtil 是提供二维码图片生成的工具 首先使用的时候须要实例化 ZXingCodeUtil 然后实例化參数
	 * ZXingConfig 和 LogoConfig 通过以下的演示能够详细看參数是依照什么循序进行设置 最后调用 ZXingCodeUtil 中方法
	 * getQR_CODEBufferedImage来生成二维码
	 */
	public static void main(String[] args) throws WriterException {
		String content = "http://cs.ftsafe.com/game/temp/dzpa/dzpa_demo_33?gameid=QBMYFmKifRfe";
		System.out.println("inputParam:" + content);
		try {
			// 生成二维码
			File file = new File("C:/Users/Administrator/Desktop/mt1.png");
			ZXingCodeUtil zp = new ZXingCodeUtil(); // 实例化二维码工具
			ZXingConfig zxingconfig = new ZXingConfig(); // 实例化二维码配置參数
			zxingconfig.setHints(zp.getDecodeHintType()); // 设置二维码的格式參数
			zxingconfig.setContent(content);// 设置二维码生成内容
			zxingconfig.setLogoPath("C:/Users/Administrator/Desktop/mt.png"); // 设置Logo图片
			zxingconfig.setLogoConfig(new LogoConfig()); // Logo图片參数设置
			zxingconfig.setLogoFlg(true); // 设置生成Logo图片
			BufferedImage bim = zp.getQR_CODEBufferedImage(zxingconfig);// 生成二维码
			ImageIO.write(bim, "png", file); // 图片写出
			Thread.sleep(500); // 缓冲
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

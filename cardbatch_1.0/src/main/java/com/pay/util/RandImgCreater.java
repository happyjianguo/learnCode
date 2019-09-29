package com.pay.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;

public class RandImgCreater {
	private static final String CODE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	//private HttpServletResponse response = null;
	private String code;//��֤��;
	private ByteArrayInputStream stream;
	private static final int HEIGHT = 20;
	private static final int FONT_NUM = 4;
	private int width = 0;
	private int iNum = 0;
	private String codeList = "";
	private boolean drawBgFlag = false;

	private int rBg = 0;
	private int gBg = 0;
	private int bBg = 0;

	public RandImgCreater() {
		this.width = 13 * FONT_NUM + 12;
		this.iNum = FONT_NUM;
		this.codeList = CODE_LIST;
	}

	public RandImgCreater(HttpServletResponse response, int iNum,
			String codeList) {
		//this.response = response;
		this.width = 13 * iNum + 12;
		this.iNum = iNum;
		this.codeList = codeList;
	}

	public void createRandImage() {
		BufferedImage image = new BufferedImage(width, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		if (drawBgFlag) {
			g.setColor(new Color(rBg, gBg, bBg));
			g.fillRect(0, 0, width, HEIGHT);
		} else {
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, HEIGHT);

			/*
			 * for (int i = 0; i < 155; i++) { g.setColor(getRandColor(140,
			 * 200)); int x = random.nextInt(width); int y =
			 * random.nextInt(HEIGHT); int xl = random.nextInt(12); int yl =
			 * random.nextInt(12); g.drawLine(x, y, x + xl, y + yl); }
			 */
		}
		String sRand = "";
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		for (int i = 0; i < iNum; i++) {
			int rand = random.nextInt(codeList.length());
			String strRand = codeList.substring(rand, rand + 1);
			sRand += strRand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(strRand, 13 * i + 6, 16);
		}
		g.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = null;
		try {
			imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (imageOut != null) {
					imageOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.setCode(sRand.toLowerCase());
		this.setStream(new ByteArrayInputStream(output.toByteArray()));
		//map.put(sRand, new ByteArrayInputStream(output.toByteArray()));
		/*
		 * try{ //ImageIO.write(image, "JPEG", response.getOutputStream()); //
		 * System.out.println(request.getSession().getServletContext().getRealPath("/"));
		 * //ImageIO.write(image, "JPEG", new
		 * File(request.getSession().getServletContext().getRealPath("/")+"/image.jpeg"));
		 * }catch(IOException e){
		 *  }
		 */

	}

	public void setBgColor(int r, int g, int b) {
		drawBgFlag = true;
		this.rBg = r;
		this.gBg = g;
		this.bBg = b;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ByteArrayInputStream getStream() {
		return stream;
	}

	public void setStream(ByteArrayInputStream stream) {
		this.stream = stream;
	}
}
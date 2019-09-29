package cn.com.jansh.controller.system;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.ImageUtil;

@Controller
@RequestMapping(value = "/messagecode")
public class MessageCodeController {

	private static final Logger logger = LogManager.getLogger(MessageCodeController.class);

	/**
	 * 获取短信验证码
	 * 
	 * @param openid
	 * @param model
	 * @return
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getcode")
	public String getCode(HttpServletRequest request) throws JsonProcessingException {
		String msgcode = RandomStringUtils.random(6, new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' });
		// String msgcode = RandomStringUtils.randomNumeric(6);
		logger.info("msgcode:" + msgcode);
		// 此处应配置银行短信接口向银行卡手机发送验证码短信
		Map<String, String> codemap = new HashMap<String, String>();
		codemap.put("msgcode", msgcode);
		codemap.put("time", String.valueOf(DateUtil.getClockMillis()));
		request.getSession().setAttribute("_app_msg_code", codemap);

		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "1");
		map.put("msgcode", msgcode);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(map);
	}

	/**
	 * 生成图片验证码
	 * 
	 * @param req
	 * @param resp
	 * @throws JsonProcessingException
	 */
	@ResponseBody
	@RequestMapping(value = "/getimagecode")
	public void getImageCode(HttpServletRequest req, HttpServletResponse resp) {
		// Set to expire far in the past.
		resp.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		resp.setHeader("Pragma", "no-cache");
		// return a jpeg
		resp.setContentType("image/jpg");

		// 1.调用工具类，生成验证码及图片
		Map<String, BufferedImage> imageMap = ImageUtil.createImage();
		// 2.从imageMap中取到验证码，并放入session
		String imageCode = imageMap.keySet().iterator().next();
		req.getSession().setAttribute("IMAGECODE", imageCode);
		// 3.从imageMap中取到图片，转为输入流
		BufferedImage image = imageMap.get(imageCode);
		req.getSession().setAttribute("time", new Date());

		ServletOutputStream out = null;
		try {
			out = resp.getOutputStream();
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			logger.error(e);
		}
	}
}

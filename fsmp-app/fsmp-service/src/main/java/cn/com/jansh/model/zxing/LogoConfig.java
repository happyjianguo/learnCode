/**
 * LogoConfig.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年12月6日
 */
package cn.com.jansh.model.zxing;

import java.awt.Color;

/**
 * logo背景配置
 * @author Mr.wong
 * @version 1.0
 */
public class LogoConfig {

	public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;    // logo默认边框颜色
    public static final int DEFAULT_BORDER = 0; // logo默认边框宽度
    public static final int DEFAULT_LOGOPART = 5;   // logo大小默觉得照片的1/5
    private final int border = DEFAULT_BORDER;  // 默认边框宽度
    private final Color borderColor;    // 边框颜色
    private final int logoPart; //  边框外围宽度

    /**
     * 二维码无參构造函数 默认设置Logo图片底色白色宽度2
     */
    public LogoConfig() {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    /**
     * 二维码有參构造函数
     * 
     * @param borderColor 边框颜色
     * @param logoPart 边框宽度
     */
    public LogoConfig(Color borderColor, int logoPart) {
        // 设置边框
        this.borderColor = borderColor;
        // 设置边框宽度
        this.logoPart = logoPart;
    }

    /**
     * 获取边框颜色
     * 
     * @return 获取边框颜色
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * 获取边框
     * 
     * @return 获取边框
     */
    public int getBorder() {
        return border;
    }

    /**
     * 外围边宽
     * 
     * @return 外围边宽
     */
    public int getLogoPart() {
        return logoPart;
    }
}

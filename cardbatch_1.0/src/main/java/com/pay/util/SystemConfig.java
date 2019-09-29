package com.pay.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * 
 * @author YP
 * @created 2007/12/18
 * 
 * 系统参数调用类
 * 
 */
public class SystemConfig {
    private static Properties ps = new Properties();

    public SystemConfig() {
    }

    public static String getValue(String name) {
        String value = null;
        try {
            if (SystemConfig.ps != null && SystemConfig.ps.size() > 0)
                value = SystemConfig.ps.getProperty(name, "");
        } catch (Exception e) {
            System.out.println("%%%%name=" + name);
            System.out.println("SystemConfig getValue ex=" + e.getClass().getName() + " " + e);
        }

        return value;
    }

    public static void setValue(String name, String value) {
        ps.setProperty(name, value);
    }

    public static String[] getNames() {
        Enumeration e = ps.propertyNames();
        int length = ps.size();
        String[] names = new String[length];
        String name = "";
        int i = 0;
        System.out.println(StringUtils.outerToInner("系统配置信息===="));
        while (e.hasMoreElements()) {
            name = (String) e.nextElement();
            names[i++] = name;
            System.out.println(name + "=" + ps.getProperty(name));
        }
        System.out.println(StringUtils.outerToInner("======系统配置信息结束"));

        return names;
    }

    public static void load(ServletContext context) {
        InputStream is = null;
        try {
            URL url = context.getResource("/WEB-INF/config/sysconf.properties");
            if (url != null) {
                is = url.openStream();
                ps.load(is);
                getNames();
            }
        } catch (Exception e) {
            System.out.println("SystemConfig load exception=" + e);
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                }
        }
    }

}

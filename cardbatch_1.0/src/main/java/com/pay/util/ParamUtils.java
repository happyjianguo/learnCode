package com.pay.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @TODO ��ȡҳ��������÷���
 *
 * @author YP
 * @created on 2007-12-19  ����01:19:59
 * @version 1.0
 */
public class ParamUtils {
    public ParamUtils() {
    }

    /**
     * 
     * @TODO ��ȡrequest�д���Ĳ���ֵ��������ת��
     *
     * @param request
     * @param name ��������
     * @return ����ֵ
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����01:20:24
     * LastModified
     * History:
     * </pre>
     */
    public static String getParameter(HttpServletRequest request, String name) {
        return getParameter(request, name, null);
    }

    /**
     * 
     * @TODO ��ȡrequest�д���Ĳ���ֵ������codetype����ת��
     *
     * @param request
     * @param name ��������
     * @param codetype ת������
     *          null ������ת��
     *          exchange ���������תΪ�������
     *          getiso   ���������תΪ�������
     * @return
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����01:21:03
     * LastModified
     * History:
     * </pre>
     */
    public static String getParameter(HttpServletRequest request, String name, String codetype) {
        if (request.getParameter(name) == null)
            return "";

        String temp = "";
        if (request.getParameter(name) != null) {
            if (codetype == null)
                temp = request.getParameter(name);
            else if (codetype.toLowerCase().intern() == "exchange")
                temp = StringUtils.innerToOuter(request.getParameter(name));
            else if (codetype.toLowerCase().intern() == "getiso")
                temp = StringUtils.outerToInner(request.getParameter(name));
            else
                temp = request.getParameter(name);
        }

        return temp;
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String name) {
        return getBooleanParameter(request, name, false);
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultVal) {
        if (request.getParameter(name) == null)
            return defaultVal;

        boolean returnBool = defaultVal;
        if (request.getParameter(name) != null) {
            String temp = request.getParameter(name);
            if ("true".equalsIgnoreCase(temp) || "on".equalsIgnoreCase(temp))
                returnBool = true;
            if ("false".equalsIgnoreCase(temp) || "off".equalsIgnoreCase(temp))
                returnBool = false;
        }

        return returnBool;
    }

    public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
        if (request.getParameter(name) == null)
            return defaultNum;

        int num = defaultNum;
        if (request.getParameter(name) != null) {
            String temp = request.getParameter(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Integer.parseInt(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

    public static int[] getIntParameters(HttpServletRequest request, String name, int defaultNum) {
        String[] paramValues = null;
        if (request.getParameter(name) != null) {
            paramValues = request.getParameterValues(name);
            if (paramValues == null)
                return null;
            if (paramValues.length < 1)
                return new int[0];
        }

        int values[] = null;
        if (paramValues != null) {
            values = new int[paramValues.length];
            for (int i = 0; i < paramValues.length; i++)
                try {
                    values[i] = Integer.parseInt(paramValues[i]);
                } catch (Exception e) {
                    values[i] = defaultNum;
                }
        }

        return values;
    }

    public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
        if (request.getParameter(name) == null)
            return defaultNum;

        double num = defaultNum;
        if (request.getParameter(name) != null) {
            String temp = request.getParameter(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Double.parseDouble(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

    public static double[] getDoubleParameters(HttpServletRequest request, String name, double defaultNum) {
        String[] paramValues = null;
        if (request.getParameter(name) != null) {
            paramValues = request.getParameterValues(name);
        }
        double values[] = null;
        if (paramValues == null)
            return null;
        else if (paramValues.length < 1)
            return new double[0];
        else {
            values = new double[paramValues.length];
            for (int i = 0; i < paramValues.length; i++)
                try {
                    values[i] = Double.parseDouble(paramValues[i]);
                } catch (Exception e) {
                    values[i] = defaultNum;
                }
        }

        return values;
    }

    public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
        if (request.getParameter(name) == null)
            return defaultNum;

        long num = defaultNum;
        if (request.getParameter(name) == null) {
            String temp = request.getParameter(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Long.parseLong(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

    public static long[] getLongParameters(HttpServletRequest request, String name, long defaultNum) {
        String[] paramValues = null;

        if (request.getParameterValues(name) != null) {
            paramValues = request.getParameterValues(name);
        }

        long values[] = null;
        if (paramValues == null)
            return null;
        else if (paramValues.length < 1)
            return new long[0];
        else {
            values = new long[paramValues.length];
            for (int i = 0; i < paramValues.length; i++)
                try {
                    values[i] = Long.parseLong(paramValues[i]);
                } catch (Exception e) {
                    values[i] = defaultNum;
                }
        }

        return values;
    }

    public static float getFloatParameter(HttpServletRequest request, String name, float defaultNum) {
        if (request.getParameter(name) == null)
            return defaultNum;

        float num = defaultNum;
        if (request.getParameter(name) != null) {
            String temp = request.getParameter(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Float.parseFloat(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

    public static float[] getFloatParameters(HttpServletRequest request, String name, float defaultNum) {
        String[] paramValues = null;
        if (request.getParameterValues(name) != null)
            paramValues = request.getParameterValues(name);

        float values[] = null;
        if (paramValues == null)
            return null;
        else if (paramValues.length < 1)
            return new float[0];
        else {
            values = new float[paramValues.length];
            for (int i = 0; i < paramValues.length; i++)
                try {
                    values[i] = Float.parseFloat(paramValues[i]);
                } catch (Exception e) {
                    values[i] = defaultNum;
                }
        }

        return values;
    }

    /**
     * 
     * @TODO  ��ȡrequest�е��ַ�������ֵ��������ת��
     *
     * @param request 
     * @param name ��������
     * @return ����ֵ
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����01:22:41
     * LastModified
     * History:
     * </pre>
     */
    public static String getStringAttribute(HttpServletRequest request, String name) {
        return getStringAttribute(request, name, null);
    }

    /**
     * 
     * @TODO ��ȡrequest�е��ַ�������ֵ������codetype����ת��
     *
     * @param request ��������
     * @param name ת������
     * @param codetype 
     *            null ������ת��
     *            exchange ��ISO8859-1����תΪGB2312����
     *            getiso   ��GB2312����תΪISO8859-1����
     * @return ����ֵ
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����01:23:05
     * LastModified
     * History:
     * </pre>
     */
    public static String getStringAttribute(HttpServletRequest request, String name, String codetype) {
        if (request.getAttribute(name) == null)
            return "";

        String temp = "";
        if (request.getAttribute(name) != null) {
            if (codetype == null)
                temp = (String) request.getAttribute(name);
            else if (codetype.toLowerCase().intern() == "exchange")
                temp = StringUtils.innerToOuter((String) request.getAttribute(name));
            else if (codetype.toLowerCase().intern() == "getiso")
                temp = StringUtils.outerToInner((String) request.getAttribute(name));
            else
                temp = (String) request.getAttribute(name);
        }

        return temp;
    }

    public static boolean getBooleanAttribute(HttpServletRequest request, String name) {
        return getBooleanParameter(request, name, false);
    }

    public static boolean getBooleanAttribute(HttpServletRequest request, String name, boolean defaultVal) {
        if (request.getAttribute(name) == null)
            return defaultVal;

        boolean returnBool = defaultVal;
        if (request.getAttribute(name) != null) {
            String temp = (String) request.getAttribute(name);
            if ("true".equalsIgnoreCase(temp) || "on".equalsIgnoreCase(temp))
                returnBool = true;
            else if ("false".equalsIgnoreCase(temp) || "off".equalsIgnoreCase(temp))
                returnBool = false;
            else
                return returnBool = defaultVal;
        }

        return returnBool;
    }

    public static int getIntAttribute(HttpServletRequest request, String name, int defaultNum) {
        if (request.getAttribute(name) == null)
            return defaultNum;

        int num = defaultNum;
        if (request.getAttribute(name) != null) {
            String temp = (String) request.getAttribute(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Integer.parseInt(temp);
                } catch (Exception ignored) {
                }
                return num;
            }
        }

        return num;
    }

    public static double getDoubleAttribute(HttpServletRequest request, String name, double defaultNum) {
        if (request.getAttribute(name) == null)
            return defaultNum;

        double num = defaultNum;
        if (request.getAttribute(name) != null) {
            String temp = (String) request.getAttribute(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Double.parseDouble(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

    public static long getLongAttribute(HttpServletRequest request, String name, long defaultNum) {
        if (request.getAttribute(name) == null)
            return defaultNum;

        long num = defaultNum;
        if (request.getAttribute(name) != null) {
            String temp = (String) request.getAttribute(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Long.parseLong(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

    public static float getFloatAttribute(HttpServletRequest request, String name, float defaultNum) {
        if (request.getAttribute(name) == null)
            return defaultNum;

        float num = defaultNum;
        if (request.getAttribute(name) != null) {
            String temp = (String) request.getAttribute(name);
            if (temp != null && temp.trim().length() > 0) {
                try {
                    num = Float.parseFloat(temp);
                } catch (Exception ignored) {
                }
            }
        }

        return num;
    }

}

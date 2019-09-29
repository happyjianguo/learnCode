package cn.yufu.core.common.util;

import org.apache.commons.beanutils.*;
import java.lang.reflect.InvocationTargetException;

/**
 �������Կ���
 */
public class ObjectCopy
{
  /**
   *  ���������е�ͬ��ͬ���͵�����
   * @param destination Object - Ŀ�����
   * @param origin Object - Դ����
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public static void beanCopy(Object destination, Object origin) throws
      InvocationTargetException, IllegalAccessException
  {
    BeanUtils.copyProperties(destination, origin);
  }


  /**
   *  ���������е�ͬ�������ԣ���ת����ͬ����������
   * @param destination Object - Ŀ�����
   * @param origin Object - Դ����
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public static void propertyCopy(Object destination, Object origin) throws
      NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    PropertyUtils.copyProperties(destination, origin);
  }
}

package cn.yufu.core.common.util;

import org.apache.commons.beanutils.*;
import java.lang.reflect.InvocationTargetException;

/**
 对象属性拷贝
 */
public class ObjectCopy
{
  /**
   *  拷贝对象中的同名同类型的属性
   * @param destination Object - 目标对象
   * @param origin Object - 源对象
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public static void beanCopy(Object destination, Object origin) throws
      InvocationTargetException, IllegalAccessException
  {
    BeanUtils.copyProperties(destination, origin);
  }


  /**
   *  拷贝对象中的同名的属性，并转换不同的数据类型
   * @param destination Object - 目标对象
   * @param origin Object - 源对象
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

package cn.yufu.core.common.util;

public final class Debug
{

  private static boolean debuggingOn = Util.stringToBoolean(Configuration.
      getInstance(StaticData.STATICDATAPROPPATH).getValue("DEBUG")); //StaticData.DEBUG;

  /**
   * 打印异常信息
   print( (Throwable) e, msg);
   * @param e Exception
   * @param msg String
   */
  public static void print(Exception e, String msg)
  {
    print( (Throwable) e, msg);
  }

  /**
   *  打印异常
      print(e, null);
   * @param e Exception
   */
  public static void print(Exception e)
  {
    print(e, null);
  }


  /**
   * 打印信息
      if (debuggingOn)
          {
              System.out.print(msg);
          }

   * @param msg String
   */
  public static void print(String msg)
  {
    if (debuggingOn)
    {
      System.out.print(msg);
    }

  }


  /**
   * 打印自定义异常信息
      if (debuggingOn)
          {
              System.out.println("Received throwable with Message: " +
                                 t.getMessage());
              if (msg != null)
              {
                  System.out.print(msg);
              }
              t.printStackTrace();
          }

   * @param t Throwable
   * @param msg String
   */
  public static void print(Throwable t, String msg)
  {
    if (debuggingOn)
    {
      System.out.println("Received throwable with Message: " +
                         t.getMessage());
      if (msg != null)
      {
        System.out.print(msg);
      }
      t.printStackTrace();
    }

  }


  /**
   * 打印异常
   print(t, null);
   * @param t Throwable
   */
  public static void print(Throwable t)
  {
    print(t, null);
  }


  /**
   *
   * @param msg String
   */
  public static void println(String msg)
  {
    if (debuggingOn)
    {
      System.out.println(msg);
    }

  }
}

package cn.yufu.core.common.util;

public final class Debug
{

  private static boolean debuggingOn = Util.stringToBoolean(Configuration.
      getInstance(StaticData.STATICDATAPROPPATH).getValue("DEBUG")); //StaticData.DEBUG;

  /**
   * ��ӡ�쳣��Ϣ
   print( (Throwable) e, msg);
   * @param e Exception
   * @param msg String
   */
  public static void print(Exception e, String msg)
  {
    print( (Throwable) e, msg);
  }

  /**
   *  ��ӡ�쳣
      print(e, null);
   * @param e Exception
   */
  public static void print(Exception e)
  {
    print(e, null);
  }


  /**
   * ��ӡ��Ϣ
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
   * ��ӡ�Զ����쳣��Ϣ
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
   * ��ӡ�쳣
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

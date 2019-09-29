import java.lang.reflect.Field;
import java.util.List;

/**
 *包名:
 *描述:
 */
/**
 * ObjectTest.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年11月28日
 * 描述:TODO
 */
public class ObjectTest {
//	public static Log log = Log.getLog(ObjectTest.class);
	/**
	 * 判断对象部分属性是否为空
	 * @param  obj b ...name(b, ...name可选)
	 * 若b为空或者为true，则判断该对象除name属性之外的其他属性是否为空
	 * 若b为false，则判断该对象name属性是否为空
	 * @return Boolean
	 * @throws IllegalAccessException
	 */
	public static  boolean checkObjFieldIsNull(Object obj,String ...name) throws IllegalAccessException {
		boolean b = true;
		return checkObjFieldIsNull(obj,b,name);
	}
	public static  boolean checkObjFieldIsNull(Object obj,Boolean b,String ...name) throws IllegalAccessException {
	    boolean flag = false;
        List<String> list = java.util.Arrays.asList(name);
	    for(Field f : obj.getClass().getDeclaredFields()){
	        f.setAccessible(true);
//	        log.info(f.getName());
	        System.out.println(f.getName());
	        if(b){
	        	if(!list.contains(f.getName()))
		        {
	        		System.out.println(f.get(obj));
	        		if(f.get(obj) == null){
		        		flag = true;
		        		return flag;
		        	}
		        }
	        }else{
	        	if(list.contains(f.getName()))
		        {
	        		System.out.println(f.get(obj));
	        		if(f.get(obj) == null){
		        		flag = true;
		        		return flag;
		        	}
		        }
	        }
	    }
	    return flag;
	}
	public static void main(String[] args) throws IllegalAccessException{
		User u = new User();
		u.setAge("12");
		u.setName("动物");
		u.setSex("Y");
		System.out.println(checkObjFieldIsNull(u));
		/**输出
		name
		动物
		age
		12
		sex
		Y
		false
		**/
		User u1 = new User();
		u1.setAge("12");
		System.out.println(checkObjFieldIsNull(u1,false,"age"));
		/**输出
		name
		age
		12
		sex
		false
		**/
		System.out.println(checkObjFieldIsNull(u1,"name","sex"));
		/**输出
		name
		age
		12
		sex
		false
		**/
		;;
	}
}

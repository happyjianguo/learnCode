package cn.com.jansh.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.com.jansh.core.constant.Operation;

/**
 * 操作返回信息
 * 
 * @author nie
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationResult {
	/**
	 * 操作类型
	 * 
	 * @return
	 */
	Operation value() default Operation.DEFULT;

	/**
	 * 提示信息key
	 * 
	 * @return
	 */
	String msgCode() default "";

}

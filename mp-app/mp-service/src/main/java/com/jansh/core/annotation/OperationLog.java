package com.jansh.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jansh.core.constant.Operation;

/**
 * 记录操作日志，controller
 * 
 * @author nie
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
	/**
	 * 日志操作类型
	 * 
	 * @return
	 */
	Operation value() default Operation.DEFULT;

	/**
	 * 日志信息key
	 * 
	 * @return
	 */
	String key() default "";

}

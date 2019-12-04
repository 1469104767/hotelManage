package core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import core.enumx.SqlType;

@Target({ElementType.METHOD})//可以定义在方法上
@Retention(RetentionPolicy.RUNTIME)//运行有效,存在class字节码文件中
public @interface Sql {
	
	String value() default "";
	
	/** 类型,默认查询 */
	SqlType type() default SqlType.select;
	
	/** 表示sql的字段名 */
	String field() default "";
	
	/** 表示sql的properties属性,properties是与类名同名的文件 */
	String properties() default "";
}

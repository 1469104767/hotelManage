package core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *	此注解用于标注mapper多参数对应字段名
 */
@Target({ElementType.PARAMETER})//可以定义在方法上
@Retention(RetentionPolicy.RUNTIME)//运行有效,存在class字节码文件中
public @interface Param {
	String value();
}

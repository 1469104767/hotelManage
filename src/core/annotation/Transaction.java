package core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
@Target({ElementType.METHOD,ElementType.TYPE})//可以定义在方法上
@Retention(RetentionPolicy.RUNTIME)//运行有效,存在class字节码文件中
public @interface  Transaction{
    String value() default "";
    boolean readOnly() default false;
}

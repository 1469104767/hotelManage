package core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import basics.action.TestAction;

public class DynaProxy implements InvocationHandler {  
    /** *//** 
     * 要处理的对象(也就是我们要在方法的前后加上业务逻辑的对象,如例子中的Hello) 
     */  
    private Object delegate;  
  
    /** *//** 
     * 动态生成方法被处理过后的对象 (写法固定) 
     *  
     * @param delegate 
     * @param proxy 
     * @return 
     */  
    public Object bind(Object delegate) {  
        //logger =Logger.getLogger(delegate.getClass());  
        this.delegate = delegate;  
        return Proxy.newProxyInstance(  
                this.delegate.getClass().getClassLoader(),   
                this.delegate.getClass().getInterfaces(),   
                this);  
    }  
    /** *//** 
     * 要处理的对象中的每个方法会被此方法送去JVM调用,也就是说,要处理的对象的方法只能通过此方法调用 
     * 此方法是动态的,不是手动调用的 
     */  
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
        Object result = null;  
        try {  
            //执行原来的方法之前记录日志  
            System.out.println("开始运行"+method.getName());  
            //JVM通过这条语句执行原来的方法(反射机制)  
            result = method.invoke(this.delegate, args);  
            //执行原来的方法之后记录日志  
            System.out.println("结束运行"+result);  
        } catch (Exception e) {  
        	System.out.println("抛出异常");  
            e.printStackTrace();  
        }finally{  
        	System.out.println("finally运行");  
        }  
        //返回方法返回值给调用者  
        return result;  
    }  
	public static void main(String[] args) throws Exception {
		List bind = (List)new DynaProxy().bind(new ArrayList<>());
		bind.add("");
	}
} 
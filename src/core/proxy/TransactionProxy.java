package core.proxy;

import java.lang.reflect.Method;
import java.sql.Connection;

import basics.service.TestService;
import core.annotation.Transaction;
import core.factory.BeanFactory;
import core.jdbc.DataSourcePool;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理类,控制事务
 */
public class TransactionProxy implements MethodInterceptor{
   //维护目标对象
   private Object target;

   public TransactionProxy(Object target) {
       this.target = target;
   }

   //给目标对象创建一个代理对象
   public Object getProxyInstance(){
       //1.工具类
       Enhancer en = new Enhancer();
       //2.设置父类
       en.setSuperclass(target.getClass());
       //3.设置回调函数
       en.setCallback(this);
       //4.创建子类(代理对象)
       return en.create();

   }

   @Override
   public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

       Transaction transaction = method.getAnnotation(Transaction.class);
       Object returnValue = null;
       if(transaction!=null&&!transaction.readOnly()){
    	   Connection connection = DataSourcePool.getInstance().getConnection();
    	   try {
    		   returnValue = method.invoke(target, args);
		} catch (Exception e) {
				
			throw e;
		}finally {
			
		}
    	   System.out.println("提交事务...");
       }else{
    	   returnValue = method.invoke(target, args);
       }

       return returnValue;
   }
   
   public static void main(String[] args) {
	   BeanFactory.init();
	   TestService a =  (TestService)new TransactionProxy(BeanFactory.getBean("basics.service.TestService")).getProxyInstance();
	   System.out.println();
   }
}
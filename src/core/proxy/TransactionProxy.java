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
	   boolean isTransaction = false;
	   //判定类和方法的事务注解
	   Transaction typeTransaction = target.getClass().getAnnotation(Transaction.class);
	   Transaction methodTransaction = method.getAnnotation(Transaction.class);
	   //类有,方法只读,则没有 事务
	   if(typeTransaction!=null&&!typeTransaction.readOnly()){
		   isTransaction = true;
		   if(methodTransaction!=null&&methodTransaction.readOnly())
			   isTransaction = false;
	   }
	   //方法有且不是只读,则有事务
	   if(methodTransaction!=null&&!methodTransaction.readOnly()){
		   isTransaction = true;
	   }
       Object returnValue = null;
       //如果开启事务管理
       if(isTransaction){
    	   Connection connection = DataSourcePool.getInstance().getConnection(true);
    	   //如果事务自动提交已关闭,说明已由外层方法管理事务,则直接执行
    	   if(connection.getAutoCommit()){
    		   try {
    			   connection.setAutoCommit(false);
    			   returnValue = method.invoke(target, args);
    			   connection.commit();
    			   return returnValue;
    		   } catch (Exception e) {
    			   connection.rollback();	
    			   throw e;
    		   }finally {
    			   DataSourcePool.getInstance().addBack(connection, true);
    		   }
    	   }
       }
       returnValue = method.invoke(target, args);

       return returnValue;
   }
}
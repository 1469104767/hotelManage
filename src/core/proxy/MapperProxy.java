package core.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.mysql.jdbc.StringUtils;

import basics.action.TestAction;
import core.annotation.Param;
import core.annotation.Sql;
import core.enumx.SqlType;
import core.jdbc.JdbcUtil;
import core.util.BeanUtil;
import core.util.MethodParamNamesScanner;
import core.util.PropertiesUtil;

/**
 *	JDK的动态代理,效率比cglib高
 */
public class MapperProxy implements InvocationHandler {  
	
    @SuppressWarnings("unchecked")
	public <T> T bind(Class<T> clazz) {  
        return (T) Proxy.newProxyInstance(  
        		clazz.getClassLoader(),   
        		new Class[]{clazz},   
                this);  
    }  
    /** *//** 
     * 要处理的对象中的每个方法会被此方法送去JVM调用,也就是说,要处理的对象的方法只能通过此方法调用 
     * 此方法是动态的,不是手动调用的 
     */  
    @SuppressWarnings("unchecked")
	@Override
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
    	Class<?> mapperType = (Class<?>) proxy.getClass().getGenericInterfaces()[0];
        Object result = null;  
        Sql annotation = method.getAnnotation(Sql.class);
        if(annotation==null){
        	throw new SQLException("you must have a Sql annotation!");
        }
        String sql = annotation.value();
        //value没有值则看field
    	if(StringUtils.isNullOrEmpty(sql)){
    		String fieldName = annotation.field();
    		//field没有则看properties
    		if(StringUtils.isNullOrEmpty(fieldName)){
    			String properties = annotation.properties();
    			sql = PropertiesUtil.getSqlProperties(method.getDeclaringClass()).getProperty(properties);
    		}else{
    			Field field = mapperType.getField(fieldName);
    			sql = (String)field.get(proxy);
    		}
    	}
    	if(StringUtils.isNullOrEmpty(sql)){
    		throw new SQLException("Sql must be not null!");
    	}
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	//处理参数 Map则直接传递, 实体(非系统类)转为Map, 其他参数根据参数名转为Map
    	if(args.length>0&&args[0]!=null){
    		Class<?> paramClass = args[0].getClass();
    		if(Map.class.isAssignableFrom(paramClass)){
    			paramMap = (Map<String, Object>) args[0];
    		}else if(paramClass.getClassLoader()!=null){
    			paramMap = BeanUtil.copyBeanToMap(args[0], paramMap);
    		}else{
    			Parameter[] parameters = method.getParameters();
    			//多参数根据注解解析,单参数根据
    			if(parameters.length>1){
    				for (int i = 0; i < parameters.length; i++) {
    					Param param = parameters[i].getAnnotation(Param.class);
    					if(param.value()==null){
    						throw new RuntimeException("parameter must have Param annotation if count > 1!");
    					}
    					paramMap.put(param.value(), args[i]);
    				}
    			}else{
    				paramMap.put(JdbcUtil.PARAM_SINGLE_NAME, args[0]);
    			}
    		}
    	}
    	
    	if(annotation.type()==SqlType.select){
    		//List返回单独处理
    		if(List.class.isAssignableFrom(method.getReturnType())){
    			Type type = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
    			Class<?> returnType = null;
    			//如果是泛型则需要获取真实类型
    			if(TypeVariable.class.isAssignableFrom(type.getClass())){
    				Type[] types = mapperType.getGenericInterfaces();
    				ParameterizedType parameterized = (ParameterizedType) types[0];
    				returnType = (Class<?>) parameterized.getActualTypeArguments()[0];
    			}else{
    				//泛型带泛型
    				if(ParameterizedType.class.isAssignableFrom(type.getClass())){
    					returnType = (Class<?>) ((ParameterizedType)type).getRawType();
    				}else{
    					returnType = (Class<?>)type;
    				}
    			}
    			//获取List的泛型
    			result = JdbcUtil.selectList(sql, returnType, paramMap);
    		}else{
    			result = JdbcUtil.selectOne(sql, method.getReturnType(), paramMap);
    		}
    	}else{
    		result = JdbcUtil.update(sql, paramMap);
    	}
        return result;  
    }  
} 
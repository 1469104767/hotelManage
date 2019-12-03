package core.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.FileInfo;

import core.util.BeanUtil;
import core.util.JsonUtils;
import core.util.MethodParamNamesScanner;

/**
 * <li>类型名称：ActionMethod
 * <li>说明：action的方法
 * <li>创建日期：2019年11月26日
 */
public class ActionMethod {
    private Method method;
    private Object bean;
    private List<String> paramNames;
    public ActionMethod() {
        super();
    }
    public ActionMethod(Method method, Object bean) {
        super();
        this.method = method;
        this.bean = bean;
        //填入方法参数
        try {
			this.paramNames = MethodParamNamesScanner.getMethodParamNames(method.getDeclaringClass(), method);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    public Object invoke(Map<String,Object> paramterMap) throws Exception{
    	//拿到方法的参数列表和参数名
		Class<?>[] types = getParameterTypes();
		List<String> paramNames = getParamNames();
		Object[] args = new Object[types.length];
		//为方法按顺序设置参数,分别考虑 String FileInfo Number map 实体 类型
		for (int i = 0; i < types.length; i++) {
			Class<?> type = types[i];
			String name = paramNames.get(i);
			//实体类和Map不需要对应参数名
			if(paramterMap.get(name)==null){
				//判断是不是系统类
				if(type.getClassLoader()!=null){
					BeanUtil.copyMapToBean(paramterMap, type.getConstructor().newInstance());
				}else if(Map.class.isAssignableFrom(type)){
					args[i] = paramterMap;
				}
			}else if(String.class.isAssignableFrom(type)||FileInfo.class.isAssignableFrom(type)){
				args[i] = paramterMap.get(name);
			}else if(Number.class.isAssignableFrom(type)){
				args[i] = type.getConstructor(String.class).newInstance(paramterMap.get(name));
			}else{
				JsonUtils.convertJSONToObject((String)paramterMap.get(name), type);
			}
        }
        return method.invoke(bean, args);
    }
    
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public Object getBean() {
        return bean;
    }
    public void setBean(Object bean) {
        this.bean = bean;
    }
	public List<String> getParamNames() {
		return paramNames;
	}
	public void setParamNames(List<String> paramNames) {
		this.paramNames = paramNames;
	}
	public Class<?>[] getParameterTypes(){
		return method.getParameterTypes();
	}
}
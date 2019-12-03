package core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

public class BeanUtil {
	
	/** 拷贝属性
	 *  前提，source、target要实现get/set方法
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copyProperties(Object source,T target) throws Exception {
        //Class对象
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        if(Map.class.isAssignableFrom(sourceClass)){
        	return copyMapToBean((Map<String,Object>)source, target);
        }
        if(Map.class.isAssignableFrom(targetClass)){
        	return (T) copyBeanToMap(source, (Map<String,Object>)target);
        }
        
        Field[] sourceClassDeclaredFields = sourceClass.getDeclaredFields();

        if (sourceClassDeclaredFields.length == 0) {
            throw new RuntimeException("数据源成员变量为0");
        }

        //遍历source的成员变量
        for (Field field : sourceClassDeclaredFields) {
            //获取当前字段的名称
            String fieldName = field.getName();

            //根据source获取target对象是否存在该字段,不存在就跳过
            Field targetClassField = targetClass.getDeclaredField(fieldName);

            if (null == targetClassField) {
                continue;
            }

            //判断字段类型是否一致,类型不一致,跳过
            if (!field.getType().equals(targetClassField.getType())) {
                continue;
            }

            //生成方法名,并得到getXxx()/setXxx(Type xxx)方法获取值,set到目标中对应字段
            String getMethodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            String setMethodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);

            //根据source对象获取方法, 入参:1 方法名 2 形参
            //如果source对象本身不存在该方法，会报NoSuchMethodException，这里就跳过该字段不作处理
            Method getMethod = null;
            try {
                //new Class[]{} 说明：因为get（）方法没有形参
                getMethod = sourceClass.getMethod(getMethodName, new Class[]{});
            } catch (NoSuchMethodException e) {
                continue;
            }

            //获取target对象的set方法, 入参: 1 方法名 2 形参(Type xxx)
            Method setMethod = null;
            try {
                setMethod = targetClass.getMethod(setMethodName, field.getType());
            } catch (NoSuchMethodException e) {
               continue;
            }

            /**
             * XxMethod.invoke（某实例对象，参数）方法
             *
             * 这里的含义是调用某实例对象的XxMethod（参数）方法
             */
            //调用source get方法获取值
            Object result = getMethod.invoke(source, new Object[]{});

            //调用target set值
            setMethod.invoke(target, result);

        }
		return target;
    }
	public static Map<String,Object> copyBeanToMap(Object source,Map<String,Object> target) throws Exception {
		//Class对象
		Class<?> sourceClass = source.getClass();
		Field[] sourceClassDeclaredFields = sourceClass.getDeclaredFields();
		
		if (sourceClassDeclaredFields.length == 0) {
			throw new RuntimeException("数据源成员变量为0");
		}
		
		//遍历source的成员变量
		for (Field field : sourceClassDeclaredFields) {
			//获取当前字段的名称
			String fieldName = field.getName();
			
			//生成方法名,并得到getXxx()/setXxx(Type xxx)方法获取值,set到目标中对应字段
			String getMethodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			
			//根据source对象获取方法, 入参:1 方法名 2 形参
			//如果source对象本身不存在该方法，会报NoSuchMethodException，这里就跳过该字段不作处理
			Method getMethod = null;
			try {
				//new Class[]{} 说明：因为get（）方法没有形参
				getMethod = sourceClass.getMethod(getMethodName, new Class[]{});
			} catch (NoSuchMethodException e) {
				continue;
			}
			
			//调用source get方法获取值
			Object result = getMethod.invoke(source, new Object[]{});
			
			target.put(fieldName, result);
		}
		return target;
	}
	public static <T> T copyMapToBean(Map<String,?> source,T target) throws Exception {
		//Class对象
		Class<?> targetClass = target.getClass();
		for (Entry<String, ?> entry : source.entrySet()) {
			String fieldName = entry.getKey();
			Object value = entry.getValue();
			
			//根据source获取target对象是否存在该字段,不存在就跳过
			Field targetClassField = targetClass.getDeclaredField(fieldName);
			if (null == targetClassField) {
				continue;
			}
			//判断字段类型是否一致,类型不一致,跳过
            if (!value.getClass().equals(targetClassField.getType())) {
                continue;
            }
            
            String setMethodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            //获取target对象的set方法, 入参: 1 方法名 2 形参(Type xxx)
			Method setMethod = null;
			try {
				setMethod = targetClass.getMethod(setMethodName, value.getClass());
			} catch (NoSuchMethodException e) {
				continue;
			}
			//调用target set值
			setMethod.invoke(target,value);
		}
		return target;
	}
}

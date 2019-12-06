package core.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import core.proxy.MapperProxy;
import core.proxy.TransactionProxy;
import core.util.FileScanner;

public class BeanFactory {
	private static final String SERVICE = "Service";
	private static final String MAPPER = "Mapper";
	private static Map<String,Object> instances = new HashMap<>();
	
	static{
		try {
			init();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/** 初始化时加载扫描的bean 
	  */
	public static void init() throws InstantiationException, IllegalAccessException{
		FileScanner scanner = new FileScanner();
		Set<Class<?>> mapperSet = scanner.search("", MAPPER);
		Set<Class<?>> serviceSet = scanner.search("", SERVICE);
		MapperProxy mapperProxy = new MapperProxy();
		for (Class<?> clazz : mapperSet) {
			instances.put(clazz.getName(), mapperProxy.bind(clazz));
		}
		for (Class<?> clazz : serviceSet) {
			instances.put(clazz.getName(), new TransactionProxy(clazz.newInstance()).getProxyInstance());
		}
	}
	
	public static <T> T getBean(String name,Class<T> clazz){
		return clazz.cast(instances.get(name));
	}
	public static Object getBean(String name){
		return instances.get(name);
	}
}

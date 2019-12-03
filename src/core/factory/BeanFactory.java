package core.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import core.util.FileScanner;

public class BeanFactory {
	private static final String[] SCAN_BEAN = {"Dao","Service"};
	private static Map<String,Object> instances = new HashMap<>();
	
	/** 初始化时加载扫描的bean */
	public static void init(){
		FileScanner scanner = new FileScanner();
		Set<Class<?>> calssSet = new HashSet<>();
		for (int i = 0; i < SCAN_BEAN.length; i++) {
			calssSet.addAll(scanner.search("", SCAN_BEAN[i]));
		}
		for (Class<?> clazz : calssSet) {
			try {
				instances.put(clazz.getName(), clazz.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static <T> T getBean(String name,Class<T> clazz){
		return clazz.cast(instances.get(name));
	}
	public static Object getBean(String name){
		return instances.get(name);
	}
}

package core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	 public static Properties getSqlProperties(Class<?> clazz){
		 	String path = "/"+clazz.getName().replaceAll("\\.", "/")+".properties";
	        InputStream is = clazz.getResourceAsStream(path);
	        Properties props = new Properties();
	        try {
	            props.load(is);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }catch (NullPointerException e) {
	                throw new RuntimeException(path+" is not fount");
	            }
	        }
	        return props;
	    }
}

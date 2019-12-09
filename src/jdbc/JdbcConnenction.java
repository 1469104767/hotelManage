package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class JdbcConnenction {
	
	public static String driverClassName;
	public static String url;
	public static String username;
	public static String password;
	
	 static {
		 InputStream is = null ;
		 try {
			// 1.通过当前类获取类加载器
	            ClassLoader classLoader = JdbcConnenction.class.getClassLoader();
	         // 2.通过类加载器的方法获得一个输入流
	            is = classLoader.getResourceAsStream("db.properties");
	            // 3.创建一个properties对象
	            Properties properties = new Properties();
	            // 4.加载输入流
	            properties.load(is);
	            
				driverClassName = properties.getProperty("driverClassName");
				url = properties.getProperty("url");
				username = properties.getProperty("username");
				password = properties.getProperty("password");
				
		 }catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	}
}

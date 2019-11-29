package com.niup.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.niup.demo.dao.StudentDao;
import com.niup.demo.dao.TestDao;
import com.niup.demo.dao.UserDao;
import com.niup.demo.entity.Student;
import com.niup.demo.entity.Test;
import com.niup.demo.entity.User;

/**
 * <li>类型名称：JdbcUtil
 * <li>说明：jdbc工具类
 * <li>创建日期：2019年11月14日
 */
public class JdbcUtil {
    private static MyDataSource dataSource = new MyDataSource();
    /** 匹配${} 的正则表达式 */
    private static final String REGEX$ = "\\$\\{.*?\\}";
    /** 匹配#{} 的正则表达式 */
    private static final String REGEX2 = "#\\{.*?\\}";
    /**
     * <li>方法名：selectList
     * <li>@param sql 
     * <li>@param claaz 返回值类型
     * <li>@param parameter 拼在sql的参数
     * <li>@return
     * <li>返回类型：List<T>
     * <li>说明：多条查询
     * <li>创建日期：2019年11月14日
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> selectList(String sql,Class<T> claaz,Object... parameter) {
        Connection connection = null;
        Object obj=null;
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < parameter.length; i++) {
                prepareStatement.setObject(i+1, parameter[i]);
            }
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                obj = claaz.newInstance();
                //利用反射，获取对象类信息中的所有属性
                Field [] fields = claaz.getDeclaredFields();
                for(Field fd:fields){
                       //屏蔽权限
                       fd.setAccessible(true);
                      //为对象属性赋值
                     fd.set(obj,rs.getObject(fd.getName()));
                }
                //返回转换后的集合
                list.add((T)obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            dataSource.addBack(connection);
        }
        return list;
       
    }
    /**
     * <li>方法名：selectOne
     * <li>@param sql
     * <li>@param claaz
     * <li>@param parameter
     * <li>@return
     * <li>返回类型：T
     * <li>说明：单条查询
     * <li>创建日期：2019年11月14日
     */
    @SuppressWarnings("unchecked")
    public static <T> T selectOne(String sql,Class<T> claaz,Object... parameter) {
        Connection connection = null;
        Object obj=null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < parameter.length; i++) {
                prepareStatement.setObject(i+1, parameter[i]);
            }
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                obj = claaz.newInstance();
                //利用反射，获取对象类信息中的所有属性
                Field [] fields = claaz.getDeclaredFields();
                
                for(Field fd:fields){
                    //屏蔽权限
                    fd.setAccessible(true);
                    //为对象属性赋值
                    fd.set(obj,rs.getObject(fd.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            dataSource.addBack(connection);
        }
        return(T)obj;
    }
    /**
     * <li>方法名：update
     * <li>@param sql
     * <li>@param parameter
     * <li>@return
     * <li>返回类型：T
     * <li>说明： 执行更新操作
     * <li>创建日期：2019年11月14日
     */
    public static <T> T update(String sql,Object... parameter) {
        Connection connection = null;
        Object obj=null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < parameter.length; i++) {
                prepareStatement.setObject(i+1, parameter[i]);
            }
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            dataSource.addBack(connection);
        }
        return(T)obj;
    }
    /**
     * <li>方法名：getSqlProperties
     * <li>@param clazz
     * <li>@return
     * <li>返回类型：Properties
     * <li>说明：获取sql的配置对象
     * <li>创建日期：2019年11月14日
     */
    public static Properties getSqlProperties(Class clazz){
        // 1.通过当前类获取类加载器
        ClassLoader classLoader =  clazz.getClassLoader();
        // 2.通过类加载器的方法获得一个输入流
        InputStream is = clazz.getResourceAsStream("/"+clazz.getName().replaceAll("entity", "sql").replaceAll("\\.", "/")+"Sql.properties");
        // 3.创建一个properties对象
        Properties props = new Properties();
        // 4.加载输入流
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }
    /**
     * <li>方法名：getSql
     * <li>@param clazz
     * <li>@param sqlName
     * <li>@return
     * <li>返回类型：String
     * <li>说明：通过类和sql名称获取sql
     * <li>创建日期：2019年11月14日
     */
    public static String getSql(Class clazz,String sqlName){
        return getSqlProperties(clazz).getProperty(sqlName);
    }
    public static void main(String[] args) throws SQLException {
    	StudentDao instance = StudentDao.getInstance();
    	instance.save("liuming",48);
    	instance.save("niupi",48);
    	List<Student> studentByAge = instance.studentByAge(48);
    	System.out.println("插入成功");
    	for (Student student : studentByAge) {
			System.out.println(student.getName());
		}
    	
    }
}

package core.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basics.dao.TestDao;
import core.util.FieldUtil;

/**
 * <li>类型名称：JdbcUtil
 * <li>说明：jdbc工具类
 * <li>创建日期：2019年11月14日
 */
public class JdbcUtil {
    private static DataSourcePool dataSource = DataSourcePool.getInstance();
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
        PreparedStatement prepareStatement = null;
		ResultSet rs = null;
        Object obj=null;
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < parameter.length; i++) {
                prepareStatement.setObject(i+1, parameter[i]);
            }
            rs = prepareStatement.executeQuery();
            //获取所有列的列名
            ResultSetMetaData rsmd = rs.getMetaData();  
            int colCount=rsmd.getColumnCount();
            List<String> colNameList=new ArrayList<String>();
            for(int i=0;i<colCount;i++){
            	colNameList.add(rsmd.getColumnName(i+1));
            } 
            
            while (rs.next()) {
                obj = claaz.newInstance();
           	 	for (String colName : colNameList) {
           	 		Object filed = rs.getObject(colName);
	           		Method setMethod = null;
	           		try {
	           			setMethod = claaz.getMethod(FieldUtil.colNameToSetMethod(colName),claaz.getDeclaredField(colName).getType());
					} catch (Exception e) {
					}
	           		if(setMethod!=null){
	           			setMethod.invoke(obj,filed);
	           		}
				}
           	 	list.add((T)obj);
               /* //利用反射，获取对象类所有方法
                Method[] methods = claaz.getMethods();
                for (Method method : methods) {
                	//如果是set方法,就set对应的属性,跳过Object的方法
					if(method.getDeclaringClass()!=Object.class&&method.getName().startsWith("set")){
						method.invoke(obj, rs.getObject(method.getName().substring(3).toLowerCase()));
					}
				}*/
                //返回转换后的集合
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			CloseResources(connection, prepareStatement, rs);
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
        PreparedStatement prepareStatement = null;
        ResultSet rs =null;
        Object obj=null;
        try {
            connection = dataSource.getConnection();
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < parameter.length; i++) {
                prepareStatement.setObject(i+1, parameter[i]);
            }
            rs = prepareStatement.executeQuery();
            //获取所有列的列名
            ResultSetMetaData rsmd = rs.getMetaData();  
            int colCount=rsmd.getColumnCount();
            List<String> colNameList=new ArrayList<String>();
            for(int i=0;i<colCount;i++){
            	colNameList.add(rsmd.getColumnName(i+1));
            } 
            
            while (rs.next()) {
            	 obj = claaz.newInstance();
            	 for (String colName : colNameList) {
            		 Method setMethod = claaz.getMethod(FieldUtil.colNameToSetMethod(colName),claaz.getDeclaredField(colName).getType());
            		 if(setMethod!=null){
            			 setMethod.invoke(obj,rs.getObject(colName));
            		 }
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	CloseResources(connection, prepareStatement, rs);
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
     * @throws Exception 
     */
    public static <T> T update(String sql,Object... parameter) throws Exception {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        Object obj=null;
        try {
            connection = dataSource.getConnection();
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < parameter.length; i++) {
                prepareStatement.setObject(i+1, parameter[i]);
            }
            prepareStatement.executeUpdate();
        } catch (Exception e) {
        	throw e;
        }finally{
        	CloseResources(connection, prepareStatement, null);
        }
        return(T)obj;
    }
    private static void CloseResources(Connection connection,PreparedStatement prepareStatement,ResultSet rs){
    	//如果不是空且没有没有开启事务自动提交,才归还链接
    	try {
			if(connection!=null&&connection.getAutoCommit()){
				dataSource.addBack(connection);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	if(prepareStatement!=null){
    		try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
}

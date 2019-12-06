package core.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import basics.mapper.TestMapper;
import core.util.FieldUtil;

/**
 * <li>类型名称：JdbcUtil
 * <li>说明：jdbc工具类
 * <li>创建日期：2019年11月14日
 */
public class JdbcUtil {
    private static DataSourcePool dataSource = DataSourcePool.getInstance();
    /** 匹配${} 的正则表达式 */
	private static final Pattern REGEX$ = Pattern.compile("\\$\\{.*?\\}");
    /** 匹配#{} 的正则表达式 */
    private static final Pattern REGEX4 = Pattern.compile("#\\{.*?\\}");
    /** 单一参数的名字 */
    public static final String PARAM_SINGLE_NAME = "SINGLE";
    
	/** 替换sql中的 ${} 和 #{},设置好参数之后返回PreparedStatement对象
	 * @param sql 
	 * @param paramMap
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement buildPrepareStatement(String sql,Map<String,Object> paramMap,Connection connection) throws SQLException{
		//替换${}的参数
		Matcher matcher = REGEX$.matcher(sql);
		StringBuffer sqlBuffer = new StringBuffer();
		while(matcher.find()){
			String field = matcher.group();
			field = field.substring(2,field.length()-1);
			if(paramMap.entrySet().size()==1){
				matcher.appendReplacement(sqlBuffer, (String) paramMap.get(PARAM_SINGLE_NAME));
				break;
			}
			matcher.appendReplacement(sqlBuffer, paramMap.get(field)==null?"":String.valueOf(paramMap.get(field)));
		}
		matcher.appendTail(sqlBuffer);
		
		//$把#{}替换成?,后顺序插入
		matcher = REGEX4.matcher(sqlBuffer.toString());
		sqlBuffer.setLength(0);
		List<Object> params = new ArrayList<>(); 
		while(matcher.find()){
			String field = matcher.group();
			field = field.substring(2,field.length()-1);
			matcher.appendReplacement(sqlBuffer, "?");
			if(paramMap.entrySet().size()==1){
				params.add(paramMap.get(PARAM_SINGLE_NAME));
				break;
			}
		}
		matcher.appendTail(sqlBuffer);
		PreparedStatement prepareStatement = connection.prepareStatement(sqlBuffer.toString());
		for (int i = 0; i < params.size(); i++) {
			prepareStatement.setObject(i+1, params.get(i));
		}
		return prepareStatement;
	}
    
    /**
     * <li>方法名：selectList
     * <li>@param sql 
     * <li>@param claaz 返回值类型
     * <li>@param parameter 拼在sql的参数
     * <li>@return
     * <li>返回类型：List<T>
     * <li>说明：多条查询
     * <li>创建日期：2019年11月14日
     * @throws SQLException 
     * @throws ReflectiveOperationException 
     */
    @SuppressWarnings({ "unchecked"})
    public static <T> List<T> selectList(String sql,Class<T> claaz,Map<String,Object> paramMap) throws SQLException, ReflectiveOperationException {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
		ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            
            //动态创建prepareStatement并设置参数
            prepareStatement = buildPrepareStatement(sql, paramMap, connection);
            rs = prepareStatement.executeQuery();
            
            //获取所有列的列名
            ResultSetMetaData rsmd = rs.getMetaData();  
            int colCount=rsmd.getColumnCount();
            List<String> colNameList=new ArrayList<String>();
            for(int i=0;i<colCount;i++){
            	colNameList.add(rsmd.getColumnName(i+1));
            } 
            
            while (rs.next()) {
            	//根据Map,基本类型和String, 实体类 分别设置值 
       	 		if(Map.class.isAssignableFrom(claaz)){
       	 			Map<String,Object> result = new HashMap<>();
       	 			for (String colName : colNameList) {
       	 				result.put(colName, rs.getObject(colName));
       	 			}
       	 			list.add((T) result);
       	 		}else if(claaz.getClassLoader()==null){
       	 			if(colNameList.size()>1){
       	 				throw new SQLException("there is more field but return is single!");
       	 			}
	       	 		for (String colName : colNameList) {
	       	 			list.add((T) rs.getObject(colName));
	       	 		}
       	 		}else{
       	 			T obj = claaz.newInstance();
       	 			for (String colName : colNameList) {
       	 				Object filed = rs.getObject(colName);
       	 				Method setMethod = null;
       	 				//根据列名获取set方法,如果获取不到报错就不执行
       	 				try {
       	 				setMethod = claaz.getMethod(FieldUtil.colNameToSetMethod(colName),claaz.getDeclaredField(FieldUtil.lineToHump(colName)).getType());
       	 				} catch (Exception e) {
       	 					continue;
       	 				}
       	 				setMethod.invoke(obj,filed);
       	 			}
       	 			list.add(obj);
       	 		}
            }
        } finally{
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
     * @throws ReflectiveOperationException 
     * @throws SQLException 
     */
    public static <T> T selectOne(String sql,Class<T> claaz,Map<String,Object> paramMap) throws SQLException, ReflectiveOperationException {
    	List<T> selectList = selectList(sql, claaz, paramMap);
    	if(selectList.size()==0){
    		return null;
    	}
    	if(selectList.size()>1){
    		throw new SQLException("required one but multiple are returned!");
    	}
        return selectList.get(0);
    }
    /**
     * <li>方法名：update
     * <li>@param sql
     * <li>@param parameter
     * <li>@return
     * <li>返回类型：T
     * <li>说明： 执行更新操作
     * <li>创建日期：2019年11月14日
     * @throws SQLException 
     */
    public static int update(String sql,Map<String,Object> paramMap) throws SQLException  {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        int count = 0;
        try {
            connection = dataSource.getConnection();
            //动态创建prepareStatement并设置参数
            prepareStatement = buildPrepareStatement(sql, paramMap, connection);
            count = prepareStatement.executeUpdate();
        }finally{
        	CloseResources(connection, prepareStatement, null);
        }
        return count;
    }
    
    /**
     * 关闭资源,归还链接
     * */
    private static void CloseResources(Connection connection,PreparedStatement prepareStatement,ResultSet rs) throws SQLException{
    	if(rs!=null){
    		try {
    			rs.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	if(prepareStatement!=null){
    		try {
    			prepareStatement.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	//如果不是空且没有没有开启事务自动提交,才归还链接
		if(connection!=null&&connection.getAutoCommit()){
			dataSource.addBack(connection);
		}
    }
}

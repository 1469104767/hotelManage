package example.dao.client;
import example.bean.client.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientJdbcDao {

	private PreparedStatement ptmt = null;
	private ResultSet rs = null;
 
	public ClientJdbcDao() {
	}
	
	
	public void findAll(Connection conn) throws SQLException
	{
		//to do
		
	}
	
	public void delete(Connection conn, String clientName) throws SQLException
	{
		String sql = "delete from client where clientName=?";
		try{
			ptmt = conn.prepareStatement(sql);
			// 对SQL语句中的第一个占位符赋值
			ptmt.setString(1, clientName);
			// 执行更新操作
			ptmt.executeUpdate();
			
		}finally{
			if (null!=ptmt) {
				ptmt.close();
			}
			
			if (null!=conn) {
				conn.close();
			}
			
		}
		
	}
	
	public void update(Connection conn, String clientName) throws SQLException
	{
		//to do
		
	}
	

}

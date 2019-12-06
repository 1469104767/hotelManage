package core.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import core.jdbc.JdbcConnection;

public class DataSourcePool implements DataSource {
	/** 连接池 */
    private static LinkedList<Connection> pool=new LinkedList<Connection>();
    /** 实例  */
    private static DataSourcePool instance = new DataSourcePool();
    /** 用于事务管理时,一个线程共用一个连接 */
    private static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();
    static{
    	for (int i = 0; i < 5; i++) {
    		Connection conn=JdbcConnection.getConnection();
    		if(conn!=null)
    			pool.add(conn);
    	}
    }
    
    private DataSourcePool(){
    	
    }
    
    
    public static DataSourcePool getInstance() {
		return instance;
	}

    @Override
    public Connection getConnection() throws SQLException {
    	return getConnection(false);
    }
    public Connection getConnection(boolean isThreadConnection) throws SQLException {
    	Connection conn = threadConnection.get();
    	//优先使用线程绑定的连接
    	if(conn != null){
    		return threadConnection.get();
    	}
    	if (pool.size()==0) {
    		for (int i = 0; i < 5; i++) {
    			conn=JdbcConnection.getConnection();
    			pool.add(conn);
    		}
    	}
    	conn=pool.remove(0);
    	//如果是线程共享的链接就放进去
    	if(isThreadConnection){
    		threadConnection.set(conn);
    	}
    	return conn;
    }
    
    public void addBack(Connection conn,boolean isThreadConnection) throws SQLException{
    	if(isThreadConnection==true){
    		conn.setAutoCommit(true);
    	}
    	pool.add(conn);
    }
    public void addBack(Connection conn) throws SQLException{
    	addBack(conn, false);;
    }
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        
        
        return null;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public Connection getConnection(String arg0, String arg1)
            throws SQLException {
        
        return null;
    }

}
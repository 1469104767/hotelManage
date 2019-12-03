package example.servlet.client;
import java.sql.*;
import java.util.*;


import example.bean.client.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// 加载数据库驱动，注册到驱动管理器
			Class.forName("com.mysql.jdbc.Driver");
			// 数据库连接字符串
			String url = "jdbc:mysql://localhost:3306/eims?useUnicode=true&characterEncoding=utf-8";
			// 数据库用户名
			String username = "root";
			// 数据库密码
			String password = "root";
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url, username,
					password);
			// 添加信息的SQL语句
			String sql = "select * from client";
			// 获取Statement
			Statement statement = conn.createStatement();
 
			ResultSet resultSet = statement.executeQuery(sql);
 
			List<client> list = new ArrayList<client>();
			while (resultSet.next()) {
 
				client clients = new client();
				
				clients.setClientName(resultSet.getString("clientName"));
				clients.setClientTelephone(resultSet.getString("clientTelephone"));
				clients.setClientAddress(resultSet.getString("clientAddress"));
				clients.setClientEmail(resultSet.getString("clientEmail"));
				list.add(clients);
 
			}
			request.setAttribute("list", list);
			resultSet.close();
			statement.close();
			conn.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		request.getRequestDispatcher("client_list.jsp")
				.forward(request, response);
 
	}


	

}

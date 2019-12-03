package example.servlet.client;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import example.dao.*
;
import example.dao.client.ClientJdbcDao;
import example.dao.client.ConnectionFactory;/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		String clientName = request.getParameter("clientName");
		try {
			ClientJdbcDao clientDao=new ClientJdbcDao();
			Connection conn=ConnectionFactory.getInstance().getConnection();
			clientDao.delete(conn,clientName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 重定向到FindServlet
		response.sendRedirect("lookClient.jsp");
	}

}

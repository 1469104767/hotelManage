package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class UpdateClientType
 */
@WebServlet("/UpdateClientType")
public class UpdateClientType extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateClientType() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("clientType"));
		UserDao userdao = new UserDao();
		int status =userdao.updateClientType(id, type);
		if(status == 0) {
			response.sendRedirect("/hotelManage/html/userlist.jsp");
		}
	}


}

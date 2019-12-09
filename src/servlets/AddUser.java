package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.User;
import dao.UserDao;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setClientName(request.getParameter("clientName"));
		user.setClientPassword(request.getParameter("clientPassword"));
		user.setClientTel(request.getParameter("clientTel"));
		user.setClientType(Integer.parseInt(request.getParameter("clientType")));
		user.toString();
		UserDao userdao = new UserDao();
		userdao.addUser(user);
		PrintWriter writer = response.getWriter();
		writer.write(0);
		writer.close();
	}

}

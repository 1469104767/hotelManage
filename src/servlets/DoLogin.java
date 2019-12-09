package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import base.User;
import dao.UserDao;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setClientName(request.getParameter("clientName"));
		user.setClientPassword(request.getParameter("clientPassword"));
		UserDao userdao = new UserDao();
		List<User> list = userdao.dologin(user);
//		HttpSession session = request.getSession();
		
		if(null == list || list.isEmpty() ){
//			session.setAttribute("clientName", null);
			PrintWriter out = response.getWriter();
			//out = response.getWriter();
			out.println("用户名或密码错误");
			out.close();
		}else{
			PrintWriter out = response.getWriter();
			//out = response.getWriter();
			out.println(list.get(0));
			out.close();
	        //将数据存储到session中
//	        session.setAttribute("clientName", list.get(0).getClientName());
		}
		
	}

}

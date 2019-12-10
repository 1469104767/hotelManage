package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.Order;
import base.Room;

@WebServlet("/EnterRoom")
public class EnterRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EnterRoom() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Room room = new Room();
		Order order = new Order();
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("clientType");
	}

}

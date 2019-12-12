package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoomDao;

@WebServlet("/OutRoom")
public class OutRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OutRoom() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		RoomDao roomdao =  new RoomDao();
		roomdao.OutRoom(id);
	}

}

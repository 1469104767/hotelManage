package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.Room;
import dao.RoomDao;

/**
 * Servlet implementation class FindRoomById
 */
@WebServlet("/FindRoomById")
public class FindRoomById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindRoomById() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		RoomDao roomdao = new RoomDao();
		System.out.println(id);
		List<Room> roomlist = roomdao.findRoomById(id);
		System.out.println(roomlist);
		PrintWriter out = response.getWriter();
		out.println(roomlist.get(0));
		out.close();
	}

}

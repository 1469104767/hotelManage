package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.Room;
import dao.RoomDao;


@WebServlet("/AddRoom")
public class AddRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddRoom() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoomDao roomdao = new RoomDao();
		Room room = new Room();
		room.setRoomName(request.getParameter("roomName"));
		room.setRoomNum(Integer.parseInt(request.getParameter("roomNum")));
		room.setRoomType(Integer.parseInt(request.getParameter("roomType")));
		room.setRoomPrice(Double.parseDouble(request.getParameter("roomPrice")));
		room.setRoomUrl("/hotelManage/imgs/lalala.jpg");
		roomdao.addRoom(room);
	}

}

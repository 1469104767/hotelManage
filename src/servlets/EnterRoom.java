package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.Order;
import base.Room;
import dao.OrderDao;
import dao.RoomDao;

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
		String innerPeople = request.getParameter("innerPeople");
		String idCard = request.getParameter("idcard");
		String innerTel = request.getParameter("innerTel");
//		try {
//			Date startTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startTime"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		try {
//			Date endTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endTime"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int roomNum = Integer.parseInt(request.getParameter("roomNum"));
		int roomType = Integer.parseInt(request.getParameter("roomType"));
		Double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
		String operator =  request.getParameter("operator");
		order.setInnerPeople(innerPeople);
		order.setIdCard(idCard);
		order.setInnerTel(innerTel);
		order.setRoomNum(roomNum);
		order.setRoomType(roomType);
		order.setRoomPrice(roomPrice);
		order.setStartTime(Date.valueOf(startTime));
		order.setEndTime(Date.valueOf(endTime));
		order.setOperator(operator);
		
		room.setId(id);
		room.setInnerPeople(innerPeople);
		room.setIdCard(idCard);
		room.setInnerTel(innerTel);
		room.setStartTime(Date.valueOf(startTime));
		room.setEndTime(Date.valueOf(endTime));
		
		OrderDao orderdao = new OrderDao();
		RoomDao roomdao = new RoomDao();
		
		roomdao.UpdateRoom(room);
		orderdao.addOrder(order);
	}

}

package basics.action;

import java.util.List;
import java.util.Map;

import core.web.Message;

import basics.entity.Room;
import basics.service.RoomService;
import core.base.BaseAction;

public class RoomAction extends BaseAction<Room,RoomService>{
	
	public Map<String,String> test(Map<String,String> parMap) throws Exception{
		service.test("");
		return parMap;
	}
	public List<Room> getRoomList(){
		return service.findList("");
	}
	public Message addRoom(String clientName,String clientpassWord,String clientTel,Integer clientType) {
		Room Room = new Room();
		return service.save(Room);

	}
}

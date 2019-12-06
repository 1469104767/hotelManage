package basics.service;

import basics.entity.Room;
import basics.mapper.RoomMapper;
import core.annotation.Transaction;
import core.base.BaseService;


public class RoomService extends BaseService<Room,RoomMapper> {
	@Transaction
	public void test(String text) throws Exception{
		System.out.println(text);
	}
	
}

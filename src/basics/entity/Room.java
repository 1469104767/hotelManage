package basics.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Room {
	private Integer id;
	private Integer roomNum;
	private String roomName;
	private Double roomPrice;
	private Integer roomStatus;
	private String innerPeople;
	private String innerTel;
	private String idCard;
	@JSONField(format="yyyy-MM-dd")
	private Date startTime;
	@JSONField(format="yyyy-MM-dd")
	private Date endTime;
	
	
}

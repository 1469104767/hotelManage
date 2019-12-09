package basics.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Room {
	private Integer id;
	private Integer roomNum;
	private String roomName;
	private Double roomPrice;
	private Integer roomType;
	private Integer roomStatus;
	private String roomUrl;
	private String innerPeople;
	private String innerTel;
	private String idCard;
	@JSONField(format="yyyy-MM-dd")
	private Date startTime;
	@JSONField(format="yyyy-MM-dd")
	private Date endTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getRoomType() {
		return roomType;
	}
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	public Integer getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getRoomUrl() {
		return roomUrl;
	}
	public void setRoomUrl(String roomUrl) {
		this.roomUrl = roomUrl;
	}
	public String getInnerPeople() {
		return innerPeople;
	}
	public void setInnerPeople(String innerPeople) {
		this.innerPeople = innerPeople;
	}
	public String getInnerTel() {
		return innerTel;
	}
	public void setInnerTel(String innerTel) {
		this.innerTel = innerTel;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}

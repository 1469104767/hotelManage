package base;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Order {

	private int id;
	private String innerPeople;
	private String innerTel;
	private String idCard;
	private int roomNum;
	private int roomType;
	private Double roomPrice;
	
	@JSONField(format="yyyy-MM-dd")
	private Date startTime;
	@JSONField(format="yyyy-MM-dd")
	private Date endTime;
	private String operator;
	public int getId() {
		return id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getRoomType() {
		return roomType;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	public Double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
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
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"innerPeople\":\"" + innerPeople + "\",\"innerTel\":\"" + innerTel
				+ "\",\"idCard\":\"" + idCard + "\",\"roomNum\":\"" + roomNum + "\",\"roomType\":\"" + roomType
				+ "\",\"roomPrice\":\"" + roomPrice + "\",\"startTime\":\"" + startTime + "\",\"endTime\":\"" + endTime
				+ "\",\"operator\":\"" + operator + "\"}  ";
	}
	
	
}

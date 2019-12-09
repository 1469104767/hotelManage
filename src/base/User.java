package base;

public class User {
	private Integer id;
	
	private String clientName;
	private String ClientPassword;
	private String clientTel;
	private Integer clientType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientPassword() {
		return ClientPassword;
	}
	public void setClientPassword(String ClientPassword) {
		this.ClientPassword = ClientPassword;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"clientName\":\"" + clientName + "\",\"ClientPassword\":\"" + ClientPassword
				+ "\",\"clientTel\":\"" + clientTel + "\",\"clientType\":\"" + clientType + "\"}  ";
	}
}

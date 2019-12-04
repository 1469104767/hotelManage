package basics.entity;

public class User {
	private Integer id;
	
	private String clientName;
	private String clientpassWord;
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
	public String getClientpassWord() {
		return clientpassWord;
	}
	public void setClientpassWord(String clientpassWord) {
		this.clientpassWord = clientpassWord;
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
}

package core.web;

public class Message {
	public static final String MESSAGE_SUCCESS = "操作成功！";
	public static final String MESSAGE_ERROR = "操作失败！";
	private Boolean success;
	private String message;
	private Object data;
	
	public Message() {
		super();
	}

	public Message(Boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public static Message success(){
		return new Message(true, MESSAGE_SUCCESS, null);
	}
	public static Message error(){
		return new Message(true, MESSAGE_ERROR, null);
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}

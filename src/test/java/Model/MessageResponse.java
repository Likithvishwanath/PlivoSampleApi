package Model;

import java.util.List;

public class MessageResponse {
	
	public String message;
	
	public List<String> message_uuid;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessage_uuid() {
		return message_uuid;
	}

	public void setMessage_uuid(List<String> message_uuid) {
		this.message_uuid = message_uuid;
	}

	public String getApi_id() {
		return api_id;
	}

	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}

	public String api_id;

}
package Model;


import java.util.HashMap;
import java.util.List;

public class MessageRequest {
	
	
	public String src;
	
	public List<String> dst;
	
	public String text;
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public List<String> getDst() {
		return dst;
	}

	public void setDst(List<String> dst) {
		this.dst = dst;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HashMap<String, String> createMessageBody(String src, List<String> dst, String  text){
		
		
		StringBuilder builder = new StringBuilder();

		for(String destination: dst) {
		   builder.append(destination).append('<');
		}

		builder.deleteCharAt(builder.length()-1); 

		String destinations = builder.toString();
		 
		HashMap<String, String> request = new HashMap<String, String>();
		request.put("src", src);
		request.put("dst", destinations);
		request.put("text", text);
		
		return request;
		
	}

}
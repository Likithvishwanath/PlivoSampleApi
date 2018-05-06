package RequestHandler;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import Model.AccountResponse;
import Model.RetrieveMessageResponse;
import Utils.ConfigLoader;
import io.restassured.response.Response;

public class MessageRequestHandler {
	
	public AccountResponse getAccountDetails(){
		
		return given().auth().basic(ConfigLoader.auth_id, ConfigLoader.auth_token)
		.pathParam("auth_id", ConfigLoader.auth_id).get(ConfigLoader.accountInfo).as(AccountResponse.class);
	
	}
	
	
	public Response sendMessage(HashMap<String, String> requestBody){
		
		return given().contentType("application/json").pathParam("auth_id", ConfigLoader.auth_id).auth()
		.basic(ConfigLoader.auth_id, ConfigLoader.auth_token)
		.with().body(requestBody).post(ConfigLoader.sendMessage);
		
	}
	
	public RetrieveMessageResponse retrieveMessage(String messageUid){
		
		return given().auth().basic(ConfigLoader.auth_id, ConfigLoader.auth_token).pathParam("auth_id", ConfigLoader.auth_id)
		.pathParam("message_uuid", messageUid).when()
		.get(ConfigLoader.retrieveMessage).as(RetrieveMessageResponse.class);
		
	}

}
package Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.AccountResponse;
import Model.MessageRequest;
import Model.MessageResponse;
import Model.RetrieveMessageResponse;
import RequestHandler.MessageRequestHandler;
import Validator.SendMessageValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class SendMessageSteps {
	
	HashMap<String, Object> scenarioContext = new HashMap<String, Object>();
	SendMessageValidator validator = new SendMessageValidator();
	MessageRequestHandler requestHandler = new MessageRequestHandler();
	
	
	@Given("^I have to send a text message \"([^\"]*)\" from \"([^\"]*)\" to \"([^\"]*)\"$") 
	public void formText(String text, String src, String dest){
	    MessageRequest requestMessage = new MessageRequest();
	    
	    List<String> destinations = new ArrayList<String>();
	    destinations.add(dest);
	    
	    HashMap<String,String> requestBody = requestMessage.createMessageBody(src, destinations, text);
	    
	    scenarioContext.put("requestBody", requestBody);
	    
	}
	
	@Given("^have balance in my account$")
	public void checkAccountBalance(){
		
		AccountResponse initialAccountDetails = requestHandler.getAccountDetails();
	
		scenarioContext.put("InitialAccountDetails", initialAccountDetails);
		
	}

	@When("^I send the message$")
	public void sendText(){
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> requestBody = (HashMap<String, String>) scenarioContext.get("requestBody");
		
		Response response = requestHandler.sendMessage(requestBody);
		
		int statusCode = response.getStatusCode();
		
		MessageResponse sendMessageResponse = response.as(MessageResponse.class);
		
		scenarioContext.put("sendMessageResponseBody", sendMessageResponse);
		scenarioContext.put("sendMessageResponseStatus", statusCode);
		
	}

	@Then("^I have to receive a response code as \"([^\"]*)\"$")
	public void checkResponse(String response){
		
		int statusCode = ((int)scenarioContext.get("sendMessageResponseStatus"));
		
		validator.validateSuccessResponseCode(Integer.parseInt(response), statusCode);
	}
	
	@Then("^message state as \"([^\"]*)\"$")
	public void checkResponseMessage(String message){
		
		MessageResponse messageResponse = ((MessageResponse)scenarioContext.get("sendMessageResponseBody"));
		
		String messageUid = messageResponse.getMessage_uuid().get(0).toString();
		
		RetrieveMessageResponse retrieveMessageResponse = requestHandler.retrieveMessage(messageUid);
		
		String messageCost = retrieveMessageResponse.getTotal_amount();
		
		scenarioContext.put("messageCost", messageCost);
		
		validator.validateMessageResponseBody(message, retrieveMessageResponse.getMessage_state());
	}

	@Then("^validate the balance$")
	public void validateAccountPostMessage()
	{
		AccountResponse postMessageAccountDetails = requestHandler.getAccountDetails();
		
		float initialCredit = Float.parseFloat(((AccountResponse)scenarioContext.get("InitialAccountDetails")).getCash_credits());
		float messageCost = Float.parseFloat((String)scenarioContext.get("messageCost"));
		float postMessageCredit = Float.parseFloat(postMessageAccountDetails.getCash_credits());
		
		validator.validateAccountCredits(initialCredit, messageCost, postMessageCredit);
		
	}
}
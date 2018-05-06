package Validator;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;


public class SendMessageValidator {
	
	
	public void validateSuccessResponseCode(int expected, int responseCode){
		
		assertEquals(expected, responseCode);
		
	}
	
	public void validateMessageResponseBody(String expectedMessage, String actualMessage){
		assertEquals(expectedMessage, actualMessage);
	}
	
	public void validateAccountCredits(float initialCredit,float messageCost, float laterCredit){
		
		float expectedCredit = initialCredit - messageCost;
		
		Assert.assertEquals(expectedCredit, laterCredit, 0);
	}

}
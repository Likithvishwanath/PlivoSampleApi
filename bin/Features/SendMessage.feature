Feature: Send Text Message
	
Scenario: Send Text Message
Given I have to send a text message "Hello" from "13252080157" to "13252080160"
And have balance in my account
When I send the message
Then I have to receive a response code as "202"
And message state as "sent"
And validate the balance
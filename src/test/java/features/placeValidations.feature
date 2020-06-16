Feature: Validating Place API's 

@AddPlace @Regression
Scenario Outline: 
	Verifying if Place is being Successfuly added using AddPlaceAPI 
	Given Add Place Payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlaceAPI" with "POST" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify Place_id created maps to "<name>" using "getPlaceAPI" 
	
	Examples: 
		|name    |language| address     |
		|Mosbah  |Arabic  |Concord      |
	#	|Esra    |English |San Ramon    |
	#	|Adam    |Spanish |San Francisco|
	#	|Mera    |French  |Oakland      |
	#	|Mohammad|German  |Berkeley     |
	#	|Yaseen  |Japanees|Orinda       |
	#	|Yaseen  |Japanees|Orinda       |

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working 
	Given DeletePlace Payload 
	When user calls "deletePlaceAPI" with "POST" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK"
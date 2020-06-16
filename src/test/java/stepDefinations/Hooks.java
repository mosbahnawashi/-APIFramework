package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		if (StepDefination.place_id == null) {
			StepDefination stepDefination = new StepDefination();
			stepDefination.add_Place_Payload_with("name", "language", "address");
			stepDefination.user_calls_with_http_request("AddPlaceAPI", "POST");
			stepDefination.verify_Place_id_created_maps_to_using("name", "getPlaceAPI");
		}
	}
}

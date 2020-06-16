package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address) {
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setName(name);
		place.setLanguage(language);
		place.setAddress(address);
		place.setPhone_number("(415) 860-4897");
		place.setWebsite("https://www.jordanfirst.com");

		List<String> myList = new ArrayList<String>();
		myList.add("Contra Costa Community Park");
		myList.add("Concord Community Park");
		place.setTypes(myList);

		Location location = new Location();
		location.setLat(-38.38777);
		location.setLng(-33.44111);
		place.setLocation(location);
		return place;
	}

	public String deletePlacePayload(String placeId) {
		return "{\"place_id\": \"" + placeId + "\"}";
	}

}

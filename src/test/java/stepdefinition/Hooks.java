package stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
@Before("@DeletePlaceAPI")
public void beforeScenario() throws IOException
{
	//execute this when place id is null
	//write a code to fetch place id
	StepDefinition s=new StepDefinition();
	if(s.place_id==null)
	{
	s.add_Place_PayLoad("niks","Bhojpuri","bihar india");
	s.user_calls_with_Http_request("AddPlaceAPI","post");
	s.verify_place_Id_created_maps_to_using("niks","GetPlaceAPI");
	}
}
}

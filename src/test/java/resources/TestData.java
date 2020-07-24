package resources;

import java.util.ArrayList;

import pojo.GoogleAPIPojo;
import pojo.Gorestinput;
import pojo.LocationPOJO;

public class TestData {
public GoogleAPIPojo AddPlacePayload(String name,String language,String address)
{
	LocationPOJO loc=new LocationPOJO();
	GoogleAPIPojo gap=new GoogleAPIPojo();
	ArrayList<String> types =new ArrayList<String>();
	loc.setLat(-38.383494);
	loc.setLng(45);
	gap.setAccuracy(50);
	gap.setAddress(address);
	gap.setLanguage(language);
	gap.setLocation(loc);
	gap.setName(name);
	gap.setPhone_number("(+91) 983 893 3937");
	gap.setWebsite("https://rahulshettyacademy.com");
	types.add("shoe park");
	types.add("shop");
	types.add("don");
	gap.setTypes(types);
	return gap;
}
public String DeletePlacePayload(String place_id)
{
	return "{\r\n" + 
			"    \"place_id\":\""+place_id+"\"\r\n" + 
			"}";
}
public Gorestinput gorestpayload(String email,String first_name,String gender,String last_name,String status)
{
	Gorestinput go=new Gorestinput();
	go.setEmail(email);
	go.setFirst_name(first_name);
	go.setGender(gender);
	go.setLast_name(last_name);
	go.setStatus(status);
	return go;
}
}

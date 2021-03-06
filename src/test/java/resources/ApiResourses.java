package resources;

public enum ApiResourses {

	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json");
	private String resourse;
	ApiResourses(String resourse)//resourse is used to find methods
	{
		this.resourse=resourse;
	}
	public String getResource() {
		return resourse;
	}
}

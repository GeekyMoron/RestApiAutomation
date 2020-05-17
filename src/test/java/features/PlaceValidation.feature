Feature: Validating Place Api
@AddPlaceAPI @Regression
Scenario Outline:Verify that if Place is succesfully added using AddPlace API
Given Add Place PayLoad "<name>" "<language>" "<address>"  
When  User calls "AddPlaceAPI" with "Post" Http request
Then  The API call gets success with status code 200
And   "status" in response body is "OK"
And   Verify place_Id created maps to "<name>" using "GetPlaceAPI"
Examples:
|name        | language    | address|
|Nikhil Tiwari|Hindi/English| 29, side layout, cohen 09|
# |Nikhil Boss|Hindi|Gurgaon India|
@DeletePlaceAPI @Regression
Scenario: Verify that delete place api working properly
Given DeletePlace payload             
When  User calls "DeletePlaceAPI" with "Post" Http request
Then  The API call gets success with status code 200
And   "status" in response body is "OK"
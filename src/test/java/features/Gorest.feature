Feature: validating Gorest Api(https://gorest.co.in/)
@GoRestRegression
Scenario Outline:Validate Add User Functionality
Given Add user payload with "<email>" "<first_name>" "<gender>" "<last_name>" "<status>"
When  User calls "AddUserAPI" with "Post" Http request
Then  The API call gets success with status code "<statuscode>"
And   "_meta.success" in response body is "<success>"
And   User id is present in response
Examples:
|email|first_name|gender|last_name|status|success|statuscode|
|nik5@gmail.com|Nikhil|male|Tiwari|active|true|302|
@GoRestRegression
Scenario Outline:Validate Update User Functionality
Given "Update" user payload with "<email>" "<first_name>" "<gender>" "<last_name>" "<status>" and userid
When  User calls "UpdateUserAPI" with "Put" Http request with user id as path parameter
Then  The API call gets success with status code "<statuscode>"
And   "result.first_name" in response body is "<first_name>"
And   User id is present in response
Examples:
|email|first_name|gender|last_name|status|statuscode|
|nik5@gmail.com|nikhil|male|Tiwari|active|200|
@GoRestRegression
Scenario Outline: Validate Fetch User Functionality
Given "Get" user payload with "<email>" "<first_name>" "<gender>" "<last_name>" "<status>" and userid
When  User calls "FetchUserAPI" with "Get" Http request with user id as path parameter
Then  The API call gets success with status code "<statuscode>"
And   "_meta.success" in response body is "<success>"
And   User id is present in response
Examples:
|email|first_name|gender|last_name|status|statuscode|success|
|nik5@gmail.com|nikhil|male|Tiwari|active|200|true|
@GoRestRegression
Scenario Outline: Validate Delete User Functionality
Given "Delete" user payload with "<email>" "<first_name>" "<gender>" "<last_name>" "<status>" and userid
When  User calls "DeleteUserAPI" with "Delete" Http request with user id as path parameter
Then  The API call gets success with status code "<statuscode>"
And   "_meta.success" in response body is "<success>"
Examples:
|email|first_name|gender|last_name|status|statuscode|success|
|nik5@gmail.com|nikhil|male|Tiwari|active|200|true|






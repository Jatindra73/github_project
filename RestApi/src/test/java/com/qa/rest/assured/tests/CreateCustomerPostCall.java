package com.qa.rest.assured.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateCustomerPostCall {
	
	@Test()
	public void CreateCustomerTest()
	{
		//Define the url
		RestAssured.baseURI ="http://restapi.demoqa.com/customer/register";
		
		//Define the http request
		RequestSpecification httpRequest = RestAssured.given();
		
		//Create a json object with all the fields
		JSONObject requestJson = new  JSONObject();
		requestJson.put("Firstname", "micheal");
		requestJson.put("LastName", "Peter");
		requestJson.put("Username", "Micpeter");
		requestJson.put("Password", "mich123");
		requestJson.put("Email", "mich@gmail.com");
		
		//Add the header
		httpRequest.header("Content-type","/application/json");
		
		//add the json payload to the body of the request
		httpRequest.body(requestJson.toJSONString());
		
		//Post the request and get the response
		Response response = httpRequest.post("/register");
		
		//get the response body
		String responseBody =response.getBody().asString();
		System.out.println("Response body is : "+responseBody);
		
		//get status code and validate it:
		int statusCode = response.getStatusCode();
		System.out.println("Ststus code is : "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		System.out.println("Status line is : "+response.getStatusLine());
		
		//get the headers:
		Headers headers = response.getHeaders();
		System.out.println(headers);
		
	}

}

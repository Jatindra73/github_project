package com.qa.rest.assured.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {
	
	@Test()
	public void getWeatherDetailsTest()
	{
		//Define the Base url
		RestAssured.baseURI="http://restapi.com/utilities/weather/city";
		
		//Define the http request
		RequestSpecification httpRequest = RestAssured.given();
		
		//Make request/execute the request
		Response response = httpRequest.request(Method.GET, "/pune");
		
		//get the response body
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: "+responseBody);
		//Validate the city/Validate the key or value
		Assert.assertEquals(responseBody.contains("Pune"), true);
		
		//get status code 
		int statusCode = response.getStatusCode();
		System.out.println("Status code is : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println("Status line is : "+response.getStatusLine());
		
		//get headers
		Headers allHeaders = response.getHeaders();
		System.out.println(allHeaders);
		
		String contentType = response.getHeader("Content-Type");
		System.out.println("Value of Content-Type value is : "+contentType); 
		
		String contentLength = response.getHeader("content-length");
		System.out.println("Value of Content-Type value is : "+contentLength); 
		
		//getting key value by using Json path
		JsonPath jsonPathValue = response.jsonPath();
		String city =jsonPathValue.get("City");
		System.out.println("City name is : "+city);
		
		String temperature =jsonPathValue.get("Temperature");
		System.out.println("Temperature is : "+temperature);
		
		String humidity =jsonPathValue.get("Humidity");
		System.out.println("Humidity is : "+humidity);
		
		String weatherfdescription =jsonPathValue.get("WeatherDescription");
		System.out.println("Weatherfdescription is : "+weatherfdescription);
	}

}

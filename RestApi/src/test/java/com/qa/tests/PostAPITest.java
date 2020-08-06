package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	
	TestBase testBase;
	String serviceUrl;
	String url;
	String apiUrl;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod()
	public void setUp() {
		
		testBase = new TestBase();
	    serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("ServiceURL"); 
		//https://reqres.in/users/api/users?page=2
		url = serviceUrl+apiUrl;	
		}
	@Test(enabled= true)
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//JACKSON API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus","leader"); //Expected users object
		//Object to json(marshalling)
		mapper.writeValue(new File("D:\\MOM\\Photon\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);//Where exactly you want generate Json file
		
		//Object to json in string()
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		httpResponse = restClient.post(url, usersJsonString, headerMap);//Call the API
		
		//Validate the response from API
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, Response_Status_Code_201,"Status code is not 201");
		
	//JsonString
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from json is :"+responseJson);
		
		//Json to java Object(unmarshalling)
		Users usersResObj = mapper.readValue(responseString, Users.class);//Actual users Object
		System.out.println(usersResObj);
		
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		System.out.println(usersResObj.getName());
		System.out.println(usersResObj.getJob());
		
	//	Assert.assertTrue(users.getId().equals(usersResObj.getId()));
	//	Assert.assertTrue(users.getCreatedAt().equals(usersResObj.getCreatedAt()));
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
		
	}

}

package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
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
	
	@Test(enabled=true)
	public void getRestAPITestWithoutHeaders() throws ClientProtocolException, IOException {
	restClient = new RestClient();
	CloseableHttpResponse httpResponse = restClient.get(url);

    //Get status
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code "+ statusCode);
		Assert.assertEquals(statusCode, Response_Status_Code_200, "Status code is not 200");
		
	//Json String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API :"+ responseJson);
		
         //Single value Assertion
		//page value
		String page = TestUtil.getValueByJPath(responseJson, "/page");
		System.out.println("Value of per page is "+ page);
		Assert.assertEquals(Integer.parseInt(page), 1);
		
		//Per_Page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page is "+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//Total
		String total = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of per page is "+ total);
		Assert.assertEquals(Integer.parseInt(total), 12);
		
		//Total_pages
		String totalPages = TestUtil.getValueByJPath(responseJson, "/total_pages");
		System.out.println("Value of per page is "+ totalPages);
		Assert.assertEquals(Integer.parseInt(totalPages), 2);
				
		//Get value from Array
		String iD = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		Assert.assertEquals(Integer.parseInt(iD), 1);
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		Assert.assertEquals(email, "george.bluth@reqres.in");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		Assert.assertEquals(firstName, "George");
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		Assert.assertEquals(lastName, "Bluth");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		
		
	//Get All Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		 for(Header header : headerArray)
		 {
			 allHeaders.put(header.getName(), header.getValue());
		 }
		 System.out.println("Headers Array "+ allHeaders);
		
	}
		
	@Test(enabled =true)
	public void getRestAPITestWithHeaders() throws ClientProtocolException, IOException {
	restClient = new RestClient();
	HashMap<String,String> headerMap = new HashMap<String,String>();
	headerMap.put("Content-Type", "application/json");
//	headerMap.put("username", "test");
//	headerMap.put("password", "test123");
//	headerMap.put("Auth token", "12344");
	
	CloseableHttpResponse httpResponse = restClient.get(url,headerMap);

    //Get status
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code "+ statusCode);
		Assert.assertEquals(statusCode, Response_Status_Code_200, "Status code is not 200");
		
	//Json String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API :"+ responseJson);
		
         //Single value Assertion
		//page value
		String page = TestUtil.getValueByJPath(responseJson, "/page");
		System.out.println("Value of per page is "+ page);
		Assert.assertEquals(Integer.parseInt(page), 1);
		
		//Per_Page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page is "+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//Total
		String total = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of per page is "+ total);
		Assert.assertEquals(Integer.parseInt(total), 12);
		
		//Total_pages
		String totalPages = TestUtil.getValueByJPath(responseJson, "/total_pages");
		System.out.println("Value of per page is "+ totalPages);
		Assert.assertEquals(Integer.parseInt(totalPages), 2);
				
		//Get value from Array
		String iD = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		Assert.assertEquals(Integer.parseInt(iD), 1);
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		Assert.assertEquals(email, "george.bluth@reqres.in");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		Assert.assertEquals(firstName, "George");
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		Assert.assertEquals(lastName, "Bluth");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		
		
	//Get All Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		 for(Header header : headerArray)
		 {
			 allHeaders.put(header.getName(), header.getValue());
		 }
		 System.out.println("Headers Array "+ allHeaders);
		
	}
}

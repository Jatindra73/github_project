package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
//Get method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
	 CloseableHttpClient httpClient = HttpClients.createDefault();
	 HttpGet httpGet = new HttpGet(url);
	 CloseableHttpResponse httpResponse =  httpClient.execute(httpGet);
	 
	 return httpResponse;
	}
	
//Get method with headers
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{
	 CloseableHttpClient httpClient = HttpClients.createDefault();
	 HttpGet httpGet = new HttpGet(url);//get url request
	 for(Map.Entry<String, String> entry:headerMap.entrySet())
	 {
		 httpGet.addHeader(entry.getKey(),entry.getValue());
	 }
	 
	 CloseableHttpResponse httpResponse =  httpClient.execute(httpGet);
	 
	 return httpResponse;
	}
	
	//Post method
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//for payload
		httpPost.setEntity(new StringEntity(entityString));//setEntity() is used to define payload
		                    
		//for header
		for(Map.Entry<String, String> entry:headerMap.entrySet())
		{
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		return httpResponse;
	}
}

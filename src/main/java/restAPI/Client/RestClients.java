package restAPI.Client;

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

public class RestClients {

	// GET call using without Headers

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();// to create client connection
		HttpGet httpget = new HttpGet(url);// get request
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget);// to hit the GET URL
		return closableHttpResponse;

	}

	// GET call using With Headers

	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();// to create client connection
		HttpGet httpget = new HttpGet(url);// get request

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {

			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget);// to hit the GET URL
		return closableHttpResponse;

	}
	
	//POST method with Headers
	
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap ) throws ClientProtocolException, IOException {//Entity string is to pass the payload
		 CloseableHttpClient httpClient=HttpClients.createDefault();
		 HttpPost httpPost=new HttpPost(url);//http post request
		 httpPost.setEntity(new StringEntity(entityString));
		 
		 for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			 httpPost.addHeader(entry.getKey(), entry.getValue());
			 
		 }
		 
		 CloseableHttpResponse closableHttpResponse= httpClient.execute(httpPost);
		 return closableHttpResponse;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

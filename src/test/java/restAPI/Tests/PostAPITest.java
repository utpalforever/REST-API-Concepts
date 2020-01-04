package restAPI.Tests;

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

import restAPI.Base.TestBase;
import restAPI.Client.RestClients;
import restAPI.Data.Users;

public class PostAPITest extends TestBase {
	TestBase testBase;
	String siteUrl;
	String serviURL;
	String completeURL;
	RestClients restClient;
	CloseableHttpResponse closableHttpResponse;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();

		siteUrl = prop.getProperty("URL");
		serviURL = prop.getProperty("serviceURL");
		completeURL = siteUrl + serviURL;
	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClients();

		HashMap<String, String> headerHashMap = new HashMap<String, String>();
		headerHashMap.put("Content-Type", "application/json");// Passing the header value #Mandatory

		// Jackson databinnd

		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");// expected users Object

		// Object to JSON in a file
		mapper.writeValue(new File("E:\\Prep\\RestAPI\\src\\main\\java\\restAPI\\Data\\users.json"), users);

		// Object to JSON in a String

		String jsonUsersString = mapper.writeValueAsString(users);
		System.out.println("---####---");
		System.out.println(jsonUsersString);
		System.out.println("---####---");
		closableHttpResponse = restClient.post(completeURL, jsonUsersString, headerHashMap);
		// Status code Validation
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is : " + statusCode);
		Assert.assertEquals(statusCode, status_code_201);
		// JSON string Validation
		String resposeString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");// other info also it
																								// fetches

		System.out.println("------");
		System.out.println(resposeString);// not arranged like a POSTMAN output
		System.out.println("------");
		
		JSONObject responseJSON = new JSONObject(resposeString);// it arranges the String from create date
		
		System.out.println("Respose JSON from API is : " + responseJSON);

		// To validate the created data value as above
		// UnMarshelling--JSON to Java
		Users usersResObj = mapper.readValue(resposeString, Users.class);// actual usersResObj Object
		System.out.println(usersResObj);
		// Validation of the values
		System.out.println(users.getName().equals(usersResObj.getName()));
		System.out.println(users.getJob().equals(usersResObj.getJob()));
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());

		

	}

}

package restAPI.Tests;

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

import restAPI.Base.TestBase;
import restAPI.Client.RestClients;
import restAPI.Utility.TestUtility;

public class GetAPITest extends TestBase {

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

	@Test // Without Headers
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClients();
		closableHttpResponse = restClient.get(completeURL);// Calling the RestClient get method

		// Status code

		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, status_code_200, "status code is not 200");

		// JSON String

		String resposeString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJSON = new JSONObject(resposeString);
		System.out.println("Respose JSON from API" + responseJSON);

		// Parsing JSON Object

		// per_page

		String per_page = TestUtility.getValueByJPath(responseJSON, "/per_page");
		System.out.println("Value of per_page is : " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 6);// it was coming as string so need to convert into Integer

		// total
		String total = TestUtility.getValueByJPath(responseJSON, "/total");
		System.out.println("Value of total field is: " + total);
		Assert.assertEquals(total, Integer.toString(12));// converted into String

		// Parsing JSON Array

		String last_nameData0 = TestUtility.getValueByJPath(responseJSON, "/data[0]/last_name");
		// data[0] we are writing because last_name attribute is present in the 0th
		// index of data[]
		System.out.println("Value of last_name from data[0] is : " + last_nameData0);

		String last_namedata1 = TestUtility.getValueByJPath(responseJSON, "/data[1]/last_name");
		System.out.println("Value of last_name from data[1] is : " + last_namedata1);
		Assert.assertEquals(last_namedata1, "Weaver", "last_name is showing wrong");

		String avatardata2 = TestUtility.getValueByJPath(responseJSON, "/data[2]/avatar");
		System.out.println("Value of avatar from data[2] is : " + avatardata2);

		// All the Header

		Header[] headersArray = closableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}

		System.out.println("Header Array is : " + allHeaders);

	}

	@Test(priority=1) // With Headers
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClients();

		HashMap<String, String> headerHashMap = new HashMap<String, String>();
		headerHashMap.put("Username", "test123");// Passing the header value
		headerHashMap.put("password", "test");// Passing the header value
		headerHashMap.put("AuthToken", "1234");// Passing the header value

		closableHttpResponse = restClient.get(completeURL, headerHashMap);// Calling the RestClient get method

		// Status code

		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, status_code_200, "status code is not 200");

		// JSON String

		String resposeString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJSON = new JSONObject(resposeString);
		System.out.println("Respose JSON from API" + responseJSON);

		// Parsing JSON Object

		// per_page

		String per_page = TestUtility.getValueByJPath(responseJSON, "/per_page");
		System.out.println("Value of per_page is : " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 6);// it was coming as string so need to convert into Integer

		// total
		String total = TestUtility.getValueByJPath(responseJSON, "/total");
		System.out.println("Value of total field is: " + total);
		Assert.assertEquals(total, Integer.toString(12));// converted into String

		// Parsing JSON Array

		String last_nameData0 = TestUtility.getValueByJPath(responseJSON, "/data[0]/last_name");
		// data[0] we are writing because last_name attribute is present in the 0th
		// index of data[]
		System.out.println("Value of last_name from data[0] is : " + last_nameData0);

		String last_namedata1 = TestUtility.getValueByJPath(responseJSON, "/data[1]/last_name");
		System.out.println("Value of last_name from data[1] is : " + last_namedata1);
		Assert.assertEquals(last_namedata1, "Weaver", "last_name is showing wrong");

		String avatardata2 = TestUtility.getValueByJPath(responseJSON, "/data[2]/avatar");
		System.out.println("Value of avatar from data[2] is : " + avatardata2);

		// All the Header

		Header[] headersArray = closableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}

		System.out.println("Header Array is : " + allHeaders);

	}

}

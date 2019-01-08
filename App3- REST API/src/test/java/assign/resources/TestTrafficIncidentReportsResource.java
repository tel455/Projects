package assign.resources;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

public class TestTrafficIncidentReportsResource 
{
	private HttpClient client;

   @Before
   public void initClient()
   {
	   client = HttpClientBuilder.create().build();
   }

   @Test
   public void testGetReports() throws Exception
   {
      System.out.println("**** Testing GetReports ***");
      
      String url = "http://localhost:8080/assignment3/trafficincidentreports/reports";
      
      // Code snippet taken from https://www.mkyong.com/java/apache-httpclient-examples/
      HttpGet request = new HttpGet(url);
      HttpResponse response = client.execute(request);

      System.out.println("Response Code : "
              + response.getStatusLine().getStatusCode());

	  BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		
	  StringBuffer result = new StringBuffer();
	  String line = "";
	  while ((line = rd.readLine()) != null) {
		  result.append(line);
	  }

      System.out.println(result);
      
      // Parse XML result
      String list = result.toString();
      // Write asserts to verify the output
      assertTrue(list.contains("C163BCD1CF90C984E9EDA4DBA311BCA369A7D1A1_1528871759"));
      assertTrue(list.contains("564FD4F695747A12F20FB2F28E3C8198AD3BA8DE_1532401908"));
   }

   @Test
   public void testGetAReport() throws Exception
   {
      System.out.println("**** Testing GetAReport ***");
      
      String url = "http://localhost:8080/assignment3/trafficincidentreports/reports/C163BCD1CF90C984E9EDA4DBA311BCA369A7D1A1_1528871759";
      
      // Code snippet taken from https://www.mkyong.com/java/apache-httpclient-examples/
      HttpGet request = new HttpGet(url);
      HttpResponse response = client.execute(request);

      System.out.println("Response Code : "
              + response.getStatusLine().getStatusCode());

	  BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
		
	  StringBuffer result = new StringBuffer();
	  String line = "";
	  while ((line = rd.readLine()) != null) {
		  result.append(line);
	  }

      System.out.println(result);
      
      // Parse XML result
      String list = result.toString();
      // Write asserts to verify the output
      assertTrue(list.contains("C163BCD1CF90C984E9EDA4DBA311BCA369A7D1A1_1528871759"));
      assertTrue(list.contains("2018-06-13T06:35:59Z"));
      assertTrue(list.contains("Crash Service"));
      assertTrue(list.contains("30.28379"));
      assertTrue(list.contains("-97.741906"));
      assertTrue(list.contains("W 21ST ST & GUADALUPE ST"));
      
   }
}

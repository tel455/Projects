package assign.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class TestTrafficIncidentReportsResource
{

   private HttpClient client;

   @Before
   public void initClient()
   {
	   client = HttpClientBuilder.create().build();
   }
   
   @Test //not finished
   public void testGetReport() throws Exception
   {
      System.out.println("**** Testing GET Method ***");
      
      String url = "http://localhost:8080/assignment4/trafficincidentreports/reports/1";
      
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

//      System.out.println(result);
      
      // Parse XML result
      String list = result.toString();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      InputSource is = new InputSource(new StringReader(list));
      Document doc = builder.parse(is);

      //this will return a list of xml tags whose name is `hi`
      NodeList hiList = doc.getElementsByTagName("hi");

      //you can iterate over hiList and read/process them
      for (int i = 0; i < hiList.getLength(); i++) {
          Node child = hiList.item(i);
          String name = child.getNodeName();
          String contents = child.getTextContent();
          System.out.println(name);
          System.out.println(contents);
      }
      // Write asserts to verify the output
   }   
   
   @Test //not finished
   public void testUpdateReport() throws Exception
   {
      System.out.println("**** Testing PUT Method ***");
      
      String url = "http://localhost:8080/assignment4/trafficincidentreports/reports/";
      
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

//      System.out.println(result);
      // Parse XML result
      // Write asserts to verify the output
   }   
}

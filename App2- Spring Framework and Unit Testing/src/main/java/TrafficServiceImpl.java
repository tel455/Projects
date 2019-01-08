
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//DOM parsing
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//DOM parsing
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TrafficServiceImpl implements TrafficService
{
	public String calculateIssues(String issue_reported) throws MalformedURLException
	{
		URL url = new URL("http://www.cs.utexas.edu/~devdatta/traffic_incident_data.xml");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try 
		{
		    builder = builderFactory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) 
		{
		    e.printStackTrace();  
		}
		
		Document document = null;
		try 
		{
			document = builder.parse(url.openStream());
		} 
		catch (SAXException e) 
		{
		    e.printStackTrace();
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		Element rootElement = document.getDocumentElement();
		Queue<Element> q = new LinkedList<Element>();
		q.add(rootElement);

		int counter = 0;
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:issue_reported")) 
			{
		    	String nodeValue = e.getTextContent();
		    	//assuming all spaces in urls will be given %20
		    	String issue = issue_reported.replaceAll("%20"," ");
		    	if (nodeValue.equalsIgnoreCase(issue_reported))
		    	{
		    		counter++;
		    	}
		    }
			NodeList nodes1 = e.getChildNodes();
			for(int i=0; i<nodes1.getLength(); i++) 
			{
				  Node node = nodes1.item(i);
				  if(node instanceof Element) 
				  {
					  q.add((Element) node);
				  }
			}
		}

		String risk = "";
		if (counter >= 0 && counter <= 5)
		{
			risk = "Rare";
		}
		if (counter >= 6 && counter <= 10)
		{
			risk = "Very Low";
		}
		if (counter >= 11 && counter <= 15)
		{
			risk = "Low";
		}
		if (counter >= 16 && counter <= 30)
		{
			risk = "Medium";
		}
		if (counter >= 31 && counter <= 50)
		{
			risk = "Moderate";
		}
		if (counter >= 51 && counter <= 100)
		{
			risk = "High";
		}
		if (counter >= 101 && counter <= 200)
		{
			risk = "Very High";
		}
		if (counter >= 201)
		{
			risk = "Extreme";
		}

		return ("Number of " + issue_reported + " incidents: " + counter + ". Risk level: " + risk + ".\n");
	}
}
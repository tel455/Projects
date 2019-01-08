package assignment6;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//DOM parsing
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Path("/incidents")
public class HelloWorldResource {
	
	public HelloWorldResource() {
		
	}

	
	@GET
	@Path("/traffic_count")
	@Produces("text/html")
	public String traffic_issues_count() throws Exception
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

		//$ will be used to split the string in the js file
		String collisionWithInjury = "COLLISION WITH INJURY" + "$";
		int injuryCount = 0;
		String privateProperty = "COLLISION/PRIVATE PROPERTY" + "$";
		int privateCount = 0;
		String collision = "COLLISION" + "$";
		int collisionCount = 0;
		String lvngScn = "COLLISN/ LVNG SCN" + "$";
		int lvngScnCount = 0;
		String crashService = "CRASH SERVICE" + "$";
		int serviceCount = 0;
		String crashUrgent = "CRASH URGENT" + "$";
		int urgentCount = 0;
		String livestock = "LOOSE LIVESTOCK" + "$";
		int livestockCount = 0;
		String debris = "TRFC HAZD/ DEBRIS" + "$";
		int debrisCount = 0;
		String trafficHazard = "TRAFFIC HAZARD" + "$";
		int hazardCount = 0;
		String impediment = "Traffic Impediment" + "$";
		int impedimentCount = 0;
		String fire = "VEHICLE FIRE" + "$";
		int fireCount = 0;
		String vehicle = "ZSTALLED VEHICLE" + "$";
		int vehicleCount = 0;
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:issue_reported")) 
			{
		    	String nodeValue = e.getTextContent();
		    	//assuming all spaces in urls will be given %20
//		    	String issue = issue_reported.replaceAll("%20"," ");
		    	if (nodeValue.equalsIgnoreCase("Collision with injury"))
		    	{
		    		injuryCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("COLLISION/PRIVATE PROPERTY"))
		    	{
		    		privateCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("COLLISION"))
		    	{
		    		collisionCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("COLLISN/ LVNG SCN"))
		    	{
		    		lvngScnCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("CRASH SERVICE"))
		    	{
		    		serviceCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("CRASH URGENT"))
		    	{
		    		urgentCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("LOOSE LIVESTOCK"))
		    	{
		    		livestockCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("TRFC HAZD/ DEBRIS"))
		    	{
		    		debrisCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("TRAFFIC HAZARD"))
		    	{
		    		hazardCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("Traffic Impediment"))
		    	{
		    		impedimentCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("VEHICLE FIRE"))
		    	{
		    		fireCount++;
		    	}
		    	if (nodeValue.equalsIgnoreCase("ZSTALLED VEHICLE"))
		    	{
		    		vehicleCount++;
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
		String incidents = collisionWithInjury + injuryCount + "$" + privateProperty + privateCount + "$" 
				+ collision + collisionCount + "$" + lvngScn + lvngScnCount + "$" + crashService + serviceCount + "$"
				+ crashUrgent + urgentCount + "$" + livestock + livestockCount + "$" + debris + debrisCount + "$"
				+ trafficHazard + hazardCount + "$" + impediment + impedimentCount + "$" + fire + fireCount + "$"
				+ vehicle + vehicleCount;
		return incidents;
	}
}

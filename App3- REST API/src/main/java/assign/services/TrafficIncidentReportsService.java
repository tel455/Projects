package assign.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.net.*;

//DOM parsing
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import assign.domain.TrafficIncidentReport;
import assign.domain.TrafficIncidentReports;
import assign.domain.Error;

public class TrafficIncidentReportsService {

	public List<TrafficIncidentReport> getAllReports() throws Exception 
	{
		List<TrafficIncidentReport> list = new ArrayList<TrafficIncidentReport>();
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

		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:traffic_report_id")) 
			{
		    	String nodeValue = e.getTextContent();
		    	TrafficIncidentReport reportId = new TrafficIncidentReport();
		    	reportId.setTrafficId(nodeValue);
		    	list.add(reportId);
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
		return list; 
	}
	
	public Integer checkExist(String traffic_report_id) throws Exception 
	{
		int count = 0;
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

		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:traffic_report_id")) 
			{
				String nodeValue = e.getTextContent();
				if (nodeValue.equalsIgnoreCase(traffic_report_id))
		    	{
		    		count++;
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
		return count; 
	}
	
	public String getReportDate(String traffic_report_id) throws Exception 
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
		String nodeT = "";
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:traffic_report_id")) 
			{
		    	String nodeValue = e.getTextContent();
		    	if (traffic_report_id.equalsIgnoreCase(nodeValue))
		    	{
		    		Node sibling = e.getNextSibling();
			    	while (sibling != null)
			    	{
			    		if (sibling.getNodeType() == Node.ELEMENT_NODE) {
			    			break;
			            }
			            sibling = sibling.getNextSibling();
			    	}
			    	if (sibling.getNodeName().equals("ds:published_date"))
			    	{
			    		nodeT = sibling.getTextContent();
			    		return nodeT;
			    	}
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
		return nodeT; 
	}	
	
	public String getIssueReported(String traffic_report_id) throws Exception 
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
		String nodeT = "";
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:published_date")) 
			{
		    	String nodeValue = e.getTextContent();
		    	if (traffic_report_id.equalsIgnoreCase(nodeValue))
		    	{
		    		Node sibling = e.getNextSibling();
			    	while (sibling != null)
			    	{
			    		if (sibling.getNodeType() == Node.ELEMENT_NODE) {
			    			break;
			            }
			            sibling = sibling.getNextSibling();
			    	}
			    	if (sibling.getNodeName().equals("ds:issue_reported"))
			    	{
			    		nodeT = sibling.getTextContent();
			    		return nodeT;
			    	}
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
		return nodeT; 
	}
	
	public String getLatitude(String traffic_report_id) throws Exception 
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
		String nodeT = "";
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:traffic_report_id")) 
			{
		    	String nodeValue = e.getTextContent();
		    	if (traffic_report_id.equalsIgnoreCase(nodeValue))
		    	{
		    		Node parent = e.getParentNode();
		    		Node sibling = parent.getLastChild();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
			    	while (sibling != null)
			    	{
			    		if (sibling.getNodeType() == Node.ELEMENT_NODE) {
			    			break;
			            }
			            sibling = sibling.getPreviousSibling();
			    	}
			    	if (sibling.getNodeName().equals("ds:latitude"))
			    	{
			    		nodeT = sibling.getTextContent();
			    		return nodeT;
			    	}
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
		return nodeT; 
	}	
	
	public String getLongitude(String traffic_report_id) throws Exception 
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
		String nodeT = "";
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:latitude")) 
			{
		    	String nodeValue = e.getTextContent();
		    	if (traffic_report_id.equalsIgnoreCase(nodeValue))
		    	{
		    		Node sibling = e.getNextSibling();
			    	while (sibling != null)
			    	{
			    		if (sibling.getNodeType() == Node.ELEMENT_NODE) {
			    			break;
			            }
			            sibling = sibling.getNextSibling();
			    	}
			    	if (sibling.getNodeName().equals("ds:longitude"))
			    	{
			    		nodeT = sibling.getTextContent();
			    		return nodeT;
			    	}
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
		return nodeT; 
	}	
	
	public String getAddress(String traffic_report_id) throws Exception 
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
		String nodeT = "";
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:traffic_report_id")) 
			{
		    	String nodeValue = e.getTextContent();
		    	if (traffic_report_id.equalsIgnoreCase(nodeValue))
		    	{
		    		Node parent = e.getParentNode();
		    		Node sibling = parent.getLastChild();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		sibling = sibling.getPreviousSibling();
		    		while (sibling != null)
			    	{
			    		if (sibling.getNodeType() == Node.ELEMENT_NODE) {
			    			break;
			            }
			            sibling = sibling.getPreviousSibling();
			    	}
			    	if (sibling.getNodeName().equals("ds:address"))
			    	{
			    		nodeT = sibling.getTextContent();
			    		return nodeT;
			    	}
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
		return nodeT; 
	}	
}

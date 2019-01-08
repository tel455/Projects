

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//added
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
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

// http://www.cs.utexas.edu/~devdatta/traffic_incident_data.xml
// @WebServlet("/trafficincidentreports")
public class TrafficIncidentReportServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Boolean sessionStart = false;
	ArrayList<String> history = new ArrayList<String>();

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException
    {
		// response.getWriter().println("is this thing working");

		//so we don't have to keep typing out response.getWriter();
		PrintWriter w = response.getWriter();

		String session = request.getParameter("session");
		String published_date = request.getParameter("published_date");
		String issue_reported = request.getParameter("issue_reported");
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

		//if the site is not being visited 
		if(session == null && published_date == null && issue_reported == null) 
		{
			w.println("History");
			w.println("\nData");
			return;
		}
		
		//if session parameter is passed in, also if published_date or issue_reported are passed in, you can ignore
		if(session != null) 
		{
			//check session parameter
			if(!session.equalsIgnoreCase("start") && !session.equalsIgnoreCase("end")) 
			{
				w.println("Incorrect value specified");
			}
			//print out History list, also add session=start to it
			if(session.equalsIgnoreCase("start")) 
			{
				sessionStart = true;
				history.clear();
				history.add(request.getRequestURL().toString() + "?" + request.getQueryString());
				w.println("History");
				for (int x = 0; x < history.size(); x++) 
				{
					w.println(history.get(x));
				}
				w.println("\nData");
				return;
			}
			//print out History list then delete the list once user calls session=end
			if(sessionStart) 
			{
				if(session.equalsIgnoreCase("end") && sessionStart) 
				{
					sessionStart = false;
					history.add(request.getRequestURL().toString() + "?" + request.getQueryString());
					w.println("History");
					for (int x = 0; x < history.size(); x++ ) 
					{
						w.println(history.get(x));
					}
					w.println("\nData");
					history.clear();
					return;
				}
			}
			else 
			{
				if(session.equalsIgnoreCase("end")) 
				{
					history.add(request.getRequestURL().toString() + "?" + request.getQueryString());
					sessionStart = false;
					w.println("History");
					for (int x = 0; x < history.size(); x++) 
					{
						w.println(history.get(x));
					}
					w.println("\nData");
					history.clear();
					return;
				}
			}
		}
		//DOM parsing referenced from example DOM parser file
		//if both published_date and issue_reported are both passed in a single request
		if(published_date != null && issue_reported != null)
		{
			w.println("Error. Pass only one parameter");
		}

		//only published_date
		if(published_date != null && issue_reported == null)
		{
			if(sessionStart == false)
			{
				history.clear();
			}
			history.add(request.getRequestURL().toString() + "?" + request.getQueryString());

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
				if (e.getNodeName().equals("ds:published_date")) 
				{
			    	String nodeValue = e.getTextContent();
			    	if (nodeValue.contains(published_date))
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
			w.println("History");
			for (int x = 0; x < history.size(); x++) 
			{
				w.println(history.get(x));
			}
			w.println("\nData");
			w.println("Number of issues on " + published_date + ": " + counter);
		}

		//only issue_reported
		if(published_date == null && issue_reported != null)
		{
			if(sessionStart == false)
			{
				history.clear();
			}
			history.add(request.getRequestURL().toString() + "?" + request.getQueryString());

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
			    	if (nodeValue.equalsIgnoreCase(issue))
			    	{
			    		counter++;
			    	}
			    }
				NodeList nodes = e.getChildNodes();
				for(int i=0; i<nodes.getLength(); i++) 
				{
					  Node node = nodes.item(i);
					  if(node instanceof Element) 
					  {
						  q.add((Element) node);
					  }
				}
			}
			w.println("History");
			for (int x = 0; x < history.size(); x++) 
			{
				w.println(history.get(x));
			}
			w.println("\nData");
			w.println("Number of issues of type " + issue_reported + ": " + counter);
		}
    }
}

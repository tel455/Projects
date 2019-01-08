package assign.services;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import assign.domain.ETL;

public class TrafficIncidentReportsServiceImpl implements TrafficIncidentReportsService 
{
	private SessionFactory sessionFactory;
	Logger logger;
	
	public TrafficIncidentReportsServiceImpl()
	{
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        
        logger = Logger.getLogger("DBLoader");
        try 
        {
        	populateDatabase();
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
	}
	
	public int getZipcodeImpl(String zipcode) throws Exception 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ETL.class).
        		add(Restrictions.eq("zipcode", zipcode));
		
		List<ETL> reports = criteria.list();
		int num = reports.size();
		
		session.close();
		
		return num;		
	}
	
	public int getPublishedDateImpl(String published_date) throws Exception 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ETL.class).
        		add(Restrictions.eq("date", published_date));
		
		List<ETL> reports = criteria.list();
		int num = reports.size();
		
		session.close();
		
		return num;		
	}
	
	public int getIssueReportedImpl(String issue_reported) throws Exception 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ETL.class).
        		add(Restrictions.eq("issue", issue_reported));
		
		List<ETL> reports = criteria.list();
		int num = reports.size();
		
		session.close();
		
		return num;		
	}
	
	public int getAddressImpl(String address) throws Exception 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ETL.class).
        		add(Restrictions.eq("address", address));
		
		List<ETL> reports = criteria.list();
		int num = reports.size();
		
		session.close();
		
		return num;		
	}
	
	public void populateDatabase() throws Exception 
	{
		List<ETL> reports = new ArrayList<ETL>();
		reports = parseXML1(reports);
		reports = parseXML2(reports);
		for(ETL rep : reports) 
		{
			addReport(rep);
		}	
	
	}
	
	public Long addReport(ETL report) throws Exception 
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Long reportId = null;
		try 
		{
			tx = session.beginTransaction();
			session.save(report);
			reportId = report.getId();
		    tx.commit();
		} 
		catch (Exception e) 
		{
			if (tx != null) 
			{
				tx.rollback();
				throw e;
			}
		}
		finally 
		{
			session.close();
		}
		return reportId;
	}
	
	@Override
	public List<ETL> parseXML1(List<ETL> reports) throws Exception
	{
		URL url = new URL("http://www.cs.utexas.edu/~devdatta/traffic_incident_data.xml\n");
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
			ETL report = new ETL();
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:published_date")) 
			{
				report.setDate(e.getTextContent());
				e = q.remove();
				report.setIssue(e.getTextContent());
				q.remove();
				q.remove();
				q.remove();
				e = q.remove();
				report.setAddress(e.getTextContent());
				reports.add(report);
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
		return reports;
	}
	
	@Override
	public List<ETL> parseXML2(List<ETL> reports) throws Exception
	{
		URL url = new URL("http://www.cs.utexas.edu/~devdatta/traffic_incident_with_zipcodes.xml\n");
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
		Iterator<ETL> reportIterator = reports.iterator();
		while(!q.isEmpty()) 
		{
			Element e = (Element) q.remove();			
			if (e.getNodeName().equals("ds:zipcode") && reportIterator.hasNext()) 
			{
				ETL report = reportIterator.next();
				report.setZipcode(e.getTextContent());
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
		return reports;
	}
}
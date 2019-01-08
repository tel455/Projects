package assign.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.ws.rs.PathParam;


import assign.domain.TrafficIncidentReport;
import assign.domain.TrafficIncidentReports;
import assign.domain.Error;
import assign.services.TrafficIncidentReportsService;

@Path("")
public class TrafficIncidentReportsResource 
{
	
	TrafficIncidentReportsService trafficIncidentReportsService;
	
	public TrafficIncidentReportsResource() 
	{
		this.trafficIncidentReportsService = new TrafficIncidentReportsService();
	}
	
	@GET
	@Path("")
	@Produces("text/html")
	public String helloWorld() 
	{
		return "Welcome to Traffic Incident Reports REST API <br> For All reports use: http://localhost:8080/assignment3/trafficincidentreports/reports <br> For a specific report use: http://localhost:8080/assignment3/trafficincidentreports/reports/{traffic_report_id}";		
	}
	
	
	@GET
	@Path("/reports")
	@Produces("application/xml")
	public StreamingOutput getReports() throws Exception 
	{
		final TrafficIncidentReports reports = new TrafficIncidentReports();
		reports.setReports(trafficIncidentReportsService.getAllReports());
			    
	    return new StreamingOutput() 
	    {
	         public void write(OutputStream outputStream) throws IOException, WebApplicationException 
	         {
	            outputTrafficIncidentReports(outputStream, reports);
	         }
	    };	    
	}
	
	@GET
	@Path("/reports/{traffic_report_id}")
	@Produces("application/xml")
	public StreamingOutput getAReport(@PathParam("traffic_report_id") final String traffic_report_id) throws Exception 
	{
		final TrafficIncidentReport report = new TrafficIncidentReport();
		int count = trafficIncidentReportsService.checkExist(traffic_report_id);
		if (count == 0)
    	{
    		final Error error = new Error();
    		error.setError("Traffic report with id " + traffic_report_id + " does not exist");
    		return new StreamingOutput() 
    		{
   	         public void write(OutputStream outputStream) throws IOException, WebApplicationException 
   	         {
   	        		outputTrafficIncidentReports(outputStream, error);
   	         }
   	        };	
    	}
		else
		{
			report.setTrafficId(traffic_report_id);
			String date = "";
			date = trafficIncidentReportsService.getReportDate(traffic_report_id);
			report.setPublishedDate(date);
			
			String issue = "";
			issue = trafficIncidentReportsService.getIssueReported(date);
			report.setIssueReported(issue);
			
			String lat = "";
			lat = trafficIncidentReportsService.getLatitude(traffic_report_id);
			report.setLatitude(lat);
			
			String longitude = "";
			longitude = trafficIncidentReportsService.getLongitude(lat);
			report.setLongitude(longitude);
			
			String address = "";
			address = trafficIncidentReportsService.getAddress(traffic_report_id);
			report.setAddress(address);
			
		    return new StreamingOutput() 
		    {
		         public void write(OutputStream outputStream) throws IOException, WebApplicationException 
		         {
		        		outputTrafficIncidentReports(outputStream, report);
		         }
		    };
		}
	}	

	protected void outputTrafficIncidentReports(OutputStream os, Error error) throws IOException 
	{
		try 
		{ 
			JAXBContext jaxbContext = JAXBContext.newInstance(Error.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(error, os);
		} catch (JAXBException jaxb) 
		{
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
	
	protected void outputTrafficIncidentReports(OutputStream os, TrafficIncidentReports reports) throws IOException 
	{
		try 
		{ 
			JAXBContext jaxbContext = JAXBContext.newInstance(TrafficIncidentReports.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(reports, os);
		} catch (JAXBException jaxb) 
		{
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}	
	
	protected void outputTrafficIncidentReports(OutputStream os, TrafficIncidentReport report) throws IOException 
	{
		try 
		{ 
			JAXBContext jaxbContext = JAXBContext.newInstance(TrafficIncidentReport.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(report, os);
		} catch (JAXBException jaxb) 
		{
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
}

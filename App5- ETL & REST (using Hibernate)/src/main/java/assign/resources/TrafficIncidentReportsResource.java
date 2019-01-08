package assign.resources;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import assign.services.TrafficIncidentReportsService;
import assign.services.TrafficIncidentReportsServiceImpl;
import assign.domain.Output;

@Path("/")
public class TrafficIncidentReportsResource 
{
	private static TrafficIncidentReportsService TrafficIncidentReportsService = new TrafficIncidentReportsServiceImpl();
	
	public TrafficIncidentReportsResource(@Context ServletContext servletContext) throws Exception 
	{
	}
	
	//#1
	@GET
	@Path("")
	@Produces("text/html")
	public String homePage() 
	{
		return "Welcome to Traffic Incident Reports REST API.<br>Following queries are supported by this REST API.\n<br>For zipcodes: http://localhost:8080/assignment5/trafficincidentreports/reports/zipcode/&ltzipcode&gt<br>For published_date: http://localhost:8080/assignment5/trafficincidentreports/reports/published_date/&ltpublished_date&gt<br>For issue_reported: http://localhost:8080/assignment5/trafficincidentreports/reports/issue_reported/&ltissue_reported&gt<br>For address: http://localhost:8080/assignment5/trafficincidentreports/reports/address/&ltaddress&gt<br>";		
	}
	
	//#2
	@GET
	@Path("/reports/zipcode/{zipcode}")
	@Produces("application/xml")
	public Response getReportZipcode(@PathParam("zipcode") String zipcode) throws Exception 
	{
		int count = TrafficIncidentReportsResource.TrafficIncidentReportsService.getZipcodeImpl(zipcode);
		final Output result = new Output();
		result.setZipcode(zipcode);
		result.setCount(count);
		StreamingOutput stream = new StreamingOutput() 
        {
             public void write(OutputStream outputStream) throws IOException, WebApplicationException 
             {
                 outputReport(outputStream, result);
             }
        };
        return Response.status(Response.Status.OK).entity(stream).build();
	}
	
	//#3
	@GET
	@Path("/reports/published_date/{published_date}")
	@Produces("application/xml")
	public Response getReportDate(@PathParam("published_date") String published_date) throws Exception 
	{
		int count = TrafficIncidentReportsResource.TrafficIncidentReportsService.getPublishedDateImpl(published_date);
		final Output result = new Output();
		result.setPublished_Date(published_date);
		result.setCount(count);
		StreamingOutput stream = new StreamingOutput() 
        {
             public void write(OutputStream outputStream) throws IOException, WebApplicationException 
             {
                 outputReport(outputStream, result);
             }
        };
        return Response.status(Response.Status.OK).entity(stream).build();	
	}
	
	//#4
	@GET
	@Path("/reports/issue_reported/{issue_reported}")
	@Produces("application/xml")
	public Response getReportIssue(@PathParam("issue_reported") String issue_reported) throws Exception 
	{
		int count = TrafficIncidentReportsResource.TrafficIncidentReportsService.getIssueReportedImpl(issue_reported);
		final Output result = new Output();
		result.setIssue_Reported(issue_reported);
		result.setCount(count);
		StreamingOutput stream = new StreamingOutput() 
        {
             public void write(OutputStream outputStream) throws IOException, WebApplicationException 
             {
                 outputReport(outputStream, result);
             }
        };
        return Response.status(Response.Status.OK).entity(stream).build();	
	}
	
	//#5
	@GET
	@Path("/reports/address/{address}")
	@Produces("application/xml")
	public Response getReportAddress(@PathParam("address") String address) throws Exception 
	{
		int count = TrafficIncidentReportsResource.TrafficIncidentReportsService.getAddressImpl(address);
		final Output result = new Output();
		result.setAddress(address);
		result.setCount(count);
		StreamingOutput stream = new StreamingOutput() 
        {
             public void write(OutputStream outputStream) throws IOException, WebApplicationException 
             {
                 outputReport(outputStream, result);
             }
        };
        return Response.status(Response.Status.OK).entity(stream).build();	
	}

	protected void outputReport(OutputStream os, Output reports) throws IOException 
    {
        try 
        { 
            JAXBContext jaxbContext = JAXBContext.newInstance(Output.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
     
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(reports, os);
        } catch (JAXBException jaxb) 
        {
            jaxb.printStackTrace();
            throw new WebApplicationException();
        }
    }
}
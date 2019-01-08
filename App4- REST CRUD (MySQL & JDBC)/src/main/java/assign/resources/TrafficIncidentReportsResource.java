package assign.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.Consumes;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.sql.SQLException;


import assign.domain.TrafficIncidentReport;
import assign.domain.TrafficIncidentReports;
import assign.services.TrafficIncidentReportsService;
import assign.services.TrafficIncidentReportsServiceImpl;

@Path("")
public class TrafficIncidentReportsResource {

	TrafficIncidentReportsService trafficIncidentReportsService;
	String password;
	String username;
	String dburl;
	String host;
	String dbname;

	public TrafficIncidentReportsResource(@Context ServletContext servletContext) {
		host = servletContext.getInitParameter("DBHOST");
		dbname = servletContext.getInitParameter("DBNAME");
		username = servletContext.getInitParameter("DBUSERNAME");
		password = servletContext.getInitParameter("DBPASSWORD");
		//jdbc:mysql://localhost:3306/student_courses
		dburl = "jdbc:mysql://" + host + ":3306/" + dbname + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		this.trafficIncidentReportsService = new TrafficIncidentReportsServiceImpl(dburl, username, password);		
	}

	//#1 is finished
	@GET
	@Path("")
	@Produces("text/html")
	public String reports() {	
		return "Welcome to Traffic Incident Reports REST API <br> For All reports use: http://localhost:8080/assignment4/trafficincidentreports/reports <br> For a specific report use: http://localhost:8080/assignment4/trafficincidentreports/reports/{id}";		
	}

	//#2 is finished
	@POST
	@Path("/reports")
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response addReport(TrafficIncidentReport reportToAdd) throws Exception {
		TrafficIncidentReport report = new TrafficIncidentReport();
		report = trafficIncidentReportsService.addReport(reportToAdd);
		
		final TrafficIncidentReport rep = report;
		StreamingOutput stream = new StreamingOutput() {
	         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
	        	 outputReport(outputStream, rep);
	         }
	      };
	      return Response.status(Response.Status.OK).entity(stream).build();
	}

	//#3 is finished
	@PUT
	@Path("/reports/{id}")
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response updateReport(@PathParam("id") final Integer id, TrafficIncidentReport update) throws Exception {
		if ((id == null || id < 0) || update == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			TrafficIncidentReport report = trafficIncidentReportsService.getReport(id);
			if (report == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
			
			if (trafficIncidentReportsService.updateReport(id, report, update)) {
				final TrafficIncidentReport rep = trafficIncidentReportsService.getReport(id);
				StreamingOutput stream = new StreamingOutput() {
			         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
			        	 outputReport(outputStream, rep);
			         }
			      };
			      return Response.status(Response.Status.OK).entity(stream).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}	

	//#4 is finished
	@GET
	@Path("/reports/{id}")
	@Produces("application/xml")
	public Response getReport(@PathParam("id") int id) throws Exception {
		if (id < 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			final TrafficIncidentReport report = trafficIncidentReportsService.getReport(id);  
			if (report != null) {
				StreamingOutput stream = new StreamingOutput() {
			         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
			        	 outputReport(outputStream, report);
			         }
			      };
			      return Response.status(Response.Status.OK).entity(stream).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	//#5 is finished
	@DELETE
	@Path("/reports/{id}")
	@Produces("application/xml")
	public Response deleteReport(@PathParam("id") final Integer id) throws Exception {
		if (id == null || id < 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			if (trafficIncidentReportsService.deleteReport(id)) {
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	//#6 and #7 are finished
	@GET
	@Path("/reports")
	@Produces("application/xml")
	public StreamingOutput getAll(@QueryParam("zipcode") String zipcode) throws Exception {
		final TrafficIncidentReports reports = new TrafficIncidentReports();
		if (zipcode == null) {
			reports.setReports(trafficIncidentReportsService.getAllReports());
		}
		else {
			reports.setReports(trafficIncidentReportsService.getZipcode(zipcode));
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputReports(outputStream, reports);
			}
		};   
	}
	
	public TrafficIncidentReport parseInput(InputStream reportToAdd) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(reportToAdd);
			Element root = doc.getDocumentElement();
			TrafficIncidentReport report = new TrafficIncidentReport();
			if(root.getAttribute("id") != null && !root.getAttribute("id").trim().equals("")) {
				report.setId(Integer.valueOf(root.getAttribute("id")));
			}
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				if (element.getTagName().equals("published_date")) {
					report.setPublishedDate(element.getTextContent());
				} else if (element.getTagName().equals("issue_reported")) {
					report.setIssueReported(element.getTextContent());
				} else if (element.getTagName().equals("address")) {
					report.setAddress(element.getTextContent());
				} else if (element.getTagName().equals("zipcode")) {
					report.setZipcode(element.getTextContent());
				}
			}
			return report;
		} catch(Exception e) {
			throw new WebApplicationException(e,Response.Status.BAD_REQUEST);
		}
	}

	protected void outputReports(OutputStream os, TrafficIncidentReports reports) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(TrafficIncidentReports.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(reports, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
	
	protected void outputReport(OutputStream os, TrafficIncidentReport reports) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(TrafficIncidentReports.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(reports, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
}
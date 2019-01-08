package assign.services;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import assign.domain.TrafficIncidentReport;
import assign.domain.TrafficIncidentReports;

public class TestTrafficIncidentReportsServiceImpl {
	
	TrafficIncidentReportsService trafficService = null;
       Logger testLogger = Logger.getLogger("testlogger");
	
	@Before
	public void setUp() {
		String host = "";
		String dbname = "cs378_devdatta_thi";
		String username = "";
		String password = "";
		String dburl = "jdbc:mysql://" + host + ":3306/" + dbname + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		trafficService = new TrafficIncidentReportsServiceImpl(dburl, username, password);
	}
	
	//POST
	@Test
	public void testAddReport() {
		try {
			TrafficIncidentReport r = new TrafficIncidentReport();
			r.setPublishedDate("July 2018");
			r.setIssueReported("Car Crash");
			r.setAddress("21st and Guadalupe ST");
			r.setZipcode("78705");
			r = trafficService.addReport(r);
			
			TrafficIncidentReport r1 = trafficService.getReport(r.getId());
			
			assertEquals(r1.getPublishedDate(), r.getPublishedDate());
			assertEquals(r1.getIssueReported(), r.getIssueReported());
			assertEquals(r1.getAddress(), r.getAddress());
			assertEquals(r1.getZipcode(), r.getZipcode());
			assertNotNull(r.getId());
			assertNotNull(r1.getId());
			assertEquals(r1.getId(), r.getId());
			assertTrue(trafficService.deleteReport(r.getId()));		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//GET
   @Test
	public void testGetReport() {
	try {
		TrafficIncidentReport r = new TrafficIncidentReport();
		r.setPublishedDate("July 2018");
		r.setIssueReported("Car Crash");
		r.setAddress("21st and Guadalupe ST");
		r.setZipcode("78705");
		r = trafficService.addReport(r);
		assertNotNull(r.getId());
		
	    TrafficIncidentReport r1 = trafficService.getReport(r.getId());
	    
	    testLogger.info(r1.getPublishedDate());
	    testLogger.info(r1.getIssueReported());
		testLogger.info(r1.getAddress());
		testLogger.info(r1.getZipcode());
	    testLogger.info(String.valueOf(r1.getId()));
	    assertEquals(r1.getPublishedDate(), "July 2018");
		assertEquals(r1.getIssueReported(), "Car Crash");
		assertEquals(r1.getAddress(), "21st and Guadalupe ST");
		assertEquals(r1.getZipcode(), "78705");
		assertNotNull(r1.getId());
		assertEquals(r1.getId(), r.getId());
	    assertTrue(trafficService.deleteReport(r.getId()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
   }
   
   //DELETE
   @Test
	public void testDeleteReport() {
	try {
		TrafficIncidentReport r = new TrafficIncidentReport();
		r.setPublishedDate("July 2018");
		r.setIssueReported("Car Crash");
		r.setAddress("21st and Guadalupe ST");
		r.setZipcode("78705");
		r = trafficService.addReport(r);

	    assertTrue(trafficService.deleteReport(r.getId()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
  }
   
   //PUT
   @Test
	public void testUpdateReport() {
	try {
		TrafficIncidentReport r = new TrafficIncidentReport();
		r.setPublishedDate("July 2018");
		r.setIssueReported("Car Crash");
		r.setAddress("21st and Guadalupe ST");
		r.setZipcode("78705");
		r = trafficService.addReport(r);
		assertNotNull(r.getId());
		
		TrafficIncidentReport r1 = new TrafficIncidentReport();
		r1.setPublishedDate("May 2018");
		r1.setIssueReported("Car Crash");
		r1.setAddress("32nd Street");
		r1.setZipcode("78717");
		trafficService.updateReport(r.getId(), r, r1); 
		
		assertEquals(r.getPublishedDate(), "May 2018");
		assertEquals(r.getIssueReported(), "Car Crash");
		assertEquals(r.getAddress(), "32nd Street");
		assertEquals(r.getZipcode(), "78717");
	    assertTrue(trafficService.deleteReport(r.getId()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
 }
}

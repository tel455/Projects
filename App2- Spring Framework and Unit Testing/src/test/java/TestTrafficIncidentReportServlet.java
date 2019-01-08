//package test.java;
//package main.java;
//import main.java.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.MalformedURLException;

public class TestTrafficIncidentReportServlet
{
	TrafficIncidentReportServlet trafficController = new TrafficIncidentReportServlet();
	TrafficService mockTraffic = null;

	@Before
	public void setUp()
	{
		mockTraffic = mock(TrafficService.class);		
		trafficController.setTrafficService(mockTraffic);
	}
	
	@Test
	public void thisAlwaysPasses() 
	{
    }
	
	@Test
	public void testWelcomePage() 
	{
		String response = trafficController.welcome();
		assertEquals("Please provide a query to begin. An example would be assignment2/trafficincidentreports", response);
	}

	@Test
	public void testMainPage()
	{
		String response = trafficController.mainPage();
		assertEquals("Welcome to Traffic Incident Report statistics calculation page. Please provide issue_reported as query parameter.\n", response);
	}

	@Test
	public void testInputDataEmptyString() throws MalformedURLException
	{
		String response = trafficController.inputData("");
		assertEquals("Please provide an incident to search for after issue_reported.", response);
		verify(mockTraffic, never()).calculateIssues("");
	}

	@Test
	public void testInputDataCorrect() throws MalformedURLException
	{
		when(mockTraffic.calculateIssues("CoLlision")).thenReturn("Number of CoLlision incidents: 35. Risk level: Moderate.\n");
		String response = trafficController.inputData("CoLlision");
		assertEquals("Number of CoLlision incidents: 35. Risk level: Moderate.\n", response);
	}

	@Test
	public void testInputDataIncorrect() throws MalformedURLException
	{
		when(mockTraffic.calculateIssues("amIdoingthisright")).thenReturn("Number of amIdoingthisright incidents: 0. Risk level: Rare.\n");
		String response = trafficController.inputData("amIdoingthisright");
		assertEquals("Number of amIdoingthisright incidents: 0. Risk level: Rare.\n", response);
	}
}
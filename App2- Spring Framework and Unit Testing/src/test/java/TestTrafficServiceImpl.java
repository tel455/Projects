import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

public class TestTrafficServiceImpl 
{	
	TrafficService trafficService;
	
	@Before
	public void setup() 
	{
		trafficService = new TrafficServiceImpl();
	}

	@Test 
	public void testCalculateIssuesVeryLow() throws MalformedURLException
	{
		String ret = trafficService.calculateIssues("collision with injury");
		assertEquals("Number of collision with injury incidents: 8. Risk level: Very Low.\n", ret);
	}
	
	@Test 
	public void testCalculateIssuesRare() throws MalformedURLException
	{
		String ret = trafficService.calculateIssues("ABCDEF");
		assertEquals("Number of ABCDEF incidents: 0. Risk level: Rare.\n", ret);
	}
	
	@Test 
	public void testCalculateIssuesVeryHigh() throws MalformedURLException
	{
		String ret = trafficService.calculateIssues("Traffic Hazard");
		assertEquals("Number of Traffic Hazard incidents: 144. Risk level: Very High.\n", ret);
	}
	
	@Test 
	public void testCalculateIssuesModerate() throws MalformedURLException
	{
		String ret = trafficService.calculateIssues("Traffic impediment");
		assertEquals("Number of Traffic impediment incidents: 48. Risk level: Moderate.\n", ret);
	}	
}

import java.io.IOException;

//added
import java.net.*;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//reference the spring email service example for help
@Controller
public class TrafficIncidentReportServlet
{
	private TrafficService trafficServiceImpl;
	
	//constructor
	public TrafficIncidentReportServlet() 
	{
	}
	
	//inject
	public void setTrafficService(TrafficService trafficServiceImpl) 
	{
		this.trafficServiceImpl = trafficServiceImpl;
	}

	@ResponseBody
    @RequestMapping(value = "/")
    public String welcome()
    {
        return "Please provide a query to begin. An example would be assignment2/trafficincidentreports";
    }

	@ResponseBody
	@RequestMapping(value = "/trafficincidentreports")
	public String mainPage() 
	{
		return "Welcome to Traffic Incident Report statistics calculation page. Please provide issue_reported as query parameter.\n";
	}

	@ResponseBody
	@RequestMapping(value = "/trafficincidentreports", params = {"issue_reported"}, method = RequestMethod.GET)
	public String inputData(@RequestParam("issue_reported") String issue_reported) throws MalformedURLException
	{
		if(!issue_reported.equals(""))
		{
			return this.trafficServiceImpl.calculateIssues(issue_reported);
		}
		// if (issue_reported.equals(""))
		else
		{
			return "Please provide an incident to search for after issue_reported.";
		}
	}
}

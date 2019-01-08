package assign.services;

import java.util.List;

// import assign.domain.Course;
import assign.domain.TrafficIncidentReport;

public interface TrafficIncidentReportsService {

	public TrafficIncidentReport addReport(TrafficIncidentReport report) throws Exception;
	
	public TrafficIncidentReport getReport(int id) throws Exception;

    public TrafficIncidentReport getReport_correct(int id) throws Exception;

    public boolean updateReport(int id, TrafficIncidentReport report, TrafficIncidentReport update) throws Exception;

    public boolean deleteReport(int id) throws Exception;

    public List<TrafficIncidentReport> getZipcode(String zipcode) throws Exception;
    
    public List<TrafficIncidentReport> getAllReports() throws Exception;
}

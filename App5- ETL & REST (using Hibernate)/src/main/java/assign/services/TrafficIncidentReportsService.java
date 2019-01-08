package assign.services;

import java.util.List;
import assign.domain.ETL;

public interface TrafficIncidentReportsService
{
	public void populateDatabase() throws Exception;
	public int getZipcodeImpl(String zipcode) throws Exception;
	public int getPublishedDateImpl(String published_date) throws Exception;
	public int getIssueReportedImpl(String issue_reported) throws Exception;
	public int getAddressImpl(String address) throws Exception;
	public List<ETL> parseXML1(List<ETL> reports) throws Exception;
	public List<ETL> parseXML2(List<ETL> reports) throws Exception;
}

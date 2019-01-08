package assign.domain;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrafficIncidentReport 
{

	private String traffic_report_id;
	private String published_date;
	private String issue_reported;
	private String latitude;
	private String longitude;
	private String address;
	
	public String getTrafficId() {
		return traffic_report_id;
	}
	
	public void setTrafficId(String id) {
		this.traffic_report_id = id;
	}
	
	public String getPublishedDate() {
		return published_date;
	}
	
	public void setPublishedDate(String date) {
		this.published_date = date;
	}
	
	public String getIssueReported() {
		return issue_reported;
	}
	
	public void setIssueReported(String issue) {
		this.issue_reported = issue;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}

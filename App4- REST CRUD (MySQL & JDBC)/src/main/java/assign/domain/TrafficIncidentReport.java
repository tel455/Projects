package assign.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrafficIncidentReport {
	
	private String published_date;
	private String issue_reported;
	private String address;
	private String zipcode;
	private int id;
	
	public String getPublishedDate() {
		return published_date;
	}
	
	public String getIssueReported() {
		return issue_reported;
	}

	public String getAddress() {
		return address;
	}

	public String getZipcode() {
		return zipcode;
	}
	
	public void setIssueReported(String issue_reported) {
		this.issue_reported = issue_reported;
	}
	
	public void setPublishedDate(String published_date) {
		this.published_date = published_date;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
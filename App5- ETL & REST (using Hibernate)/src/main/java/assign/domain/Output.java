package assign.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "output")
@XmlAccessorType(XmlAccessType.FIELD)
public class Output {
	
    private String published_date;
    private String issue_reported;
    private String address;
    private String zipcode;
    private int count;
    
    public Output() 
    {
    	// this form used by Hibernate
    }
    
    public String getPublished_Date() 
    {
		return published_date;
    }

    public void setPublished_Date(String published_date) 
    {
		this.published_date = published_date;
    }
    
    public String getIssue_Reported() 
    {
    	return this.issue_reported;
    }
    
    public void setIssue_Reported(String issue_reported) 
    {
    	this.issue_reported = issue_reported;
    }
    
    public String getAddress() 
    {
		return address;
    }

    public void setAddress(String address) 
    {
		this.address = address;
    }
    
    public String getZipcode() 
    {
    	return zipcode;
    }
    
    public void setZipcode(String zipcode) 
    {
    	this.zipcode = zipcode;
    }
    
    public void setCount(int count) 
    {
    	this.count = count;
    }
    
    public int getCount() 
    {
    	return this.count;
    }
}
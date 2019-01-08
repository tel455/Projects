package assign.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table( name = "Traffic_Records" )
public class ETL 
{
	private Long id;
    private String published_date;
    private String issue_reported;
    private String address;
    private String zipcode;
    
    public ETL() 
    {
    	// this form used by Hibernate
    }
    
    public ETL(String published_date, String issue_reported, String address)
    {
    	this.published_date = published_date;
    	this.issue_reported = issue_reported;
    	this.address = address;
    }
    
    @Id    
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId()
    {
		return id;
    }

    public void setId(Long id)
    {
		this.id = id;
    }
    
	@Column(name = "date")
    public String getDate()
	{
		return published_date;
    }

    public void setDate(String published_date) 
    {
		this.published_date = published_date;
    }
    
    @Column(name="issue")
    public String getIssue()
    {
    	return this.issue_reported;
    }
    
    public void setIssue(String issue_reported) 
    {
    	this.issue_reported = issue_reported;
    }
    
    @Column(name="address")
    public String getAddress() 
    {
		return address;
    }

    public void setAddress(String address)
    {
		this.address = address;
    }
    
    @Column(name="zipcode")
    public String getZipcode()
    {
    	return zipcode;
    }
    
    public void setZipcode(String zipcode)
    {
    	this.zipcode = zipcode;
    }
}
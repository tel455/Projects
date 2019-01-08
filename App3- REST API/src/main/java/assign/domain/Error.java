package assign.domain;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "ouput")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error 
{
	    
    private String error = null;
    
    public String getError() 
    {
    	return error;
    }
    
    public void setError(String error) 
    {
    	this.error = error;
    }
}
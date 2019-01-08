package assign.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.dbcp.BasicDataSource;

// import assign.domain.Course;
// import assign.domain.NewCourse;
import assign.domain.TrafficIncidentReport;

public class TrafficIncidentReportsServiceImpl implements TrafficIncidentReportsService {

	String dbURL = "";
	String dbUsername = "";
	String dbPassword = "";
	DataSource ds;

	// DB connection information would typically be read from a config file.
	public TrafficIncidentReportsServiceImpl(String dbUrl, String username, String password) {
		this.dbURL = dbUrl;
		this.dbUsername = username;
		this.dbPassword = password;
		
		ds = setupDataSource();
	}
	
	public DataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername(this.dbUsername);
        ds.setPassword(this.dbPassword);
        ds.setUrl(this.dbURL);
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        return ds;
    }
	
	public TrafficIncidentReport addReport(TrafficIncidentReport report) throws Exception {
		if ((report.getPublishedDate() != null && report.getPublishedDate().length() > 0 ) 
				&& (report.getIssueReported() != null && report.getIssueReported().length() > 0)
				&& (report.getAddress() != null && report.getAddress().length() > 0)
				&& (report.getZipcode() != null && report.getZipcode().length() > 0)) {
			Connection conn = ds.getConnection();
			String insert = "insert traffic_reports (published_date, issue_reported, address, zipcode) VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(insert,
	                Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, report.getPublishedDate());
			stmt.setString(2, report.getIssueReported());
			stmt.setString(3, report.getAddress());
			stmt.setString(4, report.getZipcode());
			
			int affectedRows = stmt.executeUpdate();
	
	        if (affectedRows == 0) {
	        	throw new WebApplicationException(Response.Status.BAD_REQUEST);
	        }
	        
	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	report.setId(generatedKeys.getInt(1));
	        }
	        else {
	        	throw new WebApplicationException(Response.Status.BAD_REQUEST);
	        }
	        
	        // Close the connection
	        conn.close();
	        
			return report;
		}
		else 
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
	}

	public TrafficIncidentReport getReport(int id) throws Exception {
		String query = "select * from traffic_reports where id=" + id;
		Connection conn = ds.getConnection();
		PreparedStatement s = conn.prepareStatement(query);
		ResultSet r = s.executeQuery();
		
		if (!r.next()) {
			return null;
		}
		
		TrafficIncidentReport rep = new TrafficIncidentReport();
		rep.setPublishedDate(r.getString("published_date"));
		rep.setIssueReported(r.getString("issue_reported"));
		rep.setAddress(r.getString("address"));
		rep.setZipcode(r.getString("zipcode"));
		rep.setId(r.getInt("id"));
		conn.close();
		return rep;
	}
	
	public List<TrafficIncidentReport> getAllReports() throws Exception {
		List<TrafficIncidentReport> list = new ArrayList<TrafficIncidentReport>();
		String query = "select id from traffic_reports order by id";
		Connection conn = ds.getConnection();
		PreparedStatement s = conn.prepareStatement(query);
		ResultSet r = s.executeQuery();
		while (r.next()) {
			TrafficIncidentReport rep = new TrafficIncidentReport();
	    	rep.setId(r.getInt("id"));
	    	list.add(rep);
		}
		conn.close();
		return list;
	}

    public TrafficIncidentReport getReport_correct(int id) throws Exception {
	String query = "select * from traffic_reports where id=?";
	Connection conn = ds.getConnection();
	PreparedStatement s = conn.prepareStatement(query);
	s.setString(1, String.valueOf(id));
	ResultSet r = s.executeQuery();
	
	if (!r.next()) {
	    return null;
	}
	TrafficIncidentReport rep = new TrafficIncidentReport();
	rep.setPublishedDate(r.getString("published_date"));
	rep.setIssueReported(r.getString("issue_reported"));
	rep.setAddress(r.getString("address"));
	rep.setZipcode(r.getString("zipcode"));
	rep.setId(r.getInt("id"));
	conn.close();
	return rep;
    }

    public List<TrafficIncidentReport> getZipcode(String zipcode) throws Exception {
    	List<TrafficIncidentReport> list = new ArrayList<TrafficIncidentReport>();
    	String query = "select id from traffic_reports where zipcode=" + zipcode;
    	Connection conn = ds.getConnection();
		PreparedStatement s = conn.prepareStatement(query);
		ResultSet r = s.executeQuery();
		while (r.next()) {
			TrafficIncidentReport rep = new TrafficIncidentReport();
	    	rep.setId(r.getInt("id"));
	    	list.add(rep);
		}
		conn.close();
		return list;
    }

    public boolean updateReport(int id, TrafficIncidentReport report, TrafficIncidentReport update) throws Exception {
    	if (update.getPublishedDate() != null && update.getPublishedDate().length() > 0 ) {
			report.setPublishedDate(update.getPublishedDate());
		}
    	else {
    		return false;
    	}
    	if (update.getIssueReported() != null && update.getIssueReported().length() > 0) {
			report.setIssueReported(update.getIssueReported());
		}
    	else {
    		return false;
    	}
    	if (update.getAddress() != null && update.getAddress().length() > 0) {
			report.setAddress(update.getAddress());
		}
    	else {
    		return false;
    	}
    	if (update.getZipcode() != null && update.getZipcode().length() > 0) {
			report.setZipcode(update.getZipcode());
		}
    	else {
    		return false;
    	}
    	
    	String query = "update traffic_reports set published_date=?, issue_reported=?, address=?, zipcode=? where id=" + id;
		Connection conn = ds.getConnection();
		PreparedStatement s = conn.prepareStatement(query);
		
		s.setString(1, report.getPublishedDate());
		s.setString(2, report.getIssueReported());
		s.setString(3, report.getAddress());
		s.setString(4, report.getZipcode());
		int affectedRows = s.executeUpdate();
		conn.close();
		
		if (affectedRows > 0) {
			return true;
		}
		else
			return false;
	}

    public boolean deleteReport(int id) throws Exception {
		String query = "delete from traffic_reports where id=?";
		Connection conn = ds.getConnection();
		PreparedStatement s = conn.prepareStatement(query);
		s.setString(1, String.valueOf(id));
		int r = s.executeUpdate();
		conn.close();
		if (r > 0)
			return true;
		else
			return false;
	}

}

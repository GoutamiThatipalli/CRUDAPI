package model;
import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "Student") 

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private String userid;
	private String password;
	private String mobileno;
	private String email;
	private String city;
	private String pincode;
	private String sessionid;
	public String getUserid() {
		return userid;
	}
	@XmlElement 
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	@XmlElement 
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileno() {
		return mobileno;
	}
	@XmlElement 
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	@XmlElement 
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	@XmlElement 
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	@XmlElement 
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	@Override
	public String toString() {
		return "student [userid=" + userid + ", password=" + password + ", mobileno=" + mobileno + ", email=" + email
				+ ", city=" + city + ", pincode=" + pincode + "]";
	}
	



}

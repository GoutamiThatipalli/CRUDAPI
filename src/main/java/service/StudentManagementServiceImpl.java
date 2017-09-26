package service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.Student;

public class StudentManagementServiceImpl implements StudentManagementService {
	Boolean status = false;
	static Connection con;
	static PreparedStatement ps;
	ResultSet rs;

	public Boolean searchStudent(Student s) throws SQLException {
		con = util.connection.getConnection();
		ps = con.prepareStatement("select * from Register where userid=? and password=?");
		ps.setString(1, s.getUserid());
		ps.setString(2, s.getPassword());
		rs = ps.executeQuery();
		if (rs.next()) {
			status = true;
		}
		return status;
	}

	public int insertStudent(Student s) {
		try{
		con = util.connection.getConnection();
		ps = con.prepareStatement("insert into Register values(?,?,?,?,?,?)");
		ps.setString(1, s.getUserid());
		ps.setString(2, s.getPassword());
		ps.setString(3, s.getMobileno());
		ps.setString(4, s.getEmail());
		ps.setString(5, s.getCity());
		ps.setString(6, s.getPincode());
		int a = ps.executeUpdate();
		return a;
		}
		catch(SQLException e){
			return 0;
		}
	}
	public boolean authenticate(String jsessionID) throws SQLException{
		if (jsessionID != null){
			con = util.connection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from Sessionmgmt where sessionid=?");
			ps.setString(1, jsessionID);
			rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}
			return status;
		}
		return false;
	}
				

	public void insertSession(Student s, String sessionid) throws SQLException {
		con = util.connection.getConnection();
		ps = con.prepareStatement("insert into Sessionmgmt values(?,?)");
		ps.setString(1, sessionid);
		ps.setString(2, s.getUserid());
		ps.executeUpdate();
	}

	public boolean logoutSession(String sessionid) throws SQLException {
		con = util.connection.getConnection();
		ps = con.prepareStatement("delete from Sessionmgmt where sessionid=?");
		ps.setString(1, sessionid);
		ps.executeUpdate();
		return true;
	}

	public List<Student> getAllStudents() throws SQLException {
		List<Student> studentList = new LinkedList<Student>();
		con = util.connection.getConnection();
		PreparedStatement pstmt = con.prepareStatement("select * from Register");
		rs = pstmt.executeQuery();
		Student student;
		while (rs.next()) {
			student = new Student();
			student.setUserid(rs.getString(1));
			student.setPassword(rs.getString(2));
			student.setMobileno(rs.getString(3));
			student.setEmail(rs.getString(4));
			student.setCity(rs.getString(5));
			student.setPincode(rs.getString(6));
			studentList.add(student);
		}
		return studentList;
	}

	public Student getDetails(String userid) {
		Student s = new Student();
		try {
			con = util.connection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from Register where userid=?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s.setUserid(rs.getString(1));
				s.setPassword(rs.getString(2));
				s.setMobileno(rs.getString(3));
				s.setEmail(rs.getString(4));
				s.setCity(rs.getString(5));
				s.setPincode(rs.getString(6));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;

	}
	public String getstudentProfile(String sessionid){
		
		Student s = new Student();
		try {
			con = util.connection.getConnection();
			PreparedStatement ps = con.prepareStatement("select userid from Sessionmgmt where sessionid=?");
			ps.setString(1, sessionid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s.setUserid(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s.getUserid();
	}

	public Student updateStudent(String userid, Student s) throws SQLException {
		con = util.connection.getConnection();
		ps = con.prepareStatement("update Register set password=?,mobileno=?,email=?,city=?,pincode=? where userid=?");
		ps.setString(1, s.getPassword());
		ps.setString(2, s.getMobileno());
		ps.setString(3, s.getEmail());
		ps.setString(4, s.getCity());
		ps.setString(5, s.getPincode());
		ps.setString(6, s.getUserid());
		int status = ps.executeUpdate();
		if(status==1){
		return s;
		}else {
			return null;
		}
	}

	public int deleteStudent(String userId) throws SQLException {
		con = util.connection.getConnection();
		ps = con.prepareStatement("delete from Register where userid=?");
		ps.setString(1, userId);
		int status1 = ps.executeUpdate();
		return status1;
	}

}

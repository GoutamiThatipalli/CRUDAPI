package service;

import java.sql.SQLException;
import java.util.List;

import model.Student;

public interface StudentManagementService {
	
	public Student getDetails(String userid);

	public Student updateStudent(String userid, Student s) throws SQLException;

	public int insertStudent(Student s);

	public int deleteStudent(String userId) throws SQLException;

	public List<Student> getAllStudents() throws SQLException;

	public Boolean searchStudent(Student s) throws SQLException;

	public void insertSession(Student s, String sessionid) throws SQLException;

	public boolean logoutSession(String sessionid) throws SQLException;

	public boolean authenticate(String jsessionID) throws SQLException;
	public String getstudentProfile(String sessionid);

}
package Controller;

import java.sql.SQLException;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Result;
import model.Student;
import service.StudentManagementService;
import service.StudentManagementServiceImpl;

@Path("/student-mgmt")

public class StudentManagementController {
	StudentManagementService sm = new StudentManagementServiceImpl();

	@GET
	@Path("/student/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Student getDetails(@PathParam("id") String userid) {
		return sm.getDetails(userid);
	}
	
	@GET
	@Path("/student/profile")
	@Produces(MediaType.APPLICATION_JSON)

	public Student getUserProfile(@Context HttpHeaders headers) throws SQLException {
		Cookie cookie = headers.getCookies().get("x-auth-token");
		if (cookie !=null && sm.authenticate(cookie.getValue()) == true) {
			return sm.getDetails(sm.getstudentProfile(cookie.getValue()));
			
		}
		return null;
	}

	@GET
	@Path("/student")
	@Produces(MediaType.APPLICATION_JSON)
	public Result getAllStudents(@Context HttpHeaders headers) throws SQLException {
		Result result = new Result();
		Cookie cookie = headers.getCookies().get("x-auth-token");
		if (cookie !=null && sm.authenticate(cookie.getValue()) == true) {
			result.setData(sm.getAllStudents());
			result.setStatus(Status.OK.toString());
		}else{
			result.setStatus(Status.UNAUTHORIZED.toString());
			 
		}
		return result;
	}

	@POST
	@Path("/student/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchStudent(Student s,@Context HttpHeaders headers) throws SQLException {
		Cookie cookie = headers.getCookies().get("x-auth-token");
		
		if (sm.searchStudent(s) == true && cookie ==null) {
			String uuid = UUID.randomUUID().toString();
			sm.insertSession(s, uuid);
			Cookie cookie1 =(new NewCookie("userid",s.getUserid()));
			return Response.ok(Status.OK).cookie(new NewCookie("x-auth-token", uuid)).build();
		}
		else if(cookie!=null){
			return null;
		}
		return Response.ok(Status.UNAUTHORIZED).build();
	}

	@POST
	@Path("/student")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result insertStud(Student s) throws SQLException {
		Result result = new Result();
		if(sm.insertStudent(s)==1){
			result.setData(sm.insertStudent(s));
			result.setStatus(Status.OK.toString());
			result.setMessage("Data Inserted Successfully");
		}
		else{
			result.setStatus(Status.CONFLICT.toString());
			result.setMessage("Username is already exist try with different one");
		}
		return result; 
	}

	@PUT
	@Path("student/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result updateStudentByID(@PathParam("id") String userid, Student s,@Context HttpHeaders headers) throws SQLException {
		Result result = new Result();
		Cookie cookie = headers.getCookies().get("x-auth-token");
		if (cookie != null && sm.authenticate(cookie.getValue()) == true) {
			if(sm.updateStudent(userid, s)==null){
			result.setStatus(Status.FORBIDDEN.toString());
		}
			else{
				result.setData(sm.updateStudent(userid, s));
				result.setStatus(Status.OK.toString());
				result.setMessage("Updated Successfully");
				}
			}
		else{
			result.setStatus(Status.UNAUTHORIZED.toString());
		}
		return result; 
	}

	@DELETE
	@Path("student/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Result deleteStudent(@PathParam("id") String userid,@Context HttpHeaders headers) throws SQLException {
		Result result = new Result();
		Cookie cookie = headers.getCookies().get("x-auth-token");
		if (cookie != null && sm.authenticate(cookie.getValue()) == true) {
			result.setData(sm.deleteStudent(userid));
			result.setStatus(Status.OK.toString());
			result.setMessage("Deleted Successfully");
		}
		else{
			result.setStatus(Status.UNAUTHORIZED.toString());
		}
		return result;
	}

	@DELETE
	@Path("student/logout")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Result logout(@Context HttpHeaders headers) throws SQLException {
		Result result = new Result();
		Cookie cookie = headers.getCookies().get("x-auth-token");
		if (cookie != null && sm.authenticate(cookie.getValue()) == true) {
			Boolean value=sm.logoutSession(cookie.getValue());
			if(value==true){
			result.setData(value);
			result.setStatus(Status.OK.toString());
			}
			else
			{
				result.setStatus(Status.NOT_FOUND.toString());;
			}
		}
		else{
			result.setStatus(Status.UNAUTHORIZED.toString());
			 
		}
		return result;
	}
}

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
	
	static Connection con;
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
        
    }
    public static Connection getConnection() throws SQLException 
    {
        if (con == null)
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","pramati123");
        return con;
    }
    public static void closed(Connection conn) throws SQLException
    {
    	conn.close();
		
    }
}
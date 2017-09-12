package buyNsell.DatabaseConnection;

import java.sql.*;

public class JdbcManager 
{
	public static Connection establishConnection()
	{
		Connection con = null;
    	try 
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
      		con = DriverManager.getConnection("jdbc:mysql://localhost/buynsell","root","");
       	} 
       	catch(Exception e) 
       	{
      		System.err.println("Exception: " + e.getMessage());
    	} 
    	return con;
  	}
}
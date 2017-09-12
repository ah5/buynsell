package buyNsell.DatabaseConnection;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JdbcUtil 
{
	static Connection con=null;
	static Statement stmt=null;
	static ResultSet rs=null;
	static ResultSetMetaData resSetMtDt=null;
	
	public static ArrayList exeQuery(String query)
	{
		ArrayList result=new ArrayList();
		try 
		{
			con=JdbcManager.establishConnection();
			stmt = con.createStatement();
			rs=stmt.executeQuery(query);
			resSetMtDt=rs.getMetaData();
			int column=resSetMtDt.getColumnCount();
			while (rs.next())
    		{
    			ArrayList data=new ArrayList();
    			for(int i=1;i<=column;i++)	
    			{
    				String s=rs.getString(i);
    				data.add(s);
    			}
    			result.add(data); 		
			}
			con.close();         	
		}	
		catch (SQLException ex)
		{
            ex.printStackTrace();            
            System.err.println("ERROR: Problems with processing DB...."+ex);
        }
   		return result;
 	}
	
	public static int updtQuery(String query)
	{
		int n=-1;
		
		try
    	{
    		con=JdbcManager.establishConnection();
			stmt = con.createStatement();
			n=stmt.executeUpdate(query);
			System.out.println("Updating...");
  			con.close();
		} 
		catch (SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("ERROR: Problems with processing DB....");
        }
 		
 		System.out.println("Updating successfull...");
   		return n;
	}
	
	public static ResultSet getResultSet()
	{
		return rs;
	}
	
	public static int noOfRecords() throws Exception
	{
		int i=0;
		while(rs.next())
		{
			i++;
		}
		return i;
	}
	
	public static boolean isUserValid(String u, String p) throws Exception
	{
		while(rs.next())
		{
			String tempU=rs.getString("UserID");
			String tempP=rs.getString("Password");
			if(tempU.equals(u) && tempP.equals(p))
				return true;
		}
		return false;
	}
	
	public static String fromJavaToSqlDateFormat(java.util.Date date)
	{
		int year=date.getYear();
		int month=date.getMonth()+1;
		int day=date.getDate();
		String d="";
    	String m="";
    	String dy="";
    	if(month<10)
			m="0"+month;
		else
			m=""+month;
		if(day<10)
			dy="0"+day;
		else
			dy=""+day;
			
		d=year+1900+"-"+m+"-"+dy;
		return d;
	}

	public static String fromJavaDateToSQLString(java.util.Date p)
	{
		Calendar dateCnvrt = Calendar.getInstance();
 		dateCnvrt.setTime(p);
 		int y=dateCnvrt.get(Calendar.YEAR);
		int m=dateCnvrt.get(Calendar.MONTH)+1;
		int d=dateCnvrt.get(Calendar.DATE);
		int hh=p.getHours();
		int mm=p.getMinutes();
		int ss=p.getSeconds();
		String temp	=	String.valueOf(y)	+	"-"	+	String.valueOf(m)	+	"-" +	String.valueOf(d)	+	" "	+
						String.valueOf(hh)	+	":"	+	String.valueOf(mm)	+	":"	+	String.valueOf(ss);
		return temp;
	}
	
	public static java.util.Date fromSQLStringToJavaDate(String date)
	{
		java.util.Date d=new java.util.Date();
		int year=Integer.parseInt(date.substring(0,4))-1900;
		int month=Integer.parseInt(date.substring(5,7))-1;
		int day=Integer.parseInt(date.substring(8,10));
		int hh=Integer.parseInt(date.substring(11,13));
		int mm=Integer.parseInt(date.substring(14,16));
		int ss=Integer.parseInt(date.substring(17,19));
		d.setYear(year);
		d.setMonth(month);
		d.setDate(day);
		d.setHours(hh);
		d.setMinutes(mm);
		d.setSeconds(ss);
		return d;
	}
}
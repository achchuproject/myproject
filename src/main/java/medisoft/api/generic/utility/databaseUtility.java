package medisoft.api.generic.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class databaseUtility {
	
		static Connection con=null;
		static ResultSet result  = null;
		
		 FileUtility fLib=new FileUtility();
		public  void getDbConnection(String url,String username,String password) throws SQLException
		{
			Driver driverRef;
			try
			{
				 driverRef=new Driver();
				DriverManager.registerDriver(driverRef);
				  con = DriverManager.getConnection(url, username, password);
			}catch(Exception e) {}
			}
		public  void getDbConnection() throws SQLException
		{
			try
			{
				Driver driver=new Driver();
				DriverManager.registerDriver(driver);
				 con = DriverManager.getConnection(fLib.getDataFromPropertiesFile("DBUrl"),fLib.getDataFromPropertiesFile("DB_Username"), fLib.getDataFromPropertiesFile("DB_Password"));
			}catch(Exception e) {}
			}
		public   void closeDbConnection() throws SQLException
		{
			try
			{
				con.close();
			}catch(Exception e) {}
		}
	public  ResultSet executeConSelectQuery(String query) throws SQLException
	{
		
		
		try
		{
			Statement stat = con.createStatement();
			 result = stat.executeQuery(query);

		}catch(Exception e) {}
			return result; 
	}
	public  int executeConNonSelectQuery(String query) throws SQLException
	{
		int result=0;
		try
		{
			Statement stat = con.createStatement();
			 result = stat.executeUpdate(query);

		}catch(Exception e) {}
			return result; 
	}

public  boolean executeQueryVerifyAndGetData(String query,int columnIndex,String expectData) throws SQLException
{
	boolean flag=false;
	ResultSet result = con.createStatement().executeQuery(query);
	while(result.next())
	{
		if(result.getString(columnIndex).equals(expectData))
		{
			flag=true;
			break;
		}
	}
	if(flag)
	{
		System.out.println(expectData+"=====>data verified in data base table");
		return true;
	}else
	{
		System.out.println(expectData+"=====>data not verified in data base table");
		return false;
	}
}

}

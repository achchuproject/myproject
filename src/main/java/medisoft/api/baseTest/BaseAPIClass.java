package medisoft.api.baseTest;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import medisoft.api.generic.utility.FileUtility;
import medisoft.api.generic.utility.databaseUtility;
import medisoft.api.generic.utility.javaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	public javaUtility jLib=new javaUtility();
	public FileUtility fLib=new FileUtility();
	public databaseUtility dLib=new databaseUtility();
	public static RequestSpecification specReqObj;
	public  static ResponseSpecification specResObj;
	@BeforeSuite
	public void configBS() throws SQLException, IOException
	{
		dLib.getDbConnection();
		System.out.println("==========connect db=============");
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
		builder.setBaseUri(fLib.getDataFromPropertiesFile("baseURI"));
		 specReqObj = builder.build();
		 
		 ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
		 resBuilder.expectContentType(ContentType.JSON);
	  specResObj = resBuilder.build();
	}
	@AfterSuite
	public void configAS() throws SQLException
	{
		dLib.closeDbConnection();
		System.out.println("====================disconnect db=======================");
		
	}
}

package ninzaHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import medisoft.api.baseTest.BaseAPIClass;
import medisoft.api.constants.endpoints.IEndPointsInterface;
import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public class addSingleProject extends BaseAPIClass {
	@Test
	public void postRequest() throws IOException, SQLException
	{
		String BaseUri=fLib.getDataFromPropertiesFile(baseURI);
		String actMess="Successfully Added";
		
		JSONObject jObj=new JSONObject();
		jObj.put("createdBy", "Archana");
		jObj.put("projectName", "Medisoft"+jLib.getRandomNumber());
		jObj.put("status", "Created");
		jObj.put("teamSize", 0);
		Response resp=given().contentType(ContentType.JSON)
				.body(jObj.toJSONString())
				//.spec(specReqObj)
		.when()
		.post("http://49.249.28.218:8091/addProject");
		resp.then().log().all();
		resp.then().spec(specResObj);
		String stat=resp.jsonPath().get("status");
		String projId=resp.jsonPath().get("projectId");
		System.out.println(stat);
		System.out.println(projId);
		
		resp.then().assertThat().statusCode(201);
		String actMsg = resp.jsonPath().get("msg");
		String projectName=resp.jsonPath().get("projectName");
		Assert.assertEquals(actMess, actMsg);
		
		boolean flag = dLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
		 Assert.assertTrue(flag, "project in DB is not verified");
		 
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8091");
		driver.findElement(By.name("username")).sendKeys("rmgyantra");
		driver.findElement(By.name("password")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block' and @type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		String txt = driver.findElement(By.xpath("//td[text()='"+projId+"']/parent::tr[@class='tr']/descendant::td[text()='"+stat+"']")).getText();
		assertEquals(stat, txt);
	}
	
	@Test
	public void postRequestWithOnGoingStatus() throws IOException, SQLException
	{
		
		//WebDriverManager.chromedriver().setup();
		// ChromeDriver driver=new ChromeDriver();
		String BaseUri=fLib.getDataFromPropertiesFile(baseURI);
		String actMess="Successfully Added";
		
		JSONObject jObj=new JSONObject();
		jObj.put("createdBy", "Archana");
		jObj.put("projectName", "Medisoft"+jLib.getRandomNumber());
		jObj.put("status", "Ongoing");
		jObj.put("teamSize", 0);
		Response resp=given().contentType(ContentType.JSON)
				.body(jObj.toJSONString())
				//.spec(specReqObj)
		.when()
		.post("http://49.249.28.218:8091/addProject");
		//.post(IEndPointsInterface.ADDProj);
		resp.then().log().all();
		resp.then().spec(specResObj);
		String stat=resp.jsonPath().get("status");
		String projId=resp.jsonPath().get("projectId");
		
		resp.then().assertThat().statusCode(201);
		String actMsg = resp.jsonPath().get("msg");
		String projectName=resp.jsonPath().get("projectName");
		Assert.assertEquals(actMess, actMsg);
		
		boolean flag = dLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
		 Assert.assertTrue(flag, "project in DB is not verified");
		 
		 WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("http://49.249.28.218:8091");
			driver.findElement(By.name("username")).sendKeys("rmgyantra");
			driver.findElement(By.name("password")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block' and @type='submit']")).click();
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			String txt = driver.findElement(By.xpath("//td[text()='"+projId+"']/parent::tr[@class='tr']/descendant::td[text()='"+stat+"']")).getText();
			assertEquals(stat, txt);
		
		}
	@Test
	public void postReqWithCompletedStatus() throws SQLException, IOException
	{
		String BaseUri=fLib.getDataFromPropertiesFile(baseURI);
		String actMess="Successfully Added";
		
		JSONObject jObj=new JSONObject();
		jObj.put("createdBy", "Archana");
		jObj.put("projectName", "Medisoft"+jLib.getRandomNumber());
		jObj.put("status", "Completed");
		jObj.put("teamSize", 0);
		Response resp=given().contentType(ContentType.JSON)
				.body(jObj.toJSONString())
				//.spec(specReqObj)
		.when()
		.post("http://49.249.28.218:8091/addProject");
		//.post(IEndPointsInterface.ADDProj);
		resp.then().log().all();
		resp.then().spec(specResObj);
		String stat=resp.jsonPath().get("status");
		String projId=resp.jsonPath().get("projectId");
		
		resp.then().assertThat().statusCode(201);
		String actMsg = resp.jsonPath().get("msg");
		String projectName=resp.jsonPath().get("projectName");
		Assert.assertEquals(actMess, actMsg);
		
		boolean flag = dLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
		 Assert.assertTrue(flag, "project in DB is not verified");
		 
		 WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("http://49.249.28.218:8091");
			driver.findElement(By.name("username")).sendKeys("rmgyantra");
			driver.findElement(By.name("password")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block' and @type='submit']")).click();
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			String txt = driver.findElement(By.xpath("//td[text()='"+projId+"']/parent::tr[@class='tr']/descendant::td[text()='"+stat+"']")).getText();
			assertEquals(stat, txt);

	}
	}


package ninzaHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createProject {
	@Test
	public void create()
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8091");
		driver.findElement(By.name("username")).sendKeys("rmgyantra");
		driver.findElement(By.name("password")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block' and @type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		driver.findElement(By.xpath("//input[@class='form-control' and @name='projectName']")).sendKeys("Dish_TV");
		driver.findElement(By.xpath("//input[@class='form-control' and @name='createdBy']")).sendKeys("Archana");
		driver.findElement(By.xpath("//label[@class='col-form-label']/following-sibling::select[@name='status']/descendant::option[text()='Created']")).click();
		driver.findElement(By.xpath("//input[@value='Add Project' and @class='btn btn-success']")).click();
		
	}

}

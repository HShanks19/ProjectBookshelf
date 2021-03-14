package com.qa.bookshelf.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class SeleniumTests {

	private RemoteWebDriver driver;
	
	@LocalServerPort // fetches the random port
	private int port;
	
	private WebDriverWait wait;

	@BeforeEach
	void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	}
	
	//create and read functionality
	
	@Test
	void testCreateBook() {
		
		WebDriverWait explicitWait = new WebDriverWait(driver, 5);
		
		this.driver.get("http://localhost:" + port + "/Index.html");
		
		WebElement addTitle = this.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[1]/div/input"));
		WebElement addAuthor = this.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[2]/div/input"));
		WebElement addYear = this.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[3]/div[2]/input"));
		WebElement addBook = this.driver.findElement(By.xpath("//*[@id=\"submitUpdate\"]"));
		
		//working
		addTitle.sendKeys("SeleniumTestTitle");
		addAuthor.sendKeys("SeleniumTestAuthor");
		addYear.sendKeys("2010");
		Select openGenre = new Select(this.driver.findElement(By.xpath("//*[@id=\"bookGenreSelect\"]")));
		openGenre.selectByVisibleText("Non-Fiction");
		
		addBook.sendKeys(Keys.ENTER);
		
		WebElement toShelf = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addedBook\"]/div/div/div[2]/div/div[2]/a")));

		toShelf.click();

		WebElement book3Header = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/article/div[2]/div[2]/div/div[1]/h5")));
		
		Assertions.assertTrue(book3Header.getText().contains("SeleniumTestTitle"));
		
	}
	
	
	// create and read review
	
	//@Test
	//void testReviewBook() {
		
	//	WebDriverWait explicitWait = new WebDriverWait(driver, 5);
		
	//	this.driver.get("http://localhost:" + port + "/toread.html");
		
	//	WebElement startBook = this.driver.findElement(By.xpath("//*[@id=\"output\"]/div[2]/div/div[2]/button[3]"));
		
	//	startBook.sendKeys(Keys.ENTER);
	//}
	
	//@AfterEach
	//void teardown() {
	//	driver.quit();
	//}
}

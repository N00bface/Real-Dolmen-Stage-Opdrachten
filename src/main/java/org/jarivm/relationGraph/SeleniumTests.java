package org.jarivm.relationGraph;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * @author Jari Van Melckebeke
 * @since 02.09.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@Transactional(readOnly = true,value = "transactionManager")
public class SeleniumTests {


	private static WebDriver driver;
	private static ConfigurableApplicationContext context;

	@BeforeClass
	public static void mainSetUp() {
		System.setProperty("webdriver.chrome.driver", "/home/jarivm/programs/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.jarivanmelckebeke.be/");
		context = SpringApplication.run(SeleniumTests.class);
	}

	@AfterClass
	public static void mainTearDown() {
		driver.close();
		driver.quit();
		context.close();
	}

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}


	@Test
	public void testGeneralConnectionToNetwork() {
		driver.get("http://jarivanmelckebeke.be/index.php");
		assert driver.findElements(By.className("block")).size() == 1;
	}

	@Test
	public void testSpringConnection() {
		driver.get("localhost:2904/login");
		assert Objects.equals(driver.getTitle(), "Login Page");
	}

	@Test
	public void testCreateClient() {
		driver.get("http://localhost:2904/user/create/client");
		login();
		assert !Objects.equals(driver.getTitle(), "Login Page");
		driver.findElement(By.id("name")).sendKeys("mock test");
		driver.findElement(By.id("experience")).sendKeys("15");
		Select selectBox = new Select(driver.findElement(By.id("sector")));
		selectBox.selectByIndex(0);
		driver.findElement(By.name("submit")).click();
		assert driver.getTitle().equals("User home");
	}

	public void login() {
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("root");
		driver.findElement(By.name("submit")).click();
	}

}
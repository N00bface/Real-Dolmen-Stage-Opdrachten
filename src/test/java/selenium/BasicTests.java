/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package selenium;

import org.jarivm.relationGraph.constants.AuthType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.neo4j.ogm.session.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jari Van Melckebeke
 * @since 26.12.16.
 */
public class BasicTests {
	static Session s;
	static WebDriver driver;
	static ConfigurableApplicationContext context;

	@AfterClass
	public static void mainTearDown() {
		assertNotNull(s);
		s.query("MATCH (n) where n.name CONTAINS 'test' detach delete n;", new HashMap<>());
		driver.close();
		driver.quit();
		context.close();
	}

	@BeforeClass
	public static void classSetUp() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/usr/bin/google-chrome-stable");
		driver = new ChromeDriver(options);
		context = SpringApplication.run(FunctionalityTests.class);
		driver.get("localhost:2907");
		System.out.println(driver.getTitle());
	}

	void login(AuthType type) {
		switch (type) {
			case ADMIN:
				loginAsAnonymous("admin", "root");
				return;
			case PROJECT_LEADER:
				loginAsAnonymous("jari", "jari");
				return;
			case DEVELOPER:
				loginAsAnonymous("amber", "amber");
				return;
			case EMPLOYEE:
				loginAsAnonymous("stijn", "stijn");
				return;
			case CLIENT:
				loginAsAnonymous("Colruyt", "client");
				return;
			case NONE:
				loginAsAnonymous("ava", "client");
		}
	}

	private void loginAsAnonymous(String usr, String pswd) {
		if (driver.getTitle().equals("Login Page")) {
			assertEquals("Login Page", driver.getTitle());
			driver.findElement(By.name("username")).sendKeys(usr);
			driver.findElement(By.name("password")).sendKeys(pswd);
			driver.findElement(By.name("submit")).click();
		} else {
			logout();
			loginAsAnonymous(usr, pswd);
		}
	}

	void logout() {
		driver.get("localhost:2904/logout");
	}

	@Before
	public void setUp() {
		System.out.println(context.getBeanFactory().getBeanNamesIterator().toString());
		s = (Session) context.getBean("getSession");
		driver.get("localhost:2904/login");
	}

	@After
	public void tearDown() {
	}
}

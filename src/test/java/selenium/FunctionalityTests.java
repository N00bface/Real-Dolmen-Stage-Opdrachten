/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package selenium;

import org.jarivm.relationGraph.constants.AuthType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Jari Van Melckebeke
 * @since 02.09.16
 */
@SpringBootApplication
@Transactional
@ComponentScan("org.jarivm.relationGraph")
@EnableAutoConfiguration
@ActiveProfiles("CI")
public class FunctionalityTests extends BasicTests {


	@Test
	public void testGeneralConnectionToNetwork() {
		driver.navigate().to("http://jarivanmelckebeke.be/index.php");
		assertEquals(1, driver.findElements(By.className("block")).size());
	}

	@Test
	public void testSpringConnection() {
		driver.navigate().to("http://localhost:2904/login");
		assertEquals(driver.getTitle(), "Login Page");
	}

	@Test
	public void testCreateClient() {
		login(AuthType.ADMIN);
		System.out.println(driver.getCurrentUrl());
		driver.navigate().to("http://localhost:2904/user/create/client");
		assertEquals("New Entity", driver.getTitle());
		driver.findElement(By.id("name")).sendKeys("mock test");
		driver.findElement(By.id("experience")).sendKeys("15");
		Select selectBox = new Select(driver.findElement(By.id("sector")));
		selectBox.selectByIndex(0);
		driver.findElement(By.name("submit")).click();
		assertEquals(driver.getTitle(), "User home");
	}

	@Test
	public void testCreateEmployee() {
		login(AuthType.ADMIN);
		System.out.println(driver.getCurrentUrl());
		driver.navigate().to("localhost:2904/user/create/employee");
		assertEquals("New Entity", driver.getTitle());
		driver.findElement(By.id("surname")).sendKeys("Van Melckebeke");
		driver.findElement(By.id("name")).sendKeys("test");
		driver.findElement(By.id("email")).sendKeys("aab@gmail.com");
		Select selectBox = new Select(driver.findElement(By.id("gender")));
		selectBox.selectByIndex(0);
		driver.findElement(By.id("age")).sendKeys("15");
		driver.findElement(By.id("experience")).sendKeys("5");
		driver.findElement(By.id("submit")).click();
		assertEquals(driver.getTitle(), "User home");
	}

	@Test
	public void testCreateProject() {
		login(AuthType.ADMIN);
		driver.navigate().to("localhost:2904/user/create/project");
		assertEquals("New Entity", driver.getTitle());
		driver.findElement(By.id("name")).sendKeys("test");
		driver.findElement(By.id("cost")).sendKeys("150");
		driver.findElement(By.id("version")).sendKeys("1.5.9");
		Select selectBox = new Select(driver.findElement(By.id("client")));
		selectBox.selectByVisibleText("Colruyt");
		driver.findElements(By.name("employeesCollaborated")).get(0).click();
		driver.findElements(By.name("roles")).get(0).sendKeys("a role");
		driver.findElement(By.id("submit")).click();
		assertEquals(driver.getTitle(), "User home");
	}

	@Test
	public void testCreateSector() {
		login(AuthType.ADMIN);
		driver.navigate().to("localhost:2904/user/create/sector");
		assertEquals("New Entity", driver.getTitle());
		driver.findElement(By.id("name")).sendKeys("test");
		driver.findElement(By.id("submit")).click();
		assertEquals("User home", driver.getTitle());
	}

	@Test
	public void testEmployeeOfTheMonth() {
		login(AuthType.ADMIN);
		driver.navigate().to("localhost:2904/user/employeeByScore");
		assertEquals("Employees of the month", driver.getTitle());
		assertTrue("element size", driver.findElement(By.className("fit_table"))
				.findElements(By.tagName("tbody")).get(0).findElements(By.tagName("tr")).size() > 0);
	}

	@Test
	public void testSearchNotFound() {
		login(AuthType.ADMIN);
		driver.navigate().to("localhost:2904/user/search");
		Select select = new Select(driver.findElement(By.tagName("select")));
		select.selectByIndex(0);
		driver.findElement(By.name("q")).sendKeys("delta-foxtrot");
		driver.findElement(By.name("submit")).click();
		assertEquals("no search results found!", driver.findElement(By.xpath("/html/body/div[2]/p")).getText());
	}

	@Test
	public void testSearchFound() {
		login(AuthType.ADMIN);
		driver.navigate().to("localhost:2904/user/search");
		Select select = new Select(driver.findElement(By.tagName("select")));
		select.selectByValue("Sector,name");
		driver.findElement(By.name("q")).sendKeys("random");
		driver.findElement(By.name("submit")).click();
		assertTrue("one sector with name \'random\'", driver.findElements(By.className("result")).size() > 0);
	}

}
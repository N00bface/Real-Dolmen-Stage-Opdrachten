/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package selenium;

import org.jarivm.relationGraph.constants.AuthType;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

/**
 * @author Jari Van Melckebeke
 * @since 26.12.16.
 */
@SpringBootApplication
@Transactional
@ComponentScan("org.jarivm.relationGraph")
@EnableAutoConfiguration
@ActiveProfiles("CI")
public class SecurityTests extends BasicTests {

	@Test
	public void testAdmin() {
		login(AuthType.ADMIN);
		driver.get("localhost:2904/user/home");
		assertTrue("admin logged in?", !Objects.equals(driver.getTitle(), "Login Page"));
	}

	@Test
	public void testWrongUserName() {
		login(AuthType.NONE);
		driver.get("localhost:2904/user/home");
		assertTrue("could log in?", Objects.equals(driver.getTitle(), "Login Page"));
	}
}

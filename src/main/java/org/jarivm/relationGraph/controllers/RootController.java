/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Controller
public class RootController extends BaseController {

	@RequestMapping(value = "/auth", name = "login")
	public String auth(Model model) {
		model.addAttribute("name", "jari");
		return "access";
	}

	@RequestMapping("/")
	public String root() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/index", name = "home")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login", name = "login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout", name = "log out")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";

	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}

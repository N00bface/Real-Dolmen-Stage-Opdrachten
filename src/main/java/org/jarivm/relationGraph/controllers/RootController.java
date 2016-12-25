/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
 */

package org.jarivm.relationGraph.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}

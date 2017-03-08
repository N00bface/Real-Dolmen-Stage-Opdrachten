/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.Sector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jari Van Melckebeke
 * @since 24.10.16.
 */
@Controller
@RequestMapping("/user/search")
public class SearchController extends BaseController {

	@RequestMapping
	public String search() {
		return "/user/search";
	}

	@RequestMapping(value = "/simpleSearch", name = "simple search")
	public String simpleSearch(@RequestParam(name = "q") String query, @RequestParam(name = "t") String type, Model model) {
		if (!isDeveloper() && !isClient()) noAccess();
		if (type.equals("any")) {
			model.addAttribute("type", "any");

			Iterable<Client> c = clientRepository.findByAny(query);
			Iterable<Employee> e = employeeRepository.findByAny(query);
			Iterable<Project> p = projectRepository.findByAny(query);
			Iterable<Sector> s = sectorRepository.findByAny(query);

			if (noResults(c)) model.addAttribute("client", "none");
			else model.addAttribute("client", c);

			if (noResults(e)) model.addAttribute("employee", "none");
			else model.addAttribute("employee", e);

			if (noResults(p)) model.addAttribute("project", "none");
			else model.addAttribute("project", p);

			if (noResults(s)) model.addAttribute("sector", "none");
			else model.addAttribute("sector", sectorRepository.findByAny(query));

			return "/user/searchResult";
		}
		notFound();
		return null;
	}

	private boolean noResults(Iterable it) {
		return !it.iterator().hasNext();
	}
}

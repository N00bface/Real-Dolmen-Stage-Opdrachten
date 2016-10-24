package org.jarivm.relationGraph.services;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jari Van Melckebeke
 * @since 24.10.16.
 */
@Controller
@RequestMapping("/user/search")
public class searchController {
    @RequestMapping("/simpleSearch")
    public String simpleSearch(@RequestParam(name = "q") String query, BindingResult bindingResult, Model model) {
        return "foobar";
    }
}

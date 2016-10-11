package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Jari Van Melckebeke
 * @since 11.10.16
 */
// TODO: 11.10.16 fix this
@Controller
public class ExceptionController extends Application {
    @ExceptionHandler(Exception.class)
    public String pageNotFound() {
        return "err404";
    }
}

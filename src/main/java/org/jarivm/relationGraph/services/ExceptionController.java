package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jari Van Melckebeke
 * @since 11.10.16
 */
// TODO: 11.10.16 fix this
@Controller
public class ExceptionController extends Application {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String
    defaultErrorHandler() throws Exception {
        return "err404";
    }
}

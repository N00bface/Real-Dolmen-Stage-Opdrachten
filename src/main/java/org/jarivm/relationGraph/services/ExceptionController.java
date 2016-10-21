package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jari Van Melckebeke
 * @since 11.10.16
 */
// TODO: 11.10.16 fix this
@ControllerAdvice
public class ExceptionController {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String
    defaultErrorHandler() throws Exception {
        return "err404";
    }
}

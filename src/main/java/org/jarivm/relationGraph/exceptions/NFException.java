/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jari Van Melckebeke
 * @since 26.12.16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NFException extends RuntimeException {
	public NFException() {

	}
}

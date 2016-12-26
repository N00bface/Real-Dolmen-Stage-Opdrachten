/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
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

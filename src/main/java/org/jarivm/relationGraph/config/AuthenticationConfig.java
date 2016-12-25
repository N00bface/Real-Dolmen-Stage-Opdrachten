/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
 */

package org.jarivm.relationGraph.config;

import org.jarivm.relationGraph.constants.AuthType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author Jari Van Melckebeke
 * @since 25.12.16.
 */
public class AuthenticationConfig {
	private static AuthType role;

	public static AuthType getRole() {
		return role;
	}

	public static boolean isAuthenticated() {
		return role != AuthType.NONE;
	}

	public static AuthType setRole() {
		Collection<GrantedAuthority> authorities =
				(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String a = authorities.iterator().next().getAuthority();
		switch (a) {
			case "ROLE_ADMIN":
				return AuthType.ADMIN;
			case "ROLE_CLIENT":
				return AuthType.CLIENT;
			case "ROLE_PROJECT_LEADER":
				return AuthType.PROJECT_LEADER;
			case "ROLE_EMPLOYEE":
				return AuthType.DEVELOPER;
			default:
				return AuthType.NONE;
		}
	}
}

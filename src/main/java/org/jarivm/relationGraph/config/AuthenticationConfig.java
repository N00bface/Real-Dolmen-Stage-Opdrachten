/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
 */

package org.jarivm.relationGraph.config;

import org.jarivm.relationGraph.constants.AuthType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author Jari Van Melckebeke
 * @since 25.12.16.
 */
public class AuthenticationConfig {
	private static AuthType role;
	private static String name;

	public static String getName() {
		return name;
	}

	public static AuthType getRole() {
		return role;
	}

	public static boolean isAuthenticated() {
		return role != AuthType.NONE;
	}

	public static void setRole() {
		Collection<GrantedAuthority> authorities =
				(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String a = authorities.iterator().next().getAuthority();
		System.out.println(a);
		switch (a) {
			case "ROLE_ADMIN":
				role = AuthType.ADMIN;
				break;
			case "ROLE_CLIENT":
				role = AuthType.CLIENT;
				break;
			case "ROLE_PROJECT_LEADER":
				role = AuthType.PROJECT_LEADER;
				break;
			case "ROLE_EMPLOYEE":
				role = AuthType.DEVELOPER;
				break;
			default:
				role = AuthType.NONE;
		}
	}

	public static void setName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		name = authentication.getName();
	}
}

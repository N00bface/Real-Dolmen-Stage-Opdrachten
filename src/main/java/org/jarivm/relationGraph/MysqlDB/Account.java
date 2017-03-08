/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.MysqlDB;

import org.apache.log4j.Logger;
import org.jarivm.relationGraph.constants.AuthType;

import javax.persistence.*;

/**
 * @author Jari Van Melckebeke
 * @since 28.02.17.
 */
@Entity
@Table(name = "useraccounts")
public class Account {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "avatar")
	private byte[] avatar;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "GHtoken")
	private String github_token;
	@Column(name = "email")
	private String mail;
	@Enumerated(EnumType.STRING)
	@Column(name = "authtype")
	private AuthType authType;
	@Column(name = "telnr")
	private String telnr;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Transient
	Logger logger = Logger.getLogger(Account.class);

	public Account() {
	}

	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getGithub_token() {
		return github_token;
	}

	public void setGithub_token(String github_token) {
		this.github_token = github_token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelnr() {
		return telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

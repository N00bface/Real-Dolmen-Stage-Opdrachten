/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.MysqlDB;

import org.jarivm.relationGraph.MysqlDB.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jari Van Melckebeke
 * @since 08.03.17.
 */
public class Mapper {
	@Autowired
	private AccountRepository accountRepository;

	public void overwriteAccount(Account account, Long id) {
		Account old = accountRepository.findOne(id);
		overwriteAccount(account, old);
	}

	public void overwriteAccount(Account account, Account old) {
		if (account.getUsername() == null) account.setUsername(old.getUsername());
		if (account.getPassword() == null) account.setPassword(old.getPassword());
		if (account.getAuthType() == null) account.setAuthType(old.getAuthType());
		if (account.getAvatar() == null) account.setAvatar(old.getAvatar());
		if (account.getGithub_token() == null) account.setGithub_token(old.getGithub_token());
		if (account.getTelnr() == null) account.setTelnr(old.getTelnr());
		if (account.getMail() == null) account.setMail(old.getMail());
		if (account.getName() == null) account.setName(old.getName());
		if (account.getSurname() == null) account.setSurname(old.getSurname());
	}
}

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jarivm.relationGraph.MysqlDB.Account;
import org.jarivm.relationGraph.MysqlDB.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author Jari Van Melckebeke
 * @since 06.03.17.
 */
@Controller
@RequestMapping("settings")
public class SettingsController extends BaseController {
	@Autowired
	Mapper mapper;
	Logger logger = Logger.getLogger(SettingsController.class);

	@RequestMapping("generalsettingssubmit")
	public String generalSettingsSubmit(@ModelAttribute Account account, BindingResult bindingResult, Model model) {
		submit(account);
		return "redirect:/user/settings";
	}

	@Transactional
	public void submit(Account account) {
		mapper.overwriteAccount(account, account.getId());
		accountRepository.save(account);
	}

	@RequestMapping(value = "changeavatar", method = RequestMethod.POST)
	public String changeAvatar(@RequestParam("id2") Long id, @RequestParam("image") MultipartFile image, Model model) {
		Account acc = accountRepository.findOne(id);
		try {
			acc.setAvatar(IOUtils.toByteArray(image.getInputStream()));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		submit(acc);
		return "redirect:/user/settings";
	}

	@RequestMapping(value = "/avatar/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getAvatar(@PathVariable final Long id) {
		byte[] bytes = accountRepository.findOne(id).getAvatar(); // Generate the image based on the id
		if (bytes == null) bytes = new byte[]{};
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
	}
}

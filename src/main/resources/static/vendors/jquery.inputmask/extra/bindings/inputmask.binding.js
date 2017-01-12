/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */
(function(factory) {
		if (typeof define === "function" && define.amd) {
			define(["jquery", "inputmask"], factory);
		} else if (typeof exports === "object") {
			module.exports = factory(require("jquery"), require("./inputmask"));
		} else {
			factory(jQuery, window.Inputmask);
		}
	}
	(function($, Inputmask) {
		$(document).ajaxComplete(function(event, xmlHttpRequest, ajaxOptions) {
			if ($.inArray("html", ajaxOptions.dataTypes) !== -1) {
				$(":input").each(function(ndx, lmnt) {
					if (lmnt.inputmask === undefined) {
						Inputmask().mask(lmnt);
					}
				});
			}
		}).ready(function() {
			$(":input").each(function(ndx, lmnt) {
				if (lmnt.inputmask === undefined) {
					Inputmask().mask(lmnt);
				}
			});
		});
	}));

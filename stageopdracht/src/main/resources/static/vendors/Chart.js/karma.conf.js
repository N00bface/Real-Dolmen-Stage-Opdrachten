/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module.exports = function(config) {
	config.set({
		browsers: ['Chrome', 'Firefox'],
		frameworks: ['browserify', 'jasmine'],
		reporters: ['progress', 'html'],

		preprocessors: {
			'src/**/*.js': ['browserify']
		},
		browserify: {
			debug: true
		}
	});
};
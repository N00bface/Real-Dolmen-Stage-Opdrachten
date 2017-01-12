/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module.exports = {
	options: {
		files: [
			"package.json",
			"bower.json"
		],
		updateConfigs: [
			"package"
		],
		commit: true,
		commitMessage: "Release v%VERSION%",
		commitFiles: [
			"-a"
		],
		createTag: true,
		tagName: "v%VERSION%",
		tagMessage: "Version %VERSION%",
		push: true,
		pushTo: "origin",
		gitDescribeOptions: "--tags --always --abbrev=1 --dirty=-d"
	}
};

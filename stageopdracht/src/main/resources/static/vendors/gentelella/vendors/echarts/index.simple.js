/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * Export echarts as CommonJS module
 */
module.exports = require('./lib/echarts');

require('./lib/chart/line');
require('./lib/chart/bar');
require('./lib/chart/pie');
require('./lib/component/grid');
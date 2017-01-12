/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    require('./lines/LinesSeries');
    require('./lines/LinesView');

    var zrUtil = require('zrender/core/util');
    var echarts = require('../echarts');
    echarts.registerLayout(
        require('./lines/linesLayout')
    );

    echarts.registerVisualCoding(
        'chart', zrUtil.curry(require('../visual/seriesColor'), 'lines', 'lineStyle')
    );
});
/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');
    var echarts = require('../echarts');

    require('./scatter/ScatterSeries');
    require('./scatter/ScatterView');

    echarts.registerVisualCoding('chart', zrUtil.curry(
        require('../visual/symbol'), 'scatter', 'circle', null
    ));
    echarts.registerLayout(zrUtil.curry(
        require('../layout/points'), 'scatter'
    ));

    // In case developer forget to include grid component
    require('../component/grid');
});

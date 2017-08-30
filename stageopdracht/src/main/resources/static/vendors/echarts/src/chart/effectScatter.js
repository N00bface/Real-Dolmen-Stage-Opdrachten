/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');
    var echarts = require('../echarts');

    require('./effectScatter/EffectScatterSeries');
    require('./effectScatter/EffectScatterView');

    echarts.registerVisualCoding('chart', zrUtil.curry(
        require('../visual/symbol'), 'effectScatter', 'circle', null
    ));
    echarts.registerLayout(zrUtil.curry(
        require('../layout/points'), 'effectScatter'
    ));
});

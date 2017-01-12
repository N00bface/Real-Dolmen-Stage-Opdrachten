/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');

    require('../coord/cartesian/Grid');

    require('./bar/BarSeries');
    require('./bar/BarView');

    var barLayoutGrid = require('../layout/barGrid');
    var echarts = require('../echarts');

    echarts.registerLayout(zrUtil.curry(barLayoutGrid, 'bar'));
    // Visual coding for legend
    echarts.registerVisualCoding('chart', function (ecModel) {
        ecModel.eachSeriesByType('bar', function (seriesModel) {
            var data = seriesModel.getData();
            data.setVisual('legendSymbol', 'roundRect');
        });
    });

    // In case developer forget to include grid component
    require('../component/grid');
});

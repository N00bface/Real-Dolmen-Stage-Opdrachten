/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    require('./chord/ChordSeries');
    require('./chord/ChordView');

    var echarts = require('../echarts');
    var zrUtil = require('zrender/core/util');
    echarts.registerLayout(require('./chord/chordCircularLayout'));

    echarts.registerVisualCoding(
        'chart',  zrUtil.curry(require('../visual/dataColor'), 'chord')
    );

    echarts.registerProcessor(
        'filter', zrUtil.curry(require('../processor/dataFilter'), 'pie')
    );
});
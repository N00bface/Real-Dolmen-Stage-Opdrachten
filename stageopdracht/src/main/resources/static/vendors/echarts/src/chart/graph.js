/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var echarts = require('../echarts');
    var zrUtil = require('zrender/core/util');

    require('./graph/GraphSeries');
    require('./graph/GraphView');

    require('./graph/roamAction');

    echarts.registerProcessor('filter', require('./graph/categoryFilter'));

    echarts.registerVisualCoding('chart', zrUtil.curry(
        require('../visual/symbol'), 'graph', 'circle', null
    ));
    echarts.registerVisualCoding('chart', require('./graph/categoryVisual'));
    echarts.registerVisualCoding('chart', require('./graph/edgeVisual'));

    echarts.registerLayout(require('./graph/simpleLayout'));
    echarts.registerLayout(require('./graph/circularLayout'));
    echarts.registerLayout(require('./graph/forceLayout'));

    // Graph view coordinate system
    echarts.registerCoordinateSystem('graphView', {
        create: require('./graph/createView')
    });
});

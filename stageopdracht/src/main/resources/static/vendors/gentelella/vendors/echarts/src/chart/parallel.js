/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var echarts = require('../echarts');

    require('../component/parallel');

    require('./parallel/ParallelSeries');
    require('./parallel/ParallelView');

    echarts.registerVisualCoding('chart', require('./parallel/parallelVisual'));

});
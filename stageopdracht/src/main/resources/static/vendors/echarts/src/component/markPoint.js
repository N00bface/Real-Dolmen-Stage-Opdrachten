/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// HINT Markpoint can't be used too much
define(function (require) {

    require('./marker/MarkPointModel');
    require('./marker/MarkPointView');

    require('../echarts').registerPreprocessor(function (opt) {
        // Make sure markPoint component is enabled
        opt.markPoint = opt.markPoint || {};
    });
});

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * DataZoom component entry
 */
define(function (require) {

    var echarts = require('../echarts');

    echarts.registerPreprocessor(require('./timeline/preprocessor'));

    require('./timeline/typeDefaulter');
    require('./timeline/timelineAction');
    require('./timeline/SliderTimelineModel');
    require('./timeline/SliderTimelineView');

});

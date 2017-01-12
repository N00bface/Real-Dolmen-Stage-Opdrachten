/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');

    return function (option) {
        if (!option || !zrUtil.isArray(option.series)) {
            return;
        }

        // Translate 'k' to 'candlestick'.
        zrUtil.each(option.series, function (seriesItem) {
            if (zrUtil.isObject(seriesItem) && seriesItem.type === 'k') {
                seriesItem.type = 'candlestick';
            }
        });
    };

});

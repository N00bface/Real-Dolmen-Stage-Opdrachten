/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var simpleLayoutHelper = require('./simpleLayoutHelper');
    return function (ecModel, api) {
        ecModel.eachSeriesByType('graph', function (seriesModel) {
            var layout = seriesModel.get('layout');
            if (!layout || layout === 'none') {
                simpleLayoutHelper(seriesModel);
            }
        });
    };
});
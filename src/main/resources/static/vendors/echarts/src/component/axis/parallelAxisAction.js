/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var echarts = require('../../echarts');

    var actionInfo = {
        type: 'axisAreaSelect',
        event: 'axisAreaSelected',
        update: 'updateVisual'
    };

    /**
     * @payload
     * @property {string} parallelAxisId
     * @property {Array.<Array.<number>>} intervals
     */
    echarts.registerAction(actionInfo, function (payload, ecModel) {
        ecModel.eachComponent(
            {mainType: 'parallelAxis', query: payload},
            function (parallelAxisModel) {
                parallelAxisModel.axis.model.setActiveIntervals(payload.intervals);
            }
        );

    });
});

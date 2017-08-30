/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * @file Data range action
 */
define(function(require) {

    var echarts = require('../../echarts');

    var actionInfo = {
        type: 'selectDataRange',
        event: 'dataRangeSelected',
        // FIXME use updateView appears wrong
        update: 'update'
    };

    echarts.registerAction(actionInfo, function (payload, ecModel) {

        ecModel.eachComponent({mainType: 'visualMap', query: payload}, function (model) {
            model.setSelected(payload.selected);
        });

    });

});

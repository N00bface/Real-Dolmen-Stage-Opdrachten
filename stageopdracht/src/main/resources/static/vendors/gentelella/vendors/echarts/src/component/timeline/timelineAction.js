/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * @file Timeilne action
 */
define(function(require) {

    var echarts = require('../../echarts');

    echarts.registerAction(

        {type: 'timelineChange', event: 'timelineChanged', update: 'prepareAndUpdate'},

        function (payload, ecModel) {

            var timelineModel = ecModel.getComponent('timeline');
            if (timelineModel && payload.currentIndex != null) {
                timelineModel.setCurrentIndex(payload.currentIndex);

                if (!timelineModel.get('loop', true) && timelineModel.isIndexMax()) {
                    timelineModel.setPlayState(false);
                }
            }

            ecModel.resetOption('timeline');
        }
    );

    echarts.registerAction(

        {type: 'timelinePlayChange', event: 'timelinePlayChanged', update: 'update'},

        function (payload, ecModel) {
            var timelineModel = ecModel.getComponent('timeline');
            if (timelineModel && payload.playState != null) {
                timelineModel.setPlayState(payload.playState);
            }
        }
    );

});
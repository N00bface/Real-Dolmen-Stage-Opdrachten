/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * Helper function for axisLabelInterval calculation
 */

define(function(require) {
    'use strict';

    var zrUtil = require('zrender/core/util');
    var axisHelper = require('../axisHelper');

    return function (axis) {
        var axisModel = axis.model;
        var labelModel = axisModel.getModel('axisLabel');
        var labelInterval = labelModel.get('interval');
        if (!(axis.type === 'category' && labelInterval === 'auto')) {
            return labelInterval === 'auto' ? 0 : labelInterval;
        }

        return axisHelper.getAxisLabelInterval(
            zrUtil.map(axis.scale.getTicks(), axis.dataToCoord, axis),
            axisModel.getFormattedLabels(),
            labelModel.getModel('textStyle').getFont(),
            axis.isHorizontal()
        );
    };
});

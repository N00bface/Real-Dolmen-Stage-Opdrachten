/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var ComponentModel = require('../../model/Component');
    var axisModelCreator = require('../axisModelCreator');
    var zrUtil =  require('zrender/core/util');

    var AxisModel = ComponentModel.extend({

        type: 'singleAxis',

        layoutMode: 'box',

        /**
         * @type {module:echarts/coord/single/SingleAxis}
         */
        axis: null,

        /**
         * @type {module:echarts/coord/single/Single}
         */
        coordinateSystem: null

    });

    var defaultOption = {

        left: '5%',
        top: '5%',
        right: '5%',
        bottom: '5%',

        type: 'value',

        position: 'bottom',

        orient: 'horizontal',
        // singleIndex: 0,

        axisLine: {
            show: true,
            lineStyle: {
                width: 2,
                type: 'solid'
            }
        },

        axisTick: {
            show: true,
            length: 6,
            lineStyle: {
                width: 2
            }
        },

        axisLabel: {
            show: true,
            interval: 'auto'
        },

        splitLine: {
            show: true,
            lineStyle: {
                type: 'dashed',
                opacity: 0.2
            }
        }
    };

    function getAxisType(axisName, option) {
        return option.type || (option.data ? 'category' : 'value');
    }

    zrUtil.merge(AxisModel.prototype, require('../axisModelCommonMixin'));

    axisModelCreator('single', AxisModel, getAxisType, defaultOption);

    return AxisModel;
});
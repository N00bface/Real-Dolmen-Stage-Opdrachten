/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Grid 是在有直角坐标系的时候必须要存在的
// 所以这里也要被 Cartesian2D 依赖
define(function(require) {

    'use strict';

    require('./AxisModel');
    var ComponentModel = require('../../model/Component');

    return ComponentModel.extend({

        type: 'grid',

        dependencies: ['xAxis', 'yAxis'],

        layoutMode: 'box',

        /**
         * @type {module:echarts/coord/cartesian/Grid}
         */
        coordinateSystem: null,

        defaultOption: {
            show: false,
            zlevel: 0,
            z: 0,
            left: '10%',
            top: 60,
            right: '10%',
            bottom: 60,
            // If grid size contain label
            containLabel: false,
            // width: {totalWidth} - left - right,
            // height: {totalHeight} - top - bottom,
            backgroundColor: 'rgba(0,0,0,0)',
            borderWidth: 1,
            borderColor: '#ccc'
        }
    });
});

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');
    var Axis = require('../Axis');

    function IndicatorAxis(dim, scale, radiusExtent) {
        Axis.call(this, dim, scale, radiusExtent);

        /**
         * Axis type
         *  - 'category'
         *  - 'value'
         *  - 'time'
         *  - 'log'
         * @type {string}
         */
        this.type = 'value';

        this.angle = 0;

        /**
         * Indicator name
         * @type {string}
         */
        this.name = '';
        /**
         * @type {module:echarts/model/Model}
         */
        this.model;
    }

    zrUtil.inherits(IndicatorAxis, Axis);

    return IndicatorAxis;
});

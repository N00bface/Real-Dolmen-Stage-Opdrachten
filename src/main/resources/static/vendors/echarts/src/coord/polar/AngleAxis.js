/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function(require) {
    'use strict';

    var zrUtil = require('zrender/core/util');
    var Axis = require('../Axis');

    function AngleAxis(scale, angleExtent) {

        angleExtent = angleExtent || [0, 360];

        Axis.call(this, 'angle', scale, angleExtent);

        /**
         * Axis type
         *  - 'category'
         *  - 'value'
         *  - 'time'
         *  - 'log'
         * @type {string}
         */
        this.type = 'category';
    }

    AngleAxis.prototype = {

        constructor: AngleAxis,

        dataToAngle: Axis.prototype.dataToCoord,

        angleToData: Axis.prototype.coordToData
    };

    zrUtil.inherits(AngleAxis, Axis);

    return AngleAxis;
});

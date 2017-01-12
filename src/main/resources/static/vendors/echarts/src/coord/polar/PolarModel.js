/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    'use strict';

    require('./AxisModel');

    require('../../echarts').extendComponentModel({

        type: 'polar',

        dependencies: ['polarAxis', 'angleAxis'],

        /**
         * @type {module:echarts/coord/polar/Polar}
         */
        coordinateSystem: null,

        /**
         * @param {string} axisType
         * @return {module:echarts/coord/polar/AxisModel}
         */
        findAxisModel: function (axisType) {
            var angleAxisModel;
            var ecModel = this.ecModel;
            ecModel.eachComponent(axisType, function (axisModel) {
                if (ecModel.getComponent(
                        'polar', axisModel.getShallow('polarIndex')
                    ) === this) {
                    angleAxisModel = axisModel;
                }
            }, this);
            return angleAxisModel;
        },

        defaultOption: {

            zlevel: 0,

            z: 0,

            center: ['50%', '50%'],

            radius: '80%'
        }
    });
});

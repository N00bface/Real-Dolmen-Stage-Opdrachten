/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function(require) {
    'use strict';

    var graphic = require('../util/graphic');
    var zrUtil = require('zrender/core/util');

    require('../coord/cartesian/Grid');

    require('./axis');

    // Grid view
    require('../echarts').extendComponentView({

        type: 'grid',

        render: function (gridModel, ecModel) {
            this.group.removeAll();
            if (gridModel.get('show')) {
                this.group.add(new graphic.Rect({
                    shape:gridModel.coordinateSystem.getRect(),
                    style: zrUtil.defaults({
                        fill: gridModel.get('backgroundColor')
                    }, gridModel.getItemStyle()),
                    silent: true
                }));
            }
        }
    });
});
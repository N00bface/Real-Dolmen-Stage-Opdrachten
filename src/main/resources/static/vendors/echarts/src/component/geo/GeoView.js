/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    'use strict';

    var MapDraw = require('../helper/MapDraw');

    return require('../../echarts').extendComponentView({

        type: 'geo',

        init: function (ecModel, api) {
            var mapDraw = new MapDraw(api, true);
            this._mapDraw = mapDraw;

            this.group.add(mapDraw.group);
        },

        render: function (geoModel, ecModel, api, payload) {
            // Not render if it is an toggleSelect action from self
            if (payload && payload.type === 'geoToggleSelect'
                && payload.from === this.uid
            ) {
                return;
            }

            var mapDraw = this._mapDraw;
            if (geoModel.get('show')) {
                mapDraw.draw(geoModel, ecModel, api, this, payload);
            }
            else {
                this._mapDraw.group.removeAll();
            }
        }
    });
});

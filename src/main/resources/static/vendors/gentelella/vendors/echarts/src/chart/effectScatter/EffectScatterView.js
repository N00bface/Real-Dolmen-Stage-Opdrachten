/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var SymbolDraw = require('../helper/SymbolDraw');
    var EffectSymbol = require('../helper/EffectSymbol');

    require('../../echarts').extendChartView({

        type: 'effectScatter',

        init: function () {
            this._symbolDraw = new SymbolDraw(EffectSymbol);
        },

        render: function (seriesModel, ecModel, api) {
            var data = seriesModel.getData();
            var effectSymbolDraw = this._symbolDraw;
            effectSymbolDraw.updateData(data);
            this.group.add(effectSymbolDraw.group);
        },

        updateLayout: function () {
            this._symbolDraw.updateLayout();
        },

        remove: function (ecModel, api) {
            this._symbolDraw && this._symbolDraw.remove(api);
        }
    });
});
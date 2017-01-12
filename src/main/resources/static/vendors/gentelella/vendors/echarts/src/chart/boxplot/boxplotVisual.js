/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var borderColorQuery = ['itemStyle', 'normal', 'borderColor'];

    return function (ecModel, api) {

        var globalColors = ecModel.get('color');

        ecModel.eachRawSeriesByType('boxplot', function (seriesModel) {

            var defaulColor = globalColors[seriesModel.seriesIndex % globalColors.length];
            var data = seriesModel.getData();

            data.setVisual({
                legendSymbol: 'roundRect',
                // Use name 'color' but not 'borderColor' for legend usage and
                // visual coding from other component like dataRange.
                color: seriesModel.get(borderColorQuery) || defaulColor
            });

            // Only visible series has each data be visual encoded
            if (!ecModel.isSeriesFiltered(seriesModel)) {
                data.each(function (idx) {
                    var itemModel = data.getItemModel(idx);
                    data.setItemVisual(
                        idx,
                        {color: itemModel.get(borderColorQuery, true)}
                    );
                });
            }
        });

    };
});
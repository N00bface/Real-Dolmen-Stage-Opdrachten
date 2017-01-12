/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {
    var Gradient = require('zrender/graphic/Gradient');
    return function (seriesType, styleType, ecModel) {
        function encodeColor(seriesModel) {
            var colorAccessPath = [styleType, 'normal', 'color'];
            var colorList = ecModel.get('color');
            var data = seriesModel.getData();
            var color = seriesModel.get(colorAccessPath) // Set in itemStyle
                || colorList[seriesModel.seriesIndex % colorList.length];  // Default color

            // FIXME Set color function or use the platte color
            data.setVisual('color', color);

            // Only visible series has each data be visual encoded
            if (!ecModel.isSeriesFiltered(seriesModel)) {
                if (typeof color === 'function' && !(color instanceof Gradient)) {
                    data.each(function (idx) {
                        data.setItemVisual(
                            idx, 'color', color(seriesModel.getDataParams(idx))
                        );
                    });
                }

                data.each(function (idx) {
                    var itemModel = data.getItemModel(idx);
                    var color = itemModel.get(colorAccessPath, true);
                    if (color != null) {
                        data.setItemVisual(idx, 'color', color);
                    }
                });
            }
        }
        seriesType ? ecModel.eachSeriesByType(seriesType, encodeColor)
            : ecModel.eachSeries(encodeColor);
    };
});
/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {
    return function (ecModel) {
        ecModel.eachSeriesByType('map', function (seriesModel) {
            var colorList = seriesModel.get('color');
            var itemStyleModel = seriesModel.getModel('itemStyle.normal');

            var areaColor = itemStyleModel.get('areaColor');
            var color = itemStyleModel.get('color')
                || colorList[seriesModel.seriesIndex % colorList.length];

            seriesModel.getData().setVisual({
                'areaColor': areaColor,
                'color': color
            });
        });
    };
});

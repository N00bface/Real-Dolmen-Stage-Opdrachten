/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    return function (ecModel) {
        ecModel.eachSeriesByType('themeRiver', function (seriesModel) {
            var data = seriesModel.getData();
            var rawData = seriesModel.getRawData();
            var colorList = seriesModel.get('color');

            data.each(function (index) {
                var name = data.getName(index);
                var rawIndex = data.getRawIndex(index);
                // use rawData just for drawing legend
                rawData.setItemVisual(
                    rawIndex,
                    'color',
                    colorList[(seriesModel.nameMap[name] - 1) % colorList.length]
                );
            });
        });
   };
});

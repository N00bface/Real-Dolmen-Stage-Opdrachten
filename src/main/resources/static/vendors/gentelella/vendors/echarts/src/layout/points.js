/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    return function (seriesType, ecModel, api) {
        ecModel.eachSeriesByType(seriesType, function (seriesModel) {
            var data = seriesModel.getData();
            var coordSys = seriesModel.coordinateSystem;

            var dims = coordSys.dimensions;
            data.each(dims, function (x, y, idx) {
                var point;
                if (!isNaN(x) && !isNaN(y)) {
                    point = coordSys.dataToPoint([x, y]);
                }
                else {
                    // Also {Array.<number>}, not undefined to avoid if...else... statement
                    point = [NaN, NaN];
                }

                data.setItemLayout(idx, point);
            }, true);
        });
    };
});
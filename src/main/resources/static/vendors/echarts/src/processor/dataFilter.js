/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
    return function (seriesType, ecModel) {
        var legendModels = ecModel.findComponents({
            mainType: 'legend'
        });
        if (!legendModels || !legendModels.length) {
            return;
        }
        ecModel.eachSeriesByType(seriesType, function (series) {
            var data = series.getData();
            data.filterSelf(function (idx) {
                var name = data.getName(idx);
                // If in any legend component the status is not selected.
                for (var i = 0; i < legendModels.length; i++) {
                    if (!legendModels[i].isSelected(name)) {
                        return false;
                    }
                }
                return true;
            }, this);
        }, this);
    };
});

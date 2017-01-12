/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
   return function (ecModel) {
        var legendModels = ecModel.findComponents({
            mainType: 'legend'
        });
        if (legendModels && legendModels.length) {
            ecModel.filterSeries(function (series) {
                // If in any legend component the status is not selected.
                // Because in legend series is assumed selected when it is not in the legend data.
                for (var i = 0; i < legendModels.length; i++) {
                    if (!legendModels[i].isSelected(series.name)) {
                        return false;
                    }
                }
                return true;
            });
        }
    };
});

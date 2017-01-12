/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    return function (ecModel) {
        var legendModels = ecModel.findComponents({
            mainType: 'legend'
        });
        if (!legendModels || !legendModels.length) {
            return;
        }
        ecModel.eachSeriesByType('graph', function (graphSeries) {
            var categoriesData = graphSeries.getCategoriesData();
            var graph = graphSeries.getGraph();
            var data = graph.data;

            var categoryNames = categoriesData.mapArray(categoriesData.getName);

            data.filterSelf(function (idx) {
                var model = data.getItemModel(idx);
                var category = model.getShallow('category');
                if (category != null) {
                    if (typeof category === 'number') {
                        category = categoryNames[category];
                    }
                    // If in any legend component the status is not selected.
                    for (var i = 0; i < legendModels.length; i++) {
                        if (!legendModels[i].isSelected(category)) {
                            return false;
                        }
                    }
                }
                return true;
            });
        }, this);
    };
});
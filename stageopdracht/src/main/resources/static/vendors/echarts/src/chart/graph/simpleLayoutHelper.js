/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var simpleLayoutEdge = require('./simpleLayoutEdge');

    return function (seriesModel) {
        var coordSys = seriesModel.coordinateSystem;
        if (coordSys && coordSys.type !== 'view') {
            return;
        }
        var graph = seriesModel.getGraph();

        graph.eachNode(function (node) {
            var model = node.getModel();
            node.setLayout([+model.get('x'), +model.get('y')]);
        });

        simpleLayoutEdge(graph);
    };
});

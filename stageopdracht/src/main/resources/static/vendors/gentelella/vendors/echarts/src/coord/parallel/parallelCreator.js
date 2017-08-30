/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * Parallel coordinate system creater.
 */
define(function(require) {

    var Parallel = require('./Parallel');

    function create(ecModel, api) {
        var coordSysList = [];

        ecModel.eachComponent('parallel', function (parallelModel, idx) {
            var coordSys = new Parallel(parallelModel, ecModel, api);

            coordSys.name = 'parallel_' + idx;
            coordSys.resize(parallelModel, api);

            parallelModel.coordinateSystem = coordSys;
            coordSys.model = parallelModel;

            coordSysList.push(coordSys);
        });

        // Inject the coordinateSystems into seriesModel
        ecModel.eachSeries(function (seriesModel) {
            if (seriesModel.get('coordinateSystem') === 'parallel') {
                var parallelIndex = seriesModel.get('parallelIndex');
                seriesModel.coordinateSystem = coordSysList[parallelIndex];
            }
        });

        return coordSysList;
    }

    require('../../CoordinateSystem').register('parallel', {create: create});

});
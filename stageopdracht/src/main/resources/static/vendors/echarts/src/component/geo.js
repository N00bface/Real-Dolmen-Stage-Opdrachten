/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    require('../coord/geo/GeoModel');

    require('../coord/geo/geoCreator');

    require('./geo/GeoView');

    require('../action/geoRoam');

    var echarts = require('../echarts');
    var zrUtil = require('zrender/core/util');

    function makeAction(method, actionInfo) {
        actionInfo.update = 'updateView';
        echarts.registerAction(actionInfo, function (payload, ecModel) {
            var selected = {};

            ecModel.eachComponent(
                { mainType: 'geo', query: payload},
                function (geoModel) {
                    geoModel[method](payload.name);
                    var geo = geoModel.coordinateSystem;
                    zrUtil.each(geo.regions, function (region) {
                        selected[region.name] = geoModel.isSelected(region.name) || false;
                    });
                }
            );

            return {
                selected: selected,
                name: payload.name
            }
        });
    }

    makeAction('toggleSelected', {
        type: 'geoToggleSelect',
        event: 'geoselectchanged'
    });
    makeAction('select', {
        type: 'geoSelect',
        event: 'geoselected'
    });
    makeAction('unSelect', {
        type: 'geoUnSelect',
        event: 'geounselected'
    });
});

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');
    var roamHelper = require('./roamHelper');

    var echarts = require('../echarts');

    /**
     * @payload
     * @property {string} [componentType=series]
     * @property {number} [dx]
     * @property {number} [dy]
     * @property {number} [zoom]
     * @property {number} [originX]
     * @property {number} [originY]
     */
    echarts.registerAction({
        type: 'geoRoam',
        event: 'geoRoam',
        update: 'updateLayout'
    }, function (payload, ecModel) {
        var componentType = payload.componentType || 'series';

        ecModel.eachComponent(
            { mainType: componentType, query: payload },
            function (componentModel) {
                var geo = componentModel.coordinateSystem;
                if (geo.type !== 'geo') {
                    return;
                }

                var res = roamHelper.updateCenterAndZoom(
                    geo, payload, componentModel.get('scaleLimit')
                );

                componentModel.setCenter
                    && componentModel.setCenter(res.center);

                componentModel.setZoom
                    && componentModel.setZoom(res.zoom);

                // All map series with same `map` use the same geo coordinate system
                // So the center and zoom must be in sync. Include the series not selected by legend
                if (componentType === 'series') {
                    zrUtil.each(componentModel.seriesGroup, function (seriesModel) {
                        seriesModel.setCenter(res.center);
                        seriesModel.setZoom(res.zoom);
                    });
                }
            }
        );
    });
});

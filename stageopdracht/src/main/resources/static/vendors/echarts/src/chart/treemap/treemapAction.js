/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * @file Treemap action
 */
define(function(require) {

    var echarts = require('../../echarts');
    var helper = require('./helper');

    var noop = function () {};

    var actionTypes = [
        'treemapZoomToNode',
        'treemapRender',
        'treemapMove'
    ];

    for (var i = 0; i < actionTypes.length; i++) {
        echarts.registerAction({type: actionTypes[i], update: 'updateView'}, noop);
    }

    echarts.registerAction(
        {type: 'treemapRootToNode', update: 'updateView'},
        function (payload, ecModel) {

            ecModel.eachComponent(
                {mainType: 'series', subType: 'treemap', query: payload},
                handleRootToNode
            );

            function handleRootToNode(model, index) {
                var targetInfo = helper.retrieveTargetInfo(payload, model);

                if (targetInfo) {
                    var originViewRoot = model.getViewRoot();
                    if (originViewRoot) {
                        payload.direction = helper.aboveViewRoot(originViewRoot, targetInfo.node)
                            ? 'rollUp' : 'drillDown';
                    }
                    model.resetViewRoot(targetInfo.node);
                }
            }
        }
    );

});

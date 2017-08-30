/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var featureManager = require('./featureManager');
    var zrUtil = require('zrender/core/util');

    var ToolboxModel = require('../../echarts').extendComponentModel({

        type: 'toolbox',

        layoutMode: {
            type: 'box',
            ignoreSize: true
        },

        mergeDefaultAndTheme: function (option) {
            ToolboxModel.superApply(this, 'mergeDefaultAndTheme', arguments);

            zrUtil.each(this.option.feature, function (featureOpt, featureName) {
                var Feature = featureManager.get(featureName);
                Feature && zrUtil.merge(featureOpt, Feature.defaultOption);
            });
        },

        defaultOption: {

            show: true,

            z: 6,

            zlevel: 0,

            orient: 'horizontal',

            left: 'right',

            top: 'top',

            // right
            // bottom

            backgroundColor: 'transparent',

            borderColor: '#ccc',

            borderWidth: 0,

            padding: 5,

            itemSize: 15,

            itemGap: 8,

            showTitle: true,

            iconStyle: {
                normal: {
                    borderColor: '#666',
                    color: 'none'
                },
                emphasis: {
                    borderColor: '#3E98C5'
                }
            }
            // textStyle: {},

            // feature
        }
    });

    return ToolboxModel;
});

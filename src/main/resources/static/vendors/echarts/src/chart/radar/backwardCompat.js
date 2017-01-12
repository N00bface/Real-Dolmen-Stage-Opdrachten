/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Backward compat for radar chart in 2
define(function (require) {

    var zrUtil = require('zrender/core/util');

    return function (option) {
        var polarOptArr = option.polar;
        if (polarOptArr) {
            if (!zrUtil.isArray(polarOptArr)) {
                polarOptArr = [polarOptArr];
            }
            var polarNotRadar = [];
            zrUtil.each(polarOptArr, function (polarOpt, idx) {
                if (polarOpt.indicator) {
                    if (polarOpt.type && !polarOpt.shape) {
                        polarOpt.shape = polarOpt.type;
                    }
                    option.radar = option.radar || [];
                    if (!zrUtil.isArray(option.radar)) {
                        option.radar = [option.radar];
                    }
                    option.radar.push(polarOpt);
                }
                else {
                    polarNotRadar.push(polarOpt);
                }
            });
            option.polar = polarNotRadar;
        }
        zrUtil.each(option.series, function (seriesOpt) {
            if (seriesOpt.type === 'radar' && seriesOpt.polarIndex) {
                seriesOpt.radarIndex = seriesOpt.polarIndex;
            }
        });
    };
});

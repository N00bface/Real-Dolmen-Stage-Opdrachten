/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');
    var echarts = require('../echarts');

    require('./pie/PieSeries');
    require('./pie/PieView');

    require('../action/createDataSelectAction')('pie', [{
        type: 'pieToggleSelect',
        event: 'pieselectchanged',
        method: 'toggleSelected'
    }, {
        type: 'pieSelect',
        event: 'pieselected',
        method: 'select'
    }, {
        type: 'pieUnSelect',
        event: 'pieunselected',
        method: 'unSelect'
    }]);

    echarts.registerVisualCoding(
        'chart',  zrUtil.curry(require('../visual/dataColor'), 'pie')
    );

    echarts.registerLayout(zrUtil.curry(
        require('./pie/pieLayout'), 'pie'
    ));

    echarts.registerProcessor(
        'filter', zrUtil.curry(require('../processor/dataFilter'), 'pie')
    );
});
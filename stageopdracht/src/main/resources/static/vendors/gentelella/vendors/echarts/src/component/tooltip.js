/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// FIXME Better way to pack data in graphic element
define(function (require) {

    require('./tooltip/TooltipModel');

    require('./tooltip/TooltipView');

    // Show tip action
    /**
     * @action
     * @property {string} type
     * @property {number} seriesIndex
     * @property {number} dataIndex
     * @property {number} [x]
     * @property {number} [y]
     */
    require('../echarts').registerAction(
        {
            type: 'showTip',
            event: 'showTip',
            update: 'none'
        },
        // noop
        function () {}
    );
    // Hide tip action
    require('../echarts').registerAction(
        {
            type: 'hideTip',
            event: 'hideTip',
            update: 'none'
        },
        // noop
        function () {}
    );
});
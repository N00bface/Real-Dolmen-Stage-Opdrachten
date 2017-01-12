/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {
    var getLineStyle = require('./makeStyleMapper')(
        [
            ['lineWidth', 'width'],
            ['stroke', 'color'],
            ['opacity'],
            ['shadowBlur'],
            ['shadowOffsetX'],
            ['shadowOffsetY'],
            ['shadowColor']
        ]
    );
    return {
        getLineStyle: function (excludes) {
            var style = getLineStyle.call(this, excludes);
            var lineDash = this.getLineDash();
            lineDash && (style.lineDash = lineDash);
            return style;
        },

        getLineDash: function () {
            var lineType = this.get('type');
            return (lineType === 'solid' || lineType == null) ? null
                : (lineType === 'dashed' ? [5, 5] : [1, 1]);
        }
    };
});

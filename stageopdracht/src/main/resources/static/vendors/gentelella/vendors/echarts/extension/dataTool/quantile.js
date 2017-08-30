/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */
define(function (require) {

    /**
     * @see <https://github.com/mbostock/d3/blob/master/src/arrays/quantile.js>
     * @see <http://en.wikipedia.org/wiki/Quantile>
     * @param {Array.<number>} ascArr
     */
    return function(ascArr, p) {
        var H = (ascArr.length - 1) * p + 1,
            h = Math.floor(H),
            v = +ascArr[h - 1],
            e = H - h;
        return e ? v + e * (ascArr[h] - v) : v;
    };

});
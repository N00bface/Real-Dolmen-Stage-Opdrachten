/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

var PROD = process.argv.indexOf('-p') >= 0;

module.exports = {
    entry: {
        'echarts': __dirname + '/index.js',
        'echarts.simple': __dirname + '/index.simple.js',
        'echarts.common': __dirname + '/index.common.js'
    },
    output: {
        libraryTarget: 'umd',
        library: 'echarts',
        path: __dirname + '/dist',
        filename: PROD ? '[name].min.js' : '[name].js'
    }
};
/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

var PROD = process.argv.indexOf('-p') >= 0;

module.exports = {
    entry: {
        'bmap': __dirname + '/../extension/bmap/bmap.js',
        'dataTool': __dirname + '/../extension/dataTool'
    },
    output: {
        libraryTarget: 'umd',
        library: ['echarts', '[name]'],
        path: __dirname + '/../dist/extension',
        filename: PROD ? '[name].min.js' : '[name].js'
    },
    externals: {
        'echarts': 'echarts'
    }
};

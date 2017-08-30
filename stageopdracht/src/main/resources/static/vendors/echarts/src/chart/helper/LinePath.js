/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * Line path for bezier and straight line draw
 */
define(function (require) {
    var graphic = require('../../util/graphic');
    var vec2 = require('zrender/core/vector');

    var straightLineProto = graphic.Line.prototype;
    var bezierCurveProto = graphic.BezierCurve.prototype;

    function isLine(shape) {
        return shape.cpx1 == null || shape.cpy1 == null;
    }

    return graphic.extendShape({

        type: 'ec-line',

        style: {
            stroke: '#000',
            fill: null
        },

        shape: {
            x1: 0,
            y1: 0,
            x2: 0,
            y2: 0,
            percent: 1,
            cpx1: null,
            cpy1: null
        },

        buildPath: function (ctx, shape) {
            (isLine(shape) ? straightLineProto : bezierCurveProto).buildPath(ctx, shape);
        },

        pointAt: function (t) {
            return isLine(this.shape)
                ? straightLineProto.pointAt.call(this, t)
                : bezierCurveProto.pointAt.call(this, t);
        },

        tangentAt: function (t) {
            var shape = this.shape;
            var p = isLine(shape)
                ? [shape.x2 - shape.x1, shape.y2 - shape.y1]
                : bezierCurveProto.tangentAt.call(this, t);
            return vec2.normalize(p, p);
        }
    });
});

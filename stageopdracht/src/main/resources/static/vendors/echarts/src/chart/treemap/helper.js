/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var zrUtil = require('zrender/core/util');

    var helper = {

        retrieveTargetInfo: function (payload, seriesModel) {
            if (payload
                && (
                    payload.type === 'treemapZoomToNode'
                    || payload.type === 'treemapRootToNode'
                )
            ) {
                var root = seriesModel.getData().tree.root;
                var targetNode = payload.targetNode;
                if (targetNode && root.contains(targetNode)) {
                    return {node: targetNode};
                }

                var targetNodeId = payload.targetNodeId;
                if (targetNodeId != null && (targetNode = root.getNodeById(targetNodeId))) {
                    return {node: targetNode};
                }
            }
        },

        // Not includes the given node at the last item.
        getPathToRoot: function (node) {
            var path = [];
            while (node) {
                node = node.parentNode;
                node && path.push(node);
            }
            return path.reverse();
        },

        aboveViewRoot: function (viewRoot, node) {
            var viewPath = helper.getPathToRoot(viewRoot);
            return zrUtil.indexOf(viewPath, node) >= 0;
        }
    };

    return helper;
});

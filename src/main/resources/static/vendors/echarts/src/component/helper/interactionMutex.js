/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    var ATTR = '\0_ec_interaction_mutex';

    var interactionMutex = {

        take: function (key, zr) {
            getStore(zr)[key] = true;
        },

        release: function (key, zr) {
            getStore(zr)[key] = false;
        },

        isTaken: function (key, zr) {
            return !!getStore(zr)[key];
        }
    };

    function getStore(zr) {
        return zr[ATTR] || (zr[ATTR] = {});
    }

    return interactionMutex;
});

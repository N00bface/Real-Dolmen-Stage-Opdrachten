/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    require('../../model/Component').registerSubTypeDefaulter('visualMap', function (option) {
        // Compatible with ec2, when splitNumber === 0, continuous visualMap will be used.
        return (
                !option.categories
                && (
                    !(
                        option.pieces
                            ? option.pieces.length > 0
                            : option.splitNumber > 0
                    )
                    || option.calculable
                )
            )
            ? 'continuous' : 'piecewise';
    });

});
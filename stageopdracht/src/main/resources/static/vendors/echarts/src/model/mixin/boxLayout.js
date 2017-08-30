/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function (require) {

    return {
        getBoxLayoutParams: function () {
            return {
                left: this.get('left'),
                top: this.get('top'),
                right: this.get('right'),
                bottom: this.get('bottom'),
                width: this.get('width'),
                height: this.get('height')
            };
        }
    };
});

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

'use strict';

Tinytest.add('Switchery integration', function (test) {

    var checkbox = document.createElement('input');
    checkbox.className = 'js-switch';
    var switchy = new Switchery(checkbox);

    test.instanceOf(switchy, Switchery, 'instantiation OK');
});
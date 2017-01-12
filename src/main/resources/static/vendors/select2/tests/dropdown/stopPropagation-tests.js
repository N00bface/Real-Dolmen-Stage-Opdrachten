/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module('Dropdown - Stoping event propagation');

var Dropdown = require('select2/dropdown');
var StopPropagation = require('select2/dropdown/stopPropagation');

var $ = require('jquery');
var Options = require('select2/options');
var Utils = require('select2/utils');

var CustomDropdown = Utils.Decorate(Dropdown, StopPropagation);

var options = new Options();

test('click event does not propagate', function (assert) {
  assert.expect(1);

  var $container = $('#qunit-fixture .event-container');
  var container = new MockContainer();

  var dropdown = new CustomDropdown($('#qunit-fixture select'), options);

  var $dropdown = dropdown.render();
  dropdown.bind(container, $container);

  $container.append($dropdown);
  $container.on('click', function () {
    assert.ok(false, 'The click event should have been stopped');
  });

  $dropdown.trigger('click');

  assert.ok(true, 'Something went wrong if this failed');
});

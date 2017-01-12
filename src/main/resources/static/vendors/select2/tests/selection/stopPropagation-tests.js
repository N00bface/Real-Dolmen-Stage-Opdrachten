/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module('Selection containers - Stoping event propagation');

var SingleSelection = require('select2/selection/single');
var StopPropagation = require('select2/selection/stopPropagation');

var $ = require('jquery');
var Options = require('select2/options');
var Utils = require('select2/utils');

var CutomSelection = Utils.Decorate(SingleSelection, StopPropagation);

var options = new Options();

test('click event does not propagate', function (assert) {
  assert.expect(1);

  var $container = $('#qunit-fixture .event-container');
  var container = new MockContainer();

  var selection = new CutomSelection($('#qunit-fixture select'), options);

  var $selection = selection.render();
  selection.bind(container, $container);

  $container.append($selection);
  $container.on('click', function () {
    assert.ok(false, 'The click event should have been stopped');
  });

  $selection.trigger('click');

  assert.ok(true, 'Something went wrong if this failed');
});

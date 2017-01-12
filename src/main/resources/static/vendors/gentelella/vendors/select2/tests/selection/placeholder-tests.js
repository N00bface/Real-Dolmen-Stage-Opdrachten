/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module('Selection containers - Placeholders');

var Placeholder = require('select2/selection/placeholder');
var SingleSelection = require('select2/selection/single');

var $ = require('jquery');
var Options = require('select2/options');
var Utils = require('select2/utils');

var SinglePlaceholder = Utils.Decorate(SingleSelection, Placeholder);

var placeholderOptions = new Options({
  placeholder: {
    id: 'placeholder',
    text: 'This is the placeholder'
  }
});

test('normalizing placeholder ignores objects', function (assert) {
  var selection = new SinglePlaceholder(
    $('#qunit-fixture .single'),
    placeholderOptions
  );

  var original = {
    id: 'test',
    text: 'testing'
  };

  var normalized = selection.normalizePlaceholder(original);

  assert.equal(original, normalized);
});

test('normalizing placeholder gives object for string', function (assert) {
  var selection = new SinglePlaceholder(
    $('#qunit-fixture .single'),
    placeholderOptions
  );

  var normalized = selection.normalizePlaceholder('placeholder');

  assert.equal(normalized.id, '');
  assert.equal(normalized.text, 'placeholder');
});


test('text is shown for placeholder option on single', function (assert) {
  var selection = new SinglePlaceholder(
    $('#qunit-fixture .single'),
    placeholderOptions
  );

  var $selection = selection.render();

  selection.update([{
    id: 'placeholder'
  }]);

  assert.equal($selection.text(), 'This is the placeholder');
});

test('placeholder is shown when no options are selected', function (assert) {
  var selection = new SinglePlaceholder(
    $('#qunit-fixture .multiple'),
    placeholderOptions
  );

  var $selection = selection.render();

  selection.update([]);

  assert.equal($selection.text(), 'This is the placeholder');
});

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

module('Options - Translations');

var $ = require('jquery');
var Options = require('select2/options');

test('partial dictionaries can be passed', function (assert) {
  var options = new Options({
    language: {
      searching: function () {
        return 'Something';
      }
    }
  });

  var translations = options.get('translations');

  assert.equal(
    translations.get('searching')(),
    'Something',
    'The partial dictionary still overrides translations'
  );

  assert.equal(
    translations.get('noResults')(),
    'No results found',
    'You can still get English translations for keys not passed in'
  );
});

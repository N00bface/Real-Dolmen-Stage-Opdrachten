/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
  // Dutch
  return {
    errorLoading: function () {
      return 'De resultaten konden niet worden geladen.';
    },
    inputTooLong: function (args) {
      var overChars = args.input.length - args.maximum;

      var message = 'Gelieve ' + overChars + ' karakters te verwijderen';

      return message;
    },
    inputTooShort: function (args) {
      var remainingChars = args.minimum - args.input.length;

      var message = 'Gelieve ' + remainingChars +
        ' of meer karakters in te voeren';

      return message;
    },
    loadingMore: function () {
      return 'Meer resultaten laden…';
    },
    maximumSelected: function (args) {
      var verb = args.maximum == 1 ? 'kan' : 'kunnen';
      var message = 'Er ' + verb + ' maar ' + args.maximum + ' item';

      if (args.maximum != 1) {
        message += 's';
      }
      message += ' worden geselecteerd';

      return message;
    },
    noResults: function () {
      return 'Geen resultaten gevonden…';
    },
    searching: function () {
      return 'Zoeken…';
    }
  };
});

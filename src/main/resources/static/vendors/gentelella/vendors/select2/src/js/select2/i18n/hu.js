/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
  // Hungarian
  return {
    inputTooLong: function (args) {
      var overChars = args.input.length - args.maximum;

      return 'Túl hosszú. ' + overChars + ' karakterrel több, mint kellene.';
    },
    inputTooShort: function (args) {
      var remainingChars = args.minimum - args.input.length;

      return 'Túl rövid. Még ' + remainingChars + ' karakter hiányzik.';
    },
    loadingMore: function () {
      return 'Töltés…';
    },
    maximumSelected: function (args) {
      return 'Csak ' + args.maximum + ' elemet lehet kiválasztani.';
    },
    noResults: function () {
      return 'Nincs találat.';
    },
    searching: function () {
      return 'Keresés…';
    }
  };
});

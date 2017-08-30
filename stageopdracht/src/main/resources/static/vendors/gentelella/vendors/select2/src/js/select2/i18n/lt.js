/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
  // Italian
  function ending (count, first, second, third) {
    if ((count % 100 > 9 && count % 100 < 21) || count % 10 === 0) {
      if (count % 10 > 1) {
        return second;
      } else {
        return third;
      }
    } else {
      return first;
    }
  }

  return {
    inputTooLong: function (args) {
      var overChars = args.input.length - args.maximum;

      var message = 'Pašalinkite ' + overChars + ' simbol';

      message += ending(overChars, 'ių', 'ius', 'į');

      return message;
    },
    inputTooShort: function (args) {
      var remainingChars = args.minimum - args.input.length;

      var message = 'Įrašykite dar ' + remainingChars + ' simbol';

      message += ending(remainingChars, 'ių', 'ius', 'į');

      return message;
    },
    loadingMore: function () {
      return 'Kraunama daugiau rezultatų…';
    },
    maximumSelected: function (args) {
      var message = 'Jūs galite pasirinkti tik ' + args.maximum + ' element';

      message += ending(args.maximum, 'ų', 'us', 'ą');

      return message;
    },
    noResults: function () {
      return 'Atitikmenų nerasta';
    },
    searching: function () {
      return 'Ieškoma…';
    }
  };
});

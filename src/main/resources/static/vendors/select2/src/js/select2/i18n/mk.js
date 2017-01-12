/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
  // Macedonian
  return {
    inputTooLong: function (args) {
      var overChars = args.input.length - args.maximum;

      var message = 'Ве молиме внесете ' + args.maximum + ' помалку карактер';

      if (args.maximum !== 1) {
        message += 'и';
      }

      return message;
    },
    inputTooShort: function (args) {
      var remainingChars = args.minimum - args.input.length;

      var message = 'Ве молиме внесете уште ' + args.maximum + ' карактер';

      if (args.maximum !== 1) {
        message += 'и';
      }

      return message;
    },
    loadingMore: function () {
      return 'Вчитување резултати…';
    },
    maximumSelected: function (args) {
      var message = 'Можете да изберете само ' + args.maximum + ' ставк';

      if (args.maximum === 1) {
        message += 'а';
      } else {
        message += 'и';
      }

      return message;
    },
    noResults: function () {
      return 'Нема пронајдено совпаѓања';
    },
    searching: function () {
      return 'Пребарување…';
    }
  };
});

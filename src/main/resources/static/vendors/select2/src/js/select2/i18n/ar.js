/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
  // Arabic
  return {
    errorLoading: function () {
      return 'لا يمكن تحميل النتائج';
    },
    inputTooLong: function (args) {
      var overChars = args.input.length - args.maximum;

      var message = 'الرجاء حذف ' + overChars + ' عناصر';

      return message;
    },
    inputTooShort: function (args) {
      var remainingChars = args.minimum - args.input.length;

      var message = 'الرجاء إضافة ' + remainingChars + ' عناصر';

      return message;
    },
    loadingMore: function () {
      return 'جاري تحميل نتائج إضافية...';
    },
    maximumSelected: function (args) {
      var message = 'تستطيع إختيار ' + args.maximum + ' بنود فقط';

      return message;
    },
    noResults: function () {
      return 'لم يتم العثور على أي نتائج';
    },
    searching: function () {
      return 'جاري البحث…';
    }
  };
});
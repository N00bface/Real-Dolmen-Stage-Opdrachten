/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define(function () {
  // Chinese (Simplified)
  return {
    errorLoading: function () {
      return '无法载入结果。';
    },
    inputTooLong: function (args) {
      var overChars = args.input.length - args.maximum;

      var message = '请删除' + overChars + '个字符';

      return message;
    },
    inputTooShort: function (args) {
      var remainingChars = args.minimum - args.input.length;

      var message = '请再输入至少' + remainingChars + '个字符';

      return message;
    },
    loadingMore: function () {
      return '载入更多结果…';
    },
    maximumSelected: function (args) {
      var message = '最多只能选择' + args.maximum + '个项目';

      return message;
    },
    noResults: function () {
      return '未找到结果';
    },
    searching: function () {
      return '搜索中…';
    }
  };
});

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

(function () {
// notequalto extra validators
window.ParsleyConfig = window.ParsleyConfig || {};
window.ParsleyConfig.validators = window.ParsleyConfig.validators || {};

// Greater than validator
window.ParsleyConfig.validators.notequalto = {
  fn: function (value, requirement) {
    return value !== ($(requirement).length ? $(requirement).val() : requirement);
  },
  priority: 256
};
})();

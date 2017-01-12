/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

define([
  'jquery'
], function ($) {
  function ClickMask () { }

  ClickMask.prototype.bind = function (decorate, $container, container) {
    var self = this;

    decorate.call(this, $container, container);

    this.$mask = $(
      '<div class="select2-close-mask"></div>'
    );

    this.$mask.on('mousedown touchstart click', function () {
      self.trigger('close', {});
    });
  };

  ClickMask.prototype._attachCloseHandler = function (decorate, container) {
    $(document.body).append(this.$mask);
  };

  ClickMask.prototype._detachCloseHandler = function (deocrate, container) {
    this.$mask.detach();
  };

  return ClickMask;
});

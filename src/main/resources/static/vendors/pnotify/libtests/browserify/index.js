
/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Not working? Did you `npm install` `npm run build` first?

var $ = require("jquery");
var PNotify = require("pnotify");

$(function(){
    $("#button1").click(function(){
        new PNotify({
            title: "Yay!",
            text: "It works!"
        });
    });

    $("#button12").click(function(){
        require("pnotify.reference");

        new PNotify({
            title: "Yay!",
            text: "It works!",
            reference: {
                put_thing: true
            }
        });
    });
});
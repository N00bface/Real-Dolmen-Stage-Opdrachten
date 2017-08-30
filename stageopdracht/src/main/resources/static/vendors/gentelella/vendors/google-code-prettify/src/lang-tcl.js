/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */



/**
 * @fileoverview
 * Registers a language handler for TCL
 *
 *
 * To use, include prettify.js and this file in your HTML page.
 * Then put your code in an HTML tag like
 *      <pre class="prettyprint lang-tcl">proc foo {} {puts bar}</pre>
 *
 * I copy-pasted lang-lisp.js, so this is probably not 100% accurate.
 * I used http://wiki.tcl.tk/1019 for the keywords, but tried to only
 * include as keywords that had more impact on the program flow
 * rather than providing convenience. For example, I included 'if'
 * since that provides branching, but left off 'open' since that is more
 * like a proc. Add more if it makes sense.
 *
 * @author pyrios@gmail.com
 */

PR['registerLangHandler'](
    PR['createSimpleLexer'](
        [
         ['opn',             /^\{+/, null, '{'],
         ['clo',             /^\}+/, null, '}'],
         // A line comment that starts with ;
         [PR['PR_COMMENT'],     /^#[^\r\n]*/, null, '#'],
         // Whitespace
         [PR['PR_PLAIN'],       /^[\t\n\r \xA0]+/, null, '\t\n\r \xA0'],
         // A double quoted, possibly multi-line, string.
         [PR['PR_STRING'],      /^\"(?:[^\"\\]|\\[\s\S])*(?:\"|$)/, null, '"']
        ],
        [
         [PR['PR_KEYWORD'],     /^(?:after|append|apply|array|break|case|catch|continue|error|eval|exec|exit|expr|for|foreach|if|incr|info|proc|return|set|switch|trace|uplevel|upvar|while)\b/, null],
         [PR['PR_LITERAL'],
          /^[+\-]?(?:[0#]x[0-9a-f]+|\d+\/\d+|(?:\.\d+|\d+(?:\.\d*)?)(?:[ed][+\-]?\d+)?)/i],
         // A single quote possibly followed by a word that optionally ends with
         // = ! or ?.
         [PR['PR_LITERAL'],
          /^\'(?:-*(?:\w|\\[\x21-\x7e])(?:[\w-]*|\\[\x21-\x7e])[=!?]?)?/],
         // A word that optionally ends with = ! or ?.
         [PR['PR_PLAIN'],
          /^-*(?:[a-z_]|\\[\x21-\x7e])(?:[\w-]*|\\[\x21-\x7e])[=!?]?/i],
         // A printable non-space non-special character
         [PR['PR_PUNCTUATION'], /^[^\w\t\n\r \xA0()\"\\\';]+/]
        ]),
    ['tcl']);

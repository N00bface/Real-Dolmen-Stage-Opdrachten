/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * @fileoverview
 * Support for tex highlighting as discussed on
 * <a href="http://meta.tex.stackexchange.com/questions/872/text-immediate-following-double-backslashes-is-highlighted-as-macro-inside-a-code/876#876">meta.tex.stackexchange.com</a>.
 *
 * @author Martin S.
 */

PR['registerLangHandler'](
    PR['createSimpleLexer'](
        [
         // whitespace
         [PR['PR_PLAIN'],   /^[\t\n\r \xA0]+/, null, '\t\n\r \xA0'],
         // all comments begin with '%'
         [PR['PR_COMMENT'], /^%[^\r\n]*/, null, '%']
        ],
        [
         //[PR['PR_DECLARATION'], /^\\([egx]?def|(new|renew|provide)(command|environment))\b/],
         // any command starting with a \ and contains
         // either only letters (a-z,A-Z), '@' (internal macros)
         [PR['PR_KEYWORD'], /^\\[a-zA-Z@]+/],
         // or contains only one character
         [PR['PR_KEYWORD'], /^\\./],
         // Highlight dollar for math mode and ampersam for tabular
         [PR['PR_TYPE'],    /^[$&]/],
         // numeric measurement values with attached units
         [PR['PR_LITERAL'],
          /[+-]?(?:\.\d+|\d+(?:\.\d*)?)(cm|em|ex|in|pc|pt|bp|mm)/i],
         // punctuation usually occurring within commands
         [PR['PR_PUNCTUATION'], /^[{}()\[\]=]+/]
        ]),
    ['latex', 'tex']);

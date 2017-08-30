/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */


/**
 * @fileoverview
 * Registers a language handler for S, S-plus, and R source code.
 *
 *
 * To use, include prettify.js and this file in your HTML page.
 * Then put your code in an HTML tag like
 *      <pre class="prettyprint lang-r"> code </pre>
 *
 * Language definition from
 * http://cran.r-project.org/doc/manuals/R-lang.html.
 * Many of the regexes are shared  with the pygments SLexer,
 * http://pygments.org/.
 *
 * Original: https://raw.github.com/jrnold/prettify-lang-r-bugs/master/lang-r.js
 *
 * @author jeffrey.arnold@gmail.com
 */
PR['registerLangHandler'](
    PR['createSimpleLexer'](
        [
            [PR['PR_PLAIN'],       /^[\t\n\r \xA0]+/, null, '\t\n\r \xA0'],
	    [PR['PR_STRING'],      /^\"(?:[^\"\\]|\\[\s\S])*(?:\"|$)/, null, '"'],
	    [PR['PR_STRING'],      /^\'(?:[^\'\\]|\\[\s\S])*(?:\'|$)/, null, "'"]
        ],
        [
            [PR['PR_COMMENT'],     /^#.*/],
	    [PR['PR_KEYWORD'],     /^(?:if|else|for|while|repeat|in|next|break|return|switch|function)(?![A-Za-z0-9_.])/],
	    // hex numbes
	    [PR['PR_LITERAL'], /^0[xX][a-fA-F0-9]+([pP][0-9]+)?[Li]?/],
	    // Decimal numbers
            [PR['PR_LITERAL'], /^[+-]?([0-9]+(\.[0-9]+)?|\.[0-9]+)([eE][+-]?[0-9]+)?[Li]?/],
	    // builtin symbols
	    [PR['PR_LITERAL'], /^(?:NULL|NA(?:_(?:integer|real|complex|character)_)?|Inf|TRUE|FALSE|NaN|\.\.(?:\.|[0-9]+))(?![A-Za-z0-9_.])/],
	    // assignment, operators, and parens, etc.
	    [PR['PR_PUNCTUATION'], /^(?:<<?-|->>?|-|==|<=|>=|<|>|&&?|!=|\|\|?|\*|\+|\^|\/|!|%.*?%|=|~|\$|@|:{1,3}|[\[\](){};,?])/],
	    // valid variable names
	    [PR['PR_PLAIN'], /^(?:[A-Za-z]+[A-Za-z0-9_.]*|\.[a-zA-Z_][0-9a-zA-Z\._]*)(?![A-Za-z0-9_.])/],
	    // string backtick
	    [PR['PR_STRING'], /^`.+`/]
        ]),
    ['r', 's', 'R', 'S', 'Splus']);

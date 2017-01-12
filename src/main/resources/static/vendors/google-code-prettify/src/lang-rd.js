/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/**
 * @fileoverview
 * Support for R documentation (Rd) files
 *
 * Minimal highlighting or Rd files, basically just highlighting
 * macros. It does not try to identify verbatim or R-like regions of
 * macros as that is too complicated for a lexer.  Descriptions of the
 * Rd format can be found
 * http://cran.r-project.org/doc/manuals/R-exts.html and
 * http://developer.r-project.org/parseRd.pdf.
 *
 * @author Jeffrey Arnold
 */
PR['registerLangHandler'](
    PR['createSimpleLexer'](
        [
            // whitespace
            [PR['PR_PLAIN'],   /^[\t\n\r \xA0]+/, null, '\t\n\r \xA0'],
            // all comments begin with '%'
            [PR['PR_COMMENT'], /^%[^\r\n]*/, null, '%']
        ],
        [// special macros with no args
            [PR['PR_LITERAL'], /^\\(?:cr|l?dots|R|tab)\b/],
	    // macros
            [PR['PR_KEYWORD'], /^\\[a-zA-Z@]+/],
	    // highlighted as macros, since technically they are
            [PR['PR_KEYWORD'],  /^#(?:ifn?def|endif)/ ],
	    // catch escaped brackets
	    [PR['PR_PLAIN'], /^\\[{}]/],
            // punctuation
            [PR['PR_PUNCTUATION'], /^[{}()\[\]]+/]
        ]),
    ['Rd', 'rd']);

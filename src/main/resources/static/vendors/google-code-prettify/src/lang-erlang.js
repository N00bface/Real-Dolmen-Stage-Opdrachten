/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */


/**
 * @fileoverview
 * Registers a language handler for Erlang.
 *
 * Derived from https://raw.github.com/erlang/otp/dev/lib/compiler/src/core_parse.yrl
 * Modified from Mike Samuel's Haskell plugin for google-code-prettify
 *
 * @author achew22@gmail.com
 */

PR['registerLangHandler'](
    PR['createSimpleLexer'](
        [
         // Whitespace
         // whitechar    ->    newline | vertab | space | tab | uniWhite
         // newline      ->    return linefeed | return | linefeed | formfeed
         [PR['PR_PLAIN'],       /^[\t\n\x0B\x0C\r ]+/, null, '\t\n\x0B\x0C\r '],
         // Single line double-quoted strings.
         [PR['PR_STRING'],      /^\"(?:[^\"\\\n\x0C\r]|\\[\s\S])*(?:\"|$)/,
          null, '"'],
         
         // Handle atoms
         [PR['PR_LITERAL'],      /^[a-z][a-zA-Z0-9_]*/],
         // Handle single quoted atoms
         [PR['PR_LITERAL'],      /^\'(?:[^\'\\\n\x0C\r]|\\[^&])+\'?/,
          null, "'"],
         
         // Handle macros. Just to be extra clear on this one, it detects the ?
         // then uses the regexp to end it so be very careful about matching
         // all the terminal elements
         [PR['PR_LITERAL'],      /^\?[^ \t\n({]+/, null, "?"],

          
         
         // decimal      ->    digit{digit}
         // octal        ->    octit{octit}
         // hexadecimal  ->    hexit{hexit}
         // integer      ->    decimal
         //               |    0o octal | 0O octal
         //               |    0x hexadecimal | 0X hexadecimal
         // float        ->    decimal . decimal [exponent]
         //               |    decimal exponent
         // exponent     ->    (e | E) [+ | -] decimal
         [PR['PR_LITERAL'],
          /^(?:0o[0-7]+|0x[\da-f]+|\d+(?:\.\d+)?(?:e[+\-]?\d+)?)/i,
          null, '0123456789']
        ],
        [
         // TODO: catch @declarations inside comments

         // Comments in erlang are started with % and go till a newline
         [PR['PR_COMMENT'], /^%[^\n]*/],

         // Catch macros
         //[PR['PR_TAG'], /?[^( \n)]+/],

         /**
          * %% Keywords (atoms are assumed to always be single-quoted).
          * 'module' 'attributes' 'do' 'let' 'in' 'letrec'
          * 'apply' 'call' 'primop'
          * 'case' 'of' 'end' 'when' 'fun' 'try' 'catch' 'receive' 'after'
          */
         [PR['PR_KEYWORD'], /^(?:module|attributes|do|let|in|letrec|apply|call|primop|case|of|end|when|fun|try|catch|receive|after|char|integer|float,atom,string,var)\b/],
         
         /**
          * Catch definitions (usually defined at the top of the file)
          * Anything that starts -something
          */
         [PR['PR_KEYWORD'], /^-[a-z_]+/],

         // Catch variables
         [PR['PR_TYPE'], /^[A-Z_][a-zA-Z0-9_]*/],

         // matches the symbol production
         [PR['PR_PUNCTUATION'], /^[.,;]/]
        ]),
    ['erlang', 'erl']);

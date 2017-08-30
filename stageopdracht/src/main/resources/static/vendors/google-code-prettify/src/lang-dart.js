/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */



/**
 * @fileoverview
 * Registers a language handler Dart.
 * Loosely structured based on the DartLexer in Pygments: http://pygments.org/.
 *
 * To use, include prettify.js and this file in your HTML page.
 * Then put your code in an HTML tag like
 *      <pre class="prettyprint lang-dart">(Dart code)</pre>
 *
 * @author armstrong.timothy@gmail.com
 */

PR['registerLangHandler'](
  PR['createSimpleLexer'](
    [
      // Whitespace.
      [PR['PR_PLAIN'], /^[\t\n\r \xA0]+/, null, '\t\n\r \xA0']
    ],
    [
      // Script tag.
      [PR['PR_COMMENT'], /^#!(?:.*)/],

      // `import`, `library`, `part of`, `part`, `as`, `show`, and `hide`
      // keywords.
      [PR['PR_KEYWORD'], /^\b(?:import|library|part of|part|as|show|hide)\b/i],

      // Single-line comments.
      [PR['PR_COMMENT'], /^\/\/(?:.*)/],

      // Multiline comments.
      [PR['PR_COMMENT'], /^\/\*[^*]*\*+(?:[^\/*][^*]*\*+)*\//], // */

      // `class` and `interface` keywords.
      [PR['PR_KEYWORD'], /^\b(?:class|interface)\b/i],

      // General keywords.
      [PR['PR_KEYWORD'], /^\b(?:assert|break|case|catch|continue|default|do|else|finally|for|if|in|is|new|return|super|switch|this|throw|try|while)\b/i],

      // Declaration keywords.
      [PR['PR_KEYWORD'], /^\b(?:abstract|const|extends|factory|final|get|implements|native|operator|set|static|typedef|var)\b/i],

      // Keywords for types.
      [PR['PR_TYPE'], /^\b(?:bool|double|Dynamic|int|num|Object|String|void)\b/i],

      // Keywords for constants.
      [PR['PR_KEYWORD'], /^\b(?:false|null|true)\b/i],

      // Multiline strings, single- and double-quoted.
      [PR['PR_STRING'], /^r?[\']{3}[\s|\S]*?[^\\][\']{3}/],
      [PR['PR_STRING'], /^r?[\"]{3}[\s|\S]*?[^\\][\"]{3}/],

      // Normal and raw strings, single- and double-quoted.
      [PR['PR_STRING'], /^r?\'(\'|(?:[^\n\r\f])*?[^\\]\')/],
      [PR['PR_STRING'], /^r?\"(\"|(?:[^\n\r\f])*?[^\\]\")/],

      // Identifiers.
      [PR['PR_PLAIN'], /^[a-z_$][a-z0-9_]*/i],
      
      // Operators.
      [PR['PR_PUNCTUATION'], /^[~!%^&*+=|?:<>/-]/],

      // Hex numbers.
      [PR['PR_LITERAL'], /^\b0x[0-9a-f]+/i],

      // Decimal numbers.
      [PR['PR_LITERAL'], /^\b\d+(?:\.\d*)?(?:e[+-]?\d+)?/i],
      [PR['PR_LITERAL'], /^\b\.\d+(?:e[+-]?\d+)?/i],

      // Punctuation.
      [PR['PR_PUNCTUATION'], /^[(){}\[\],.;]/]
    ]),
  ['dart']);

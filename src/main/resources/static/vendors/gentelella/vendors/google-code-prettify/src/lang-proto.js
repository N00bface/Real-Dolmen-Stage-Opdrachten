/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */


/**
 * @fileoverview
 * Registers a language handler for Protocol Buffers as described at
 * http://code.google.com/p/protobuf/.
 *
 * Based on the lexical grammar at
 * http://research.microsoft.com/fsharp/manual/spec2.aspx#_Toc202383715
 *
 * @author mikesamuel@gmail.com
 */

PR['registerLangHandler'](PR['sourceDecorator']({
        'keywords': (
            'bytes,default,double,enum,extend,extensions,false,'
            + 'group,import,max,message,option,'
            + 'optional,package,repeated,required,returns,rpc,service,'
            + 'syntax,to,true'),
        'types': /^(bool|(double|s?fixed|[su]?int)(32|64)|float|string)\b/,
        'cStyleComments': true
      }), ['proto']);

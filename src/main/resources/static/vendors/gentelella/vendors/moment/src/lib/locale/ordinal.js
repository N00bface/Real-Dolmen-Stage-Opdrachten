/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

export var defaultOrdinal = '%d';
export var defaultOrdinalParse = /\d{1,2}/;

export function ordinal (number) {
    return this._ordinal.replace('%d', number);
}


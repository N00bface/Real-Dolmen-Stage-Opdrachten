/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

import { createLocalOrUTC } from './from-anything';

export function createLocal (input, format, locale, strict) {
    return createLocalOrUTC(input, format, locale, strict, false);
}

/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

import { addFormatToken } from '../format/format';

// FORMATTING

addFormatToken('z',  0, 0, 'zoneAbbr');
addFormatToken('zz', 0, 0, 'zoneName');

// MOMENTS

export function getZoneAbbr () {
    return this._isUTC ? 'UTC' : '';
}

export function getZoneName () {
    return this._isUTC ? 'Coordinated Universal Time' : '';
}

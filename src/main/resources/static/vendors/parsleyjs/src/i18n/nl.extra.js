/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
import Parsley from '../parsley';

Parsley.addMessages('nl', {
  dateiso:  "Deze waarde moet een datum in het volgende formaat zijn: (YYYY-MM-DD).",
  minwords: "Deze waarde moet minstens %s woorden bevatten.",
  maxwords: "Deze waarde mag maximaal %s woorden bevatten.",
  words:    "Deze waarde moet tussen de %s en %s woorden bevatten.",
  gt:       "Deze waarde moet groter dan %s zijn.",
  lt:       "Deze waarde moet kleiner dan %s zijn."
});

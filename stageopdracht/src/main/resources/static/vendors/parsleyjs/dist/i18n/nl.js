/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
// Load this after Parsley

Parsley.addMessages('nl', {
  defaultMessage: "Deze waarde lijkt onjuist.",
  type: {
    email:        "Dit lijkt geen geldig e-mail adres te zijn.",
    url:          "Dit lijkt geen geldige URL te zijn.",
    number:       "Deze waarde moet een nummer zijn.",
    integer:      "Deze waarde moet een nummer zijn.",
    digits:       "Deze waarde moet numeriek zijn.",
    alphanum:     "Deze waarde moet alfanumeriek zijn."
  },
  notblank:       "Deze waarde mag niet leeg zijn.",
  required:       "Dit veld is verplicht.",
  pattern:        "Deze waarde lijkt onjuist te zijn.",
  min:            "Deze waarde mag niet lager zijn dan %s.",
  max:            "Deze waarde mag niet groter zijn dan %s.",
  range:          "Deze waarde moet tussen %s en %s liggen.",
  minlength:      "Deze tekst is te kort. Deze moet uit minimaal %s karakters bestaan.",
  maxlength:      "Deze waarde is te lang. Deze mag maximaal %s karakters lang zijn.",
  length:         "Deze waarde moet tussen %s en %s karakters lang zijn.",
  equalto:        "Deze waardes moeten identiek zijn."
});

Parsley.setLocale('nl');

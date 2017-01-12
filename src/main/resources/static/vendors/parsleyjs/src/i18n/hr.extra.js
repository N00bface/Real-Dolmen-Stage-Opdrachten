/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
import Parsley from '../parsley';

Parsley.addMessages('hr', {
  dateiso:  "Ovo polje treba sadržavati ispravno unešen datum (GGGG-MM-DD).",
  minwords: "Unos je prekratak. Treba sadržavati %s ili više riječi.",
  maxwords: "Unos je predugačak. Treba sadržavati %s ili manje riječi.",
  words:    "Neispravna duljina unosa. Treba sadržavati između %s i %s riječi.",
  gt:       "Ova vrijednost treba biti veća.",
  gte:      "Ova vrijednost treba biti veća ili jednaka.",
  lt:       "Ova vrijednost treba biti manja.",
  lte:      "Ova vrijednost treba biti manja ili jednaka.",
  notequalto: "Ova vrijednost treba biti drugačija."
});

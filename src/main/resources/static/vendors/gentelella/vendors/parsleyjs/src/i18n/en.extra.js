/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
import Parsley from '../parsley';

Parsley.addMessages('en', {
  dateiso:  "This value should be a valid date (YYYY-MM-DD).",
  minwords: "This value is too short. It should have %s words or more.",
  maxwords: "This value is too long. It should have %s words or fewer.",
  words:    "This value length is invalid. It should be between %s and %s words long.",
  gt:       "This value should be greater.",
  gte:      "This value should be greater or equal.",
  lt:       "This value should be less.",
  lte:      "This value should be less or equal.",
  notequalto: "This value should be different."
});

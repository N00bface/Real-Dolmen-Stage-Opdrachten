/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

// Validation errors messages for Parsley
import Parsley from '../parsley';

Parsley.addMessages('uk', {
  dateiso:  "Це значення має бути коректною датою (РРРР-ММ-ДД).",
  minwords: "Це значення повинно містити не менше %s слів.",
  maxwords: "Це значення повинно містити не більше %s слів.",
  words:    "Це значення повинно містити від %s до %s слів."
});
